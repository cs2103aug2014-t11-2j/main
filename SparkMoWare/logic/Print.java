package logic;
import static org.junit.Assert.*;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;

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
		
		assertTrue(list.size()>0);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

	public static void printHelpList(ArrayList<String> list) {
		
		assertTrue(list.size()>0);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public static void printToUser(String output) {

		if (!output.equals("")) {
			System.out.println(output);
		}
	}
}
