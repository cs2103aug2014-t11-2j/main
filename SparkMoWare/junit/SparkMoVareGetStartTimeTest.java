package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareGetStartTimeTest {

	@Test
	public void test() {
		//confirm method returns time input
		assertEquals("0900", ParserTestDriver.testGetStartTime("0900"));
		
		//confirm method returns first time input
		assertEquals("0900", ParserTestDriver.testGetStartTime("add 0900 0800"));
		
		//confirm method returns time input instead of the year 2345
		assertEquals("0900", ParserTestDriver.testGetStartTime("add 12/34/2345 0900"));
	}

}
