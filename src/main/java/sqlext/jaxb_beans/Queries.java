package sqlext.jaxb_beans;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Queries {
	private String group;
	private Collection<Query> queries;
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public Collection<Query> getQueries() {
		return queries;
	}
	public void setQueries(Collection<Query> queries) {
		this.queries = queries;
	}
	
	
}
