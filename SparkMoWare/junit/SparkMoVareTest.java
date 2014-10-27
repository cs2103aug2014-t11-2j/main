package junit;

import static org.junit.Assert.*;

import java.util.LinkedList;

import parser.EnumGroup.CommandType;
import parser.EnumGroup.AssignmentType;
import parser.RefinedUserInput;
import logic.Add;
import logic.Assignment;
import logic.Comparator;
import logic.Filter;
import logic.InternalStorage;
import logic.Print;
import logic.Edit;
import logic.Sort;

import org.junit.Test;

public class SparkMoVareTest<Assignment> {

	private static final String command = "add";
	private static final String id_1 = "140119940001";
	private static final String id_2 = "140119940002";
	private static final String id_3 = "150119940001";
	private static final String title = "JUnit testing";
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

		LinkedList<Assignment> testBuffer = new LinkedList<Assignment>();
		testBuffer = (LinkedList<Assignment>) InternalStorage.getBuffer();
		int testSize = testBuffer.size();
		
		//testing Add class
		assertSame(Add.addAssignment(id_1, title, isDone, priority), id_1 + "~" + title + "~" + "Assignment" + "~" + "false" + "~" + "false" + "~" + priority);
		assert(InternalStorage.getBuffer().size() == testSize+1);
		testSize++;
		
		assertSame(Add.addAppointment(id_1, title, startDate, startTime, endDate, endTime, isDone, priority), id_1 + "~" + title + "~" + "APPT" + "~" + startDate + "~" + startTime + "~" + endDate + "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority);
		assert(InternalStorage.getBuffer().size() == testSize+1);
		testSize++;
		
		assertSame(Add.addTask(id_1, title, endDate, endTime, isDone, priority), id_1 + "~" + title + "~" + "TASK" + "~" + endDate + "~" + endTime + "~" + "false" + "~" + "false" + "~" + priority);
		assert(InternalStorage.getBuffer().size() == testSize+1);
		testSize++;
		
		//testing Comparator class
		assertTrue(Comparator.serialNumberComparator(id_1, id_2));
		assertFalse(Comparator.serialNumberComparator(id_2, id_3));
		assertTrue(Comparator.serialNumberComparator(id_1, id_1));
		assertEquals(1, Comparator.dateComparator(startDate, endDate));
		assertEquals(0, Comparator.dateComparator(endDate, endDate));
		assertEquals(-1, Comparator.dateComparator(endDate, startDate));
		assertEquals(1, Comparator.timeComparator(startTime, endTime));
		assertEquals(0, Comparator.timeComparator(endTime, endTime));
		assertEquals(-1, Comparator.timeComparator(endTime, startTime));
		//check for isClashing
		
		//testing Delete class
		//assertTrue(DateLocal.determineDate());
		
		
		
		
	}
}
