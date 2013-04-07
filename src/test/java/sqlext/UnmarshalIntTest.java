package sqlext;

import java.util.Arrays;

import org.junit.Test;

public class UnmarshalIntTest {
	
	@Test
	public void loadQueries() {
		QueryLoader ql = new QueryLoader();
		ql.setInputSources(Arrays.asList(getClass().getResourceAsStream("/sample1.xml"), getClass().getResourceAsStream("/sample2.xml")));
		ql.load();
	}
}
