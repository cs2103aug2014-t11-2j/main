package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserTestDriver;

public class SparkMoVareHasTwoTimeInputTest {

	@Test
	public void test() {
		//confirm method returns true if 2 time inputs
		assertEquals(true, ParserTestDriver.testHasTwoTimeInputs("0900 0800"));
		
		//confirm method returns true if 2 time inputs with additional portions
		assertEquals(true, ParserTestDriver.testHasTwoTimeInputs("add 0900 nothing 0800 work"));
		
		//confirm method returns false if only 1 date input
		assertEquals(false, ParserTestDriver.testHasTwoTimeInputs("0900"));
	}

}
