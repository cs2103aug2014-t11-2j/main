package parser;

import parser.EnumGroup.AssignmentType;
import parser.EnumGroup.CommandType;

public class InputIsAdd {

	protected static RefinedUserInput refineInput(String userInput) {
		String title = Misc.extractTitle(userInput);

		if(title == null) {
			return new RefinedUserInput(1);
		}
		
		String endDate = ParserDateLocal.extractEndDate(userInput);		
		
		if(endDate == null) {
			return new RefinedUserInput(1);
		}
		
		String endTime = ParserTimeLocal.extractEndTime(userInput);
		
		if(endTime == null) {
			return new RefinedUserInput(1);
		}

		if(ParserDateLocal.hasTwoDateInputs(userInput)){			
			String startDate = ParserDateLocal.extractStartDate(userInput);

			if(startDate == null) {
				return new RefinedUserInput(1);
			}
			
			String startTime = ParserTimeLocal.extractStartTime(userInput);
			
			if(startTime == null) {
				return new RefinedUserInput(1);
			}

			RefinedUserInput inputAddAppointment =  new RefinedUserInput(
					CommandType.ADD, null,
					title, startDate,
					startTime, endDate,
					endTime, AssignmentType.APPOINTMENT,
					null);

			return inputAddAppointment;

		} else {
			RefinedUserInput inputAddTask =  new RefinedUserInput(
					CommandType.ADD, null,
					title, null,
					null, endDate,
					endTime, AssignmentType.TASK,
					null);
			
			return inputAddTask;
		}
	}
}