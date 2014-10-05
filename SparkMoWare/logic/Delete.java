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

	enum DeleteAllType {
		DELETEALL_ON, DELETEALL_BEFORE, DELETEALL_BETWEEN;
	}
	
	protected static String delete(String id) {
		 
		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(id);
		
		// PS: Have to check if nullAssignment will increase the numAppointment by 1
		Assignment nullAssignment = new Assignment();
		nullAssignment.setNumAppointment(nullAssignment.getNumAppointment() - 1); 
		
		if(idFound.size() == 0) {
			return String.format(Message.DOES_NOT_EXISTS, "Serial Number " + id);
		} else {
			String stringDeleted;
			int bufferPosition = SparkMoVare.getBufferPosition(id);
			
			stringDeleted = SparkMoVare.buffer.get(bufferPosition).getTitle();
			SparkMoVare.buffer.remove(bufferPosition);
			
			nullAssignment.setNumAppointment(nullAssignment.getNumAppointment() - 1);
			
			return String.format(Message.DELETED, SparkMoVare.filePath, stringDeleted);
		}
	}

	protected static String deleteAll(String duration, String endDate, String startDate) {

		switch (getDuration(duration)) {

		case DELETEALL_ON:
			deleteOn(endDate);
			return String.format(Message.DELETE_ON, endDate);

		case DELETEALL_BEFORE:
			startDate = SparkMoVare.buffer.getFirst().getEndDate();
			deleteBetween(endDate, startDate);
			return String.format(Message.DELETE_BEFORE, endDate);

		case DELETEALL_BETWEEN:
			deleteBetween(endDate, startDate);
			return String.format(Message.DELETE_BETWEEN, endDate, startDate);

		default:
			SparkMoVare.buffer.clear();
			return String.format(Message.DELETE, SparkMoVare.filePath);
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
			return null;
		}
	}

	private static void deleteOn(String deleteOnDate) {

		LinkedList<Assignment> toDelete = new LinkedList<Assignment>();
		toDelete = SearchAll.searchAll(deleteOnDate);

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