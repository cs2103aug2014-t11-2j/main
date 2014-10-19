package logic;

import java.util.LinkedList;

public class ModifyOutput {
	
	private static Output returnOutput = new Output();
	
	public static Output returnModification(LinkedList<Assignment> buffer,
			String message, int totalAssignment, int totalCompleted, int totalOnTime,
			boolean isStats) {
		
		returnOutput.setReturnBuffer(buffer);
		returnOutput.setFeedback(message);
		returnOutput.setTotalAssignment(totalAssignment);
		returnOutput.setTotalCompleted(totalCompleted);
		returnOutput.setTotalOnTime(totalOnTime);
		returnOutput.setIsStats(isStats);
		
		return returnOutput;
	}
}
