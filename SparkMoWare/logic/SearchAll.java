package logic;

import java.util.*;

public class SearchAll {

	private static final int ID_FORMAT_LENGTH = 8;
	private static final int TIME_FORMAT_LENGTH = 4;
	private static final int DATE_FORMAT_LENGTH = 6;
	
    private static LinkedList<Assignment> search(String userInput){
        
        LinkedList<Assignment> stringsFound = new LinkedList<Assignment>();
        int listCount;
        
        
        if(userInput.length() >= ID_FORMAT_LENGTH) {
        	
        	for(listCount = 0; listCount < buffer.size(); listCount++) {
        		
        		if(buffer.get(listCount).getId().equals(userInput)) {
        			stringsFound.add(buffer.get(listCount));
        		}
        	}
        }
        // accepts into the searchList as long as startTime or endTime is the same as input
        else if(userInput.length() == TIME_FORMAT_LENGTH && userInput.contains("[0-9]+")) {
        	
        	for(listCount = 0; listCount < buffer.size(); listCount++) {
        	
        		if(buffer.get(listCount).getStartTime().equals(userInput)) {
        			stringsFound.add(buffer.get(listCount));
        		}
        		if(buffer.get(listCount).getEndTime().equals(userInput)) {
        			stringsFound.add(buffer.get(listCount));
        		}
        	}
        }
        // accepts into the searchList as long as startDate or endDate is the same as input
        else if(userInput.length() == DATE_FORMAT_LENGTH) {
        	
        	for(listCount =0; listCount < buffer.size(); listCount++) {
        		
        		if(buffer.get(listCount).getStartDate().equals(userInput)) {
        			stringsFound.add(buffer.get(listCount));
        		}
        		if(buffer.get(listCount).getEndDate().equals(userInput)) {
        			stringsFound.add(buffer.get(listCount));
        		}
        	}
        }
        else {
        	for(listCount = 0; listCount < buffer.size(); listCount++) {
        		if(buffer.get(listCount).getTitle().contains(userInput)) {
                
        			// When the title is only a word long or equals to the sentence
        			if(buffer.get(listCount).getTitle().equals(userInput)) {
        				stringsFound.add(buffer.get(listCount));
        			}
        			// Check for phrases or word in the title
        			else {
        				
        				String[] textArray = buffer.get(listCount).getTitle().split(" ");
                
        				for(int textCount = 0; textCount < textArray.length; textCount++) {
        					StringBuilder checkText = new StringBuilder(textArray[textCount]);
        					
        					for(int textExtendCount = textCount + 1; textExtendCount < textArray.length; textExtendCount++) {
        						checkText.append(textArray[textExtendCount]);
        						
        						if(checkText.equals(userInput)){
            						stringsFound.add(buffer.get(listCount));	
        						}
        					}
        				}
        			}
        		}        
        	}
        }
        return stringsFound;
    }    
}