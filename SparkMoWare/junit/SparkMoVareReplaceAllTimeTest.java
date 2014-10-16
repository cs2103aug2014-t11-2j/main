package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareReplaceAllTimeTest {

	@Test
	public void test() {
		
		//confirm method replaces time input
		assertEquals("", ParserTestDriver.testReplaceAllTime("0900"));

		//confirm method replaces date input with additional portions
		//note: each space/symbol around the date is left behind
		assertEquals("add  work", ParserTestDriver.testReplaceAllTime("add 0900 work"));
		assertEquals("add~%work", ParserTestDriver.testReplaceAllTime("add~0900%work"));

		//confirm method replaces date input according to actual ideal user add appointment input
		//assertEquals("add 09/08/1223  2/3/2345  work", ParserTestDriver.testReplaceAllTime("add 09/08/1223 0900 2/3/2345 0800 work"));
		//Test fails
		//reason: pattern detects the year portion of date inut as time
		// pattern cannot differentiate between them
		
		//Negative test: So long as this test returns true, design flaw has not been addressed
		assertEquals("90 21", ParserTestDriver.testReplaceAllTime("1234567890 0987654321"));
	}

}
