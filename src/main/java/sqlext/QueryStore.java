package sqlext;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sqlext.jaxb_beans.Queries;
import sqlext.jaxb_beans.Query;
import sqlext.jaxb_beans.QueryVersion;
import sqlext.jaxb_beans.SqlDialectEnum;

/**
 * Provides methods to store and retrieve queries.
 */
public class QueryStore {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Map<String, String> fullNameToSqlMap = new HashMap<String, String>();
	private SqlDialectEnum dialect;
	
	public QueryStore(SqlDialectEnum dialect) {
		this.dialect = dialect;
	}
	
	/**
	 * Check the query data and store it for quick retrieval.
	 */
	public void validateAndStoreQueries(Queries queries) {
		if (queries == null || queries.getQueries() == null) return;
		
		for (Query query : queries.getQueries()) {
			String fullName = getFullName(queries.getGroupName(), query.getName());
			if (fullName == null || fullName.isEmpty()) {
				logger.warn("Skipped query with empty name.");
				continue;
			}
			if (fullNameToSqlMap.get(fullName) != null) {
				logger.warn("Skipped query with duplicate name: " + fullName);
				continue;
			}
			if (query.getVersions() == null || query.getVersions().size() == 0) {
				logger.warn("Query has no SQL versions: " + fullName);
				continue;
			}
			
			String sql = findClosestDialect(query, dialect);
			if (sql == null || sql.trim().isEmpty()) {
				logger.warn("Skipped query with no matching dialect: " + fullName);
				continue;
			}
			
			fullNameToSqlMap.put(fullName, sql.trim());
		}
	}

	/**
	 * Find closest matching SQL version of query to desired dialect.
	 * @param query
	 * @param desiredDialect
	 * @return null if no matching or generic dialect found 
	 */
	private String findClosestDialect(Query query, SqlDialectEnum desiredDialect) {
		String genericSql = null, matchingSql = null;
		
		for (QueryVersion qversion : query.getVersions()) {
			if (qversion.getDialect() == desiredDialect) {
				matchingSql = qversion.getQueryText();
			}
			if (qversion.getDialect() == null || qversion.getDialect() == SqlDialectEnum.GENERIC) {
				genericSql = qversion.getQueryText();
			}
		}
		
		return (matchingSql != null) ? matchingSql : genericSql;
	}
	
	/**
	 * Form a full name of a query as <group name>.<query name>
	 * @param groupName
	 * @param query
	 * @return empty string if it is impossible to form a valid full name
	 */
	private String getFullName(String groupName, String queryName) {
		if (queryName == null || queryName.isEmpty()) {
			return "";
		}
		if (groupName == null || groupName.isEmpty()) {
			// group is default, return only query name as full name
			return queryName;
		} 
		return groupName.trim() + "." + queryName.trim();
	}
	
	/**
	 * Get the query by its full name.
	 * @param queryName - full name in form: <group name>.<query name>
	 * @return null if no SQL query or its version is found by given name
	 */
	public String getQuerySql(String queryName) {
		return getQuerySql(null, queryName);
	}
	
	/**
	 * Get the query by its name and group name.
	 * @param groupName - can be null if query is not in group (default group)
	 * @param queryName - name of query within group
	 * @return null if no SQL query or its version is found by given name
	 */
	public String getQuerySql(String groupName, String queryName) {
		return fullNameToSqlMap.get(getFullName(groupName, queryName));
	}	
}
