package parser;

public class InputIsEdit {

	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputEdit = new RefinedUserInput();
		String id = Misc.extractId(userInput);
		
		switch(Determine.getEditType(userInput)) {
		
		case TITLE:
			String title = Misc.extractTitle(userInput, "edit");
			
			if(title.isEmpty()) {
				inputEdit.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputEdit;
			}
			inputEdit.setTitle(title);
			inputEdit.setSpecialContent("title");
			break;
			
		case END_DATE:
			String endDate = ParserDateLocal.extractEndDate(userInput);
			
			if(endDate.isEmpty()) {
				inputEdit.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputEdit;
			}
			inputEdit.setEndDate(endDate);
			inputEdit.setSpecialContent("end date");
			break;
			
		case END_TIME:
			String endTime = ParserTimeLocal.extractEndTime(userInput);
			
			if(endTime.isEmpty()) {
				inputEdit.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputEdit;
			}
			inputEdit.setEndTime(endTime);
			inputEdit.setSpecialContent("end time");
			break;
			
		//if user leaves priority blank, system automatically changes it to not important
		case PRIORITY:
			String priority = Misc.extractPriority(userInput);
			
			inputEdit.setPriority(priority);
			inputEdit.setSpecialContent("priority");
			break;
			
		case START_DATE:
			String startDate = ParserDateLocal.extractStartDate(userInput);
			
			if(startDate.isEmpty()) {
				inputEdit.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputEdit;
			}
			inputEdit.setSpecialContent("start date");
			inputEdit.setStartDate(startDate);
			break;
			
		case START_TIME:
			String startTime = ParserTimeLocal.extractStartTime(userInput);
			
			if(startTime.isEmpty()) {
				inputEdit.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputEdit;
			}
			inputEdit.setSpecialContent("start time");
			inputEdit.setStartTime(startTime);
			break;
			
		case INVALID:
			inputEdit.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputEdit;
			
		default:
			inputEdit.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputEdit;
		}
		
		inputEdit.setCommandType(EnumGroup.CommandType.EDIT);
		inputEdit.setId(id);
		
		return inputEdit;
	}
}