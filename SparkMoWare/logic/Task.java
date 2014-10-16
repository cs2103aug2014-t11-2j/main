package logic;

public class Task extends Assignment{

	/************** Data members **********************/

	private String endDate; 
	private String endTime;

	/************** Constants **********************/

	private static final String DEFAULT_DATE = "01012014";
	private static final String DEFAULT_TIME = "0000";

	/************** Constructors **********************/
	// Default constructor
	public Task() {
		this(DEFAULT_DATE,DEFAULT_TIME);

	}
	public Task(String startDate, String startTime) {

		super();
		setEndDate(endDate);
		setEndTime(endTime);
		setAssignType(assignmentType.TASK);
	}

	/**************** Accessors ***********************/

	public String getEndDate() {
		return this.endDate;
	}

	public String getEndTime() {
		return this.endTime;
	}

	/**************** Mutators ************************/

	public void setEndDate(String newEndDate) {
		endDate = newEndDate;
	}

	public void setEndTime(String newEndTime) {
		endTime = newEndTime;
	}

	/**************** Overriding ************************/

	@Override
	public String toString() {
		return getId() + "~" + getTitle() + "~" + getAssignType() + "~"
				+ getEndDate() + "~" + getEndTime() + "~" + getIsDone() + "~"
				+ getIsOnTime() + "~" + getPriority();
	}
}