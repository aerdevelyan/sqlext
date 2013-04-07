package sqlext.jaxb_beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="queries") 
public class Queries {
	
	@XmlAttribute(name="group")
	private String groupName;
	@XmlElement(name="query")
	private Collection<Query> queries;
	
	public void addQueries(Collection<Query> newQueries) {
		if (queries == null) {
			queries = new ArrayList<Query>();
		}
		if (newQueries != null) {
			queries.addAll(newQueries);
		}
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Collection<Query> getQueries() {
		return queries;
	}
	public void setQueries(Collection<Query> queries) {
		this.queries = queries;
	}	
}
