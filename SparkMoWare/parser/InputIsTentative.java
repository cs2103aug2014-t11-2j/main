package parser;

import java.util.Vector;
import java.util.regex.Matcher;

public class InputIsTentative {

	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputTentative = new RefinedUserInput();
		Vector<String> tentativeDates = new Vector<String>();
		Vector<String> tentativeTimes = new Vector<String>();
		String id = Misc.extractId(userInput);
		
		userInput = Misc.removeId(userInput);
		
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
		Matcher timeMatcher = ParserPatternLocal.timePattern.matcher(userInput);
		
		tentativeDates = ParserDateLocal.extractTentativeDates(userInput);
		tentativeTimes = ParserTimeLocal.extractTentativeTimes(userInput);
		String title = Misc.extractTitle(userInput, "tentative");
		
		if(tentativeDates.size() == 0 || tentativeTimes.size() == 0 || !(tentativeDates.size() == tentativeTimes.size()) ||
		   title.isEmpty() || id.isEmpty()) {
			inputTentative.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			
			return inputTentative;
		} else {

			inputTentative.setCommandType(EnumGroup.CommandType.TENTATIVE);
			inputTentative.setId(id);
			inputTentative.setTitle(title);
			inputTentative.setIsNewTentative(true);
			inputTentative.setTentativeDates(tentativeDates);
			inputTentative.setTentativeTimes(tentativeTimes);

			return inputTentative;
		}
	}
}
