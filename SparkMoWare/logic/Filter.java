package logic;

import java.util.LinkedList;

public class Filter {
	
	private static final int FORMAT_DEADLINE = 8;
	
	public static LinkedList<Assignment> filterMain(String filterType,
			String startDate, String endDate) {

		LinkedList<Assignment> filteredList = new LinkedList<Assignment> ();
		
		if(startDate != null && endDate != null) {
			filteredList = Trancation.trancateList(filter(filterType), startDate, endDate);
		} else {
			filteredList = filter(filterType);
		}
		
		return filteredList;
	}

	public static LinkedList<Assignment> filter(String type) {
		
		LinkedList<Assignment> filterList = new LinkedList<Assignment>();
		
		if(type.matches("[0-9]+")) {
			
			if(type.length() == FORMAT_DEADLINE) {
				filterList = SearchAll.searchByDeadline(type);
			} else {
				filterList = SearchAll.searchAll(type);
			}
		}
		return filterList; 
	}
}