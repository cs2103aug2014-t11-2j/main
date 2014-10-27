package logic;

import java.util.LinkedList;

public class Filter {
	
	private static final int FORMAT_DEADLINE = 8;
	private static final String IMPORTANT = "important";
	
	protected static LinkedList<Assignment> multipleFilter(LinkedList<Assignment> filterBuffer,
			String userInput) {
		
		// assertTrue(filterBuffer.size()>0);
		
		String[] multipleFilterInput = userInput.split(";");
		
		for(int searchCount = 0; searchCount < multipleFilterInput.length; searchCount++) {
			filterBuffer = filter(filterBuffer, multipleFilterInput[searchCount]);
		}
		return filterBuffer;
	}
		
	public static LinkedList<Assignment> filterMain(LinkedList<Assignment> buffer, String filterType,
			String startDate, String endDate) {

		// assertTrue(buffer.size()>0);
		
		LinkedList<Assignment> filteredList = new LinkedList<Assignment> ();
		
		filteredList = multipleFilter(buffer, filterType);
		
		if(startDate != null && endDate != null) {
			filteredList = Truncation.truncateList(filteredList, startDate, endDate);
		}
		
		// assertFalse(SearchAll.searchByDeadline(filteredList, DateLocal.updateDate(endDate)).size()>0);
		
		return filteredList;
	}

	public static LinkedList<Assignment> filter(LinkedList<Assignment> buffer, String type) {
		
		LinkedList<Assignment> filterList = new LinkedList<Assignment>();
		
		if(type.matches("[0-9]+")) {
			
			if(type.length() == FORMAT_DEADLINE) {
				filterList = SearchAll.searchByDeadline(buffer, type);
			} else {
				filterList = SearchAll.searchAll(buffer, type);
			}
		}
		
		else if(type.equals(IMPORTANT)){
			filterList = SearchAll.searchAll(buffer, IMPORTANT);
		}
		return filterList; 
	}
}