package sqlext;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import org.junit.Test;

import sqlext.jaxb_beans.SqlDialectEnum;

/**
 * Integration tests that load queries from XML and then check getting them.
 */
public class UnmarshalIntTest {
	
	@Test
	public void loadOracleQueries() {
		Collection<InputStream> inputSources = Arrays.asList(
				getClass().getResourceAsStream("/sample1.xml"), 
				getClass().getResourceAsStream("/sample2.xml"), 
				getClass().getResourceAsStream("/sample3.xml"));
		
		QueryLoader ql = new QueryLoader();
		ql.load(SqlDialectEnum.ORACLE, inputSources);
		QueryStore qs = ql.getQueryStore();
		
		assertEquals("oracle_sql_query1", qs.getQuerySql("group1.query_name1"));
		assertEquals("oracle_sql_query2", qs.getQuerySql("group1", "query_name2"));
		assertEquals("oracle_sql_query3", qs.getQuerySql("query_name3"));
		assertNull(qs.getQuerySql("query_wo_sql"));
	}
	
	@Test
	public void loadMssqlQueries() {
		Collection<InputStream> inputSources = Arrays.asList(
				getClass().getResourceAsStream("/sample1.xml"), 
				getClass().getResourceAsStream("/sample2.xml"), 
				getClass().getResourceAsStream("/sample3.xml"));
		
		QueryLoader ql = new QueryLoader();
		ql.load(SqlDialectEnum.MSSQL, inputSources);
		QueryStore qs = ql.getQueryStore();
		
		assertEquals("generic_sql_query1", qs.getQuerySql("group1.query_name1"));
		assertEquals("mssql_sql_query2", qs.getQuerySql("group1.query_name2"));
		assertEquals("mssql < > sql_query3", qs.getQuerySql("query_name3"));
	}
}
