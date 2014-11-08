package parser;

import java.util.regex.Matcher;

/**
 * 
 * @author Matthew Song
 *
 */
public class InputIsClear {
	
	private static final String START_DATE = "01/01/01";
	
	/**
	 * Method creates a RefinedUserInput for the clear command. If upon extracting the special
	 * content from the input and empty string is detected, an Invalid Format CommandType is returned.
	 * <br><br>
	 * The cases are as follows:
	 * <br>
	 * 1. on/before: A date is extracted from the input. If no date input is detected, the current
	 * system's date is set. If the format of the input date is invalid, an Invalid Format CommandType
	 * is returned.
	 * <br><br>
	 * 2. between: Two dates are extracted from the input. If only a single date input is detected or
	 * either date inputs' format are invalid, an Invalid Format CommandType is returned.
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object for the clear command.
	 */
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputClear = new RefinedUserInput();
		String specialContent = ExtractSpecialContent.forClear(userInput);
		String endDate = ParserDateLocal.extractEndDate(userInput);
		
		if(specialContent.isEmpty() || endDate.isEmpty()){
			inputClear.setCommandType(EnumGroup.CommandType.CLEAR);
			inputClear.setSpecialContent("clear");
			return inputClear;
		}
		
		Matcher onMatcher = ParserPatternLocal.onPattern.matcher(specialContent);
		Matcher beforeMatcher = ParserPatternLocal.beforePattern.matcher(specialContent);
		Matcher betweenMatcher = ParserPatternLocal.betweenPattern.matcher(specialContent);

		if(onMatcher.find() || beforeMatcher.find()) {
			
			inputClear.setCommandType(EnumGroup.CommandType.CLEAR);
			inputClear.setStartDate(START_DATE);
			inputClear.setEndDate(endDate);
			inputClear.setSpecialContent(specialContent);

		} else if (betweenMatcher.find()) { 
			if (!ParserDateLocal.hasTwoDateInputs(userInput)) {
				inputClear.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputClear;
			}
			
			String startDate = ParserDateLocal.extractStartDate(userInput);

			if(startDate.isEmpty()){
				inputClear.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputClear;
			}

			inputClear.setCommandType(EnumGroup.CommandType.CLEAR);
			inputClear.setStartDate(startDate);
			inputClear.setEndDate(endDate);
			inputClear.setSpecialContent(specialContent);

		}
		return inputClear;
	}
}
