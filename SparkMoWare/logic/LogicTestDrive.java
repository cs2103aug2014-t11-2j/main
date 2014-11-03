package logic;

import java.util.LinkedList;

import parser.RefinedUserInput;


public class LogicTestDrive {

	public static String addSomething(RefinedUserInput userInput){
		return addSomething(userInput);
	}

	public static boolean serialNumberComparator(int idA, int idB){
		return Comparator.serialNumberComparator(idA, idB);
	}

	public static int dateComparator(String dateA, String dateB){
		return Comparator.dateComparator(dateA, dateB);
	}	

	public static int timeComparator(String timeA, String timeB){
		return Comparator.timeComparator(timeA, timeB);
	}	

	public static int addToBigBuffer(Appointment newAppointment) {
		return Comparator.addToBigBuffer(newAppointment);
	}

	public static boolean isClashing(Appointment newAppointment){
		return Comparator.isClashing(newAppointment);
	}	

	public static String updateDate(String date){
		return DateLocal.updateDate(date);
	}

	public static void deleteAll(String duration, String startDate, String endDate){
		Delete.deleteAll(duration, startDate, endDate);
	}

	public static void delete(int id){
		Delete.delete(id);
	}

	public static LinkedList<Assignment> filterMain(LinkedList<Assignment> buffer, String filterType, String startDate, String endDate) {
		return Filter.filterMain(buffer, filterType, startDate, endDate);
	}

	public static String removeFrontZero(String input) {
		return Comparator.removeFrontZero(input);
	}

	public static Output returnModification(LinkedList<Assignment> buffer,
			String message, int totalAssignment, int totalCompleted, int totalOnTime,
			boolean isStats, boolean isInValid) {
		return returnModification(buffer, message, totalAssignment, totalCompleted, totalOnTime, isStats, isInValid);
	}

	public static LinkedList<Assignment> searchAll(LinkedList<Assignment> buffer, String userInput){
		return searchAll(buffer, userInput);
	}

	public static LinkedList<Assignment> searchByDeadline(LinkedList<Assignment> buffer, String searchDeadline) {
		return searchByDeadline(buffer, searchDeadline);
	}

	public static LinkedList<Assignment> multipleSortRequired(LinkedList<Assignment> sortBuffer, String sortType, String startDate, String endDate){
		return multipleSortRequired(sortBuffer, sortType, startDate, endDate);
	}

	public static LinkedList<Assignment> truncateList(LinkedList<Assignment> truncatedList, String startDate, String endDate) {
		return truncateList(truncatedList, startDate, endDate);
	}
}
