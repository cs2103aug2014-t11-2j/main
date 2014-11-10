package logic;

//@author A0111572R

public class Mission extends Assignment {
	
	/************** Data members **********************/

	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;

	/************** Constants **********************/

	private static final String DEFAULT_DATE = "01012014";
	private static final String DEFAULT_TIME = "0000";

	/************** Constructors **********************/

	public Mission() {
		this(DEFAULT_DATE, DEFAULT_TIME, DEFAULT_DATE, DEFAULT_TIME);
	}

	public Mission(String startDate, String startTime, String endDate, String endTime) {

		super();
		setStartDate(startDate);
		setStartTime(startTime);
		setEndDate(endDate);
		setEndTime(endTime);
	}

	/**************** Accessors ***********************/

	public String getStartDate() {
		return this.startDate;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public String getEndTime() {
		return this.endTime;
	}
	
	/**************** Mutators ************************/

	public void setStartDate(String newStartDate) {
		this.startDate = newStartDate;
	}

	public void setStartTime(String newStartTime) {
		this.startTime = newStartTime;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**************** Overriding ************************/

	public String toString() {
		return getIndex() + "~" + getTitle() + "~" + getStartDate() + "~" + getStartTime() + "~" + getEndDate() + "~" + getEndTime();
	}
}
