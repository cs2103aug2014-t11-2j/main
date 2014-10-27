package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserDateLocal;
import parser.ParserTestDriver;

public class ParserAllTest {

	@Test
	public void test() {
		
		/*************InputIsAdd Tests*************/
		
		assertEquals("ADD~default~buy chicken~09091234~0900"
					+ "~02032345~0800~APPOINTMENT~NMPT"
					+ "~false~default", ParserTestDriver.testInputIsAdd("add buy chicken 09/09/1234 0900 2/3/2345 0800"));
		
		//confirm method for full input
		assertEquals("ADD~default~buy chicken~09091234~0900"
				+ "~02032345~0800~APPOINTMENT~IMPT"
				+ "~false~default", ParserTestDriver.testInputIsAdd("add buy chicken 09/09/1234 0900 2/3/2345 0800 Important"));
		
		assertEquals("ADD~default~go 103T lecture~31102014~1400"
				+ "~31102014~1600~APPOINTMENT~NMPT"
				+ "~false~default", ParserTestDriver.testInputIsAdd("Add go 103T lecture 31/10/2014 1400 31/10/2014 1600"));
		
		assertEquals("ADD~default~EAT DINNER~default~default"
				+ "~default~default~ASSIGNMENT~IMPT"
				+ "~false~default", ParserTestDriver.testInputIsAdd("Add EAT DINNER IMPT"));
		
		
		/*************ParserDateLocal Tests*************/
		
		//Test extractEndDate()

		//confirm method returns date input
		assertEquals("09091234", ParserTestDriver.testExtractEndDate("add 09/09/1234"));

		//confirm method returns second date input
		assertEquals("02032345", ParserTestDriver.testExtractEndDate("add 09/09/1234 2/3/2345"));
		
		//confirm method fills in today's date if no date is detected
		//change date input accordingly
		//assertEquals("16102014", ParserTestDriver.testExtractEndDate("add"));

		//following test case is "pointless" since it assumes that the dateString
		//method already works and returns current date.
		assertEquals(ParserDateLocal.dateString(), ParserTestDriver.testExtractEndDate("add"));
		
		/**************************/		
		
		//Test hasTwoDateInputsTest

		//confirm method returns true if 2 date inputs
		assertTrue(ParserTestDriver.testHasTwoDateInputs("09/09/1234 2/3/2345"));

		//confirm method returns true if 2 date inputs with additional portions
		assertTrue(ParserTestDriver.testHasTwoDateInputs("add 09/09/1234 nothing 2/3/2345 works"));
		assertTrue(ParserTestDriver.testHasTwoDateInputs("add 09/09/1234 nothing 2/3/2345"));
		assertTrue(ParserTestDriver.testHasTwoDateInputs("add buy eggs 09/09/1234 0900 2/3/2345 0800"));
		assertTrue(ParserTestDriver.testHasTwoDateInputs("add title 09091234 0900 02032345 0800"));

		//confirm method returns false if only 1 date input
		assertFalse(ParserTestDriver.testHasTwoDateInputs("09/08/1245"));
		
		/* Methods should change the date input from whatever format
		 * for eg. 12/3/2014 or 12-3-2014 to 12032014 
		 */
		
		/**************************/
		
		//Test extractStartdate
		
		//confirm method returns first date input
		assertEquals("09091234", ParserTestDriver.testExtractStartDate("add 09/09/1234 2/3/2345"));
		
		/**************************/
		
		//Test determineDateValidity
		
		
		/**************************/
		
		//Test DateFormatValid
		
		
		/**************************/
		
		//Test dateExists
		
		
		/**************************/
		
		//Test dateString
		
		
		/**************************/
		
		//Test replaceAllDate

		//confirm method replaces date input
		assertEquals("", ParserTestDriver.testReplaceAllDate("09/08/1223"));

		//confirm method replaces date input with additional portions
		//note: each space/symbol around the date is left behind
		assertEquals("add  work", ParserTestDriver.testReplaceAllDate("add 09/08/1223 work"));
		assertEquals("add~%work", ParserTestDriver.testReplaceAllDate("add~09/08/1223%work"));

		//confirm method replaces date input according to actual ideal user add appointment input
		assertEquals("add  0900  0800 work", ParserTestDriver.testReplaceAllDate("add 09/08/1223 0900 2/3/2345 0800 work"));
		
		//Error test 1
		//assertEquals("add 0900 0800", ParserTestDriver.testReplaceAllDate("add 0900 0800"));
		
		/***********ParserTimeLocal Tests***************/
		
		//Test extractEndTime
		
		//confirm method returns second time input
		assertEquals("0800", ParserTestDriver.testExtractEndTime("add 09/09/1234 0900 2/3/2345 0800"));
		
		//Test extractStartDate
		
		//confirm method returns first date input
		assertEquals("09091234", ParserTestDriver.testExtractStartDate("add 09/09/1234 2/3/2345"));
		
		/**************************/
		
		//Test hasTwoTimeInputsTest	

		//confirm method returns true if 2 time inputs
		assertEquals(true, ParserTestDriver.testHasTwoTimeInputs("0900 0800"));

		//confirm method returns true if 2 time inputs with additional portions
		assertEquals(true, ParserTestDriver.testHasTwoTimeInputs("add 0900 nothing 0800 work"));
		assertEquals(true, ParserTestDriver.testHasTwoTimeInputs("add 0900 nothing 0800"));
		assertEquals(true, ParserTestDriver.testHasTwoTimeInputs("0900 nothing 0800 work"));
		assertEquals(true, ParserTestDriver.testHasTwoTimeInputs("0900 nothing 0800"));

		//confirm method returns false if only 1 date input
		assertEquals(false, ParserTestDriver.testHasTwoTimeInputs("0900"));
				
		/**************************/
		
		//Test extractStartTime
		
		//confirm method returns time input
		assertEquals("0900", ParserTestDriver.testExtractStartTime("0900"));

		//confirm method returns first time input
		assertEquals("0900", ParserTestDriver.testExtractStartTime("add void 0900 nada 0800 nothing"));
		
		//ERROR: Pattern interprets 1234 5678 as a date input, refer to error test 1
		//assertEquals("0900", ParserTestDriver.testExtractStartTime("add 0900 0800"));

		//confirm method returns time input instead of the year 2345
		assertEquals("0900", ParserTestDriver.testExtractStartTime("add 12/11/2345 0900"));
		
		/**************************/
		
		//Test determineTimeValidity
		
		
		/**************************/
		
		//Test timeFormatValid
		
		
		/**************************/
		
		//Test timeExists
		
		
		/**************************/
		
		//Test replaceAllTime

		//confirm method replaces time input
		assertEquals("", ParserTestDriver.testReplaceAllTime("0900"));

		//confirm method replaces date input with additional portions
		//note: each space/symbol around the date is left behind
		assertEquals("add  work", ParserTestDriver.testReplaceAllTime("add 0900 work"));
		assertEquals("add~%work", ParserTestDriver.testReplaceAllTime("add~0900%work"));

		//confirm method replaces date input according to actual ideal user add appointment input
		//assertEquals("add 09/08/1223  2/3/2345  work", ParserTestDriver.testReplaceAllTime("add 09/08/1223 0900 2/3/2345 0800 work"));
		//Test fails
		//reason: pattern detects the year portion of date inut as time
		// pattern cannot differentiate between them

		//Negative test: So long as this test returns true, design flaw has not been addressed
		assertEquals("90 21", ParserTestDriver.testReplaceAllTime("1234567890 0987654321"));
		
		/*************Misc Tests*************/
		
		//Test isFloatingAssignment
		
		//confirm method returns true if no date and time
		assertTrue(ParserTestDriver.testIsFloatingAssignment("add buy eggs"));
		
		//confirm method returns false with either date and/or time
		assertFalse(ParserTestDriver.testIsFloatingAssignment("add buy eggs 0900"));
		assertFalse(ParserTestDriver.testIsFloatingAssignment("add buy eggs 21072014"));
		assertFalse(ParserTestDriver.testIsFloatingAssignment("add buy eggs 0900 21072014"));
		
		/**************************/
		
		//Test extractTitle
		
		//confirm method returns nothing with only date and time input and add command
		assertEquals("", ParserTestDriver.testExtractTitle("add 12/23/1234 0900", "add"));

		//confirm method returns title
		assertEquals("work to do", ParserTestDriver.testExtractTitle("add 12/23/1234 0900 work to do", "add"));

		//confirm method returns title in appropriate form
		assertEquals("work to do", ParserTestDriver.testExtractTitle("add work 12/23/1234 to 0900 do", "add"));

		/**************************/
		
		//Test removeCommand
		
		
		/**************************/
		
		//Test removePriority
		

		/**************************/
		
		//Test refineString

		String[] testArray = {"work ", "to  ", "do "};

		//Confirm method returns string with only single spaces
		assertEquals("work to do", ParserTestDriver.testRefineString(testArray));

		/**************************/
		
		//Test extractId
		
		
		/**************************/
		
		//Test determineIdValidty
		
		
		/**************************/
		
		//Test extractPriority
		assertEquals("NMPT", ParserTestDriver.testExtractPriority("add buy eggs 0900 21072014"));
		assertEquals("IMPT", ParserTestDriver.testExtractPriority("add buy eggs 0900 21072014 Important"));
		
		/**************************/
		
		//Test determinePriorityValidty
		
		
		/***********ExtractSpecialContent Tests***************/
		
		//Test forClear
		
		
		/**************************/
		
		//Test forSearch
		
		
		/**************************/
		
		//Test forTentative
		
		
		/**************************/
		
		//Test forSort
		
		
		/**************************/
		
		//Test forFilter
		
		
		/*************InvalidSpecialContent Tests*************/
		
		//Test contentForClear

		//confirm method returns true for ideal cases
		assertTrue(ParserTestDriver.testContentForClear("on "));
		assertTrue(ParserTestDriver.testContentForClear(" before"));
		assertTrue(ParserTestDriver.testContentForClear("between"));

		//confirm method can and does return false
		assertFalse(ParserTestDriver.testContentForClear("o n"));

		//confirm method will not return true if word is in a long sequence
		assertFalse(ParserTestDriver.testContentForClear("ion"));
		assertFalse(ParserTestDriver.testContentForClear("beforetime"));
		assertFalse(ParserTestDriver.testContentForClear("isbetween"));
		
		/**************************/	
	
	}
}
