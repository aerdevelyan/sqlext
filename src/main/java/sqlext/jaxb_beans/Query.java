package sqlext.jaxb_beans;

import java.util.Collection;

public class Query {
	private String name;
	private Collection<QueryVersion> versions;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<QueryVersion> getVersions() {
		return versions;
	}
	public void setVersions(Collection<QueryVersion> versions) {
		this.versions = versions;
	}
	
	
}
