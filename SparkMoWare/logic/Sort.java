package logic;

import logic.Assignment;
import java.util.LinkedList;

/*
 * This is to sort out differently, as per user request
 * Can be either 
 * Deadlines, task, appointment or ID
 */
public class Sort {

	private static int listCount;
	private static int sortedListCount;
	
	protected static LinkedList<Assignment> sortRequired(String sortType){
		
		LinkedList<Assignment> sortedList = new LinkedList<Assignment>();
	
		if(sortType.equalsIgnoreCase("title")) {
//			sortedList = insertionSortTitle(SparkMoVare.buffer);
			
		} else if(sortType.equalsIgnoreCase("serial")) {

			sortedList = insertionSortId(SparkMoVare.buffer);
		}
		return sortedList;
	}
	/*
	private static LinkedList<Assignment> insertionSortTitle(LinkedList<Assignment> list) {
		
		LinkedList<Assignment> titleListSorted = new LinkedList<Assignment>();
		
		for(listCount = 0; listCount < list.size(); listCount++) {
			if(list.size() == 0) {
				titleListSorted.add(list.get(listCount));
			}
			else {
				for(sortedListCount = 0; sortedListCount < titleListSorted.size(); sortedListCount++) {
					if(Comparator.dateComparator());
				}
			}
		}
		return titleListSorted;
	}
	*/
	private static LinkedList<Assignment> insertionSortId(LinkedList<Assignment> list) {
		
		LinkedList<Assignment> idListSorted = new LinkedList<Assignment>();
		
		for(listCount = 0; listCount < list.size(); listCount++) {
			if(idListSorted.size() == 0) {
				idListSorted.add(list.get(listCount));
			}
			else {
				for(sortedListCount = 0; sortedListCount < idListSorted.size(); sortedListCount++) {
					
					if(Comparator.serialNumberComparator(idListSorted.get(sortedListCount).getId(), 
							list.get(listCount).getId())) {
						
						System.out.println("YAY");
						idListSorted.add(sortedListCount, list.get(listCount));
					} 
					if(sortedListCount + 1 == idListSorted.size()) {
						idListSorted.add(list.get(listCount));				
					}
				}
			}
		}
		System.out.println(idListSorted.size());
		return idListSorted;
	}
}
	/*
	 * this method works in the following steps: 1. searches the assignments in
	 * end date 2. sorts according to Id 3. adds the sorted Id list to the back
	 * of final sortedList 4. decrements date towards start date
	 */
	/*
	public static LinkedList<Assignment> sortId(String endDate,
			String startDate) {

		LinkedList<Assignment> sortedList = new LinkedList<Assignment>();
		LinkedList<Assignment> tempList = new LinkedList<Assignment>();

		while (Comparator.dateComparator(endDate, startDate) != -1) {

			tempList = SearchAll.searchAll(endDate);
			//to ensure that all elements in tempList have the same endDate
			for(int i=0; i<tempList.size(); i++){
				if(!tempList.get(i).getEndDate().equals(endDate)){
					tempList.remove(i);
				}
			}	
			tempList = bubbleSortId(tempList);

			for (int i = 0; i < tempList.size(); i++) {
				sortedList.addLast(tempList.get(i));
			}
			endDate = DateLocal.updateDate(endDate);
		}
		return sortedList;
	}

	private static LinkedList<Assignment> bubbleSortId(
			LinkedList<Assignment> sortingList) {

		for (int i = 1; i < sortingList.size(); i++) {

			boolean isSorted = true;

			for (int j = 0; j < sortingList.size() - i; j++) {

				if (Comparator.serialNumberComparator(sortingList.get(j)
						.getId(), sortingList.get(j + 1).getId())) {

					Assignment temp = sortingList.get(j);
					Assignment temp2 = sortingList.get(j + 1);
					sortingList.add(j, temp2);
					sortingList.add(j + 1, temp);
					isSorted = false;
				}

				if (isSorted) {
					return sortingList;
				}
			}
		}
		return sortingList;
	}

	// following lines of code are for general date sort usage with an intrinsic
	// time sort
	protected static LinkedList<Assignment> sort(String endDate, String startDate) {

		LinkedList<Assignment> tempList = new LinkedList<Assignment>();
		LinkedList<Assignment> sortingList = new LinkedList<Assignment>();

		// compare dates/timings and add into sortedList
		while (Comparator.dateComparator(endDate, startDate) != -1) {

			tempList = SearchAll.searchAll(endDate);
			for (int i = 0; i < tempList.size(); i++) {
				sortingList.add(tempList.get(i));
			}
			endDate = DateLocal.updateDate(endDate);
		}
		return bubbleSort(sortingList);
	}

	private static LinkedList<Assignment> bubbleSort(
			LinkedList<Assignment> sortingList) {

		for (int i = 1; i < sortingList.size(); i++) {

			boolean isSorted = true;

			for (int j = 0; j < sortingList.size() - i; j++) {

				if (Comparator.dateComparator(sortingList.get(j).getEndDate(),
						sortingList.get(j + 1).getEndDate()) != 1) {

					Assignment temp = sortingList.get(j);
					Assignment temp2 = sortingList.get(j + 1);
					sortingList.add(j, temp2);
					sortingList.add(j + 1, temp);
					isSorted = false;

					if (Comparator.timeComparator(sortingList.get(j)
							.getStartTime(), sortingList.get(j + 1)
							.getStartTime()) != 1) {

						temp = sortingList.get(j);
						temp2 = sortingList.get(j + 1);
						sortingList.add(j, temp2);
						sortingList.add(j + 1, temp);
						isSorted = false;
					}
				}
				if (isSorted) {
					return sortingList;
				}
			}
		}
		return sortingList;
	}

 * // merge sort algorithm to sort sortingList private static void
 * mergeSort(LinkedList<Assignment> sortingList, String start, String end) {
 * 
 * if (SparkMoVare.dateComparator(end, start) != -1) {
 * 
 * int mid = sortingList.size() / 2; mergeSort(sortingList, start,
 * sortingList.get(mid).getEndDate()); mergeSort(sortingList,
 * sortingList.get(mid + 1).getEndDate(), end); merge(sortingList, start,
 * sortingList.get(mid), end);
 * 
 * }
 * 
 * }
 * 
 * private static void merge(LinkedList<Assignment> sortingList, String start,
 * Assignment mid, String end) {
 * 
 * LinkedList<Assignment> temporary = new LinkedList<Assignment>(); String left
 * = start; String right =
 * sortingList.get((sortingList.size()/2)+1).getEndDate(); String it = " ";
 * 
 * while
 * (SparkMoVare.dateComparator(sortingList.get(sortingList.size()/2).getEndDate
 * (),left) != -1 && SparkMoVare.dateComparator(end, right) != -1) {
 * 
 * if(dateComparator(right, left) != -1){
 * 
 * } }
 */