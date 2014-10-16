package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareGetTitleTest {

	@Test
	public void test() {
		//confirm method returns nothing with only date and time input and add command
		assertEquals(null, ParserTestDriver.testGetTitle("add 12/23/1234 0900"));
		
		//confirm method returns title
		assertEquals("work to do", ParserTestDriver.testGetTitle("add 12/23/1234 0900 work to do"));
		
		//confirm method returns title in appropriate form
		assertEquals("work to do", ParserTestDriver.testGetTitle("add work 12/23/1234 to 0900 do"));
	}

}
