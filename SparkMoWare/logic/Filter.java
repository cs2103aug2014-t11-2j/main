package logic;

import java.util.LinkedList;

public class Filter {
	
	private static final int FORMAT_DEADLINE = 8;
	private static final String IMPORTANT = "important";
	
	private static final String TYPE_TASK = "task";
	private static final String TYPE_ASSIGNMENT = "assignment";
	private static final String TYPE_APPOINTMENT = "appointment";
	private static final String TYPE_TENTATIVE = "tentative";
	
	private static LinkedList<Assignment> multipleFilter(LinkedList<Assignment> filterBuffer,
			String userInput) {
		
		String[] multipleFilterInput = userInput.split(" ");
		
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

	protected static LinkedList<Assignment> filter(LinkedList<Assignment> buffer, String type) {
		
		LinkedList<Assignment> filterList = new LinkedList<Assignment>();
		
		if(type.contains("/")) {
			if(type.length() == FORMAT_DEADLINE) {
				filterList = SearchAll.searchByDeadline(buffer, type);
			} else {
				filterList = SearchAll.searchAll(buffer, type);
			}
		} else if(type.equals(IMPORTANT)){
			filterList = SearchAll.searchAll(buffer, "IMPT");
		} else if(type.equalsIgnoreCase(TYPE_TASK)) {
			filterList = SearchAll.searchAll(buffer, TYPE_TASK);
		} else if(type.equalsIgnoreCase(TYPE_ASSIGNMENT)) {
			filterList = SearchAll.searchAll(buffer, TYPE_ASSIGNMENT);
		} else if(type.equalsIgnoreCase(TYPE_APPOINTMENT)) {
			filterList = SearchAll.searchAll(buffer, TYPE_APPOINTMENT);
		} else if(type.equalsIgnoreCase(TYPE_TENTATIVE)) {
			filterList = SearchAll.searchAll(buffer, TYPE_TENTATIVE);
		}
		return filterList; 
	}
}