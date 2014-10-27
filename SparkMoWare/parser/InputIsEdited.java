package parser;

public class InputIsEdited {

	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputEdited = new RefinedUserInput(); 
		String[] newContentArray = userInput.split("~");
		
		if(!(newContentArray.length == 8) || (Misc.determineIdValidity(newContentArray[1])).isEmpty()
				|| newContentArray[2].isEmpty()
				|| ParserDateLocal.determineDateValidity(newContentArray[3]).isEmpty()
				|| ParserTimeLocal.determineTimeValidity(newContentArray[4]).isEmpty()
				|| ParserDateLocal.determineDateValidity(newContentArray[5]).isEmpty()
				|| ParserTimeLocal.determineTimeValidity(newContentArray[6]).isEmpty()
				|| Misc.determinePriorityValidity(newContentArray[7])) {
			inputEdited.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputEdited;
		}
		
		inputEdited.setCommandType(EnumGroup.CommandType.EDITED);
		inputEdited.setId(newContentArray[1]);
		inputEdited.setTitle(newContentArray[2]);
		inputEdited.setStartDate(newContentArray[3]);
		inputEdited.setStartTime(newContentArray[4]);
		inputEdited.setEndDate(newContentArray[5]);
		inputEdited.setEndTime(newContentArray[6]);
		inputEdited.setPriority(newContentArray[7]);

		return inputEdited;
	}

}
