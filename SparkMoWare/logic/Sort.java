package logic;

import logic.Assignment;

import java.util.LinkedList;

public class Sort {

	enum SortType {
		DEADLINES, TASKS, APPOINTMENTS, ID;
	}

	protected static LinkedList<Assignment> sortClassify(String sortType,
			String endDate, String startDate) {

		switch (convertToSortEnum(sortType)) {

		case TASKS:
			return sort(0, endDate, startDate);

		case APPOINTMENTS:
			return sort(1, endDate, startDate);

		case DEADLINES:
			return sort(-1, endDate, startDate);

		case ID:
			return sortId(endDate, startDate);

		default:
			// check for just "sort" input by user, then print out current
			// linked list as it is chronological already
			// sort according to a particular title or ID. parse for int? or
			// directly search for those first, then use
			// null is a stub
			return null;
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

	/*
	 * sorting by tasks/appt/deadlines, all use this method. As assigned in
	 * assignment class, task is type int 0, appointment is type int 1. Deadline
	 * is set to type int -1.
	 */
	private static LinkedList<Assignment> sort(int type, String endDate,
			String startDate) {

		LinkedList<Assignment> sortedList = new LinkedList<Assignment>();
		sortedList = trancateList(endDate, startDate);

		if (type != -1) {

			for (int i = 0; i < sortedList.size(); i++) {
				if (sortedList.get(i).getType() != type)
					sortedList.remove(i);
			}

		}
		return sortedList;
	}

	private static LinkedList<Assignment> trancateList(String endDate,
			String startDate) {

		LinkedList<Assignment> trancatedList = new LinkedList<Assignment>();
		trancatedList = SparkMoVare.buffer;

		int lowerLimit = trancatedList.indexOf(startDate);
		int upperLimit = trancatedList.lastIndexOf(endDate);

		for (int i = 0; i < lowerLimit; i++) {
			trancatedList.remove(i);
		}

		for (int j = trancatedList.size(); j > upperLimit; j--) {
			trancatedList.remove(j);
		}
		return trancatedList;
	}

	/*
	 * this method works in the following steps: 1. searches the assignments in
	 * end date 2. sorts according to Id 3. adds the sorted Id list to the back
	 * of final sortedList 4. decrements date towards start date
	 */
	private static LinkedList<Assignment> sortId(String endDate,
			String startDate) {

		LinkedList<Assignment> sortedList = new LinkedList<Assignment>();
		LinkedList<Assignment> tempList = new LinkedList<Assignment>();

		while (Comparator.dateComparator(endDate, startDate) != -1) {

			tempList = SearchAll.searchAll(endDate);
			tempList = bubbleIdSort(tempList);

			for (int i = 0; i < tempList.size(); i++) {
				sortedList.addLast(tempList.get(i));
			}
			endDate = Delete.updateDate(endDate);
		}
		return sortedList;
	}

	private static LinkedList<Assignment> bubbleIdSort(
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
	protected static LinkedList<Assignment> sort(String end, String start) {

		String timeEnd;
		String timeStart;
		String dateEnd;
		String dateStart;
		Assignment temp;
		Assignment temp2;
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
		while (Comparator.dateComparator(end, start) != -1) {

			tempList = SearchAll.searchAll(end);
			for (int i = 0; i < tempList.size(); i++) {
				sortingList.add(tempList.get(i));
			}
			end = Delete.updateDate(end);
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
}

/*
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

