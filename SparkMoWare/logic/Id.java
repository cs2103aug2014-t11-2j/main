package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Id {

	private static final int SERIAL_LENGTH = 12;
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

	public static String determineID(String id){

		while(!_IDFormatValid(id)) {

			Print.printToUser(Message.INVALID_FORMAT);
			Print.printToUser(String.format(Message.FORMAT_PROMPT, "ID"));		
			id = InternalStorage.getScanner().nextLine();			
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
