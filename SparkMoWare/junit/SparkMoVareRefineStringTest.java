package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareRefineStringTest {

	@Test
	public void test() {
		String[] testArray = {"work ", "to  ", "do "};
		
		//Confirm method returns string with only single spaces
		assertEquals("work to do", ParserTestDriver.testRefineString(testArray));
	}

}
