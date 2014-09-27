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
	
	private static final String MESSAGE_DELETE_ON = "all content(s) of date %1$s is(are) deleted";
	private static final String MESSAGE_DELETE_BEFORE = "all content(s) before date %1$s is(are) deleted";
	private static final String MESSAGE_DELETE_BETWEEN = "all content(s) from date %1$s to date %2$s is(are) deleted";
	private static final String MESSAGE_DELETE = "all content deleted from %1$s";
	
	protected static String delete(String id) {
		
		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(id);
		
		// PS:Have to check if nullAssignment will increase the numAppointment by 1
		Assignment nullAssignment = new Assignment();
		nullAssignment.setNumAppointment(nullAssignment.getNumAppointment() - 1 ); 
		
		if(idFound.size() == 0) {
			return String.format(SparkMoVare.MESSAGE_DOES_NOT_EXISTS, "Serial Number " + id);
		} else {
			String stringDeleted;
			int bufferPosition = SparkMoVare.getBufferPosition(id);
			
			stringDeleted = SparkMoVare.buffer.get(bufferPosition).getTitle();
			SparkMoVare.buffer.remove(bufferPosition);
			
			nullAssignment.setNumAppointment(nullAssignment.getNumAppointment() - 1);
			
			return String.format(SparkMoVare.MESSAGE_DELETED, SparkMoVare.filePath, stringDeleted);
		}
	}

	protected static String deleteAll(String duration, String endDate, String startDate) {

		switch (getDuration(duration)) {

		case DELETEALL_ON:
			deleteOn(endDate);
			return String.format(MESSAGE_DELETE_ON, endDate);

		case DELETEALL_BEFORE:
			startDate = SparkMoVare.buffer.getFirst().getEndDate();
			deleteBetween(endDate, startDate);
			return String.format(MESSAGE_DELETE_BEFORE, endDate);

		case DELETEALL_BETWEEN:
			deleteBetween(endDate, startDate);
			return String.format(MESSAGE_DELETE_BETWEEN, endDate, startDate);

		default:
			SparkMoVare.buffer.clear();
			return String.format(MESSAGE_DELETE, SparkMoVare.filePath);
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
			deleteTill = updateDate(deleteTill);
		}
		deleteOn(startDate);
	}

	// decrementing date
	protected static String updateDate(String date) {

		String[] endDate = new String[3];

		endDate[0] = date.substring(0, 2);
		endDate[1] = date.substring(2, 4);
		endDate[2] = date.substring(4, 8);
		
		int[] intEndDate = new int[3];
		String updatedDate = "";
		
		for(int dateCharCount = 0; dateCharCount < endDate.length; dateCharCount++) {
			intEndDate[dateCharCount] = Integer.parseInt(endDate[dateCharCount]); 
		}

		if ((intEndDate[0] - 1) == 0) {
			if ((intEndDate[1] - 1) == 0) {
				intEndDate[2]--;
				intEndDate[1] = 12;
				intEndDate[0] = 31;
			} else {
				intEndDate[1] = updateMonth(intEndDate[1], intEndDate[2]);
			}
		} else {
			intEndDate[0]--;
		}
		
		for(int dateIntCount = 0; dateIntCount < intEndDate.length; dateIntCount++) {
			updatedDate += String.valueOf(intEndDate[dateIntCount]);
		}
		
		return updatedDate;
	}

	private static int updateMonth(int intEndMonth, int intEndYear) {

		if(intEndMonth == 2){			
			if (intEndYear % 4 == 0){
				return 29;
			} else {
				return 28;
			}				
		} else if(intEndMonth == 4 || intEndMonth == 6 || intEndMonth == 9 || intEndMonth == 11) {
			return 30;
		} 
		return 31;	
	}
}