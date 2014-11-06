package parser;

import java.util.regex.Matcher;

public class RefineInputWithSpecial {
	
	private static final String START_DATE = "010101";
	private static final String END_DATE = "31/12/99";
	//incomplete. Refer to project manual
	protected static RefinedUserInput inputIsFilter(String userInput) {
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(userInput);
		RefinedUserInput inputFilter = new RefinedUserInput(); 
		String specialContent  = ExtractSpecialContent.forFilter(userInput);

		//there might be redundancy at this step.
		if(!dateMatcher.find() && specialContent.isEmpty()) {
			inputFilter.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputFilter;
		} else if(dateMatcher.find() && specialContent.isEmpty()) {
			String deadline = ParserDateLocal.extractEndDate(userInput);
			
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
			
			inputFilter.setCommandType(EnumGroup.CommandType.FILTER);
			inputFilter.setStartDate(START_DATE);
			inputFilter.setEndDate(deadline);
			inputFilter.setSpecialContent(specialContent);
		}
		
		return inputFilter;
	}
	
	protected static RefinedUserInput inputIsSearch(String userInput) {
		RefinedUserInput inputSearch = new RefinedUserInput(); 
		String specialContent  = ExtractSpecialContent.forSearch(userInput);
		Matcher dateMatcher = ParserPatternLocal.datePattern.matcher(specialContent);

		if(specialContent.isEmpty()) {
			inputSearch.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputSearch;
		}
		
		if(dateMatcher.find()) {
			specialContent = ParserDateLocal.extractEndDate(specialContent);
		}
		
		inputSearch.setCommandType(EnumGroup.CommandType.SEARCH);
		inputSearch.setSpecialContent(specialContent);
		
		return inputSearch;
	}
	
	//Does not support within a period of time yet
	protected static RefinedUserInput inputIsSort(String userInput) {
		RefinedUserInput inputSort = new RefinedUserInput(); 
		String specialContent  = ExtractSpecialContent.forSort(userInput);

		if(specialContent.isEmpty()) {
			inputSort.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputSort;
		}
		
		inputSort.setCommandType(EnumGroup.CommandType.SORT);
		inputSort.setStartDate(START_DATE);
		inputSort.setEndDate(END_DATE);
		inputSort.setSpecialContent(specialContent);
		
		return inputSort;
	}	
}