package logic;

import java.util.Vector;

public class Tentative extends Assignment {
	
	/************** Data members **********************/
	
	private Vector<String> startDate; 
	private Vector<String> startTime;
	private Vector<String> endDate;
	private Vector<String> endTime;
	
	/************** Constants **********************/

	/************** Constructors **********************/
	
	// Default constructor
	public Tentative() {
		super();
		
	}

	public Tentative(String id, String title, String type, // Refer to BUILDER PATTERN
			boolean isDone, boolean isOnTime, String priority) {

		super(id,title,type,isDone,isOnTime,priority,assignmentType.TENTATIVE);
		this.startDate = new Vector<String>();
		//setId(id);
		//setTitle(title);
		//setType(type);
		//setIsDone(isDone);
		//setIsOnTime(isOnTime);
		//setPriority(priority);
		//setTag(tag);
	}

	/**************** Accessors ***********************/
	
	public Vector<String> getStartDateList() {
		return startDate;
	}
	
	public Vector<String> getStartTimeList() {
		return startTime;
	}
	
	public Vector<String> getEndDateList() {
		return endDate;
	}
	
	public Vector<String> getEndTimeList() {
		return endTime;
	}
	
	/**************** Mutators ************************/
	
	public void setStartDate(String date) {
		startDate.add(date);
	}
	
	public void setStartTime(String time) {
		startTime.add(time);
	}
	
	public void setEndDate(String date) {
		endDate.add(date);
	}
	
	public void setEndTime(String time) {
		endTime.add(time);
	}
	
	/**************** Overriding ************************/

	@Override
	public String toString() {
		return getId() + "~" + getTitle() + "~" + getType() + "~" + getStartDateList() + "~" + 
				getStartTimeList() + "~" + getEndDateList() + "~" + getEndTimeList() + "~" + getIsDone() + 
				"~" + getIsOnTime() + "~" + getPriority();
	}
}