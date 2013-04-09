package sqlext;

import java.io.InputStream;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sqlext.jaxb_beans.Queries;
import sqlext.jaxb_beans.SqlDialectEnum;

/**
 * Facilitates loading queries from XML resources.
 * After calling load() method, loaded queries can be accessed via QueryStore object.
 */
public class QueryLoader {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private QueryStore queryStore;
	
	/**
	 * Load queries from a collection of XML sources and store them in QueryStore object
	 * @param dialect - query versions of that given dialect or generic versions will be loaded 
	 * @param inputSources
	 */
	public void load(SqlDialectEnum dialect, Collection<InputStream> inputSources) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(Queries.class);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			queryStore = new QueryStore(dialect);
			for (InputStream src : inputSources) {
				Queries queries = (Queries) unmarshaller.unmarshal(src);
				queryStore.validateAndStoreQueries(queries);
			}
		} 
		catch (JAXBException e) {
			logger.error("Exception occured while unmarshalling XML with queries.", e);
		}
	}

	
	public QueryStore getQueryStore() {
		return queryStore;
	}
	
}
