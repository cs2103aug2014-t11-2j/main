package parser;

public class UserInputAddAppointment extends UserInputAddTask {
	
	/************** Data members **********************/
	
	private String startDate;
	private String startTime;
	
	/************** Constructors **********************/
	
	public UserInputAddAppointment() {
		
	}

	public UserInputAddAppointment(String title, String startDate, String startTime, 
								   String endDate, String endTime) {
		super(title, endDate, endTime);
		setStartDate(startDate);
		setStartTime(startTime);
		setAssignmentType(EnumGroup.AssignmentType.APPOINTMENT);
	}
	
	/**************** Accessors ***********************/
	
		protected String getStartDate(){
			return this.startDate;
		}
		
		protected String getStartTime(){
			return this.startTime;
		}
	
	/**************** Mutators ************************/

		private void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		
		private void setStartTime(String startTime) {
			this.startTime = startTime;
		}

	/**************** Overriding ************************/

		public String toString() {
			return getCommandType() + "~" + getTitle() + "~" + getStartDate() + "~" + getStartTime() + getEndDate() + "~" + getEndTime()+ "~" + getAssignmentType();
		}
}
