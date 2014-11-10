package logic;

import java.util.LinkedList;
import java.util.ListIterator;

//@author A0117057J

public class Print {

	public static String display() {

		for(int i = 0; i < InternalStorage.getBuffer().size(); i++){
			String lineToAdd = "";
			lineToAdd += String.valueOf(i+1);
			lineToAdd += ". ";
			lineToAdd += InternalStorage.getBuffer().get(i);
			System.out.println(lineToAdd);
		}

		if (InternalStorage.getLineCount() == 0){
			return (InternalStorage.getFilePath() + " is empty");
		}
		else{
			return "";
		}	
	}

	public static void printList(LinkedList<Mission> list) {

		ListIterator<Mission> listIterate = list.listIterator();

		while(listIterate.hasNext()) {
			System.out.println(listIterate.next().toString());
		}
	}
	
	public static void printAssignmentList(LinkedList<Assignment> list) {

		ListIterator<Assignment> listIterate = list.listIterator();

		while(listIterate.hasNext()) {
			System.out.println(listIterate.next().toString());
		}
	}
	
	public static void printToUser(String output) {

		if (!output.equals("")) {
			System.out.println(output);
		}
	}
}