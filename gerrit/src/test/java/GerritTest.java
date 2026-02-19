import com.google.gerrit.extensions.api.changes.ReviewInput;
import com.google.gerrit.extensions.api.changes.ReviewResult;
import com.google.gerrit.extensions.restapi.RestApiException;
import com.urswolfer.gerrit.client.rest.GerritAuthData;
import com.urswolfer.gerrit.client.rest.GerritRestApi;
import com.urswolfer.gerrit.client.rest.GerritRestApiFactory;
import org.junit.jupiter.api.Test;

public class GerritTest {

    @Test
    void vote() throws RestApiException {
        GerritRestApi client = new GerritRestApiFactory().create(new GerritAuthData.Basic("",
                "",
                ""));

        ReviewInput reviewInput = new ReviewInput();
        reviewInput.message("hello").label("Compile-Verified", 1);
        ReviewResult result = client.changes().id(115316).revision("af26946da3bd066f688fc88527aca70b4c708745").review(reviewInput);

        System.out.println(result.labels);

    }
}
