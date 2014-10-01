package junit;

import logic.Add;
import logic.Id;
import logic.Sort;

import org.junit.Test;


public class SparkMoVareTest {
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
	*/
	@Test
	public void test(){
	
		Add.addAssignment("020819940001", "Task1", 0, " ", " ", "03081994", "2359", false, null);
		Add.addAssignment("140120000001", "Task2", 0, " ", " ", "20012000", "2359", false, null);
		Add.addAssignment("140120000002", "Task3", 0, " ", " ", "20012000", "2359", false, null);
		Add.addAssignment("140919940001", "Appointment", 1, "14091994", "0800", "14091994", "0900", false, null);
		Add.addAssignment("010620140001", "AppointmentConference", 1, "03062014", "0800", "05062014", "1200", false, null);
		Add.addAssignment("010919940001", "tentativeTask", 2, " ", " ", "01091994", "1600", false, null);
		
		//Sort.sortClassify("tasks", "31012000", "01012000");
		
		System.out.println(Id.serialNumGen());
		
	}
}

