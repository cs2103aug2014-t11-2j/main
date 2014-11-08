package parser;

import java.util.Vector;
import java.util.regex.Matcher;

import logic.Assignment.AssignmentType;

/**
 * 
 * @author blu
 *
 */
public class InputIsTentative {

	/**
	 * Method creates a RefinedUserInput for the tentative command. Method extracts all the input dates
	 * and times and stores each in a vector. If the dates and times are found to be in an incorrect
	 * format, or the number of tentative dates and times are not equal to one another, or either
	 * tentative dates or times are empty, an Invalid Format CommandType is returned.
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object for the tentative command.
	 */
	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputTentative = new RefinedUserInput();
		Vector<String> tentativeDates = new Vector<String>();
		Vector<String> tentativeTimes = new Vector<String>();
		
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(userInput);
		
		tentativeDates = ParserDateLocal.extractTentativeDates(userInput);
		tentativeTimes = ParserTimeLocal.extractTentativeTimes(userInput);
		String title = Misc.extractTitle(userInput, "tentative");
		
		if(tentativeDates.size() == 0 || tentativeTimes.size() == 0 || !(tentativeDates.size() == tentativeTimes.size()) ||
		   title.isEmpty()) {
			inputTentative.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			
			return inputTentative;
		} else {

			inputTentative.setCommandType(EnumGroup.CommandType.TENTATIVE);
			inputTentative.setTitle(title);
			inputTentative.setAssignmentType(AssignmentType.TNTV);
			inputTentative.setIsNewTentative(true);
			inputTentative.setTentativeDates(tentativeDates);
			inputTentative.setTentativeTimes(tentativeTimes);

			return inputTentative;
		}
	}
}
