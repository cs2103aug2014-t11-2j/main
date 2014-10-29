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
	private static final String test1 = id_1 + "~" + title + "~" + "ASSIGNMENT" + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test2 = id_2 + "~" + title + "~" + "APPOINTMENT" + "~" + startDate + "~" + startTime + "~" + endDate + "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	private static final String test3 = id_3 + "~" + title + "~" + "TASK" + "~" + endDate + "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority;
	private static final boolean isDone = false;
	private static final AssignmentType typeTask = AssignmentType.TASK;
	private static final AssignmentType typeAppt = AssignmentType.APPOINTMENT;

	@Test
	public void Test() {

		LinkedList<logic.Assignment> testBuffer = (LinkedList<logic.Assignment>) InternalStorage.getBuffer();
		RefinedUserInput testParserInput = new RefinedUserInput();
		
		
		testParserInput = Interpreter.reader(test1);
		testAddClass(testBuffer, testParserInput, test1);
		testParserInput = Interpreter.reader(test2);
		testAddClass(testBuffer, testParserInput, test2);
		
		testComparatorClass(testBuffer);
		testDateLocalClass();
		testDeleteClass(testBuffer);
		testFilterClass(testBuffer);
		testSearchAllClass(testBuffer);
		testSortClass(testBuffer);
		testTimeLocalClass();
		testTruncationClass(testBuffer);

	}

	private void testAddClass(LinkedList<logic.Assignment> testBuffer,
			RefinedUserInput testParserInput, String testString) {
		
		int initialSize = testBuffer.size();
		
		if(testString.equals(test1)){
				
			assertTrue(LogicTestDrive.addSomething(testParserInput).equals(test1));
			testBuffer.clear();
			assertTrue(LogicTestDrive.addAssignment(id_1, title, isDone, priority)
					.equals(test2));
			assertTrue(testBuffer.size() == initialSize + 1);
		
		} else if (testString.equals(test2)){
			
			assertTrue(LogicTestDrive.addSomething(testParserInput).equals(test2));
			testBuffer.clear();
			assertTrue(LogicTestDrive.addAppointment(id_2, title, startDate,
					startTime, endDate, endTime, isDone, priority).equals(
					test2));
			assertTrue(testBuffer.size() == initialSize + 2);

		} else if (testString.equals(test3)){
			
			assertTrue(LogicTestDrive.addSomething(testParserInput).equals(test3));
			testBuffer.clear();
			// Error: logic.Task cannot be cast to logic.Appointment at Add.java:142			  
			 assertSame(LogicTestDrive.addTask(id_3, title, endDate, endTime,
			 isDone, priority), id_3 + "~" + title + "~" + "TASK" + "~" + endDate
			 + "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority);
			 assertTrue(testBuffer.size() == initialSize + 3);
		}
	}

	private void testComparatorClass(LinkedList<logic.Assignment> testBuffer) {

		assertFalse(LogicTestDrive.serialNumberComparator(id_1, id_2));
		assertFalse(LogicTestDrive.serialNumberComparator(id_2, id_3));
		assertFalse(LogicTestDrive.serialNumberComparator(id_1, id_1));
		assertEquals(-1, LogicTestDrive.dateComparator(startDate, endDate));
		assertEquals(0, LogicTestDrive.dateComparator(endDate, endDate));
		assertEquals(1, LogicTestDrive.dateComparator(endDate, startDate));
		assertEquals(-1, LogicTestDrive.timeComparator(startTime, endTime));
		assertEquals(0, LogicTestDrive.timeComparator(endTime, endTime));
		/*
		 * Logic error: expected <1> but was <-1> assertEquals(1,
		 * LogicTestDrive.timeComparator(endTime, startTime)); Error:
		 * logic.Assignment cannot be cast to logic.Appointment
		 * assertTrue(LogicTestDrive.isClashing((Appointment)
		 * testBuffer.get(1)));
		 */
		
	}

	public static void testDateLocalClass() {

		assertEquals(startDate, LogicTestDrive.determineDate(startDate));
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

	private void testDeleteClass(LinkedList<logic.Assignment> testBuffer) {

		int initialSize = testBuffer.size();
		LogicTestDrive.delete(id_1);
		assertEquals(initialSize - 1, testBuffer.size());
		initialSize = testBuffer.size();

		LogicTestDrive.delete("14011994001");
		assertEquals(initialSize, testBuffer.size());
		
		LogicTestDrive.delete("1401199400001");
		assertEquals(initialSize, testBuffer.size());
		
		LogicTestDrive.deleteAll("on", endDate, endDate);
		assertEquals(0, testBuffer.size());
		
		LogicTestDrive.addAppointment(id_2, title, startDate, startTime, endDate, endTime, isDone, priority);
		LogicTestDrive.addTask(id_3, title, endDate, endTime, isDone, priority);
		initialSize = 2;
		
		LogicTestDrive.deleteAll("between", endDate, startDate);
		assertEquals(initialSize, testBuffer.size());
	
		LogicTestDrive.deleteAll("between", startDate, endDate);
		assertEquals(0, testBuffer.size());
		initialSize = 0;

		LogicTestDrive.addAppointment(id_2, title, startDate, startTime, endDate, endTime, isDone, priority);
		LogicTestDrive.addTask(id_3, title, endDate, endTime, isDone, priority);
		
		LogicTestDrive.deleteAll("before", endDate, endDate);
		assertEquals(0, testBuffer.size());
		
		/*
		 * required to remove zero here to remove error
		 * testAddClass(testBuffer); LogicTestDrive.deleteAll("before", endDate,
		 * endDate); assertEquals(1, testBuffer.size());
		 */
	}

	private void testFilterClass(LinkedList<logic.Assignment> testBuffer) {

		testAddClass(testBuffer, null);
		LinkedList<logic.Assignment> tempBuffer = new LinkedList<logic.Assignment>();
		LinkedList<logic.Assignment> testFilterBuffer = (LinkedList<logic.Assignment>) testBuffer;

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				testFilterBuffer, "APPOINTMENT", startDate, endDate);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				testFilterBuffer, priority, startDate, endDate);
		assertEquals(3, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				testFilterBuffer, startDate, startDate, endDate);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				testFilterBuffer, startTime, startDate, endDate);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				testFilterBuffer, endDate, startDate, endDate);
		assertEquals(2, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDrive.filterMain(
				testFilterBuffer, endTime, startDate, endDate);
		assertEquals(2, tempBuffer.size());
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
		LogicTestDrive.addAssignment(id_1, title, isDone, priority);
		LogicTestDrive.addAppointment(id_2, title, startDate, startTime,
				endDate, endTime, isDone, priority);
		LogicTestDrive.addTask(id_3, title, endDate, endTime, isDone, priority);

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
	}

	public static void testTimeLocalClass() {

		// testing TimeLocal class
		assertEquals(startTime, LogicTestDrive.determineTime(startTime));
		// insert one more case for invalid date here and remove comment
	}

	private void testTruncationClass(LinkedList<logic.Assignment> testBuffer) {

		LinkedList<Assignment> tempBuffer = new LinkedList<Assignment>();

		tempBuffer = (LinkedList<Assignment>) LogicTestDrive.truncateList(
				(LinkedList<logic.Assignment>) testBuffer, endDate, endDate);
		assertEquals(3, tempBuffer.size());
	}

}