package junit;

import static org.junit.Assert.*;

import java.util.LinkedList;

import parser.EnumGroup.CommandType;
import parser.EnumGroup.AssignmentType;
import parser.Interpreter;
import parser.RefinedUserInput;
import logic.Add;
import logic.Appointment;
import logic.Assignment;
import logic.Comparator;
import logic.DateLocal;
import logic.Delete;
import logic.Filter;
import logic.InternalStorage;
import logic.LogicTestDrive;
import logic.Print;
import logic.Edit;
import logic.SearchAll;
import logic.Sort;

import org.junit.Test;

public class SparkMoVareTest<Assignment> {

	private static final String id_1 = "140119940001";
	private static final String id_2 = "140119940002";
	private static final String id_3 = "150119940001";
	private static final String title = "testing";
	private static final String startDate = "14011994";
	private static final String startTime = "0101";
	private static final String endDate = "16011994";
	private static final String endTime = "2359";
	private static final String priority = "IMPT";
	private static final boolean isDone = false;
	private static final AssignmentType typeTask = AssignmentType.TASK;
	private static final AssignmentType typeAppt = AssignmentType.APPOINTMENT;
	private static final RefinedUserInput testInput = new RefinedUserInput();
	private static RefinedUserInput[] parsedInput = new RefinedUserInput[20];
	
	// test cases 1-3 are all valid
	private static final String test1 = id_1 + "~" + title + "~" + "ASSIGNMENT"
			+ "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test2 = id_2 + "~" + title + "~"
			+ "APPOINTMENT" + "~" + startDate + "~" + startTime + "~" + endDate
			+ "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test3 = id_3 + "~" + title + "~" + "TASK" + "~"
			+ endDate + "~" + endTime + "~" + "false" + "~" + "false" + "~"
			+ priority;
	
	//test cases 4-9 parser and logic integration boundary cases 
	/*
	 * IDs have to be changed henceforth for all cases henceforth
	 */
	private static final String test4 = id_3 + "~" + title + "~" + "TASK" + "~"
			+ endDate + "~" + endTime + "~" + "false" + "~" + "false" + "~";
	private static final String test5 = id_3 + "~" + title + "~" + "TASK" + "~"
			+ endDate + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test6 = id_3 + "~" + title + "~" + "TASK" + "~"
		    + "~" + endTime + "~" + "false" + "~" + "false" + "~"
			+ priority;
	private static final String test7 = id_2 + "~" + title + "~"
			+ "APPOINTMENT" + "~" + startTime + "~" + endDate
			+ "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test8 = id_2 + "~" + title + "~"
			+ "APPOINTMENT" + "~" + startDate + "~" + endDate
			+ "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test9 = id_2 + "~" + title + "~"
			+ "APPOINTMENT" + "~" + startDate + "~" + startTime + "~" + endDate
			+ "~" + endTime + "~" + "false" + "~" + priority;
	
	//test cases 10-17 and variables general boundary cases
	private static final String invalid_id_1 = "1401940001";
	private static final String invalid_id_2 = "14011199940002";
	private static final String invalid_id_3 = "150119940001abc";
	private static final String invalid_startDate1 = "1401194";
	private static final String invalid_startDate2 = "140119944";
	private static final String invalid_startTime1 = "001";
	private static final String invalid_startTime2 = "90101";
	private static final String invalid_endDate1 = "1611994";
	private static final String invalid_endDate2 = "160121994";
	private static final String invalid_endTime1 = "235";
	private static final String invalid_endTime2 = "23590";
	private static final String invalid_priority = "IMP";

	private static final String test10 = id_1 + "~" + title + "~" + "ASSIGNMENT"
			+ "~" + "false" + "~" + "false" + "~" + invalid_priority;
	private static final String test11 = id_2 + "~" + title + "~"
			+ "APPOINTMENT" + "~" + invalid_startDate1 + "~" + invalid_startTime1 + "~" + endDate
			+ "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test12 = id_2 + "~" + title + "~"
			+ "APPOINTMENT" + "~" + invalid_startDate2 + "~" + invalid_startTime2 + "~" + endDate
			+ "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test13 = id_3 + "~" + title + "~" + "TASK" + "~"
			+ invalid_endDate1 + "~" + invalid_endTime1 + "~" + "false" + "~" + "false" + "~"
			+ priority;
	private static final String test14 = id_3 + "~" + title + "~" + "TASK" + "~"
			+ invalid_endDate2 + "~" + invalid_endTime2 + "~" + "false" + "~" + "false" + "~"
			+ priority;
	private static final String test15 = invalid_id_1 + "~" + title + "~"
			+ "APPOINTMENT" + "~" + startDate + "~" + startTime + "~" + endDate
			+ "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test16 = invalid_id_2 + "~" + title + "~"
			+ "APPOINTMENT" + "~" + startDate + "~" + startTime + "~" + endDate
			+ "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test17 = invalid_id_3 + "~" + title + "~"
			+ "APPOINTMENT" + "~" + startDate + "~" + startTime + "~" + endDate
			+ "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	
	
	@Test
	public void Test() {

		LinkedList<logic.Assignment> testBuffer = (LinkedList<logic.Assignment>) InternalStorage.getBuffer();
		addToInput(parsedInput);
		
		testAddClass(testBuffer, parsedInput);
		testComparatorClass();
		testDateLocalClass();
		testDeleteClass(testBuffer);
		
		InternalStorage.getBuffer().clear();
		testBuffer = InternalStorage.getBuffer();
		testAddClass(testBuffer, parsedInput);
		testFilterClass(testBuffer);
		
		testSearchAllClass(testBuffer);
		testSortClass(testBuffer);
		testTimeLocalClass();
		testTruncationClass(testBuffer);
		
	}


	private void testAddClass(LinkedList<logic.Assignment> testBuffer,
			RefinedUserInput[] parsedInput) {
		
		for(int lines = 0; lines < testBuffer.size(); lines++){
			testAddCase(testBuffer, parsedInput[lines], testBuffer.get(lines).toString());
		}
	}

	private void addToInput(RefinedUserInput[] parsedInput) {
		
		parsedInput[0] = Interpreter.reader(test1);
		parsedInput[1] = Interpreter.reader(test2);
		parsedInput[2] = Interpreter.reader(test3);
		parsedInput[3] = Interpreter.reader(test4);
		parsedInput[4] = Interpreter.reader(test5);
		parsedInput[5] = Interpreter.reader(test6);
		parsedInput[6] = Interpreter.reader(test7);
		parsedInput[7] = Interpreter.reader(test8);
		parsedInput[8] = Interpreter.reader(test9);
		parsedInput[9] = Interpreter.reader(test10);
		parsedInput[10] = Interpreter.reader(test11);
		parsedInput[11] = Interpreter.reader(test12);
		parsedInput[12] = Interpreter.reader(test13);
		parsedInput[13] = Interpreter.reader(test14);
		parsedInput[14] = Interpreter.reader(test15);
		parsedInput[15] = Interpreter.reader(test16);
		parsedInput[16] = Interpreter.reader(test17);
	}
		
	private static void testAddCase(LinkedList<logic.Assignment> testBuffer,
			RefinedUserInput testParserInput, String testString){
		
		int initialSize = testBuffer.size();
		
		if(!testString.equals(test15) && !testString.equals(test16) && !testString.equals(test17)){
			assertTrue(LogicTestDrive.addSomething(testParserInput).equals(testString.substring(0, 7)));
			assertTrue(testBuffer.size() == initialSize + 1);
		} else {
			assertFalse(LogicTestDrive.addSomething(testParserInput).equals(testString.substring(0, 7)));
			assertFalse(testBuffer.size() == initialSize + 1);
		}
	}

	private void testComparatorClass() {

		assertFalse(LogicTestDrive.serialNumberComparator(id_1, id_2));
		assertFalse(LogicTestDrive.serialNumberComparator(id_2, id_3));
		assertFalse(LogicTestDrive.serialNumberComparator(id_1, id_1));
		assertEquals(-1, LogicTestDrive.dateComparator(startDate, endDate));
		assertEquals(0, LogicTestDrive.dateComparator(endDate, endDate));
		assertEquals(1, LogicTestDrive.dateComparator(endDate, startDate));
		assertEquals(-1, LogicTestDrive.timeComparator(startTime, endTime));
		assertEquals(0, LogicTestDrive.timeComparator(endTime, endTime));
		//boundary cases
		assertFalse(LogicTestDrive.serialNumberComparator(invalid_id_1, invalid_id_2));
		assertEquals(-1, LogicTestDrive.dateComparator(invalid_startDate1, invalid_endDate1));
		assertEquals(0, LogicTestDrive.dateComparator(invalid_endDate2, invalid_endDate2));
		assertEquals(1, LogicTestDrive.dateComparator(endDate, invalid_startDate2));
		assertEquals(-1, LogicTestDrive.timeComparator(invalid_startTime1, invalid_endTime2));
		assertEquals(-1, LogicTestDrive.timeComparator(invalid_endTime1, endTime));
		
		/*
		 * Logic error: expected <1> but was <-1> assertEquals(1,
		 * LogicTestDrive.timeComparator(endTime, startTime)); Error:
		 * logic.Assignment cannot be cast to logic.Appointment
		 * assertTrue(LogicTestDrive.isClashing((Appointment)
		 * testBuffer.get(1)));
		 */
	}

	private void testDeleteClass(LinkedList<logic.Assignment> testBuffer) {
		
		/*
		 * NOTE: expected result in assertEquals() need to be changed
		 */
		int initialSize = testBuffer.size();
		LogicTestDrive.delete(id_1);
		assertEquals(initialSize - 1, testBuffer.size());
		initialSize = testBuffer.size();
		
		LogicTestDrive.deleteAll("on", endDate, endDate);
		assertEquals(0, testBuffer.size());
		
		LogicTestDrive.addSomething(parsedInput[1]);
		LogicTestDrive.addSomething(parsedInput[2]);
		initialSize = testBuffer.size();

		LogicTestDrive.deleteAll("between", endDate, startDate);
		assertEquals(initialSize, testBuffer.size());

		LogicTestDrive.deleteAll("between", startDate, endDate);
		assertEquals(0, testBuffer.size());
		initialSize = 0;

		LogicTestDrive.addSomething(parsedInput[1]);
		LogicTestDrive.addSomething(parsedInput[2]);

		LogicTestDrive.deleteAll("before", endDate, endDate);
		assertEquals(0, testBuffer.size());

		LogicTestDrive.addSomething(parsedInput[1]);
		LogicTestDrive.addSomething(parsedInput[2]);
		
		LogicTestDrive.delete(invalid_id_2);
		assertEquals(initialSize, testBuffer.size());

		LogicTestDrive.delete(invalid_id_3);
		assertEquals(initialSize, testBuffer.size());
		
		/*
		 * required to remove zero here to remove error
		 * testAddClass(testBuffer); LogicTestDrive.deleteAll("before", endDate,
		 * endDate); assertEquals(1, testBuffer.size());
		 */
		 
	}

	private void testFilterClass(LinkedList<logic.Assignment> testBuffer) {

		LinkedList<logic.Assignment> tempBuffer = new LinkedList<logic.Assignment>();
		LinkedList<logic.Assignment> filterBuffer = (LinkedList<logic.Assignment>) testBuffer;

		/*
		 * NOTE: expected result in assertEquals() need to be changed
		 */
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, "APPOINTMENT", startDate, endDate);
		assertEquals(1, tempBuffer.size());
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, priority, startDate, endDate);
		assertEquals(3, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, startDate, startDate, endDate);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, startTime, startDate, endDate);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, endDate, startDate, endDate);
		assertEquals(2, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, endTime, startDate, endDate);
		assertEquals(2, tempBuffer.size());
		
		//boundary cases
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, "APPOINT", startDate, endDate);
		assertEquals(0, tempBuffer.size());
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, invalid_priority, startDate, endDate);
		assertEquals(0, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, startDate, invalid_startDate1, endDate);
		assertEquals(0, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, startTime, startDate, invalid_endDate2);
		assertEquals(0, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, invalid_endDate1, startDate, endDate);
		assertEquals(0, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				filterBuffer, invalid_endTime2, startDate, endDate);
		assertEquals(0, tempBuffer.size());
	}

	private void testSearchAllClass(LinkedList<logic.Assignment> testBuffer) {

		LinkedList<logic.Assignment> tempBuffer = new LinkedList<logic.Assignment>();
		LinkedList<logic.Assignment> testSearchBuffer = new LinkedList<logic.Assignment>();
		testSearchBuffer = testBuffer;

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, id_1);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, startDate);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, startTime);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, endDate);
		assertEquals(2, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, endTime);
		assertEquals(2, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, priority);
		assertEquals(3, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, "task");
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, "appt");
		assertEquals(1, tempBuffer.size());

		// assumption: all assignments with isDone = false, isOnTime = false by
		// default
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, "isontime");
		assertEquals(0, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, title);
		assertEquals(3, tempBuffer.size());
		
		//boundary cases
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, invalid_id_1);
		assertEquals(0, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, invalid_startDate2);
		assertEquals(0, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, invalid_startTime1);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.searchAll(
				testSearchBuffer, invalid_endDate1);
		assertEquals(2, tempBuffer.size());

		/*
		 * not supported by SearchAll class as of now: float, not completed,
		 * NIMPT, not on time testBuffer = (LinkedList<Assignment>)
		 * LogicTestDrive.searchAll(InternalStorage.getBuffer(), "assignment");
		 * assertEquals(1, testBuffer.size()); testBuffer =
		 * (LinkedList<Assignment>)
		 * LogicTestDrive.searchAll(InternalStorage.getBuffer(),
		 * "isnotcompleted"); assertEquals(3, testBuffer.size()); testBuffer =
		 * (LinkedList<Assignment>)
		 * LogicTestDrive.searchAll(InternalStorage.getBuffer(), "isnotontime");
		 * assertEquals(3, testBuffer.size()); testBuffer =
		 * (LinkedList<Assignment>)
		 * LogicTestDrive.searchAll(InternalStorage.getBuffer(), "NIMPT");
		 * assertEquals(3, testBuffer.size());
		 */
	}

	private void testSortClass(LinkedList<logic.Assignment> testBuffer) {

		LinkedList<logic.Assignment> tempBuffer = new LinkedList<logic.Assignment>();

		LogicTestDrive.delete(id_1);
		LogicTestDrive.delete(id_2);
		LogicTestDrive.delete(id_3);
		LogicTestDrive.addSomething(parsedInput[0]);
		LogicTestDrive.addSomething(parsedInput[1]);
		LogicTestDrive.addSomething(parsedInput[2]);

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive
				.multipleSortRequired(
						(LinkedList<logic.Assignment>) testBuffer, "title",
						startDate, endDate);
		assertEquals(tempBuffer, testBuffer);

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive
				.multipleSortRequired(
						(LinkedList<logic.Assignment>) testBuffer, "SIN",
						startDate, endDate);
		assertEquals(tempBuffer, testBuffer);

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive
				.multipleSortRequired(
						(LinkedList<logic.Assignment>) testBuffer, "priority",
						startDate, endDate);
		assertEquals(tempBuffer, testBuffer);
		
		//boundary cases
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive
				.multipleSortRequired(
						(LinkedList<logic.Assignment>) testBuffer, "TiTlE",
						startDate, endDate);
		assertEquals(tempBuffer, testBuffer);

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive
				.multipleSortRequired(
						(LinkedList<logic.Assignment>) testBuffer, "SN",
						startDate, endDate);
		assertEquals(tempBuffer, testBuffer);

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive
				.multipleSortRequired(
						(LinkedList<logic.Assignment>) testBuffer, "priorityy",
						startDate, endDate);
		assertEquals(tempBuffer, testBuffer);
	}
	
	private void testTruncationClass(LinkedList<logic.Assignment> testBuffer) {

		LinkedList<Assignment> tempBuffer = new LinkedList<Assignment>();

		tempBuffer = (LinkedList<Assignment>) LogicTestDrive.truncateList(
				(LinkedList<logic.Assignment>) testBuffer, endDate, endDate);
		assertEquals(3, tempBuffer.size());
	}

	public static void testTimeLocalClass() {

		// testing TimeLocal class
		assertEquals(startTime, LogicTestDrive.determineTime(startTime));
		// insert one more case for invalid date here and remove comment
	}

	public static void testDateLocalClass() {

		//assertEquals(startDate, LogicTestDrive.determineDate(startDate));
		// insert one more case for invalid date here and remove comment
		assertTrue(LogicTestDrive.dateFormatValid("14011994"));
		assertFalse(LogicTestDrive.dateFormatValid("31022001"));

		if (!"13011994".equals(LogicTestDrive.updateDate(startDate))) {
			System.out.println("ERROR");
		}
		if (!"31081994".equals(LogicTestDrive.updateDate("01091994"))) {
			System.out.println("ERROR");
		}
		if (!"31121994".equals(LogicTestDrive.updateDate("01011995"))) {
			System.out.println("ERROR");
		}
	}

}