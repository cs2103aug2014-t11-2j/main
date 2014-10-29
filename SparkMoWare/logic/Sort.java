package logic;

import logic.Assignment;
import java.util.LinkedList;

public class Sort {

	private static int listCount;
	private static int sortedListCount;
	
	protected static LinkedList<Assignment> multipleSortRequired(LinkedList<Assignment> sortBuffer,
			String sortType, String startDate, String endDate) {
		
		String[] multipleSortInput = sortType.split(";");
		
		for(int sortCount = 0; sortCount < multipleSortInput.length; sortCount++) {
			sortBuffer = sortRequired(sortBuffer, multipleSortInput[sortCount]);
		}
		if(startDate != null && endDate != null) {
			sortBuffer = Truncation.truncateList(sortBuffer, startDate, endDate);
		}
		return sortBuffer;
	}
	
	private static LinkedList<Assignment> sortRequired(LinkedList<Assignment> buffer,
			String sortType){

		LinkedList<Assignment> sortedList = new LinkedList<Assignment>();

		if(sortType.equalsIgnoreCase("title")) {
			sortedList = insertionSortTitle(buffer);

		} else if(sortType.equalsIgnoreCase("SIN")) {
			sortedList = insertionSortId(buffer);
		} else if(sortType.equalsIgnoreCase("important")){
			sortedList = insertionSortPriority(buffer);
		}
		return sortedList;
	}

	private static LinkedList<Assignment> insertionSortPriority(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> prioritySortList = new LinkedList<Assignment>();
		
		prioritySortList = SearchAll.searchAll(buffer, "important");

		for(int counter = 0; counter < InternalStorage.getLineCount(); counter++){
			
				if(!buffer.get(counter).getPriority().equals("important")){
					prioritySortList.add(buffer.get(counter));
				}
		}
		return prioritySortList;
	}

	private static LinkedList<Assignment> insertionSortTitle(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> titleListSorted = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {
			if(titleListSorted.size() == 0) {
				titleListSorted.add(buffer.get(listCount));
			}
			else if(titleListSorted.size() >= 1) {
				titleListSorted = insertionSortTitle2(buffer,titleListSorted);
			}
		}
		return titleListSorted;
	}

	private static LinkedList<Assignment> insertionSortId(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> idListSorted = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {

			if(idListSorted.size() == 0) {
				idListSorted.add(buffer.get(listCount));

			} else if(idListSorted.size() >= 1) {
				idListSorted = insertionSortId2(buffer, idListSorted);
			}
		}
		return idListSorted;
	}
	
	private static LinkedList<Assignment> insertionSortId2(LinkedList<Assignment> buffer, 
			LinkedList<Assignment> idListSorted) {
		
		for(sortedListCount = 0; sortedListCount < idListSorted.size(); sortedListCount++) {

			if(Comparator.serialNumberComparator(idListSorted.get(sortedListCount).getId(), 
					buffer.get(listCount).getId())) {

				idListSorted.add(sortedListCount, buffer.get(listCount));
				break;
			} else if(sortedListCount == idListSorted.size() - 1) {
				idListSorted.add(buffer.get(listCount));
				break;
			}
		}
		return idListSorted;
	}
	
	private static LinkedList<Assignment> insertionSortTitle2(LinkedList<Assignment> buffer, 
			LinkedList<Assignment> titleListSorted) {
		
		for(sortedListCount = 0; sortedListCount < titleListSorted.size(); sortedListCount++) {
			
			if(titleListSorted.get(sortedListCount).getTitle().compareToIgnoreCase(buffer.get(listCount).getTitle()) >= 0) {
				titleListSorted.add(sortedListCount, buffer.get(listCount));
				break;
				
			} else if(sortedListCount == titleListSorted.size() - 1) {
				titleListSorted.add(buffer.get(listCount));
				break;
			}
		}
		return titleListSorted;
	}
	
	protected static LinkedList<Assignment> insertionSortDeadline(LinkedList<Assignment> buffer) {
		LinkedList<Assignment> UndoListSort = new LinkedList<Assignment>();
		
		return UndoListSort;
	}
}