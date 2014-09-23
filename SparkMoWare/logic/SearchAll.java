package logic;


import java.util.*;

public class SearchAll {

	public static void main(String[] args){
		searchAll(userCommand);
	}

	private static final int ID_MAX_LIMIT = 150;
	private static final int TIME_FORMAT_LENGTH = 4;
	private static final int DATE_FORMAT_LENGTH = 6;
	
    private static LinkedList<Assignment> search(String userCommand){
        
        String[] stringArray = userCommand.split(" ", 2);
        String stringToSearch = stringArray[1];
        LinkedList<Assignment> stringsFound = new LinkedList<Assignment>();
        int listCount;
        
        if(Integer.parseInt(stringToSearch) < ID_MAX_LIMIT) {
        	int searchId = Integer.parseInt(stringToSearch);
        	
        	for(listCount = 0; listCount < buffer.size(); listCount++) {
        		
        		if(buffer.get(listCount).getId() == searchId) {
        			stringsFound.add(buffer.get(listCount));
        		}
        	}
        }
        else if(stringToSearch.length() == TIME_FORMAT_LENGTH && stringToSearch.contains("[0-9]+")) {
        	
        	for(listCount = 0; listCount < buffer.size(); listCount++) {
        	
        		if(buffer.get(listCount).getStartTime() == searchTime) {
        			stringsFound.add(buffer.get(listCount));
        		}
        		if(buffer.get(listCount).getEndTime() == searchTime) {
        			stringsFound.add(buffer.get(listCount));
        		}
        	}
        }
        else if(stringToSearch.length() == DATE_FORMAT_LENGTH && stringToSearch.contains("\"")) {
        	
        	for(listCount =0; listCount < buffer.size(); listCount++) {
        		
        		if(buffer.get(listCount).getStartDate() == searchDate) {
        			stringsFound.add(buffer.get(listCount));
        		}
        		if(buffer.get(listCount).getEndDate() == searchDate) {
        			stringsFound.add(buffer.get(listCount));
        		}
        	}
        }
        else {
        	for(listCount = 0; listCount < buffer.size(); listCount++) {
        		if(buffer.get(listCount).getTitle().contains(stringToSearch)) {
                
        			// When the title is only a word long or equals to the sentence
        			if(buffer.get(listCount).getTitle() == stringToSearch) {
        				stringsFound.add(buffer.get(listCount));
        			}
        			else {
        				
        				String[] textArray = buffer.get(listCount).getTitle().split(" ");
                
        				for(int textCount = 0; textCount < textArray.length; textCount++) {
        					StringBuilder checkText = new StringBuilder(textArray[textCount]);
        					
        					for(int textExtendCount = textCount + 1; textExtendCount < textArray.length; textExtendCount++) {
        						checkText.append(textArray[textExtendCount]);
        						
        						if(checkText.equals(stringToSearch)){
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