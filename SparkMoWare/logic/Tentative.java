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
	public Tentative() {
		
		super();
		startDate = new Vector<String>();
		startTime = new Vector<String>();
		endDate = new Vector<String>();
		endTime = new Vector<String>();
		setAssignType(AssignmentType.TENTATIVE);
	}
	
	public Vector<String> getStartDate() {
		return this.startDate;
	}
	
	public Vector<String> getStartTime() {
		return this.startTime;
	}
	
	public Vector<String> getEndDate() {
		return this.endDate;
	}
	
	public Vector<String> getEndTime() {
		return this.endTime;
	}
	
	/**************** Mutators ************************/
	
	public void setStartDate(String newstartDate) {
		this.startDate.add(newstartDate);
	}
	
	public void setStartTime(String newStartTime) {
		this.startTime.add(newStartTime);
	}
	
	public void setEndDate(String newEndDate) {
		this.endDate.add(newEndDate);
	}
	
	public void setEndTime(String newEndTime) {
		this.endTime.add(newEndTime);
	}
	
	/**************** Overriding ************************/
	
	public String toString() {
		return getId() + "~" + getTitle() + "~" + getAssignType() + "~" + getStartDate() + "~" + 
				getStartTime() + "~" + getEndDate() + "~" + getEndTime() + "~" + getIsDone() + 
				"~" + getIsOnTime() + "~" + getPriority();
	}
}
