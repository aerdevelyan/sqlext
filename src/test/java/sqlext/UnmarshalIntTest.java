package sqlext;

import java.util.Arrays;

import org.junit.Test;

import sqlext.jaxb_beans.SqlDialectEnum;

public class UnmarshalIntTest {
	
	@Test
	public void loadQueries() {
		QueryLoader ql = new QueryLoader();
		ql.setInputSources(Arrays.asList(
				getClass().getResourceAsStream("/sample1.xml"), 
				getClass().getResourceAsStream("/sample2.xml"), 
				getClass().getResourceAsStream("/sample3.xml")));
		
		ql.load(SqlDialectEnum.ORACLE);
	}
}
