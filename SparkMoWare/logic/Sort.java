package logic;

import logic.Assignment;
import logic.Assignment.AssignmentType;

import java.util.LinkedList;

//@author A0111572R

public class Sort {

	private static int listCount;
	private static int sortedListCount;

	protected static LinkedList<Assignment> sortRequired(LinkedList<Assignment> buffer,
			String sortType, String startDate, String endDate){

		LinkedList<Assignment> sortedList = new LinkedList<Assignment>();

		if(sortType.equalsIgnoreCase("title")) {
			sortedList = insertionSortTitle(buffer);

		} else if(sortType.equalsIgnoreCase("SIN")) {
			sortedList = insertionSortId(buffer);

		} else if(sortType.equalsIgnoreCase("important")){
			sortedList = insertionSortPriority(buffer);
		}
		
		sortedList = Truncation.truncateList(sortedList, startDate, endDate);
		
		return sortedList;
	}

	private static LinkedList<Assignment> insertionSortPriority(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> prioritySortList = new LinkedList<Assignment>();

		prioritySortList.addAll(SearchAll.searchAll(buffer, "important"));
		prioritySortList.addAll(SearchAll.searchAll(buffer, "NIMPT"));

		return prioritySortList;
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

			if(Comparator.serialNumberComparator(idListSorted.get(sortedListCount).getIndex(), 
					buffer.get(listCount).getIndex())) {
				idListSorted.add(sortedListCount, buffer.get(listCount));
				break;

			} else if(sortedListCount == idListSorted.size() - 1) {
				idListSorted.add(buffer.get(listCount));
				break;
			}
		}
		return idListSorted;
	}

	private static LinkedList<Assignment> insertionSortTitle(LinkedList<Assignment> buffer) {

		LinkedList<Assignment> titleListSorted = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {
			if(titleListSorted.size() == 0) {
				titleListSorted.add(buffer.get(listCount));

			} else if(titleListSorted.size() >= 1) {
				titleListSorted = insertionSortTitle2(buffer,titleListSorted);
			}
		}
		return titleListSorted;
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

		LinkedList<Assignment> deadlineSorted = new LinkedList<Assignment>();

		for(listCount = 0; listCount < buffer.size(); listCount++) {
			if(deadlineSorted.size() == 0) {
				deadlineSorted.add(buffer.get(listCount));
			} else if(listCount == listCount - 1) {
				deadlineSorted.addLast(buffer.get(listCount));
			} else if (deadlineSorted.size() >= 1) {
			
				deadlineSorted = insertionSortDeadline2(buffer, deadlineSorted);
			} 
		}
		return deadlineSorted;
	}

	private static LinkedList<Assignment> insertionSortDeadline2(LinkedList<Assignment> buffer, 
			LinkedList<Assignment> deadlineSorted) {

		int position;

		if(buffer.get(listCount).getIsDone() == true) {
			deadlineSorted.addFirst(buffer.get(listCount));

		} else if(buffer.get(listCount).getAssignType().equals(AssignmentType.APPT)) {
			Appointment appointment = (Appointment) buffer.get(listCount);
			position = Comparator.addToBigBuffer(appointment);
			deadlineSorted.add(position, appointment);

		} else if(buffer.get(listCount).getAssignType().equals(AssignmentType.TASK)) {
			Task task = (Task) buffer.get(listCount);
			position = Comparator.addTaskToBigBuffer(task);
			deadlineSorted.add(position, task);
			
		} else if(buffer.get(listCount).getAssignType().equals(AssignmentType.ASGN)) {
			Assignment assignment = buffer.get(listCount);
			deadlineSorted = insertionSortDeadline3(assignment, deadlineSorted);
			
		} else if(buffer.get(listCount).getAssignType().equals(AssignmentType.TNTV)) {
			Tentative tentative = (Tentative) buffer.get(listCount);
			position = Comparator.addTentativeToBigBuffer(tentative);
			deadlineSorted.add(position, tentative);
		}
		return deadlineSorted;
	}
	
	private static LinkedList<Assignment> insertionSortDeadline3(Assignment assignment,
			LinkedList<Assignment> deadlineSorted) {
		
		int position;
		int size = InternalStorage.getLineCount();
		
		for(int i = 0; i < size; i++) {
			if(!deadlineSorted.get(i).getIsDone()) {
				deadlineSorted.add(i, assignment);
				break;
				
			} else if(i == size - 1) {
				position = i;
				deadlineSorted.add(position + 1, assignment);
				break;
			}
		}
		return deadlineSorted;
	}
}