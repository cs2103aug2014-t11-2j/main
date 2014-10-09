package parser;

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

	
	private static boolean checkIfAppt(String userInput) {		
		
		//if statement checks if user input has 2 date inputs.
		if(ParserPatternLocal.datePattern.matcher(userInput).find()) {
			return true;
		}
		
		return false;
	}
	
	private static String getTitle(String userInput) {
		
		return null; //stub
	}
	
	private static String getStartDate(String userInput) {
		ParserPatternLocal.datePattern.matcher(userInput);
			
		 
		
		return null;//stub
	}
	
	private static String getEndDate(String userInput) {
		//ParserPatternLocal.datePattern.matcher(userInput);
			
		 
		
		return null;//stub
	}
	
	private static String getStartTime(String userInput) {
		//ParserPatternLocal.datePattern.matcher(userInput);
			
		 
		
		return null;//stub
	}
	
	private static String getEndTime(String userInput) {
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
