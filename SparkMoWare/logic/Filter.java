package logic;

import java.util.LinkedList;


public class Filter {
	
	private static final int TYPE_TASK = 0;
	private static final int TYPE_APPOINTMENT = 1;
	private static final int TYPE_DEADLINE =3;

	
	//overloading filterMain method for 2 sets of parameters
	public static LinkedList<Assignment> filterMain(String filterType) {

		LinkedList<Assignment> filteredList = new LinkedList<Assignment>();
		
		filteredList = filter(filterType);
		
		return filteredList;
	}
	
	
	public static LinkedList<Assignment> filterMain(String filterType,
			String endDate, String startDate) {

		LinkedList<Assignment> filteredList = new LinkedList<Assignment>();
		
		filteredList = Trancation.trancateList(filter(filterType), endDate, startDate);
		
		return filteredList;
	}

	
	public static LinkedList<Assignment> filter(String type) {
		
		LinkedList<Assignment> filterList = new LinkedList<Assignment>();
		
		if(type.length() == 1 && type.contains("[0-9]+")) {
			int assignmentType = Integer.parseInt(type);
			if(assignmentType == TYPE_TASK) {
				filterList = SearchAll.searchAll(type);
			} else if(assignmentType == TYPE_APPOINTMENT) {
				filterList = SearchAll.searchAll(type);
			} else if(assignmentType == TYPE_DEADLINE) {
				filterList = SearchAll.searchAll(type);
			}
		}
		return filterList; 
	}
	
}
