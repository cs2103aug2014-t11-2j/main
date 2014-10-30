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
	
	static LinkedList<Appointment> modifyBuffer(LinkedList<Assignment> buffer) {
		
		LinkedList<Appointment> modifiedBuffer = new LinkedList<Appointment>();
		Appointment temp = new Appointment();
        ListIterator<Assignment> bufferIterator = buffer.listIterator();
        
        while(bufferIterator.hasNext()) {
            if(bufferIterator.next().getAssignType().equals(AssignmentType.APPOINTMENT)) {
                temp = ((Appointment) bufferIterator.previous());
                modifiedBuffer.addLast(temp);
                
            } else if(bufferIterator.previous().getAssignType().equals(AssignmentType.TASK)) {
                Task tmp = (Task) bufferIterator.next();
                modifiedBuffer.addLast(addTask(tmp));

			} else {
				
                Assignment tmp = (Assignment) bufferIterator.next();
                modifiedBuffer.add(addAssignment(tmp));
			}
		}
		return modifiedBuffer;
	}
    
    private static Appointment addTask(Task tmp) {
        
        Appointment temp = new Appointment();
        
        temp.setId(tmp.getId());
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
        temp.setTitle(tmp.getTitle());
        temp.setAssignType(AssignmentType.ASSIGNMENT);
        temp.setStartDate(DEFAULT_NONE);
        temp.setStartTime(DEFAULT_NONE);
        temp.setEndDate(DEFAULT_NONE);
        temp.setEndTime(DEFAULT_NONE);
        temp.setIsDone(tmp.getIsDone());
        temp.setIsOnTime(tmp.getIsOnTime());
        temp.setPriority(tmp.getPriority());
        
        return temp;
    }

}