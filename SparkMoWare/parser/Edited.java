package parser;


/* Eventually this will be transfered to logic 
 * Most methods will be stubs, instead having a description/psuedocode
 */

public class Edited {

	@SuppressWarnings("unused")
	public static void Editedmethod(RefinedUserInput editedContent) {
		String id = editedContent.getId();
		
		//method to confirm id exists in buffer
		
		if(false) {
			editedContent.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
		}
		
		/* method to create a new assignment with attributes from the following get methods
		 * 
		 * editedContent.getTitle()
		 * editedContent.getStartDate()
		 * editedContent.getStartTime()
		 * editedContent.getEndDate()
		 * editedContent.getEndTime()
		 * editedContent.getPriority()
		 */
		
		//Assignment has not determined which AssignmentType it is: task, assignment or appointment.
		
		//likely solution is a comparison.
		
		/* for eg, some variation of
		 * if(oldAssignment.getstartdate().equalsIgnoreCase("default") && (newAssignment.getstartdate().equalsIgnoreCase("default") || => still a task
		 *    oldAssignment has start date and new assignment also has start date => still an appointment) {
		 *  no change
		 * }
		 * 
		 */
		
		//method to replace the current assignment in the buffer with this new assignment
		
		//return method to GUI
	}
}
