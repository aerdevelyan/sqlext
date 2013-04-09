package sqlext;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import sqlext.jaxb_beans.Queries;
import sqlext.jaxb_beans.Query;
import sqlext.jaxb_beans.QueryVersion;
import sqlext.jaxb_beans.SqlDialectEnum;


public class QueryStoreTest {

	@Test
	public void testValidateAndStoreQueries() {
		QueryVersion q1v1 = new QueryVersion();
		q1v1.setDialect(null);
		q1v1.setQueryText("q1_generic_sql");
		QueryVersion q1v2 = new QueryVersion();
		q1v2.setDialect(SqlDialectEnum.ORACLE);
		q1v2.setQueryText("q1_oracle_sql");
		
		Query q1 = new Query();
		q1.setName("q1");
		q1.setVersions(Arrays.asList(q1v1, q1v2));
		
		Queries queries = new Queries();
		queries.addQueries(Arrays.asList(q1, new Query()));
		
		QueryStore qs = new QueryStore(SqlDialectEnum.ORACLE);
		qs.validateAndStoreQueries(queries);
		Assert.assertEquals("q1_oracle_sql", qs.getQuerySql("q1"));
		
		qs = new QueryStore(SqlDialectEnum.MSSQL);
		qs.validateAndStoreQueries(queries);
		Assert.assertEquals("q1_generic_sql", qs.getQuerySql("q1"));
		
	}
}
