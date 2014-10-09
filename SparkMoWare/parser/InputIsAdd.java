package parser;

public class InputIsAdd {

	protected static String[] refineInput(String userInput) {
		if(checkIfAppt(userInput)){
			String startDate = getStartDate(userInput);
			UserInputAddAppointment inputAddAppointment =  new UserInputAddAppointment();
			
		} else {
			UserInputAddTask inputAddTask = new UserInputAddTask();
		}
		
		return null;
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
