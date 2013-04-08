package sqlext.jaxb_beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class QueryVersion {
	
	@XmlAttribute(name="dialect")
	private SqlDialectEnum dialect;
	
	@XmlValue
	private String queryText;
	
	
	public SqlDialectEnum getDialect() {
		return dialect;
	}
	public void setDialect(SqlDialectEnum dialect) {
		this.dialect = dialect;
	}
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	
	
}
