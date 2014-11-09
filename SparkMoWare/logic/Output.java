package logic;

import java.util.LinkedList;

/**
 * Logic: Output class to return everything that GUI wants.
 * @author Teck Zhi
 */

public class Output {
	
	/************** Data members **********************/
	
	private LinkedList<Mission> returnBuffer;
	private String feedback;
	private boolean isStats;
	private int totalAssignment;
	private int totalCompleted;
	private int totalOnTime;
	private boolean isInvalid;
	
	/************** Constants **********************/
	
	private static final LinkedList<Mission> DEFAULT_LINKEDLIST = new LinkedList<Mission>();
	private static final String DEFAULT_FEEDBACK = "NONE";
	private static final int DEFAULT_INTEGER = 0;
	
	/************** Constructors **********************/
	public Output() {
		this(DEFAULT_LINKEDLIST, DEFAULT_FEEDBACK, false, DEFAULT_INTEGER, DEFAULT_INTEGER, DEFAULT_INTEGER, false);
	}
	
	public Output(LinkedList<Mission> returnBuffer, String feedback, 
			boolean isStats, int totalAssignment, int totalCompleted, int totalOnTime, boolean isInvalid) {
		
		setReturnBuffer(returnBuffer);
		setFeedback(feedback);
		setIsStats(isStats);
		setTotalAssignment(totalAssignment);
		setTotalCompleted(totalCompleted);
		setTotalOnTime(totalOnTime);
		setIsInvalid(isInvalid);
	}
	
	/**************** Accessors ***********************/
	
	public LinkedList<Mission> getReturnBuffer() {
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
	
	public boolean getIsInvalid() {
		return this.isInvalid;
	}
	
	/**************** Mutators ************************/
	
	public void setReturnBuffer(LinkedList<Mission> returnBuffer) {
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
	
	public void setIsInvalid(boolean isInvalid) {
		this.isInvalid = isInvalid;
	}
}