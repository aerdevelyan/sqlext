package sqlext;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import sqlext.jaxb_beans.Queries;
import sqlext.jaxb_beans.Query;
import sqlext.jaxb_beans.SqlDialectEnum;

public class QueryLoader {
	private Collection<InputStream> inputSources;
	private Map<String, Queries> groupedQueries = new HashMap<String, Queries>();

	public void load(SqlDialectEnum dialect) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(Queries.class);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			unmarshaller.setEventHandler(new QueryValidationHandler());
			for (InputStream src : inputSources) {
				Queries queries = (Queries) unmarshaller.unmarshal(src);
				validateAndFilterQueries(queries.getQueries(), dialect);
				addQueriesToGroup(queries);
			}
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private void addQueriesToGroup(Queries queries) {
		// if queries with that group name are exist, merge them
		Queries storedQueries = groupedQueries.get(queries.getGroupName());
		if (storedQueries != null) {
			storedQueries.addQueries(queries.getQueries());
		}
		else {
			groupedQueries.put(queries.getGroupName(), queries);
		}
	}
	
	/**
	 * 
	 * @param dialect
	 */
	private void validateAndFilterQueries(Collection<Query> queries, SqlDialectEnum dialect) {
		for (Query q : queries) {
			
		}
	}
	
	public void setInputSources(Collection<InputStream> inputSources) {
		this.inputSources = inputSources;
	}
	
	public Map<String, Queries> getGroupedQueries() {
		return groupedQueries;
	}
	
	/**
	 * Get the query by its name in the 'default' group. 
	 * @param queryName
	 * @return
	 */
	public String getQuery(String queryName) {
		return getQuery(null, queryName);
	}
	
	/**
	 * Get the query by its name and group name.
	 * @param groupName
	 * @param queryName
	 * @return
	 */
	public String getQuery(String groupName, String queryName) {
		Queries groupQueries = groupedQueries.get(groupName);
		for (Query q : groupQueries.getQueries()) {
			if (q.getName().equals(queryName)) {
				return "";
			}
		}
		return null;
	}	
}
