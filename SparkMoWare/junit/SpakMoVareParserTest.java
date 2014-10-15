package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserDateLocal;
import parser.ParserTestDriver;

public class SpakMoVareParserTest {

	@Test
	public void test() {
		//confirm method returns true if 2 date inputs
		assertEquals(true, ParserTestDriver.testAppointmentChecker("09/09/1234 2/3/2345"));
		
		//confirm method returns false if only 1 date input
		assertEquals(false, ParserTestDriver.testAppointmentChecker("09/08/1245"));
		
		/* Methods should change the date input from whatever format
		 * for eg. 12/3/2014 or 12-3-2014 to 12032014 
		 */
		
		//confirm method returns first date input found
		assertEquals("09/09/1234", ParserTestDriver.testGetStartDate("add 09/09/1234 2/3/2345"));
		
		//confirm method works for single date input
		assertEquals("09/09/1234", ParserTestDriver.testGetEndDate("add 09/09/1234"));
		
		//confirm method returns second date input
		assertEquals("2/3/2345", ParserTestDriver.testGetEndDate("add 09/09/1234 2/3/2345"));
		
		//confirm method fills in today's date if no date is detected
		//change date input accordingly or call dateString() method from ParserDateLocal class
		assertEquals("15102014", ParserTestDriver.testGetEndDate("add"));
		
		
		assertEquals(ParserDateLocal.dateString(), ParserTestDriver.testGetEndDate("add"));
	}

}
