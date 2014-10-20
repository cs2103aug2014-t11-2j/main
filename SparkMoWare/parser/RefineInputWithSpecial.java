package parser;


public class RefineInputWithSpecial {
	
	//incomplete. Refer to project manual
	protected static RefinedUserInput inputIsFilter(String userInput) {
		RefinedUserInput inputFilter = new RefinedUserInput(); 
		String specialContent  = ExtractSpecialContent.forFilter(userInput);

		if(specialContent.isEmpty()) {
			return inputFilter;
		}
		
		inputFilter.setCommandType(EnumGroup.CommandType.FILTER);
		inputFilter.setSpecialContent(specialContent);
		
		return inputFilter;
	}
	
	protected static RefinedUserInput inputIsSearch(String userInput) {
		RefinedUserInput inputSearch = new RefinedUserInput(); 
		String specialContent  = ExtractSpecialContent.forSearch(userInput);

		if(specialContent.isEmpty()) {
			return inputSearch;
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
			return inputSort;
		}
		
		inputSort.setCommandType(EnumGroup.CommandType.SORT);
		inputSort.setSpecialContent(specialContent);
		
		return inputSort;
	}
	
	protected static RefinedUserInput inputIsTentative(String userInput) {
		RefinedUserInput inputTentative = new RefinedUserInput(); 
		String specialContent  = ExtractSpecialContent.forTentative(userInput);

		if(specialContent.isEmpty()) {
			return inputTentative;
		}
		
		inputTentative.setCommandType(EnumGroup.CommandType.TENTATIVE);
		inputTentative.setSpecialContent(specialContent);
		
		return inputTentative;
	}	
}