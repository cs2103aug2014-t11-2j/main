package parser;

import java.util.regex.Matcher;

public class InputIsAdd {

	protected static String[] refineInput(String userInput) {
		
		if(checkIfAppt(userInput)){
			UserInputAddAppointment inputAddAppointment =  new UserInputAddAppointment(getTitle(userInput),
					getStartDate(userInput), getStartTime(userInput), getEndDate(userInput), getEndTime(userInput));
			
			return inputAddAppointment.toString().split("~");
			
		} else {
			UserInputAddTask inputAddTask =  new UserInputAddTask(getTitle(userInput),
					getEndDate(userInput), getEndTime(userInput));
		}
		
		return null; //stub
	}

	//Checks if there are 2 date inputs
	protected static boolean checkIfAppt(String userInput) {		
		String[] temp = ParserPatternLocal.datePattern.split(userInput);
		
		if(temp.length == 2) {
			return true;
		} else {
			return false;
			}
		}

	
	protected static String getTitle(String userInput) {
		
		return null; //stub
	}
	
	//code works fine for single date input
	//ASSUMPTION: in appointment case, input format is start date followed by end date
	protected static String getStartDate(String userInput) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
		String startDate = new String();
		
		if(dateMatcher.find()) {
			startDate = dateMatcher.group();
		}
		return startDate;
	}
	
	protected static String getEndDate(String userInput) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
		String endDate = new String();
		
		if(checkIfAppt(userInput)) {
			userInput = dateMatcher.replaceFirst("");
		}
		
		endDate = getStartDate(userInput);
		
		if(!dateMatcher.find()) {
			endDate = ParserDateLocal.dateString();
		}
		
		return endDate;
	}
	
	protected static String getStartTime(String userInput) {
		//ParserPatternLocal.datePattern.matcher(userInput);
			
		 
		
		return null;//stub
	}
	
	protected static String getEndTime(String userInput) {
		//ParserPatternLocal.datePattern.matcher(userInput);
			
		//stub
		//if statement checks if user input has end time
		//if not, return 2359
		 if(true) {
			 return ParserTimeLocal.defaultEndTime;
		 }
		
		return null;//stub
	}
}
