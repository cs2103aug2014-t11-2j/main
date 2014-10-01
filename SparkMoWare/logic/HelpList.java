package logic;

import java.util.LinkedList;

public class HelpList {
	
	public static LinkedList<String> helpLine () {
		
		LinkedList<String> helpList = new LinkedList<String>();
		
		String addName = "ADD: ";
		String addTaskHelp = "add <Task Title> <ddmmyyyy> <hhmm>";
		String addAppointmentHelp = "add <Appointment Title> <ddmmyyyy> <hhmm>";
		
		String editName = "EDIT: ";
		String editHelp = "edit <SIN> <Type> <New Version>";
		
		String deleteName = "DELETE: ";
		String deleteHelp = "delete <SIN>";
		String deleteOnHelp = "deleteAll on <ddmmyyyy>";
		String deleteBeforeHelp = "deleteAll before <ddmmyyyy>";
		String deletePeriodHelp = "deleteAll between <ddmmyyyy> <ddmmyyyy>";
		String clearHelp = "clear";
		
		String tentativeName = "TENTATIVE: ";
		String tentativeHelp1 = "tentative <Num of Dates> <Title>";
		String tentativeHelp2 = "<ddmmyyyy> <hhmm> <ddmmyyyy> <hhmm>";
		String confirmHelp = "confirm <SIN> <ddmmyyyy> <hhmm> <ddmmyyyy> <hhmm>";
		
		String searchName = "SEARCH: ";
		String searchHelp = "search <SIN/ DATE/ Time/ Title>";
		
		String redoUndoName = "REDO/ UNDO: ";
		String undoHelp = "undo";
		String redoHelp = "redo";
		
		String statisticName = "STATISTIC: ";
		String statisticHelp = "statistic";
		
		String sortName = "SORT: ";
		String sortHelp = "sort <Type>";
		
		String filterName = "FILTER: ";
		String filterHelp = "filter <Type>";
		
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