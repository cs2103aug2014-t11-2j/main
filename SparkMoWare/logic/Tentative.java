package logic;

import java.util.*;

/*
 * Onli error is the ID auto-generator.
 */
public class Tentative {
	
	public static final int TYPE_TENTATIVE = 2;
	
	public static Scanner scanner = new Scanner(System.in);
	
	public static void addTentative(String numOfTentative) {

        int tentativeNum = Integer.parseInt(numOfTentative);
        
        for(int tentativeCount = 1; tentativeCount < tentativeNum; tentativeCount++) {
        	
        	String[] inputArray = scanner.nextLine().split(" ");
        	
        	String endTime = inputArray[inputArray.length - 1];
        	String endDate = inputArray[inputArray.length - 2];
        	String startTime = inputArray[inputArray.length - 3];
        	String startDate = inputArray[inputArray.length - 4];
        	
        	String title = inputArray[0];
        	
        	for(int titleLength = 1; titleLength < inputArray.length - 4; titleLength++) {
        		title += inputArray[titleLength];        			
        	}
        	
        	title += "[tentative]";
        	
        	String idGen = "";
        	
        	//addTask(ID, title, type, startDate, startTime, endDate, endTime, isCompletion)
        	AddTask.addTask(idGen, title, TYPE_TENTATIVE, startDate, startTime, endDate, endTime, false);
        }
	}
}