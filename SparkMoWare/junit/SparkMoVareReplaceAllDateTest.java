package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareReplaceAllDateTest {

	@Test
	public void test() {
		//confirm method replaces date input
		assertEquals("", ParserTestDriver.testReplaceAllDate("09/08/1223"));
		
		//confirm method replaces date input with additional portions
		//note: each space/symbol around the date is left behind
		assertEquals("add  work", ParserTestDriver.testReplaceAllDate("add 09/08/1223 work"));
		assertEquals("add~%work", ParserTestDriver.testReplaceAllDate("add~09/08/1223%work"));
		
		//confirm method replaces date input according to actual ideal user add appointment input
		assertEquals("add  0900  0800 work", ParserTestDriver.testReplaceAllDate("add 09/08/1223 0900 2/3/2345 0800 work"));
	}

}
