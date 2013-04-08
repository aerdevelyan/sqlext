package sqlext;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;


public class QueryValidationHandler implements ValidationEventHandler {

	@Override
	public boolean handleEvent(ValidationEvent event) {
		System.out.println(event.getMessage());
		return true;
	}

}
