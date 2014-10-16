package parser;

import java.util.regex.Matcher;

import parser.EnumGroup.AssignmentType;
import parser.EnumGroup.CommandType;

public class InputIsAdd {

	protected static RefinedUserInput refineInput(String userInput) {
		
		if(checkIfAppt(userInput)){
			RefinedUserInput inputAddAppointment =  new RefinedUserInput(
					CommandType.ADD, null,
					extractTitle(userInput), extractStartDate(userInput),
					extractStartTime(userInput), extractEndDate(userInput),
					extractEndTime(userInput), AssignmentType.APPOINTMENT,
					null);
			
			return inputAddAppointment;
			
		} else {
			RefinedUserInput inputAddTask =  new RefinedUserInput(
					CommandType.ADD, null,
					extractTitle(userInput), null,
					null, extractEndDate(userInput),
					extractEndTime(userInput), AssignmentType.TASK,
					null);
			return inputAddTask;
		}
	}

	//Checks if there are 2 date inputs
	protected static boolean checkIfAppt(String userInput) {		
		String[] temp = ParserPatternLocal.datePattern.split(userInput);
		
		if(temp.length >= 2) {
			return true;
		} else {
			return false;
			}
		}

	protected static String extractTitle(String userInput) {
		
		userInput = replaceAllDate(userInput);
		userInput = replaceAllTime(userInput);
		userInput = userInput.replace("add", "");
		userInput.trim();
		String[] temp = userInput.split(" ");
		
		if(temp.length == 0) {
			return null;
		}
		
		return refineString(temp);
	}
	
	protected static String replaceAllDate(String input) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(input);
		
		while(dateMatcher.find()) {
			input = dateMatcher.replaceFirst("");
			dateMatcher.reset(input);
		}
		
		return input;
	}
	
	//DESIGN FLAW: method will replace any and all 4 number input
	//for eg. the year part of date or even in a long sequence of numbers
	protected static String replaceAllTime(String input) {
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(input);
		
		while(timeMatcher.find()) {
			input = timeMatcher.replaceFirst("");
			timeMatcher.reset(input);
		}
		
		return input;
	}

	protected static String refineString(String [] unrefinedString) {
		int length = unrefinedString.length;
		String refinedString = new String();
		
		for(int counter = 0; counter<length; counter ++) {
			unrefinedString[counter] = unrefinedString[counter].trim();
			refinedString = refinedString.concat(unrefinedString[counter]);
			
			if(!unrefinedString[counter].isEmpty()){
				refinedString = refinedString.concat(" ");
			}
		}
		return refinedString.trim();
	}
	
	//code works fine for single date input
	//ASSUMPTION: in appointment case, input format is start date followed by end date
	protected static String extractStartDate(String userInput) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
		String startDate = new String();
		
		if(dateMatcher.find()) {
			startDate = dateMatcher.group();
		}
		return startDate;
	}
	
	protected static String extractEndDate(String userInput) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
		String endDate = new String();
		
		if(checkIfAppt(userInput)) {
			userInput = dateMatcher.replaceFirst("");
		}
		
		endDate = extractStartDate(userInput);
		
		if(!dateMatcher.find()) {
			endDate = ParserDateLocal.dateString();
		}
		
		return endDate;
	}
	
	protected static String extractStartTime(String userInput) {
		userInput = replaceAllDate(userInput);
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(userInput);
		String startTime = new String();
		
		if(timeMatcher.find()) {
			startTime = timeMatcher.group();
		} else if(!timeMatcher.find()) { //no time input
			startTime = ParserTimeLocal.defaultStartTime; 
		}
		
		return startTime;
	}
	
	/* Still haven't dealt with following inputs: 
	 * [add] [start date] [start time] [end date]
	 * [add] [start date] [end date] [end time]
	 * Parser cannot distinguish between the two YET
	 */
	protected static String extractEndTime(String userInput) {
		userInput = replaceAllDate(userInput);
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(userInput);
		String endTime = new String();

		//this is assuming for appointment creation, user either has two time inputs
		//or no time inputs
		if(hasTwoTimeInputs(userInput)) {
			userInput = timeMatcher.replaceFirst("");
			}

		endTime = extractStartTime(userInput);

		if(!timeMatcher.find()) {
			endTime = ParserTimeLocal.defaultEndTime;
		}

		return endTime;
	}

	protected static boolean hasTwoTimeInputs(String input) {
		String[] temp = ParserPatternLocal.timePattern.split(input);

		if(temp.length >= 2) {
			return true;
		} else {
			return false;
		}
	}
}
