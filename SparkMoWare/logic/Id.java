package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
 * only work till Sn 9999 otherwise the year will be corrupted
 * Malfunctions if latestSerialNumber is in the future (in theory should not happen? unless system clock change)
 */
public class Id {
	
	protected static Scanner scanner = new Scanner(System.in);
	
	public static String serialNumGen() {
		
		String serialNum = "";
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		//get current date time with Date()
		Date todayDate = new Date();
		
		serialNum += dateFormat.format(todayDate);
		serialNum += "0001";
		
		if(SparkMoVare.latestSerialNumber.isEmpty() || Comparator.serialNumberComparator(serialNum,SparkMoVare.latestSerialNumber)) {
			return serialNum;
		} else {
			Long Sn = Long.parseLong(SparkMoVare.latestSerialNumber);
			System.out.println("test" + Sn.toString()); // testing
			Sn++;
			
			return Long.toString(Sn);
		}
	}
	
	protected static String determineID(String id){
		
		while(!_IDFormatValid(id)) {//will continuously prompt user for correct ID format currently no way to exit
			//FATAL ERROR: if user enters edit command while file/program is empty, this prompt will run forever.
			
			Message.printToUser(Message.INVALID_FORMAT);
			Message.printToUser(String.format(Message.FORMAT_PROMPT, "ID"));		
			id = scanner.nextLine();			
		}
		return id;
	}

	public static boolean _IDFormatValid(String id) {
		
		if(id.length() != 10) {
			return false;
		} 
		/*else if(id.equalsIgnoreCase("exit")){ //Method for dealing with fatal error
			return true;
		}*/
		else if(id.matches(".*\\D+.*")) {
			return false;
		} else if(!_IDExists(Integer.parseInt(id))) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean _IDExists(int id) {
		
		if(DateLocal.dateExists(id /10000)) {
			return true;
		} else{
			return false;
		}
	}



}
