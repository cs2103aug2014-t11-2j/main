package parser;

import logic.Add;

public class InputIsAdd {

	protected static void executeCommand(String userInput) {
		if(checkIfAppt(userInput)){
			String startDate = getStartDate(userInput);
			UserInputAddAppointment inputAddAppointment =  new UserInputAddAppointment();
			//Add.addAssignment();
		} else {
			UserInputAddTask inputAddTask = new UserInputAddTask();
		}
	}

	
	private static boolean checkIfAppt(String userInput) {		
		
		if(ParserPatternLocal.datePattern.matcher(userInput).find()) {
			return true;
		}
		
		return false;
	}
	
	private static String getStartDate(String userInput) {
		ParserPatternLocal.datePattern.matcher(userInput);
			
		 
		
		return null;
	}
}
