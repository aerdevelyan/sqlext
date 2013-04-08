package sqlext.jaxb_beans;

import javax.xml.bind.annotation.XmlEnumValue;

public enum SqlDialectEnum {
	@XmlEnumValue("generic")
	GENERIC,
	@XmlEnumValue("oracle")
	ORACLE,
	@XmlEnumValue("mysql")
	MYSQL,
	@XmlEnumValue("mssql")
	MSSQL,
	@XmlEnumValue("postgres")
	POSTGRES,
	@XmlEnumValue("hsql")
	HSQL
}
