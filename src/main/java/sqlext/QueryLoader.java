package sqlext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import sqlext.jaxb_beans.Queries;

public class QueryLoader {

	
	public void load() {
		try {
			JAXBContext.newInstance(Queries.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
