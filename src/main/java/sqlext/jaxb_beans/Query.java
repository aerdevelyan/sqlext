package sqlext.jaxb_beans;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Query {
	
	@XmlAttribute(name="name", required = true)
	private String name;
	
	@XmlElement(name="sql")
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
