package parser;


public class RefineInputWithSpecial {
	
	//incomplete. Refer to project manual
	protected static RefinedUserInput inputIsFilter(String userInput) {
		RefinedUserInput inputFilter = new RefinedUserInput(); 
		String specialContent  = ExtractSpecialContent.forFilter(userInput);

		if(specialContent.isEmpty()) {
			inputFilter.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
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
			inputSearch.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
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
			inputSort.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
			return inputSort;
		}
		
		inputSort.setCommandType(EnumGroup.CommandType.SORT);
		inputSort.setSpecialContent(specialContent);
		
		return inputSort;
	}	
}