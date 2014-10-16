package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareGetEndTimeTest {

	@Test
	public void test() {
		//confirm method returns second time input
		assertEquals("0800", ParserTestDriver.testGetEndTime("add 09/09/1234 0900 2/3/2345 0800"));
	}

}
