package logic;

import java.util.LinkedList;

public class LogicTestDriver {

	public static String addAssignment(String id, String title, boolean isDone, String priority) {
		return Add.addAssignment(id, title, isDone, priority);
	}
	
	public static String addTask(String id, String title, String endDate, String endTime, boolean isDone, String priority) {
		return Add.addTask(id, title, endDate, endTime, isDone, priority);
	}
	
	public static String addAppointment(String id, String title, String startDate, String startTime, String endDate, String endTime, boolean isDone, String priority) {
		return Add.addAppointment(id, title, startDate, startTime, endDate, endTime, isDone, priority);
	}
	
	public static boolean serialNumberComparator(String idA, String idB){
		return Comparator.serialNumberComparator(idA, idB);
	}
	
	public static int dateComparator(String dateA, String dateB){
		return Comparator.dateComparator(dateA, dateB);
	}	

	public static int timeComparator(String timeA, String timeB){
		return Comparator.timeComparator(timeA, timeB);
	}	
	
	public static boolean isClashing(Appointment newAppointment){
		return Comparator.isClashing(newAppointment);
	}	
	
	public static String determineDate(String inputDate) {
		return DateLocal.determineDate(inputDate);
	}
	
	public static boolean dateFormatValid(String date) {
		return DateLocal.dateFormatValid(date);
	}
	
	public static String updateDate(String date){
		return updateDate(date);
	}
	
	public static void deleteAll(String duration, String startDate, String endDate){
		Delete.deleteAll(duration, startDate, endDate);
	}
	
	public static void delete(String id){
		Delete.delete(id);
	}
	
	public static LinkedList<Assignment> filterMain(LinkedList<Assignment> buffer, String filterType, String startDate, String endDate) {
		return Filter.filterMain(buffer, filterType, startDate, endDate);
	}
	
	public static LinkedList<Assignment> searchAll(LinkedList<Assignment> buffer, String userInput){
		return searchAll(buffer, userInput);
	}
	
	public static LinkedList<Assignment> multipleSortRequired(LinkedList<Assignment> sortBuffer, String sortType, String startDate, String endDate){
		return multipleSortRequired(sortBuffer, sortType, startDate, endDate);
	}
	
	public static LinkedList<Assignment> truncateList(LinkedList<Assignment> truncatedList, String startDate, String endDate) {
		return truncateList(truncatedList, startDate, endDate);
	}

	public static String determineTime(String inputTime) {
		return TimeLocal.determineTime(inputTime);
	}
		
}
