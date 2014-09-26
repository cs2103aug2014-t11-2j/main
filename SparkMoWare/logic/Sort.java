package logic;



import java.util.LinkedList;

import logic.Delete.DeleteAllType;

public class Sort {

	enum SortType {
		DEADLINES, TASKS, APPOINTMENTS;
	}

	private static LinkedList<Assignment> sortClassify(String sortType, String endDate, String startDate) {

		switch (convertToSortEnum(sortType)) {

		case DEADLINES:

			return sort(endDate, startDate);

		case TASKS:

			return sortTasks(endDate, startDate);

		case APPOINTMENTS:

			return sortAppointments(endDate, startDate);

		default:
			// check for just "sort" input by user, then print out current
			// linked list as it is chronological already
			// sort according to a particular title or ID. parse for int? or
			// directly search for those first, then use
		}
	}

	
	private static SortType convertToSortEnum(String duration) {

		if (duration.length() == 9) {

			return SortType.DEADLINES;

		} else if (duration.length() == 5) {

			return SortType.TASKS;

		} else if (duration.length() == 12) {

			return SortType.APPOINTMENTS;

		} else {

			return null;
		}

	}

	private static LinkedList<Assignment> sortAppointments(String endDate,
			String startDate) {
		
		LinkedList<Assignment> sortedList = new LinkedList<Assignment>();
		sortedList = sort(endDate, startDate);
		
		for(int i=0; i<sortedList.size(); i++){
			if(sortedList.get(i).getType() != 1)
				sortedList.remove(i);
		}
		
		return sortedList;
	}

	private static LinkedList<Assignment> sortTasks(String endDate,
			String startDate) {
		
		LinkedList<Assignment> sortedList = new LinkedList<Assignment>();
		sortedList = sort(endDate, startDate);
		
		for(int i=0; i<sortedList.size(); i++){
			if(sortedList.get(i).getType() != 0)
				sortedList.remove(i);
		}
				
		return sortedList;

	}
	
	private static LinkedList<Assignment> sort(String end, String start) {

		String timeEnd;
		String timeStart;
		String dateEnd;
		String dateStart;
		LinkedList<Assignment> tempList = new LinkedList<Assignment>();
		LinkedList<Assignment> sortingList = new LinkedList<Assignment>();

		// checking if input is date or time
		if (end.length() == 4) {
			timeEnd = end;
			if (start == null) {
				SparkMoVare.buffer.getFirst().getStartTime();
			} else {
				timeStart = start;
			}
		} else {
			dateEnd = end;
			if (start == null) {
				SparkMoVare.buffer.getFirst().getStartDate();
			} else {
				dateStart = start;
			}
		}

		// compare dates/timings and add into sortedList
		while (SparkMoVare.dateComparator(end, start) != -1) {

			tempList = SparkMoVare.search(" " + end);
			for (int i = 0; i < tempList.size(); i++) {
				sortingList.add(tempList.get(i));
			}

			end = SparkMoVare.updateDate(end);
		}

		return bubbleSort(sortingList);
	}

	private static LinkedList<Assignment> bubbleSort(LinkedList<Assignment> sortingList) {
		
		for(int i=1; i<sortingList.size(); i++){
			
			boolean isSorted = true;
			
			for(int j=0; j<sortingList.size()-i; j++){
				
				if(dateComparator(sortingList.get(j).getEndDate(), sortingList.get(j+1).getEndDate()) != 1){
					
					Assignment temp = sortingList.get(j);
					Assignment temp2 = sortingList.get(j+1);
					sortingList.add(j, temp2);
					sortingList.add(j+1, temp);
					isSorted = false;
					
					if(timeComparator(sortingList.get(j).getStartTime(), sortingList.get(j+1).getStartTime()) != 1){
						
						Assignment temp = sortingList.get(j);
						Assignment temp2 = sortingList.get(j+1);
						sortingList.add(j, temp2);
						sortingList.add(j+1, temp);
						isSorted = false;
						
				}
			}
			
			if(isSorted){
				return sortingList;
			}
		}
		
	}
		
	
	
	
/*
	// merge sort algorithm to sort sortingList
	private static void mergeSort(LinkedList<Assignment> sortingList,
			String start, String end) {

		if (SparkMoVare.dateComparator(end, start) != -1) {

			int mid = sortingList.size() / 2;
			mergeSort(sortingList, start, sortingList.get(mid).getEndDate());
			mergeSort(sortingList, sortingList.get(mid + 1).getEndDate(), end);
			merge(sortingList, start, sortingList.get(mid), end);

		}

	}

	private static void merge(LinkedList<Assignment> sortingList,
				String start, Assignment mid, String end) {
			
			LinkedList<Assignment> temporary = new LinkedList<Assignment>();
			String left = start;
			String right = sortingList.get((sortingList.size()/2)+1).getEndDate();
			String it = " ";
			
			while (SparkMoVare.dateComparator(sortingList.get(sortingList.size()/2).getEndDate(),left) != -1 
					&& SparkMoVare.dateComparator(end, right) != -1) {
				
				if(dateComparator(right, left) != -1){
					
				}
			}
			
*/
			
		}
}
