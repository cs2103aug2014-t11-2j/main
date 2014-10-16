package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareCheckIfApptTest {

	@Test
	public void test() {
		//confirm method returns true if 2 date inputs
		assertTrue(ParserTestDriver.testCheckIfAppt("09/09/1234 2/3/2345"));
		
		//confirm method returns true if 2 date inputs with additional portions
		assertTrue(ParserTestDriver.testCheckIfAppt("add 09/09/1234 nothing 2/3/2345 works"));
		
		//confirm method returns false if only 1 date input
		assertTrue(ParserTestDriver.testCheckIfAppt("09/08/1245"));
		
		
		/* Methods should change the date input from whatever format
		 * for eg. 12/3/2014 or 12-3-2014 to 12032014 
		 */
	}

}
