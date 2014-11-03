package logic;

import java.util.LinkedList;

public class Delete {
	
	enum DeleteAllType {
		DELETEALL_ON, DELETEALL_BEFORE, DELETEALL_BETWEEN, CLEAR;
	}
	
	protected static LinkedList<Assignment> deleteAll(String duration, String startDate, String endDate) {
		
		LinkedList<Assignment> deleted = new LinkedList<Assignment>();
		
		switch (getDuration(duration)) {

		case DELETEALL_ON:
			deleted = deleteOn(endDate);
			break;

		case DELETEALL_BEFORE:
			startDate = DateLocal.getStartDate();
			deleted = deleteBetween(endDate, startDate);
			break;

		case DELETEALL_BETWEEN:
			deleted = deleteBetween(endDate, startDate);
			break;

		default:
			deleted = InternalStorage.getBuffer();
			InternalStorage.getBuffer().clear();
			
			break;
		}
		return deleted;
	}
	
	protected static void delete(int id) {
		 
		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(InternalStorage.getBuffer(), String.valueOf(id));
		
		if(idFound.size() > 0) {
			int bufferPosition = InternalStorage.getBufferPosition(id);
			
			InternalStorage.getBuffer().remove(bufferPosition);
		}
	}

	private static DeleteAllType getDuration(String duration) {

		if (duration.length() == 2) {
			return DeleteAllType.DELETEALL_ON;
		} else if (duration.length() == 6) {
			return DeleteAllType.DELETEALL_BEFORE;
		} else if (duration.length() == 7) {
			return DeleteAllType.DELETEALL_BETWEEN;
		} else {
			return DeleteAllType.CLEAR;
		}
	}

	private static LinkedList<Assignment> deleteOn(String deleteOnDate) {

		LinkedList<Assignment> toDelete = new LinkedList<Assignment>();
		toDelete = SearchAll.searchByDeadline(InternalStorage.getBuffer(), deleteOnDate);

		for (int toDeleteCount = 0; toDeleteCount < toDelete.size(); toDeleteCount++) {
			delete(toDelete.get(toDeleteCount).getIndex());
		}
		return toDelete;
	}

	private static LinkedList<Assignment> deleteBetween(String deleteTill, String startDate) {
		
		LinkedList<Assignment> deleteInBetween = new LinkedList<Assignment>();
		
		while (!deleteTill.equals(startDate)) {
			deleteInBetween.addAll(deleteOn(deleteTill));
			deleteTill = DateLocal.updateDate(deleteTill);
		}
		deleteInBetween.addAll(deleteOn(startDate));
		
		return deleteInBetween;
	}
}