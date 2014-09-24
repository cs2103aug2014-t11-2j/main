package junit;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import logic.SparkMoVare;
import logic.Assignment;

import org.junit.Test;

public class SparkMoVareTest {

	@Test
	public void test() {
		// add status
		assertEquals(SparkMoVare.addTask("666","testing adding",1,"010101","1010",
				"101010","2359",false, new Vector<String>() ),"666~testing adding~1~010101~1010~101010~2359~false");
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
		
		System.out.println(SparkMoVare.serialNumGen());
	}
	
	

}
