package parser;

import java.util.regex.Matcher;

public class InputIsEdit {

	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputEdit = new RefinedUserInput();
		int index = ParserIndexLocal.extractIndex(userInput, "edit");
		Exception exception = null;
		
		 try {
				if (index == -1) {
					throw exception;
				}

				switch (Determine.getEditType(userInput)) {

				case TITLE:
					userInput = Misc.removeEditTitle(userInput);
					userInput = ParserIndexLocal.removeIndex(userInput);
					String title = Misc.extractTitle(userInput, "edit");

					Matcher symbolMatcher = ParserPatternLocal.symbolsPattern
							.matcher(title);

					if (title.isEmpty()) {
						throw exception;
					}

					if (symbolMatcher.find()) {
						title = symbolMatcher.replaceFirst("");
					}

					inputEdit.setTitle(title.trim());
					inputEdit.setSpecialContent("title");
					break;

				case END_DATE:
					String endDate = ParserDateLocal.extractEndDate(userInput);

					if (endDate.isEmpty()) {
						throw exception;
					}
					inputEdit.setEndDate(endDate);
					inputEdit.setSpecialContent("end date");
					break;

				case END_TIME:
					String endTime = ParserTimeLocal.extractEndTime(userInput);

					if (endTime.isEmpty()) {
						throw exception;
					}
					inputEdit.setEndTime(endTime);
					inputEdit.setSpecialContent("end time");
					break;

				// if user leaves priority blank, system automatically changes it to
				// not important
				case PRIORITY:
					String priority = Misc.extractPriority(userInput);

					inputEdit.setPriority(priority);
					inputEdit.setSpecialContent("priority");
					break;

				case START_DATE:
					String startDate = ParserDateLocal.extractStartDate(userInput);

					if (startDate.isEmpty()) {
						throw exception;
					}
					inputEdit.setSpecialContent("start date");
					inputEdit.setStartDate(startDate);
					break;

				case START_TIME:
					String startTime = ParserTimeLocal.extractStartTime(userInput);

					if (startTime.isEmpty()) {
						throw exception;
					}
					inputEdit.setSpecialContent("start time");
					inputEdit.setStartTime(startTime);
					break;

				case INVALID:
					throw exception;

				default:
					throw exception;
				}
			} catch (Exception e) {
				inputEdit.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputEdit;
			}
		inputEdit.setCommandType(EnumGroup.CommandType.EDIT);
		inputEdit.setIndex(index);
		
		return inputEdit;
	}
}