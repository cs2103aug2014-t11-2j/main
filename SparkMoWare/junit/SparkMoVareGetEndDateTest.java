package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserDateLocal;
import parser.ParserTestDriver;

public class SparkMoVareGetEndDateTest {

	@Test
	public void test() {
		//confirm method returns date input
		assertEquals("09/09/1234", ParserTestDriver.testGetEndDate("add 09/09/1234"));
		
		//confirm method returns second date input
		assertEquals("2/3/2345", ParserTestDriver.testGetEndDate("add 09/09/1234 2/3/2345"));
		
		//confirm method fills in today's date if no date is detected
		//change date input accordingly
		assertEquals("16102014", ParserTestDriver.testGetEndDate("add"));
		
		//following test case is "pointless" since it assumes that the dateString
		//method already works and returns current date.
		assertEquals(ParserDateLocal.dateString(), ParserTestDriver.testGetEndDate("add"));
	}

}
