package sqlext;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import sqlext.jaxb_beans.Queries;

public class QueryLoader {
	private Collection<InputStream> inputSources;
	private Map<String, Queries> groupedQueries = new HashMap<String, Queries>();

	public void load() {
		try {
			JAXBContext ctx = JAXBContext.newInstance(Queries.class);
			Unmarshaller u = ctx.createUnmarshaller();
			for (InputStream src : inputSources) {
				Queries q = (Queries) u.unmarshal(src);
				groupedQueries.put(q.getGroup(), q); 
			}
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void setInputSources(Collection<InputStream> inputSources) {
		this.inputSources = inputSources;
	}
	
	public Map<String, Queries> getGroupedQueries() {
		return groupedQueries;
	}
}
