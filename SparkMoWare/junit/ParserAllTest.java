package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserDateLocal;
import parser.ParserTestDriver;

public class ParserAllTest {

	@Test
	public void test() {
		
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
		
		//case of inputs around date
		assertFalse(ParserTestDriver.testHasTwoDateInputs("add assignment due 09/09/1234 IMPT"));
		
		//negative test
		//assertFalse(ParserTestDriver.testHasTwoDateInputs("add assignment 12345678 due 09/09/1234 IMPT"));
		
		/**************************/
		
		//Test extractStartdate
		
		//confirm method returns first date input
		assertEquals("09091234", ParserTestDriver.testExtractStartDate("add 09/09/1234 2/3/2345"));
		
		//Failure test
		//assertEquals("09091234", ParserTestDriver.testExtractStartDate("add 9091234"));
		
		/**************************/
		
		//Test determineDateValidity
		//Essentially is the method dateFormatValid
		
		/**************************/
		
		//Test dateFormatValid
		
		//generalised test case
		assertTrue(ParserTestDriver.testDateFormatValid("23122013"));
		
		//test for length of string !=8
		assertFalse(ParserTestDriver.testDateFormatValid("123456789"));
		assertFalse(ParserTestDriver.testDateFormatValid("1234567"));
		
		//test if String isn't only made of int
		assertFalse(ParserTestDriver.testDateFormatValid("2902201a"));
		
		/**************************/
		
		//Test dateExists
		
		//test for leap year, 29th feb
		assertTrue(ParserTestDriver.testDateExists(29022004));
		
		//test for non-leap year, 29th feb
		assertFalse(ParserTestDriver.testDateExists(29022014));
		
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
		
		/**************************/
		
		//Test extractTentativeDates
		
		//single tentative
		assertEquals("[09081234]", ParserTestDriver.testExtractTentativeDates("09/08/1234 0900"));
		
		//two tentatives
		assertEquals("[09081234, 02031234]", ParserTestDriver.testExtractTentativeDates("09/08/1234 0900 02/03/1234 0800"));
		
		/***********ParserTimeLocal Tests***************/
		
		//Test extractEndTime
		
		//confirm method returns second time input
		assertEquals("0800", ParserTestDriver.testExtractEndTime("add 09/09/1234 0900 2/3/2345 0800"));
		
		/**************************/
		
		//Test hasTwoTimeInputsTest	

		//confirm method returns true if 2 time inputs
		assertTrue(ParserTestDriver.testHasTwoTimeInputs("0900 0800"));

		//confirm method returns true if 2 time inputs with additional portions
		assertTrue(ParserTestDriver.testHasTwoTimeInputs("add 0900 nothing 0800 work"));
		assertTrue(ParserTestDriver.testHasTwoTimeInputs("add 0900 nothing 0800"));
		assertTrue(ParserTestDriver.testHasTwoTimeInputs("0900 nothing 0800 work"));
		assertTrue(ParserTestDriver.testHasTwoTimeInputs("0900 nothing 0800"));

		//confirm method returns false if only 1 date input
		assertFalse(ParserTestDriver.testHasTwoTimeInputs("0900"));
				
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
		//Essentially is the method timeFormatValid
		
		/**************************/
		
		//Test timeFormatValid
		
		//generalise test case
		assertTrue(ParserTestDriver.testTimeFormatValid("1245"));
		
		//Test for length!=4
		assertFalse(ParserTestDriver.testTimeFormatValid("12345"));
		assertFalse(ParserTestDriver.testTimeFormatValid("123"));
		
		//Test if String isn't only made of int
		assertFalse(ParserTestDriver.testTimeFormatValid("123A"));
		
		/**************************/
		
		//Test timeExists
		
		//Test on "Midnight"
		assertTrue(ParserTestDriver.testTimeFormatValid("0000"));
		
		//Test for minute greater than 59
		assertFalse(ParserTestDriver.testTimeFormatValid("1260"));
		
		//Test for hour greater than 23
		assertFalse(ParserTestDriver.testTimeFormatValid("2400"));
		
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
		//reason: pattern detects the year portion of date input as time
		// pattern cannot differentiate between them
		
		//Negative test: So long as this test returns true, design flaw has not been addressed
		assertEquals("90 21", ParserTestDriver.testReplaceAllTime("1234567890 0987654321"));
		
		/**************************/
		
		//Test extractTentativeDates
		
		//single tentative
		assertEquals("[0900]", ParserTestDriver.testExtractTentativeTimes("tentative 09/08/1234 0900"));
		
		//two tentatives
		assertEquals("[09081234, 02031234]", ParserTestDriver.testExtractTentativeDates("tentative 09/08/1234 0900 02/03/1234 0800"));
		
		
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
		
		//test for add command
		assertEquals("buy a duck", ParserTestDriver.testRemoveCommand("add buy a duck", "add"));
		
		//test for tentative command
		assertEquals("consultation", ParserTestDriver.testRemoveCommand("tentative consultation", "tentative"));
		
		/**************************/
		
		//Test removePriority
		
		//test removal of various pattern of important
		assertEquals("add buy eggs 0900 21072014", ParserTestDriver.testRemovePriority("add buy eggs 0900 21072014 Important"));
		assertEquals("add buy eggs 0900 21072014", ParserTestDriver.testRemovePriority("add buy eggs 0900 21072014 Impt"));
		assertEquals("add buy eggs 0900 21072014", ParserTestDriver.testRemovePriority("add buy eggs 0900 21072014 IMPT"));
		
		//test removal of various patterns of not important
		assertEquals("add buy eggs 0900 21072014", ParserTestDriver.testRemovePriority("add buy eggs 0900 21072014 Nimportant"));
		assertEquals("add buy eggs 0900 21072014", ParserTestDriver.testRemovePriority("add buy eggs 0900 21072014 NIMPT"));

		/**************************/
		
		//Test refineString

		String[] testArray = {"work ", "to  ", "do "};

		//Confirm method returns string with only single spaces
		assertEquals("work to do", ParserTestDriver.testRefineString(testArray));

		/**************************/
		
		//Test extractId
		
		//generalised test case
		assertEquals("090820140001", ParserTestDriver.testExtractId("delete 090820140001"));
		
		/**************************/
		
		//Test determineIdValidty
		
		//correct id
		assertEquals("090820140001", ParserTestDriver.testExtractId("delete 090820140001"));
		
		//incorrect date portion
		assertEquals("", ParserTestDriver.testExtractId("delete 09080140001"));
		
		//date does not exists
		assertEquals("", ParserTestDriver.testExtractId("delete 990820140001"));
		
		//missing index
		//this might be a valid form
		assertEquals("", ParserTestDriver.testExtractId("delete 09082014001"));
		
		/**************************/
		
		//Test extractPriority
		
		//generalised test cases
		assertEquals("NIMPT", ParserTestDriver.testExtractPriority("add buy eggs 0900 21072014"));
		assertEquals("IMPT", ParserTestDriver.testExtractPriority("add buy eggs 0900 21072014 Important"));
		assertEquals("NIMPT", ParserTestDriver.testExtractPriority("add buy eggs 0900 21072014 Nimportant"));
		
		/**************************/
		
		//Test determinePriorityValidty
		
		//generalised test cases
		assertTrue(ParserTestDriver.testDeterminePriorityValidity("important"));
		assertTrue(ParserTestDriver.testDeterminePriorityValidity("nimportant"));
		
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

		//Test forSort
		
		//stub
		
		
		/**************************/
		
		//Test forFilter
		
		//stub
		
		
		/***********ExtractSpecialContent Tests***************/
		
		//Test forClear
		assertEquals("between", ParserTestDriver.testforClear("clear between 09/08/2014 10/8/2014"));
		assertEquals("on", ParserTestDriver.testforClear("clear on 09/08/2014"));
		assertEquals("before", ParserTestDriver.testforClear("clear before 09/08/2014"));
		
		/**************************/
		
		//Test forSearch
		
		//generalised test case
		assertEquals("assignment", ParserTestDriver.testforSearch("search assignment"));
		
		/**************************/
	
		//Test forSort
		
		//generalised test case
		assertEquals("start date", ParserTestDriver.testforSort("sort start date"));
		
		/**************************/
		
		//Test forFilter
		
		//generalised test case
		assertEquals("start date", ParserTestDriver.testforFilter("filter start date"));
			
		/*************InputIsAdd Tests*************/
		
		//confirm method for floating tasks/assignment && without indication of importance
		assertEquals("ADD~default~EAT DINNER~default~default"
				+ "~default~default~ASSIGNMENT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsAdd("Add EAT DINNER"));

		//confirm method for task with single date input
		assertEquals("ADD~default~assignment due~default~default"
				+ "~23122014~2359~TASK~IMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsAdd("Add assignment due IMPT 23/12/2014"));

		//confirm method for task with single time input
		//test case requires update for date output to current/system date 
		/*assertEquals("ADD~default~assignment due~default~default"
				+ "~27102014~2300~TASK~IMPT"
				+ "~false~default", ParserTestDriver.testInputIsAdd("Add assignment due IMPT 2300"));
				*/

		//confirm method returns correct output for full input
		assertEquals("ADD~default~buy chicken~09091234~0900"
				+ "~02032345~0800~APPOINTMENT~IMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsAdd("add buy chicken 09/09/1234 0900 2/3/2345 0800 Important"));

		//negative test case
		/*assertEquals("ADD~default~go 2103T lecture~31102014~1400"
				+ "~31102014~1600~APPOINTMENT~NMPT"
				+ "~false~default", ParserTestDriver.testInputIsAdd("Add go 2103T lecture 31/10/2014 1400 31/10/2014 1600"));
				*/
		//Design flaw: returns <go T lecture> instead of <go 2103T lecture>
		//The replace time method replaces the any 4 number pattern

		/*************InputIsClear Tests*************/
		
		
		
		/*************InputIsConfirm Tests*************/
		
		
		
		/*************InputIsEdit Tests*************/
		
		
		
		/*************InputIsTentative Tests*************/
		
		//single tentative
		assertEquals("TENTATIVE~010320140001~consultation~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~true~default~[12082014]~[0900]", ParserTestDriver.testInputIsTentative("Tentative 010320140001 consultation 12/8/2014 0900"));
		
		//more than one tentative
		assertEquals("TENTATIVE~010320140001~consultation~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~true~default~[12082014, 01092014, 01092014]"
				+ "~[0900, 1000, 1100]", ParserTestDriver.testInputIsTentative("Tentative 010320140001 consultation 12/8/2014 0900 1/09/2014 1000 1/9/2014 1100"));
		
		//confirm invalid if number of date and times dont correspond
		assertEquals("INVALID_FORMAT~default~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsTentative("Tentative 010320140001 consultation 12/8/2014 0900 1/09/2014 1000 1/9/2014"));
		
		//confirm invalid if title is empty
		assertEquals("INVALID_FORMAT~default~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsTentative("Tentative 010320140001 12/8/2014 0900 1/09/2014 1000 1/9/2014 1100"));
		
		/*************RefineInputWithId Tests*************/

		//test inputIsDelete
		
		
		/**************************/
		
		//test inputIsFinish
		
		
		/*************RefineInputWithSpecial Tests*************/
				
		//test inputIsFilter
		
		
		/**************************/
		
		//test inputIsSearch
		
		
		/**************************/
		
		//test inputIsSort
			
	}
}
