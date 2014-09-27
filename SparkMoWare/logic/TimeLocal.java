package logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * This is to check time format and its validity
 * Prompt the user until the user input the correct format <hhmm>
 */
public class TimeLocal {
	
	public static String determineTime(String inputTime) {
		
		while(!timeFormatValid(inputTime)){
			
			Message.printToUser(Message.INVALID_FORMAT);
			Message.printToUser(String.format(Message.FORMAT_PROMPT, "time"));
			inputTime = SparkMoVare.scanner.nextLine();			
		}
		return inputTime;
	}

	public static boolean timeFormatValid(String time) {
		
		boolean timeValidity = true;
		
		if(time.length() != 4) {
			timeValidity = false;
		} else if(!time.matches("[0-9]+")) {
			timeValidity = false;
		} else if(!timeExists(Integer.parseInt(time))) {
			timeValidity = false;
		}
		return timeValidity;
	}
	
	public static boolean timeExists(int time) {
		
		boolean timeExist = false;
		
		int min = time % 100;
		int hr = time / 100;
		
		if(min <= 59 && min >= 0) {
			timeExist = true;
		} else if(hr <= 23 && hr >= 0) {
			timeExist = true;
		}
		return timeExist;
	}


	protected static String timeString(){
		DateFormat dateFormat = new SimpleDateFormat("HHmm");
		Date todayDate = new GregorianCalendar().getTime();
		
		return dateFormat.format(todayDate);
	}
}
