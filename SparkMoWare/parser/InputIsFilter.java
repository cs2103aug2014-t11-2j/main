package parser;

import java.util.regex.Matcher;
/**
 * 
 * @author Matthew Song
 *
 */
public class InputIsFilter {
	
	static final String START_DATE= "01/01/01";
	static final String END_DATE = "31/12/99";
	
	/**
	 * Method creates a RefinedUserInput for the filter command. If no date or special content is
	 * found, an Invalid Format CommandType is returned.
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object for the filter command.
	 */
	protected static RefinedUserInput refineInput(String userInput) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
		RefinedUserInput inputFilter = new RefinedUserInput(); 
		String specialContent  = ExtractSpecialContent.forFilter(userInput);
	
		if(!dateMatcher.find() && specialContent.isEmpty()) {
			inputFilter.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputFilter;
		} else if(dateMatcher.find() && specialContent.isEmpty()) {
			String deadline = ParserDateLocal.extractEndDate(userInput);
			
			if(deadline.isEmpty()) {
				inputFilter.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputFilter;
			}
			
			inputFilter.setCommandType(EnumGroup.CommandType.FILTER);
			inputFilter.setStartDate(START_DATE);
			inputFilter.setEndDate(deadline);
		} else if(!dateMatcher.find() && !specialContent.isEmpty()) {
			
			inputFilter.setCommandType(EnumGroup.CommandType.FILTER);
			inputFilter.setStartDate(START_DATE);
			inputFilter.setEndDate(END_DATE);
			inputFilter.setSpecialContent(specialContent);
		} else if(dateMatcher.find() && !specialContent.isEmpty()){
			String deadline = ParserDateLocal.extractEndDate(userInput);
			
			if(deadline.isEmpty()) {
				inputFilter.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputFilter;
			}
			
			inputFilter.setCommandType(EnumGroup.CommandType.FILTER);
			inputFilter.setStartDate(START_DATE);
			inputFilter.setEndDate(deadline);
			inputFilter.setSpecialContent(specialContent);
		}
		
		return inputFilter;
	}
}