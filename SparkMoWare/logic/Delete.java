package logic;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.*;

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
		DELETEALL_ON, DELETEALL_BEFORE, DELETEALL_BETWEEN, CLEAR;
	}
	
	protected static void deleteAll(String duration, String startDate, String endDate) {
		
		// assert(startDate.length() == 8 && endDate.length() == 8);
		
		switch (getDuration(duration)) {

		case DELETEALL_ON:
			deleteOn(endDate);
			break;

		case DELETEALL_BEFORE:
			startDate = DateLocal.getStartDate();
			deleteBetween(endDate, startDate);
			break;

		case DELETEALL_BETWEEN:
			deleteBetween(endDate, startDate);
			break;

		default:
			InternalStorage.getBuffer().clear();
			break;
		}
	}
	
	protected static void delete(String id) {
		 
		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(InternalStorage.getBuffer(), id);
		
		// PS: Have to check if nullAssignment will increase the numAppointment by 1
		Appointment nullAssignment = new Appointment();
		nullAssignment.setNumAppointment(nullAssignment.getNumAppointment() - 1); 
		
		if(idFound.size() > 0) {
			
			int bufferPosition = InternalStorage.getBufferPosition(id);
						
			InternalStorage.getBuffer().remove(bufferPosition);
			
			nullAssignment.setNumAppointment(nullAssignment.getNumAppointment() - 1);
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
			// assertTrue(duration.equalsIgnoreCase("CLEAR"));
			return DeleteAllType.CLEAR;
		}
	}

	private static void deleteOn(String deleteOnDate) {

		LinkedList<Assignment> toDelete = new LinkedList<Assignment>();
		toDelete = SearchAll.searchByDeadline(InternalStorage.getBuffer(), deleteOnDate);

		for (int toDeleteCount = 0; toDeleteCount < toDelete.size(); toDeleteCount++) {
			delete(toDelete.get(toDeleteCount).getId());
		}
		// assertFalse(SearchAll.searchByDeadline(InternalStorage.getBuffer(), deleteOnDate).size()>0);
	}

	private static void deleteBetween(String deleteTill, String startDate) {

		while (!deleteTill.equals(startDate)) {
			deleteOn(deleteTill);
			deleteTill = DateLocal.updateDate(deleteTill);
		}
		deleteOn(startDate);
		
		// assertFalse(SearchAll.searchByDeadline(InternalStorage.getBuffer(), startDate).size()>0);
	}
}