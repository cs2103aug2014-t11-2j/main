package logic;

import java.util.ArrayList;

/*
 * Enter after every node
 * 
 */
public class HelpList {
	
	private static final String addName = "ADD: ";
	private static final String addTaskHelp = "add <Task Title> <ddmmyyyy> <hhmm>";
	private static final String addAppointmentHelp = "add <Appointment Title> <ddmmyyyy> <hhmm>";
	
	private static final String editName = "EDIT: ";
	private static final String editHelp = "edit <SIN> <Type> <New Version>";
	
	private static final String deleteName = "DELETE: ";
	private static final String deleteHelp = "delete <SIN>";
	private static final String deleteOnHelp = "clear on <ddmmyyyy>";
	private static final String deleteBeforeHelp = "clear before <ddmmyyyy>";
	private static final String deletePeriodHelp = "clear between <ddmmyyyy> <ddmmyyyy>";
	private static final String clearHelp = "clear";
	
	private static final String tentativeName = "TENTATIVE: ";
	private static final String tentativeHelp1 = "tentative <Num of Dates> <Title>";
	private static final String tentativeHelp2 = "<ddmmyyyy> <hhmm> <ddmmyyyy> <hhmm>";
	private static final String confirmHelp = "confirm <SIN> <ddmmyyyy> <hhmm> <ddmmyyyy> <hhmm>";
	
	private static final String searchName = "SEARCH: ";
	private static final String searchHelp = "search <SIN/ DATE/ Time/ Title>";
	
	private static final String redoUndoName = "REDO/ UNDO: ";
	private static final String undoHelp = "undo";
	private static final String redoHelp = "redo";
	
	private static final String statisticName = "STATISTIC: ";
	private static final String statisticHelp = "statistic";
	
	private static final String sortName = "SORT: ";
	private static final String sortHelp = "sort <Type>";
	
	private static final String filterName = "FILTER: ";
	private static final String filterHelp = "filter <Type>";
	
	public static ArrayList<String> helpLine () {
		
		ArrayList<String> helpList = new ArrayList<String>();
		
		helpList.add(addName);
		helpList.add(addTaskHelp);
		helpList.add(addAppointmentHelp);
		
		helpList.add(editName);
		helpList.add(editHelp);
		
		helpList.add(deleteName);
		helpList.add(deleteHelp);
		helpList.add(deleteOnHelp);
		helpList.add(deleteBeforeHelp);
		helpList.add(deletePeriodHelp);
		helpList.add(clearHelp);
		
		helpList.add(tentativeName);
		helpList.add(tentativeHelp1);
		helpList.add(tentativeHelp2);
		helpList.add(confirmHelp);
		
		helpList.add(searchName);
		helpList.add(searchHelp);
		
		helpList.add(redoUndoName);
		helpList.add(undoHelp);
		helpList.add(redoHelp);
		
		helpList.add(statisticName);
		helpList.add(statisticHelp);
		
		helpList.add(sortName);
		helpList.add(sortHelp);
		helpList.add(filterName);
		helpList.add(filterHelp);
		
		return helpList;
	}
}