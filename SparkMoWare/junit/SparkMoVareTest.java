package junit;

import java.util.LinkedList;

import logic.Add;
import logic.Assignment;
import logic.BufferValidity;
import logic.Comparator;
import logic.Filter;
import logic.InternalStorage;
import logic.Print;
import logic.RefineInput;
import logic.Edit;
import logic.Sort;
import logic.Assignment.assignmentType;

import org.junit.Test;

public class SparkMoVareTest<Assignment> {
	/*
	@Test
	public void test() {
		// add status
		//assertEquals(SparkMoVare.addTask("666","testing adding",1,"010101","1010",
		//		"101010","2359",false, new Vector<String>() ),"666~testing adding~1~010101~1010~101010~2359~false");
		//saving
		//SparkMoVare.display();
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		//get current date time with Date()
		Date todayDate = new Date();
		System.out.println(dateFormat.format(todayDate));
		String idA = "250920160001";
		String idB = "250920150001";
		SparkMoVare.setLatestSerialNumber("250920149999");
		System.out.println(idA.substring(8));
		if (Integer.parseInt(idA.substring(4, 8))>Integer.parseInt(idB.substring(4, 8))){
			System.out.println("True");
		}
		else
			System.out.println("False");
		
		System.out.println("ddMMyyyyhhmm".substring(0,8));
		System.out.println("ddMMyyyyhhmm".substring(8));
	}

	@Test
	public void test(){
	
		Add.addAssignment("020819940001", "Task1", 0, " ", " ", "03081994", "2359", false, "important", null);
		Add.addAssignment("140120000001", "Task2", 0, " ", " ", "20012000", "2359", false, null, null);
		Add.addAssignment("140120000002", "Task3", 0, " ", " ", "20012000", "2359", false, "important", null);
		Add.addAssignment("140919940001", "Appointment", 1, "14091994", "0800", "14091994", "0900", false, null, null);
		Add.addAssignment("010620140001", "AppointmentConference", 1, "03062014", "0800", "05062014", "1200", false, null, null);
		Add.addAssignment("010919940001", "tentativeTask", 2, " ", " ", "01091994", "1600", false, null, null);
		
//		System.out.println(Comparator.priorityComparator("important",null));
		
		String[] refinedUserInput = new String[10];
		refinedUserInput[8] = "edit";
		refinedUserInput[1] = "140120000001";
		refinedUserInput[8] = "priority";
		refinedUserInput[9] = "important";
		String edited = Edit.editAssignment(refinedUserInput);
		System.out.println(edited);
		Print.printList(Filter.filter("important"));
		Print.printList(Sort.insertionSortPriority());
	}
*/
	@Test
	public void test(){
		
		LinkedList<Assignment> testBuffer = new LinkedList<Assignment>();
		Assignment testAssignment = Assignment.Assignment(123456, "test", false, false, "important",
				Assignment.assignmentType.APPOINTMENT);
		testBuffer.add(testAssignment);
		BufferValidity.validType(testBuffer,testAssignment);
	}

}

