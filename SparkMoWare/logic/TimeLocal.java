package logic;

import java.util.Scanner;

public class TimeLocal {
	
	protected static Scanner scanner = new Scanner(System.in);
	
	public static String determineTime(String inputTime) {
		
		while(!timeFormatValid(inputTime)){//will continuously prompt user for correct time format currently no way to exit
			
			Message.printToUser(Message.INVALID_FORMAT);
			Message.printToUser(String.format(Message.FORMAT_PROMPT, "time"));
			inputTime = scanner.nextLine();			
		}
		return inputTime;
	}

	public static boolean timeFormatValid(String time) {

		if(time.length() != 4) {
			return false;
		} else if(time.matches(".*\\D+.*")) {
			return false;
		} else if(!timeExists(Integer.parseInt(time))) { //hex or decimal format should not matter
			return false;
		}
		return true;
	}
	
	// Time format is hhmm
	public static boolean timeExists(int time) {
		
		int min = time % 100;
		int hr = time / 100;

		if(min > 59) {
			return false;
		} else if(hr > 23) {
			return false;
		}
		return true;
	}


}
