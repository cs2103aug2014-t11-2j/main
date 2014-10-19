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

	private static final int SERIAL_LENGTH = 12;
	private static Scanner scanner = new Scanner(System.in);
	protected static String latestSerialNumber = "";

	public static void setLatestSerialNumber(String newSn) {
		latestSerialNumber = newSn;
	}

	public static String getLatestSerialNumber(){
		return latestSerialNumber;
	}

	public static String serialNumGen() {

		String serialNum = "";
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

		//get current date time with Date()
		Date todayDate = new Date();

		serialNum += dateFormat.format(todayDate);
		serialNum += "0001";

		if(latestSerialNumber.isEmpty() || Comparator.serialNumberComparator(serialNum,latestSerialNumber)) {
			setLatestSerialNumber(serialNum);
			return serialNum;
		} else {
			return newSerialNum(todayDate);
		}
	}

	private static String newSerialNum(Date todayDate) {

		DateFormat yearFormat = new SimpleDateFormat("yyyy");
		DateFormat dayMonthFormat = new SimpleDateFormat("ddMM");

		String newSerialNum = dayMonthFormat.format(todayDate);
		String currentSN = yearFormat.format(todayDate) + getLatestSerialNumber().substring(8);
		Long Sn = Long.parseLong (currentSN);
		Sn++;
		newSerialNum += Long.toString(Sn);
		setLatestSerialNumber(newSerialNum);

		return newSerialNum;
	}

	/* 
	 * will continuously prompt user for correct ID format currently no way to exit
	 * FATAL ERROR: if user enters edit command while file/program is empty, this prompt will run forever.
	 */
	protected static String determineID(String id){

		while(!_IDFormatValid(id)) {

			Print.printToUser(Message.INVALID_FORMAT);
			Print.printToUser(String.format(Message.FORMAT_PROMPT, "ID"));		
			id = scanner.nextLine();			
		}
		return id;
	}

	public static boolean _IDFormatValid(String id) {

		boolean idFormatValidity = true;

		if(id.length() != SERIAL_LENGTH) {
			idFormatValidity = false;
		} 
		/* else if(id.equalsIgnoreCase("exit")){ //Method for dealing with fatal error
			return true;
		}*/
		else if(id.matches("\\d+")) {
			
			if(!_IDExists(id)) {
				return false;
			}
		}
		return idFormatValidity;
	}

	public static boolean _IDExists(String id) {
		
		if(DateLocal.dateFormatValid(id.substring(0, 8))) {
			return true;
		} else{
			return false;
		}
	}
	
	protected static String removeFrontZero(String input) {
		while (input.length() > 0 && input.charAt(0) == '0') {
			input = input.substring(1);
		}
		return input;
	}
}
