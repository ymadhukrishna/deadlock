package deadlock;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JUnitDemoTest {

	JUnitDemo demoObject = null;
	String expectedOutput = null;
	

	@Before
	public void setUp() throws Exception {

		demoObject = new JUnitDemo();
		expectedOutput = "11---12---2014";
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testDateUtilDemo() {

		String output = demoObject.DateUtilDemo(new Date());
		
		System.out.println("TESTMADHU");
		assertEquals(expectedOutput, output);
		
		
	}

}
