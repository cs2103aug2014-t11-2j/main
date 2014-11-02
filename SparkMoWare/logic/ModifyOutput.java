package logic;

import java.util.LinkedList;
import java.util.ListIterator;

import logic.Assignment.AssignmentType;

public class ModifyOutput {
	
	private static Output returnOutput = new Output();
	
	private static final String DEFAULT_NONE = "-";
	
	protected static Output returnModification(LinkedList<Assignment> buffer,
			String message, int totalAssignment, int totalCompleted, int totalOnTime,
			boolean isStats, boolean isInValid) {
		
		LinkedList<Appointment> returnBuffer = new LinkedList<Appointment>();

        if(buffer.size() > 0) {
            returnBuffer.addAll(modifyBuffer(buffer));
        }

		returnOutput.setReturnBuffer(returnBuffer);
		returnOutput.setFeedback(message);
		returnOutput.setTotalAssignment(totalAssignment);
		returnOutput.setTotalCompleted(totalCompleted);
		returnOutput.setTotalOnTime(totalOnTime);
		returnOutput.setIsStats(isStats);
		returnOutput.setIsInvalid(isInValid);
		
		return returnOutput;
	}
	
	private static LinkedList<Appointment> modifyBuffer(LinkedList<Assignment> buffer) {
		
		LinkedList<Appointment> modifiedBuffer = new LinkedList<Appointment>();
		Assignment tempOriginal = new Assignment();
		Appointment temp = new Appointment();
        ListIterator<Assignment> bufferIterator = buffer.listIterator();
                
        while(bufferIterator.hasNext()) {
        	tempOriginal= bufferIterator.next();
        	
            if(tempOriginal.getAssignType().equals(AssignmentType.APPT)) {
                temp = ((Appointment) tempOriginal);
                modifiedBuffer.addLast(temp);
                
            } else if(tempOriginal.getAssignType().equals(AssignmentType.TASK)) {
                Task tmp = ((Task) tempOriginal);
                modifiedBuffer.addLast(addTask(tmp));
                
			} else if(tempOriginal.getAssignType().equals(AssignmentType.TNTV)) {
				Tentative tmp = ((Tentative) tempOriginal);
				modifiedBuffer.addAll(addTentativeSlots(tmp));
			} else {
                modifiedBuffer.add(addAssignment(tempOriginal));
			}
		}
		return modifiedBuffer;
	}
    
    private static Appointment addTask(Task tmp) {
        
        Appointment temp = new Appointment();
        
        temp.setId(tmp.getId());
        temp.setDateCreation(tmp.getDateCreation());
        temp.setIndex(tmp.getIndex());
        temp.setTitle(tmp.getTitle());
        temp.setAssignType(AssignmentType.TASK);
        temp.setStartDate(DEFAULT_NONE);
        temp.setStartTime(DEFAULT_NONE);
        temp.setEndDate(tmp.getEndDate());
        temp.setEndTime(tmp.getEndTime());
        temp.setIsDone(tmp.getIsDone());
        temp.setIsOnTime(tmp.getIsOnTime());
        temp.setPriority(tmp.getPriority());
        
        return temp;
    }
    
    private static Appointment addAssignment(Assignment tmp) {
        
        Appointment temp = new Appointment();
        
        temp.setId(tmp.getId());
        temp.setDateCreation(tmp.getDateCreation());
        temp.setIndex(tmp.getIndex());
        temp.setTitle(tmp.getTitle());
        temp.setAssignType(AssignmentType.ASGN);
        temp.setStartDate(DEFAULT_NONE);
        temp.setStartTime(DEFAULT_NONE);
        temp.setEndDate(DEFAULT_NONE);
        temp.setEndTime(DEFAULT_NONE);
        temp.setIsDone(tmp.getIsDone());
        temp.setIsOnTime(tmp.getIsOnTime());
        temp.setPriority(tmp.getPriority());
        
        return temp;
    }
    
    private static LinkedList<Appointment> addTentativeSlots(Tentative tmp) {
    	
    	LinkedList<Appointment> tempStore = new LinkedList<Appointment>();
    	
    	for(int slotsCount = 0; slotsCount < tmp.getStartDate().size(); slotsCount++) {
    		tempStore.add(addSlots(tmp, slotsCount));
    	}
    	return tempStore;
    }
    
    private static Appointment addSlots(Tentative tmp, int slotsCount) {
    	
    	Appointment temp = new Appointment();
    	String startDate = tmp.getStartDate().get(slotsCount);
    	String startTime = tmp.getStartTime().get(slotsCount);
    	String endDate = tmp.getEndDate().get(slotsCount);
    	String endTime = tmp.getEndTime().get(slotsCount);
    	
    	temp.setId(tmp.getId());
        temp.setDateCreation(tmp.getDateCreation());
        temp.setIndex(tmp.getIndex());
    	temp.setTitle(tmp.getTitle());
    	temp.setAssignType(AssignmentType.TNTV);
    	temp.setStartDate(startDate);
    	temp.setStartTime(startTime);
    	temp.setEndDate(endDate);
    	temp.setEndTime(endTime);
    	temp.setIsDone(tmp.getIsDone());
    	temp.setIsOnTime(tmp.getIsOnTime());
    	temp.setPriority(tmp.getPriority());
    	
    	return temp;
    }
}