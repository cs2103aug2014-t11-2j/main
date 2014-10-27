package logic;

import java.util.LinkedList;
import java.util.ListIterator;

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

	public static void printList(LinkedList<Appointment> list) {
		
		ListIterator<Appointment> listIterate = list.listIterator();
		
		while(listIterate.hasNext()) {
			System.out.println(listIterate.next().toString());
		}
		// assertTrue(list.size()>0);
	}

	public static void printToUser(String output) {

		if (!output.equals("")) {
			System.out.println(output);
		}
	}
}
