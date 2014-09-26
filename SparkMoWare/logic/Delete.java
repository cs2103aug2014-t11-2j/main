package logic;

import java.util.LinkedList;

public class Delete {

	enum DeleteAllType {
		DELETEALL_ON, DELETEALL_BEFORE, DELETEALL_BETWEEN;
	}

	public static String delete(String id) {
		LinkedList<Assignment> idFound = new LinkedList<Assignment>();
		idFound = SearchAll.searchAll(id);
		
		if(idFound.size() == 0) {
			return String.format(SparkMoVare.MESSAGE_DOES_NOT_EXISTS, "Serial Number" + id);
		} else {
			String stringDeleted = "";
			int bufferPosition = SparkMoVare.getBufferPosition(id);
			
			stringDeleted = SparkMoVare.buffer.get(bufferPosition).getTitle();
			SparkMoVare.buffer.remove(bufferPosition);

			return String.format(SparkMoVare.MESSAGE_DELETED, SparkMoVare.filePath, stringDeleted);
		}
	}

	private static String deleteAll(String duration, String endDate, String startDate) {

		/*
		 * the method below passes linked list element to deleteTask to delete
		 * that element. May need to change according to input parameter of
		 * deleteTask method.
		 */

		switch (convertToEnum(duration)) {

		case DELETEALL_ON:

			deleteOn(endDate);
			return ("all content(s) of date " + endDate + " is(are) deleted");

		case DELETEALL_BEFORE:

			startDate = SparkMoVare.buffer.getFirst().getEndDate();
			deleteBetween(endDate, startDate);
			return ("all content(s) before date " + endDate + " is(are) deleted");

		case DELETEALL_BETWEEN:

			deleteBetween(endDate, startDate);
			return ("all content(s) from date " + endDate + "to date "
					+ startDate + " is(are) deleted");

		default:

			SparkMoVare.buffer.clear();
			return ("all content deleted from " + SparkMoVare.filePath);

		}

	}

	private static DeleteAllType convertToEnum(String duration) {

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

	private static DeleteAllType getDuration(String userInput) {

		String[] checkDuration = userInput.split(" ");

		if (checkDuration[1].length() == 2) {
			return DeleteAllType.DELETEALL_ON;
		} else if (checkDuration[1].length() == 6) {
			return DeleteAllType.DELETEALL_BEFORE;
		} else if (checkDuration[1].length() == 7) {
			return DeleteAllType.DELETEALL_BETWEEN;
		} else {
			return null;
		}

	}

	private static void deleteOn(String deleteOnDate) {

		// following lines are to store & delete assignments on the particular
		// date
		LinkedList<Assignment> toDelete = new LinkedList<Assignment>();
		toDelete = SparkMoVare.search(" " + deleteOnDate);

		for (int i = 0; i < toDelete.size(); i++) {

			delete(toDelete.get(i).getId());

		}
	}

	private static void deleteBetween(String deleteTill, String startDate) {

		String[] endDate = deleteTill.split("(?<=\\G.{2})");

		while (!deleteTill.equals(startDate)) {

			deleteOn(deleteTill);
			deleteTill = updateDate(deleteTill);

		}

		deleteOn(startDate);
	}

	// decrementing date
	private static String updateDate(String date) {

		
		int[] intEndDate = Integer.parseInt(date); // if not use a for-loop to
													// convert strings to int n
													// store into the int array
		String updatedDate;

		if ((intEndDate[0] - 1) == 0) {
			if ((intEndDate[1] - 1) < 0) {
				intEndDate[2]--;
				intEndDate[1] = 12;
				intEndDate[0] = 31;
			} else {
				intEndDate[2]--;
				intEndDate[1] = updateMonth(intEndDate[1], intEndDate[2]);
			}
		} else {
			intEndDate[0]--;
		}

		updatedDate = String.valueOf(Arrays.toString(intEndDate));
		return date = updatedDate.toString();

	}

	private static int updateMonth(int intEndMonth, int intEndYear) {

		
		if(intEndMonth == 2){
			
			if (intEndYear%4 == 0){
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

