package logic;

import java.util.LinkedList;

/*
 * Able to delete individual assignment as per requested
 * or
 * delete the period where all the assignments have
 * 
 * Other than deleting individually,
 * all methods will call search function and receive a linkedlist before 
 * deleting them one by one
 */
public class Delete {
	
	private static DeleteForStats deleteForStats = new DeleteForStats();
	
	enum DeleteAllType {
		DELETEALL_ON, DELETEALL_BEFORE, DELETEALL_BETWEEN, CLEAR;
	}
	
	protected static DeleteForStats deleteAll(String duration, String endDate, String startDate) {
		
		switch (getDuration(duration)) {

		case DELETEALL_ON:
			deleteOn(endDate);
			return deleteForStats;

		case DELETEALL_BEFORE:
			startDate = DateLocal.getStartDate();
			deleteBetween(endDate, startDate);
			return deleteForStats;

		case DELETEALL_BETWEEN:
			deleteBetween(endDate, startDate);
			return deleteForStats;

		default:
			InternalStorage.getBuffer().clear();
			return deleteForStats;
		}
	}
	
	protected static DeleteForStats delete(String id) {
		 
		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(InternalStorage.getBuffer(), id);
		
		// PS: Have to check if nullAssignment will increase the numAppointment by 1
		Appointment nullAssignment = new Appointment();
		nullAssignment.setNumAppointment(nullAssignment.getNumAppointment() - 1); 
		
		if(idFound.size() == 0) {
			return deleteForStats;
		} else {
			
			int bufferPosition = InternalStorage.getBufferPosition(id);
			Assignment assignmentDelete = InternalStorage.getBuffer().get(bufferPosition);
			
			if(assignmentDelete.getIsDone() == true) {
				deleteForStats.increaseDeleteTotalCompleted();
			}
			if(assignmentDelete.getIsOnTime() == true) {
				deleteForStats.increaseDeleteTotalOnTime();
			}
			deleteForStats.increaseDeleteTotalAssignment();
			
			InternalStorage.getBuffer().remove(bufferPosition);
			
			nullAssignment.setNumAppointment(nullAssignment.getNumAppointment() - 1);
			
			return deleteForStats;
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

	private static void deleteOn(String deleteOnDate) {

		LinkedList<Assignment> toDelete = new LinkedList<Assignment>();
		toDelete = SearchAll.searchByDeadline(InternalStorage.getBuffer(), deleteOnDate);

		for (int toDeleteCount = 0; toDeleteCount < toDelete.size(); toDeleteCount++) {
			delete(toDelete.get(toDeleteCount).getId());
		}
	}

	private static void deleteBetween(String deleteTill, String startDate) {

		while (!deleteTill.equals(startDate)) {
			deleteOn(deleteTill);
			deleteTill = DateLocal.updateDate(deleteTill);
		}
		deleteOn(startDate);
	}
}