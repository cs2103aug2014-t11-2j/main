package junit;

import static org.junit.Assert.*;

import java.util.LinkedList;

import parser.EnumGroup.CommandType;
import parser.EnumGroup.AssignmentType;
import parser.RefinedUserInput;
import logic.Add;
import logic.Appointment;
import logic.Assignment;
import logic.Comparator;
import logic.DateLocal;
import logic.Delete;
import logic.Filter;
import logic.InternalStorage;
import logic.LogicTestDriver;
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

	@Test
	public void Test() {

		LinkedList<Assignment> testBuffer = (LinkedList<Assignment>) InternalStorage.getBuffer();
		// LinkedList<Assignment> tempBuffer = (LinkedList<Assignment>)
		// InternalStorage.getBuffer()

		testAddClass(testBuffer);
		testComparatorClass(testBuffer);
		testDateLocalClass();
		testDeleteClass(testBuffer);
		testFilterClass(testBuffer);
		testSearchAllClass(testBuffer);
		testSortClass(testBuffer);
		testTimeLocalClass();
		testTruncationClass(testBuffer);

	}

	public void testAddClass(LinkedList<Assignment> testBuffer) {

		int initialSize = testBuffer.size();

		assertSame(
				LogicTestDriver.addAssignment(id_1, title, isDone, priority),
				id_1 + "~" + title + "~" + "Assignment" + "~" + "false" + "~"
						+ "false" + "~" + priority);
		assertTrue(testBuffer.size() == initialSize + 1);

		assertSame(LogicTestDriver.addAppointment(id_2, title, startDate,
				startTime, endDate, endTime, isDone, priority), id_2 + "~"
				+ title + "~" + "APPT" + "~" + startDate + "~" + startTime
				+ "~" + endDate + "~" + endTime + "~" + "false" + "~" + "false"
				+ "~" + priority);
		assertTrue(testBuffer.size() == initialSize + 1);

		assertSame(LogicTestDriver.addTask(id_3, title, endDate, endTime,
				isDone, priority), id_3 + "~" + title + "~" + "TASK" + "~"
				+ endDate + "~" + endTime + "~" + "false" + "~" + "false" + "~"
				+ priority);
		assertTrue(testBuffer.size() == initialSize + 1);
	}

	private void testComparatorClass(LinkedList<Assignment> testBuffer) {

		assertFalse(LogicTestDriver.serialNumberComparator(id_1, id_2));
		assertFalse(LogicTestDriver.serialNumberComparator(id_2, id_3));
		assertTrue(LogicTestDriver.serialNumberComparator(id_1, id_1));
		assertEquals(1, LogicTestDriver.dateComparator(startDate, endDate));
		assertEquals(0, LogicTestDriver.dateComparator(endDate, endDate));
		assertEquals(-1, LogicTestDriver.dateComparator(endDate, startDate));
		assertEquals(1, LogicTestDriver.timeComparator(startTime, endTime));
		assertEquals(0, LogicTestDriver.timeComparator(endTime, endTime));
		assertEquals(-1, LogicTestDriver.timeComparator(endTime, startTime));
		assertTrue(LogicTestDriver.isClashing((Appointment) testBuffer.get(1)));
	}

	public static void testDateLocalClass() {

		assertEquals(startDate, LogicTestDriver.determineDate(startDate));
		// insert one more case for invalid date here and remove comment
		assertTrue(LogicTestDriver.dateFormatValid("14011994"));
		assertFalse(LogicTestDriver.dateFormatValid("31022001"));
		assertEquals("15011994", LogicTestDriver.updateDate(startDate));
		assertEquals("01091994", LogicTestDriver.updateDate("31081994"));
		assertEquals("01011995", LogicTestDriver.updateDate("31121994"));
	}

	private void testDeleteClass(LinkedList<Assignment> testBuffer) {

		int initialSize = testBuffer.size();
		LogicTestDriver.delete(id_1);
		assertEquals(initialSize - 1, testBuffer.size());
		initialSize = testBuffer.size();

		LogicTestDriver.deleteAll("on", endDate, endDate);
		assertEquals(initialSize - 2, testBuffer.size());
		initialSize = testBuffer.size();

		testAddClass(testBuffer);
		LogicTestDriver.deleteAll("before", endDate, endDate);
		assertEquals(1, testBuffer.size());
	}

	private void testFilterClass(LinkedList<Assignment> testBuffer) {

		LinkedList<logic.Assignment> tempBuffer = new LinkedList<logic.Assignment>();
		LinkedList<logic.Assignment> testFilterBuffer = (LinkedList<logic.Assignment>) testBuffer;

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.filterMain(
				testFilterBuffer, "appoinment", startDate, endDate);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.filterMain(
				testFilterBuffer, priority, startDate, endDate);
		assertEquals(3, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.filterMain(
				testFilterBuffer, startDate, startDate, endDate);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.filterMain(
				testFilterBuffer, startTime, startDate, endDate);
		assertEquals(1, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.filterMain(
				testFilterBuffer, endDate, startDate, endDate);
		assertEquals(2, tempBuffer.size());

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.filterMain(
				testFilterBuffer, endTime, startDate, endDate);
		assertEquals(2, tempBuffer.size());
	}

	private void testSearchAllClass(LinkedList<Assignment> testBuffer) {

		LinkedList<logic.Assignment> tempBuffer = new LinkedList<logic.Assignment>();
		LinkedList<logic.Assignment> testSearchBuffer = (LinkedList<logic.Assignment>) testBuffer;

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, id_1);
		assertEquals(1, tempBuffer.size());
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, startDate);
		assertEquals(1, tempBuffer.size());
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, startTime);
		assertEquals(1, tempBuffer.size());
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, endDate);
		assertEquals(2, tempBuffer.size());
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, endTime);
		assertEquals(2, tempBuffer.size());
	
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, priority);
		assertEquals(3, tempBuffer.size());
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, "task");
		assertEquals(1, tempBuffer.size());
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, "appt");
		assertEquals(1, tempBuffer.size());
		
		// assumption: all assignments with isDone = false, isOnTime = false by default
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, "isontime");
		assertEquals(0, tempBuffer.size());
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.searchAll(testSearchBuffer, title);
		assertEquals(3, tempBuffer.size());

		/*
		 * not supported by SearchAll class as of now: float, not completed,
		 * NIMPT, not on time testBuffer = (LinkedList<Assignment>)
		 * LogicTestDriver.searchAll(InternalStorage.getBuffer(), "assignment");
		 * assertEquals(1, testBuffer.size()); testBuffer =
		 * (LinkedList<Assignment>)
		 * LogicTestDriver.searchAll(InternalStorage.getBuffer(),
		 * "isnotcompleted"); assertEquals(3, testBuffer.size()); testBuffer =
		 * (LinkedList<Assignment>)
		 * LogicTestDriver.searchAll(InternalStorage.getBuffer(),
		 * "isnotontime"); assertEquals(3, testBuffer.size()); testBuffer =
		 * (LinkedList<Assignment>)
		 * LogicTestDriver.searchAll(InternalStorage.getBuffer(), "NIMPT");
		 * assertEquals(3, testBuffer.size());
		 */
	}

	private void testSortClass(LinkedList<Assignment> testBuffer) {

		LinkedList<logic.Assignment> tempBuffer = new LinkedList<logic.Assignment>();
		
		LogicTestDriver.delete(id_1);
		LogicTestDriver.delete(id_2);
		LogicTestDriver.delete(id_3);
		LogicTestDriver.addAssignment(id_1, title, isDone, priority);
		LogicTestDriver.addAppointment(id_2, title, startDate, startTime, endDate, endTime, isDone, priority);
		LogicTestDriver.addTask(id_3, title, endDate, endTime, isDone, priority);

		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.multipleSortRequired((LinkedList<logic.Assignment>) testBuffer, "title",startDate, endDate);
		assertEquals(tempBuffer, testBuffer);
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.multipleSortRequired((LinkedList<logic.Assignment>) testBuffer, "SIN",startDate, endDate);
		assertEquals(tempBuffer, testBuffer);
		
		tempBuffer = (LinkedList<logic.Assignment>) LogicTestDriver.multipleSortRequired((LinkedList<logic.Assignment>) testBuffer, "priority",startDate, endDate);
		assertEquals(tempBuffer, testBuffer);	
	}

	public static void testTimeLocalClass() {

		// testing TimeLocal class
		assertEquals(startTime, LogicTestDriver.determineTime(startTime));
		// insert one more case for invalid date here and remove comment
	}

	private void testTruncationClass(LinkedList<Assignment> testBuffer) {

		LinkedList<Assignment> tempBuffer = new LinkedList<Assignment>();
		
		tempBuffer = (LinkedList<Assignment>) LogicTestDriver.truncateList((LinkedList<logic.Assignment>) testBuffer, endDate, endDate);
		assertEquals(3, tempBuffer.size());
	}

}
