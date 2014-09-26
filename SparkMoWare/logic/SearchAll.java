package logic;

import java.util.*;

/*
 * Errors are all related to buffer.
 */
public class SearchAll {

	private static final int ID_FORMAT_LENGTH = 12;
	private static final int TIME_FORMAT_LENGTH = 4;
	private static final int DATE_FORMAT_LENGTH = 6;
	
    private static int listCount;
    
    private static final int IS_COMPLETED = 9;
    private static final int IS_ON_TIME = 8;
    
	public static LinkedList<Assignment> searchAll(String userInput){
        
    	LinkedList<Assignment> stringsFound = new LinkedList<Assignment>();
    	
        if(userInput.length() >= ID_FORMAT_LENGTH) {
        	stringsFound = searchById(userInput);
        } else if(userInput.length() == TIME_FORMAT_LENGTH && userInput.contains("[0-9]+")) {
        	stringsFound = searchByTime(userInput);
        } else if(userInput.length() == DATE_FORMAT_LENGTH && userInput.contains("[0-9]+")) {
        	stringsFound = searchByDate(userInput);
        } else {
        	stringsFound = searchByWords(userInput);
        }
        
        if(stringsFound.isEmpty()) {
        	string.format(SparkMoVare.MESSAGE_INVALID_SEARCH_PARAMETER);
        } else {
        	return stringsFound;
        }
    }
	
	private static LinkedList<Assignment> searchById(String searchId) {
		
    	LinkedList<Assignment> idFound = new LinkedList<Assignment>();
    	
    	for(listCount = 0; listCount < SparkMoVare.buffer.size(); listCount++) {
    		
    		if(SparkMoVare.buffer.get(listCount).getId().equals(searchId)) {
    			idFound.add(SparkMoVare.buffer.get(listCount));
    		}
    	}
    	return idFound;
	}
	
	// accepts into the searchedList as long as startDate or endDate is the same as input
	private static LinkedList<Assignment> searchByDate(String searchTime) {
		
		LinkedList<Assignment> timeFound = new LinkedList<Assignment>();
		
    	for(listCount = 0; listCount < SparkMoVare.buffer.size(); listCount++) {
        	
    		if(SparkMoVare.buffer.get(listCount).getStartTime().equals(searchTime)) {
    			timeFound.add(SparkMoVare.buffer.get(listCount));
    		}
    		if(SparkMoVare.buffer.get(listCount).getEndTime().equals(searchTime)) {
    			timeFound.add(SparkMoVare.buffer.get(listCount));
    		}
    	}
    	return timeFound;
	}
	
	// accepts into the searchedList as long as startTime or endTime is the same as input
	private static LinkedList<Assignment> searchByTime(String searchDate) {
		
		LinkedList<Assignment> datesFound = new LinkedList<Assignment>();
		
    	for(listCount =0; listCount < SparkMoVare.buffer.size(); listCount++) {
    		
    		if(SparkMoVare.buffer.get(listCount).getStartDate().equals(searchDate)) {
    			datesFound.add(SparkMoVare.buffer.get(listCount));
    		}
    		if(SparkMoVare.buffer.get(listCount).getEndDate().equals(searchDate)) {
    			datesFound.add(SparkMoVare.buffer.get(listCount));
    		}
    	}
    	return datesFound;
	}
    
	/*
	 * Method will check for key phrases or keyword in the title
	 * or
	 * When the searchKey is only a word long or equals to the title
	 */
	private static LinkedList<Assignment> searchByWords(String searchKeyWord) {
		
		LinkedList<Assignment> keysFound = new LinkedList<Assignment> ();
		
		for(listCount = 0; listCount < SparkMoVare.buffer.size(); listCount++) {
    		if(SparkMoVare.buffer.get(listCount).getTitle().contains(searchKeyWord)) {
            
    			if(SparkMoVare.buffer.get(listCount).getTitle().equalsIgnoreCase(searchKeyWord)) {
    				keysFound.add(SparkMoVare.buffer.get(listCount));
    			} else {
    				
    				String[] textArray = SparkMoVare.buffer.get(listCount).getTitle().split(" ");
            
    				for(int textCount = 0; textCount < textArray.length; textCount++) {
    					String checkText = textArray[textCount];
    					
    					for(int textExtendCount = textCount + 1; textExtendCount < textArray.length; textExtendCount++) {
    						checkText += textArray[textExtendCount];
    						
    						if(checkText.equalsIgnoreCase(searchKeyWord)){
        						keysFound.add(SparkMoVare.buffer.get(listCount));	
    						}
    					}
    				}
    			}
    		}        
    	}
		return keysFound;
	}    
}