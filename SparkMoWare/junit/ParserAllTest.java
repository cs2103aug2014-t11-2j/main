package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserDateLocal;
import parser.ParserTestDriver;

public class ParserAllTest {

	@Test
	public void test() {
		
		/*************ParserDateLocal Tests*************/
		
		//test dateComparator

		//test first date smaller than second date
		assertEquals(-1, ParserTestDriver.testDateComparator("090814","100814"));
		assertEquals(-1, ParserTestDriver.testDateComparator("090814","090914"));
		assertEquals(-1, ParserTestDriver.testDateComparator("090814","090815"));

		//test first date larger than second date
		assertEquals(1, ParserTestDriver.testDateComparator("100814", "090814"));
		assertEquals(1, ParserTestDriver.testDateComparator("090914", "090814"));
		assertEquals(1, ParserTestDriver.testDateComparator("090815", "090814"));
		
		//test dates are the same
		assertEquals(0, ParserTestDriver.testDateComparator("090814", "090814"));

		/**************************/
		
		//Test extractEndDate()

		//confirm method returns date input
		assertEquals("090934", ParserTestDriver.testExtractEndDate("add 09/09/34"));

		//confirm method returns second date input
		assertEquals("020345", ParserTestDriver.testExtractEndDate("add 09/09/1234 2/3/45"));
		
		//confirm method fills in today's date if no date is detected
		//change date input accordingly
		//assertEquals("16102014", ParserTestDriver.testExtractEndDate("add"));

		//following test case is "pointless" since it assumes that the dateString
		//method already works and returns current date.
		assertEquals(ParserDateLocal.dateString(), ParserTestDriver.testExtractEndDate("add"));
		
		/**************************/		
		
		//Test hasTwoDateInputsTest

		//confirm method returns true if 2 date inputs
		assertTrue(ParserTestDriver.testHasTwoDateInputs("09/09/34 2/3/45"));

		//confirm method returns true if 2 date inputs with additional portions
		assertTrue(ParserTestDriver.testHasTwoDateInputs("add 09/09/34 nothing 2/3/45 works"));
		assertTrue(ParserTestDriver.testHasTwoDateInputs("add 09/09/34 nothing 2/3/45"));
		assertTrue(ParserTestDriver.testHasTwoDateInputs("add buy eggs 09/09/34 0900 2/3/45 0800"));

		//confirm method returns false if only 1 date input
		assertFalse(ParserTestDriver.testHasTwoDateInputs("09/08/45"));
		
		//case of inputs around date
		assertFalse(ParserTestDriver.testHasTwoDateInputs("add assignment due 09/09/34 IMPT"));
		
		/**************************/
		
		//Test extractStartdate
		
		//confirm method returns first date input
		assertEquals("090934", ParserTestDriver.testExtractStartDate("add 09/09/34 2/3/45"));
		
		//Failure test
		//assertEquals("09091234", ParserTestDriver.testExtractStartDate("add 9091234"));
		
		/**************************/
		
		//Test determineDateValidity
		//Essentially is the method dateFormatValid
		
		/**************************/
		
		//Test dateFormatValid
		
		//generalised test case
		assertTrue(ParserTestDriver.testDateFormatValid("231213"));
		
		//test for length of string !=8
		assertFalse(ParserTestDriver.testDateFormatValid("123456789"));
		assertFalse(ParserTestDriver.testDateFormatValid("1234567"));
		
		//test if String isn't only made of int
		assertFalse(ParserTestDriver.testDateFormatValid("2902201a"));
		
		/**************************/
		
		//Test dateExists
		
		//test for leap year, 29th feb
		assertTrue(ParserTestDriver.testDateExists(290204));
		
		//test for non-leap year, 29th feb
		assertFalse(ParserTestDriver.testDateExists(290214));
		
		/**************************/
		
		//Test replaceAllDate

		//confirm method replaces date input
		assertEquals("", ParserTestDriver.testReplaceAllDate("09/08/23"));

		//confirm method replaces date input with additional portions
		//note: each space/symbol around the date is left behind
		assertEquals("add  work", ParserTestDriver.testReplaceAllDate("add 09/08/23 work"));
		assertEquals("add~%work", ParserTestDriver.testReplaceAllDate("add~09/08/23%work"));

		//confirm method replaces date input according to actual ideal user add appointment input
		assertEquals("add  0900  0800 work", ParserTestDriver.testReplaceAllDate("add 09/08/23 0900 2/3/45 0800 work"));
		
		/**************************/
		
		//Test extractTentativeDates
		
		//single tentative
		assertEquals("[090834]", ParserTestDriver.testExtractTentativeDates("09/08/34 0900"));
		
		//two tentatives
		assertEquals("[090834, 020334]", ParserTestDriver.testExtractTentativeDates("09/08/34 0900 02/03/34 0800"));
		
		/***********ParserTimeLocal Tests***************/
		
		//test timeComparator
		
		//test first time smaller than second time
		assertEquals(-1, ParserTestDriver.testTimeComparator("0900", "1000"));
		assertEquals(-1, ParserTestDriver.testTimeComparator("0900", "0959"));
		
		//test first time smaller than second time
		assertEquals(1, ParserTestDriver.testTimeComparator("1000", "0900"));
		assertEquals(1, ParserTestDriver.testTimeComparator("0959", "0900"));
		
		//test times are the same
		assertEquals(0, ParserTestDriver.testTimeComparator("0900", "0900"));
		
		/**************************/
		
		//Test extractEndTime
		
		//confirm method returns second time input
		assertEquals("0800", ParserTestDriver.testExtractEndTime("add 09/09/34 0900 2/3/45 0800"));
		
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
		
		//extractStartTime negative test 1
		//assertEquals("0900", ParserTestDriver.testExtractStartTime("add 0900 0800"));

		//confirm method returns time input instead of the year 2345
		assertEquals("0900", ParserTestDriver.testExtractStartTime("add 12/11/45 0900"));
		
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

		//replaceAllTime negative test 1
		//assertEquals("add 09/08/1223  2/3/2345  work", ParserTestDriver.testReplaceAllTime("add 09/08/1223 0900 2/3/2345 0800 work"));
		
		//replaceAllTime Negative test 2: So long as this test returns true, design flaw has not been addressed
		assertEquals("90 21", ParserTestDriver.testReplaceAllTime("1234567890 0987654321"));
		
		/**************************/
		
		//Test extractTentativeTimes
		
		//single tentative
		assertEquals("[0900]", ParserTestDriver.testExtractTentativeTimes("tentative 09/08/34 0900"));
		
		//two tentatives
		assertEquals("[0900, 0800]", ParserTestDriver.testExtractTentativeTimes("tentative 09/08/1234 0900 02/03/1234 0800"));
		
		/*************ParserIndexLocal Tests*************/
		
		//Test extractIndex

		//generalised test case
		assertEquals(1, ParserTestDriver.testExtractIndex("delete 1", "delete"));
		assertEquals(-1, ParserTestDriver.testExtractIndex("delete", "delete"));

		//test done/finish command
		assertEquals(12, ParserTestDriver.testExtractIndex("done 12", "finish"));
		
		//test front zeroes
		assertEquals(20, ParserTestDriver.testExtractIndex("delete 020", "delete"));
		
		//test shows ability to deal with typos for index
		assertEquals(2, ParserTestDriver.testExtractIndex("delete A2", "delete"));
		
		//test for full confirm
		
		/**************************/
		
		//Test indexExists
		
		//generalised test case
		assertTrue(ParserTestDriver.testIndexExists("delete 1"));
		
		/**************************/
		
		//Test removeIndex

		//generalised test case
		assertEquals("delete", ParserTestDriver.testRemoveIndex("delete 1"));

		//test front zero and done
		assertEquals("done", ParserTestDriver.testRemoveIndex("done 01"));
		
		//test longer than 1 digit
		assertEquals("delete", ParserTestDriver.testRemoveIndex("delete 20"));
		
		//remove index negative test 1
		//cannot have index greater than 999
		//assertEquals("delete", ParserTestDriver.testRemoveIndex("delete 2000"));
		
		//test for edit input
		assertEquals("edit  start date 09/08/14", ParserTestDriver.testRemoveIndex("edit 1 start date 09/08/14"));
		
		/*************Misc Tests*************/
		
		//Test isFloatingAssignment
		
		//confirm method returns true if no date and time
		assertTrue(ParserTestDriver.testIsFloatingAssignment("add buy eggs"));
		
		//confirm method returns false with either date and/or time
		assertFalse(ParserTestDriver.testIsFloatingAssignment("add buy eggs 0900"));
		assertFalse(ParserTestDriver.testIsFloatingAssignment("add buy eggs 210714"));
		assertFalse(ParserTestDriver.testIsFloatingAssignment("add buy eggs 0900 210714"));
		
		/**************************/
		
		//Test extractTitle
		
		//confirm method returns nothing with only date and time input and add command
		assertEquals("", ParserTestDriver.testExtractTitle("add 12/23/34 0900", "add"));

		//confirm method returns title
		assertEquals("work to do", ParserTestDriver.testExtractTitle("add 12/23/34 0900 work to do", "add"));

		//confirm method returns title in appropriate form
		assertEquals("work to do", ParserTestDriver.testExtractTitle("add work 12/23/34 to 0900 do", "add"));

		/**************************/
		
		//Test removeCommand
		
		//test for add command
		assertEquals("buy a duck", ParserTestDriver.testRemoveCommand("add buy a duck", "add"));
		
		//test for tentative command
		assertEquals("consultation", ParserTestDriver.testRemoveCommand("tentative consultation", "tentative"));
		
		/**************************/
		
		//Test removePriority
		
		//test removal of various pattern of important
		assertEquals("add buy eggs 0900 210714", ParserTestDriver.testRemovePriority("add buy eggs 0900 210714 Important"));
		assertEquals("add buy eggs 0900 210714", ParserTestDriver.testRemovePriority("add buy eggs 0900 210714 Impt"));
		assertEquals("add buy eggs 0900 210714", ParserTestDriver.testRemovePriority("add buy eggs 0900 210714 IMPT"));
		
		//test removal of various patterns of not important
		assertEquals("add buy eggs 0900 210714", ParserTestDriver.testRemovePriority("add buy eggs 0900 210714 Nimportant"));
		assertEquals("add buy eggs 0900 210714", ParserTestDriver.testRemovePriority("add buy eggs 0900 210714 NIMPT"));

		/**************************/
		
		//Test refineString

		String[] testArray = {"work ", "to  ", "do "};

		//Confirm method returns string with only single spaces
		assertEquals("work to do", ParserTestDriver.testRefineString(testArray));

		/**************************/
		
		//Test extractPriority
		
		//generalised test cases
		assertEquals("NIMPT", ParserTestDriver.testExtractPriority("add buy eggs 0900 210714"));
		assertEquals("IMPT", ParserTestDriver.testExtractPriority("add buy eggs 0900 210714 Important"));
		assertEquals("NIMPT", ParserTestDriver.testExtractPriority("add buy eggs 0900 210714 Nimportant"));
		
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
		assertEquals("between", ParserTestDriver.testforClear("clear between 09/08/14 10/8/14"));
		assertEquals("on", ParserTestDriver.testforClear("clear on 09/08/14"));
		assertEquals("before", ParserTestDriver.testforClear("clear before 09/08/14"));
		
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

		//confirm method returns invalid for single command
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsAdd("Add"));
		
		//confirm method returns invalid if appointment dates and times are the same
		assertEquals("INVALID_FORMAT~0~default~311014~2359"
				+ "~311014~2359~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsAdd("Add buy fish 31/10/14 2359 31/10/14 2359"));

		//confirm method for floating tasks/assignment && without indication of importance
		assertEquals("ADD~0~EAT DINNER~default~default"
				+ "~default~default~ASGN~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsAdd("Add EAT DINNER"));

		//confirm method for task with single date input
		assertEquals("ADD~0~assignment due~default~default"
				+ "~231214~2359~TASK~IMPT"
				+ "~false~default~null~null",
				ParserTestDriver.testInputIsAdd("Add assignment due IMPT 23/12/14"));

		//ZY 1
		assertEquals("ADD~0~buy fish~default~default"
				+ "~311014~2359~TASK~NIMPT"
				+ "~false~default~null~null",
				ParserTestDriver.testInputIsAdd("Add buy fish 31/10/14 2359"));

		//ZY 2
		assertEquals("ADD~0~buy fish~311014~2359"
				+ "~011015~2359~APPT~NIMPT"
				+ "~false~default~null~null",
				ParserTestDriver.testInputIsAdd("Add buy fish 31/10/14 2359 01/10/15 2359"));

		//confirm method for task with single time input
		//test case requires update for date output to current/system date 
		/*assertEquals("ADD~0~assignment due~default~default"
				+ "~271014~2300~TASK~IMPT"
				+ "~false~default",
				ParserTestDriver.testInputIsAdd("Add assignment due IMPT 2300"));
		 */

		//confirm method returns correct output for full input
		assertEquals("ADD~0~buy chicken~090934~0900"
				+ "~020345~0800~APPT~IMPT"
				+ "~false~default~null~null",
				ParserTestDriver.testInputIsAdd("add buy chicken 09/09/34 0900 2/3/45 0800 Important"));

		//InputIsAdd negative test case 1
		/*assertEquals("ADD~0~go 2103T lecture~311014~1400"
				+ "~311014~1600~APPT~NMPT"
				+ "~false~default",
				ParserTestDriver.testInputIsAdd("Add go 2103T lecture 31/10/14 1400 31/10/14 1600"));
		 */
		//Design flaw: returns <go T lecture> instead of <go 2103T lecture>
		//The replace time method replaces the any 4 number pattern

		//test correct output if dates are in wrong order
		assertEquals("ADD~0~buy fish~311014~2359"
				+ "~011015~2359~APPT~NIMPT"
				+ "~false~default~null~null",
				ParserTestDriver.testInputIsAdd("Add buy fish 01/10/15 2359 31/10/14 2359"));
		
		//test correct output if times are in the wrong order
		assertEquals("ADD~0~buy fish~311014~2300"
				+ "~311014~2359~APPT~NIMPT"
				+ "~false~default~null~null",
				ParserTestDriver.testInputIsAdd("Add buy fish 31/10/14 2359 31/10/14 2300"));
		
		//test correct output if times are in the wrong order
				assertEquals("ADD~0~buy fish~311014~2300"
						+ "~311014~2359~APPT~NIMPT"
						+ "~false~default~null~null",
						ParserTestDriver.testInputIsAdd("Add 1 more function 15/10/14 1400"));
		
		/*************InputIsClear Tests*************/

		//confirm method works for clear, aka clear all
		assertEquals("CLEAR~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~clear~null~null", ParserTestDriver.testInputIsClear("clear"));

		//confirm method works for between 2 dates
		assertEquals("CLEAR~0~default~290814~default"
				+ "~030914~default~DEFAULT~NIMPT"
				+ "~false~between~null~null",
				ParserTestDriver.testInputIsClear("clear between 29/08/14 3/9/14"));
		
		//confirm method works for before a date
		assertEquals("CLEAR~0~default~010114~default"
				+ "~030914~default~DEFAULT~NIMPT"
				+ "~false~before~null~null",
				ParserTestDriver.testInputIsClear("clear before 3/9/14"));

		//confirm method works for on a date
		//clear issue 1
		assertEquals("CLEAR~0~default~010114~default"
				+ "~030914~default~DEFAULT~NIMPT"
				+ "~false~on~null~null",
				ParserTestDriver.testInputIsClear("clear on 3/9/14"));

		/*************InputIsConfirm Tests*************/

		//confirm method returns invalid for single command
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsConfirm("confirm"));

		//generalised test case
		assertEquals("CONFIRM~1~default~151014~1300"
				+ "~151014~1500~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsConfirm("confirm 1 15/10/14 1300 15/10/14 1500"));

		/*************InputIsEdit Tests*************/

		//confirm method returns invalid for single command
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsEdit("edit"));
		
		//confirm method does not work without id
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsEdit("edit title this is an appointment"));

		//confirm method works for editing title
		assertEquals("EDIT~2~this is an appointment~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~title~null~null", ParserTestDriver.testInputIsEdit("edit 2 title this is an appointment"));
		
		//confirm method works for editing title
		assertEquals("EDIT~2~tsk tsk~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~title~null~null", ParserTestDriver.testInputIsEdit("edit 2 title tsk tsk"));
		
		/*************InputIsTentative Tests*************/

		//confirm method returns invalid for single command
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsTentative("tentative"));

		//single tentative
		assertEquals("TENTATIVE~0~consultation~default~default"
				+ "~default~default~TNTV~NIMPT"
				+ "~true~default~[120814]~[0900]", ParserTestDriver.testInputIsTentative("Tentative consultation 12/8/14 0900"));

		//more than one tentative
		assertEquals("TENTATIVE~0~consultation~default~default"
				+ "~default~default~TNTV~NIMPT"
				+ "~true~default~[120814, 010914, 010914]"
				+ "~[0900, 1000, 1100]", ParserTestDriver.testInputIsTentative("Tentative consultation 12/8/14 0900 1/09/14 1000 1/9/14 1100"));

		//confirm invalid if number of date and times dont correspond
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsTentative("Tentative consultation 12/8/14 0900 1/09/14 1000 1/9/14"));

		//confirm invalid if title is empty
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsTentative("Tentative 12/8/14 0900 1/09/14 1000 1/9/14 1100"));

		/*************RefineInputWithId Tests*************/

		//test inputIsDelete

		//confirm method returns invalid for single command
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsDelete("delete"));
		
		//generalised test  case
		/*assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsDelete("delete 090814/0001"));*/

		/**************************/

		//test inputIsFinish

		//confirm method returns invalid for single command
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsFinish("finish"));
		
		//generalised test case
		assertEquals("DONE~2~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsFinish("finish 2"));

		/*************RefineInputWithSpecial Tests*************/

		//test inputIsFilter

		/*
		
		//confirm method returns invalid for single command
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsFilter("filter"));
		
		//generalised test case
		assertEquals("FILTER~0~default~01012000~default"
				+ "~31122600~default~DEFAULT~NIMPT"
				+ "~false~assignment~null~null", ParserTestDriver.testInputIsFilter("filter assignment"));
		
		//test for changing deadline
				assertEquals("FILTER~0~default~01012000~default"
						+ "~15102014~default~DEFAULT~NIMPT"
						+ "~false~default~null~null", ParserTestDriver.testInputIsFilter("filter 15/10/2014"));
		
		//test for multiple filter types
		assertEquals("FILTER~0~default~01012000~default"
				+ "~31122600~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsFilter("filter assignment "));
		
		*/

		/**************************/

		//test inputIsSearch

		//confirm method returns invalid for single command
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsSearch("search"));
		
		//generalised test case
		assertEquals("SEARCH~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~assignment~null~null", ParserTestDriver.testInputIsSearch("search assignment"));
		
		//test input date is converted into a string consisting only of digits
		assertEquals("SEARCH~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~100814~null~null", ParserTestDriver.testInputIsSearch("search 10/8/14"));
		
		/**************************/

		//test inputIsSort

		//confirm method returns invalid for single command
		assertEquals("INVALID_FORMAT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testInputIsSort("sort"));
		
		//generalised test case
		assertEquals("SORT~0~default~01012000~default"
				+ "~31122600~default~DEFAULT~NIMPT"
				+ "~false~id~null~null", ParserTestDriver.testInputIsSort("sort id"));
		
		/***********Interpreter***************/
		
		//confirm method returns invalid if non supported command is entered
		assertEquals("DEFAULT~0~default~default~default"
				+ "~default~default~DEFAULT~NIMPT"
				+ "~false~default~null~null", ParserTestDriver.testReader("play"));
		
		/**************Exceptions testing***********/
/*		assertEquals(EnumGroup.CommandType.INVALID, Determine.getCommandType("askjbd"));
		assertEquals(EnumGroup.EditType.INVALID, Determine.getEditType("askjbd"));
		assertEquals(EnumGroup.CommandType.INVALID, InputIsEdit.refineInput("edit -1"));
*/
	}
}
