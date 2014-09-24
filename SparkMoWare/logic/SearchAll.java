package logic;

import java.util.*;

/*
 * Errors are all related to buffer.
 * 
 */
public class SearchAll {

	private static final int ID_FORMAT_LENGTH = 8;
	private static final int TIME_FORMAT_LENGTH = 4;
	private static final int DATE_FORMAT_LENGTH = 6;
    private static int listCount;
        
	private static LinkedList<Assignment> searchById(String searchId) {
		
    	LinkedList<Assignment> idFound = new LinkedList<Assignment>();
    	
    	for(listCount = 0; listCount < buffer.size(); listCount++) {
    		
    		if(buffer.get(listCount).getId().equals(searchId)) {
    			idFound.add(buffer.get(listCount));
    		}
    	}
    	return idFound;
	}
	
	private static LinkedList<Assignment> searchByDate(String searchTime) {
		
		LinkedList<Assignment> timeFound = new LinkedList<Assignment>();
		
    	for(listCount = 0; listCount < buffer.size(); listCount++) {
        	
    		if(buffer.get(listCount).getStartTime().equals(searchTime)) {
    			timeFound.add(buffer.get(listCount));
    		}
    		if(buffer.get(listCount).getEndTime().equals(searchTime)) {
    			timeFound.add(buffer.get(listCount));
    		}
    	}
    	return timeFound;
	}
	
	private static LinkedList<Assignment> searchByTime(String searchDate) {
		
		LinkedList<Assignment> datesFound = new LinkedList<Assignment>();
		
    	for(listCount =0; listCount < buffer.size(); listCount++) {
    		
    		if(buffer.get(listCount).getStartDate().equals(searchDate)) {
    			datesFound.add(buffer.get(listCount));
    		}
    		if(buffer.get(listCount).getEndDate().equals(searchDate)) {
    			datesFound.add(buffer.get(listCount));
    		}
    	}
    	return datesFound;
	}
    
	private static LinkedList<Assignment> searchByWords(String searchKeyWord) {
		
		LinkedList<Assignment> keysFound = new LinkedList<Assignment> ();
		
		for(listCount = 0; listCount < buffer.size(); listCount++) {
    		if(buffer.get(listCount).getTitle().contains(searchKeyWord)) {
            
    			// When the title is only a word long or equals to the sentence
    			if(buffer.get(listCount).getTitle().equals(searchKeyWord)) {
    				keysFound.add(buffer.get(listCount));
    			}
    			// Check for key phrases or keyword in the title
    			else {
    				
    				String[] textArray = buffer.get(listCount).getTitle().split(" ");
            
    				for(int textCount = 0; textCount < textArray.length; textCount++) {
    					StringBuilder checkText = new StringBuilder(textArray[textCount]);
    					
    					for(int textExtendCount = textCount + 1; textExtendCount < textArray.length; textExtendCount++) {
    						checkText.append(textArray[textExtendCount]);
    						
    						if(checkText.equals(searchKeyWord)){
        						keysFound.add(buffer.get(listCount));	
    						}
    					}
    				}
    			}
    		}        
    	}
		return keysFound;
	}
	
	public static LinkedList<Assignment> searchAll(String userInput){
        
    	LinkedList<Assignment> stringsFound = new LinkedList<Assignment>();
    	
        if(userInput.length() >= ID_FORMAT_LENGTH) {
        	stringsFound = searchById(userInput);
        }
        // accepts into the searchedList as long as startTime or endTime is the same as input
        else if(userInput.length() == TIME_FORMAT_LENGTH && userInput.contains("[0-9]+")) {
        	stringsFound = searchByTime(userInput);
        }
        // accepts into the searchedList as long as startDate or endDate is the same as input
        else if(userInput.length() == DATE_FORMAT_LENGTH && userInput.contains("[0-9]+")) {
        	stringsFound = searchByDate(userInput);
        }
        else {
        	stringsFound = searchByWords(userInput);
        }
        return stringsFound;
    }    
}