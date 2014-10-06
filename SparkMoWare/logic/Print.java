package logic;

import logic.Assignment;

import java.util.ArrayList;
import java.util.LinkedList;

public class Print {

	public static String display() {

		for(int i = 0; i < SparkMoVare.buffer.size(); i++){
			String lineToAdd = "";
			lineToAdd += String.valueOf(i+1);
			lineToAdd += ". ";
			lineToAdd += SparkMoVare.buffer.get(i);
			System.out.println(lineToAdd);
		}

		if (SparkMoVare.getLineCount() == 0){
			return (SparkMoVare.filePath + " is empty");
		}
		else{
			return "";
		}	
	}

	public static void printList(LinkedList<Assignment> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

	public static void printHelpList(ArrayList<String> list) {
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
