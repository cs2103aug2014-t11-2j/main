package logic;

import java.util.LinkedList;

public class Filter {
	
	private static final int FORMAT_DEADLINE = 8;
	private static final String IMPORTANT = "important";
	
	private static LinkedList<Assignment> multipleFilter(LinkedList<Assignment> filterBuffer,
			String userInput) {
		
		String[] multipleFilterInput = userInput.split(";");
		
		for(int searchCount = 0; searchCount < multipleFilterInput.length; searchCount++) {
			filterBuffer = filter(filterBuffer, multipleFilterInput[searchCount]);
		}
		return filterBuffer;
	}
		
	protected static LinkedList<Assignment> filterMain(LinkedList<Assignment> buffer, String filterType,
			String startDate, String endDate) {
		
		LinkedList<Assignment> filteredList = new LinkedList<Assignment> ();
		
		filteredList = multipleFilter(buffer, filterType);
		
		if(startDate != null && endDate != null) {
			filteredList = Truncation.truncateList(filteredList, startDate, endDate);
		}
		return filteredList;
	}

	private static LinkedList<Assignment> filter(LinkedList<Assignment> buffer, String type) {
		
		LinkedList<Assignment> filterList = new LinkedList<Assignment>();
		
		if(type.contains("/")) {
			
			if(type.length() == FORMAT_DEADLINE) {
				filterList = SearchAll.searchByDeadline(buffer, type);
			} else {
				filterList = SearchAll.searchAll(buffer, type);
			}
		}
		
		else if(type.equals(IMPORTANT)){
			filterList = SearchAll.searchAll(buffer, "IMPT");
		}
		return filterList; 
	}
}