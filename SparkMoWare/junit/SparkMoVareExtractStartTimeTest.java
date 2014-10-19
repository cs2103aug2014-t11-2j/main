package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareExtractStartTimeTest {

	@Test
	public void test() {
		//confirm method returns time input
		assertEquals("0900", ParserTestDriver.testExtractStartTime("0900"));
		
		//confirm method returns first time input
		assertEquals("0900", ParserTestDriver.testExtractStartTime("add 0900 0800"));
		assertEquals("0900", ParserTestDriver.testExtractStartTime("add void 0900 nada 0800 nothing"));
		
		//confirm method returns time input instead of the year 2345
		assertEquals("0900", ParserTestDriver.testExtractStartTime("add 12/11/2345 0900"));
	}

}
