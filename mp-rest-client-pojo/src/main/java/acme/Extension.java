package acme;

import java.util.List;

public class Extension  {

	public String id;
	public String name;
	public String shortName;
	public List<String> keywords;

	@Override
	public String toString() {
		return "Extension{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", shortName='" + shortName + '\'' + ", keywords="
				+ keywords + '}';
	}
}