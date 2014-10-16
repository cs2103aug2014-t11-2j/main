package logic;

import java.util.LinkedList;

public class Output {
	
	/************** Data members **********************/
	
	private LinkedList<Assignment> returnBuffer;
	private String feedback;
	private boolean isStats;
	private int totalAssignment;
	private int totalCompleted;
	private int totalOnTime;
	
	/************** Constants **********************/
	
	private static final LinkedList<Assignment> DEFAULT_LINKEDLIST = new LinkedList<Assignment>();
	private static final String DEFAULT_FEEDBACK = "NONE";
	private static final int DEFAULT_INTEGER = 0;
	
	/************** Constructors **********************/
	public Output() {
		this(DEFAULT_LINKEDLIST, DEFAULT_FEEDBACK, false, DEFAULT_INTEGER, DEFAULT_INTEGER, DEFAULT_INTEGER);
	}
	
	public Output(LinkedList<Assignment> returnBuffer, String feedback, 
			boolean isStats, int totalAssignment, int totalCompleted, int totalOnTime) {
		
		setReturnBuffer(returnBuffer);
		setFeedback(feedback);
		setIsStats(isStats);
		setTotalAssignment(totalAssignment);
		setTotalCompleted(totalCompleted);
		setTotalOnTime(totalOnTime);
	}
	
	/**************** Accessors ***********************/
	
	public LinkedList<Assignment> getReturnBuffer() {
		return this.returnBuffer;
	}
	
	public String getFeedback() {
		return this.feedback;
	}
	
	public boolean getIsStats() {
		return this.isStats;
	}
	
	public int getTotalCompleted() {
		return this.totalCompleted;
	}
	
	public int getTotalAssignment() {
		return this.totalAssignment;
	}
	
	public int getTotalOnTime() {
		return this.totalOnTime;
	}
	/**************** Mutators ************************/
	
	public void setReturnBuffer(LinkedList<Assignment> returnBuffer) {
		this.returnBuffer = returnBuffer;
	}
	
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	public void setIsStats(boolean isStats) {
		this.isStats = isStats;
	}
	
	public void setTotalAssignment(int totalAssignment) {
		this.totalAssignment = totalAssignment;
	}
	
	public void setTotalCompleted(int totalCompleted) {
		this.totalCompleted = totalCompleted;
	}
	
	public void setTotalOnTime(int totalOnTime) {
		this.totalOnTime = totalOnTime;
	}
}