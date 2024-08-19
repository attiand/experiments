package client;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApacheClient {

	private static final Logger LOG = LoggerFactory.getLogger(ApacheClient.class);

	private final String url = "https://nya-tools.nya-srv.its.umu.se/cm/product-owners.json";

	private final ThreadFactory threadFactory = runnable -> {
		Thread thread = Executors.defaultThreadFactory().newThread(runnable);
		thread.setName("project-owner-fetcher");
		thread.setDaemon(true);
		return thread;
	};

	private final ConcurrentMap<String, Set<String>> projectOwners = new ConcurrentHashMap<>();


	public void start(long initialDelay, long interval, TimeUnit unit) {
		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, threadFactory);
		scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
		scheduledThreadPoolExecutor.scheduleAtFixedRate(this::fetch, initialDelay, interval, unit);
	}

	public boolean isTeamOwnerOfProject(String team) {
		Set<String> projects = projectOwners.get(team);

		LOG.info("products for {}: {}", team, projects);

		return projects != null && projects.contains(team);
	}


	private void fetch() {
		String owners = fetch(url);
		if (owners != null) {
			JsonObject ownerObject = JsonParser.parseString(owners).getAsJsonObject();
			for (Map.Entry<String, JsonElement> entry : ownerObject.entrySet()) {
				Set<String> projects = StreamSupport.stream(entry.getValue().getAsJsonArray().spliterator(), false)
						.map(JsonElement::getAsString)
						.map(this::getRepoFromPath)
						.collect(Collectors.toSet());

				if (!projectOwners.containsKey(entry.getKey())) {
					projectOwners.put(entry.getKey(), projects);
				} else {
					projectOwners.remove(entry.getKey());
				}
			}
		}
		System.out.println("Fetched product-owner list: " + projectOwners.toString());
		LOG.info("Fetched product-owner list: {}", projectOwners.toString());
	}

	private String getRepoFromPath(String path) {
		String[] parts = path.split("/");
		return parts[parts.length - 1];
	}

	private String fetch(String url) {
		LOG.info("Fetching product-owner list from: {}", url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response) throws IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			return httpclient.execute(httpget, responseHandler);
		} catch (IOException e) {
			LOG.error("Can't fetch product-owner list", e);
			return null;
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				LOG.error("Can't close httpclient", e);
			}
		}
	}

}
