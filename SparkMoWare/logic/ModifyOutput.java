package logic;

import java.util.LinkedList;
import logic.Assignment.AssignmentType;

public class ModifyOutput {
	
	private static Output returnOutput = new Output();
	
	private static final String DEFAULT_NONE = "-";
	
	public static Output returnModification(LinkedList<Assignment> buffer,
			String message, int totalAssignment, int totalCompleted, int totalOnTime,
			boolean isStats, boolean isInValid) {
		
		LinkedList<Appointment> returnBuffer = new LinkedList<Appointment>();
		
		returnBuffer = modifyBuffer(buffer);
		
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
		
		for(int bufferCount = 0; bufferCount < modifiedBuffer.size(); bufferCount++) {
			if(buffer.get(bufferCount).getAssignType().equals(AssignmentType.APPOINTMENT)) {
				temp = ((Appointment) buffer.get(bufferCount));
				modifiedBuffer.add(temp);
				
			} else if(buffer.get(bufferCount).getAssignType().equals(AssignmentType.TASK)) {
				temp = ((Appointment) buffer.get(bufferCount));
				temp.setStartDate(DEFAULT_NONE);
				temp.setStartTime(DEFAULT_NONE);
				
			} else {
				temp = ((Appointment) buffer.get(bufferCount));
				temp.setStartDate(DEFAULT_NONE);
				temp.setStartTime(DEFAULT_NONE);
				temp.setEndDate(DEFAULT_NONE);
				temp.setEndTime(DEFAULT_NONE);
			}
		}
		return modifiedBuffer;
	}
}