package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareGetStartDateTest {

	@Test
	public void test() {
		
		//confirm method returns first date input
				assertEquals("09/09/1234", ParserTestDriver.testGetStartDate("add 09/09/1234 2/3/2345"));
	}

}
