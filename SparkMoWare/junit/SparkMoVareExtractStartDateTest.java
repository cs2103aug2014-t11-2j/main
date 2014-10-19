package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareExtractStartDateTest {

	@Test
	public void test() {
		
		//confirm method returns first date input
				assertEquals("09091234", ParserTestDriver.testExtractStartDate("add 09/09/1234 2/3/2345"));
	}

}
