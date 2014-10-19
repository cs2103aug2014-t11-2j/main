package logic;

public class Assignment{

	/************** Data members **********************/

	private String title;
	private String id; // date of creation and index;in the format DDMMYYYIIII
	private String dateCreation;
	private int index;
	private boolean isDone;
	private boolean isOnTime;
	private String priority; // IMPT else NIMPT
	private assignmentType aType;
	// private String description;
	// private int alarm; // in the format HHMM, if inactive null
	// private Vector<String> tag; // to support tagging

	/************** Constants **********************/

	enum assignmentType {
		TASK, APPOINTMENT, TENTATIVE, ASSIGNMENT;
	}

	protected static final String DEFAULT_STRING = "DEFAULT";
	protected static final String DEFAULT = "NONE";

	protected static final String TYPE_TASK = "TASK";
	protected static final String TYPE_APPOINTMENT = "APPT";
	protected static final String TYPE_TENTATIVE = "TNTV";

	protected static final String PRIORITY_NONE = "NIMPT";
	protected static final String PRIORITY_IMPT = "IMPT";

	/************** Constructors **********************/

	// Default constructor
	public Assignment() {
		this(DEFAULT, DEFAULT_STRING, false, false, PRIORITY_NONE, assignmentType.ASSIGNMENT);
	}

	public Assignment(String id, String title, boolean isDone, boolean isOnTime, String priority,
			assignmentType atype) {

		setId(id);
		setDateCreation(DateLocal.dateString());
		setIndex(Integer.parseInt(Id.removeFrontZero(id.substring(8))));
		setTitle(title);
		setIsDone(isDone);
		setIsOnTime(isOnTime);
		setPriority(priority);
		//setTag(tag);
		setAssignType(atype);
	}

	/**************** Accessors ***********************/

	public String getTitle() {
		return this.title;
	}

	public String getId() {
		return this.id;
	}

	public boolean getIsDone() {
		return this.isDone;
	}

	public boolean getIsOnTime() {
		return this.isOnTime;
	}

	public String getPriority() {
		return this.priority;
	}

	public String getDateCreation() {
		return this.dateCreation;
	}

	public int getIndex() {
		return this.index;
	}

	public assignmentType getAssignType() {
		return this.aType;
	}

	/*
	 * public String getDescription() {
	 *	 return description;
	 * }
	 *
	 * public int getAlarm() {
	 *	 return alarm;
	 * }
	 *
	 * public Vector<String> getTag() {
	 * 	 return this.tag;
	 * }
	 */

	/**************** Mutators ************************/

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public void setId(String newId) {
		this.id = newId;
	}

	public void setAssignType(assignmentType atype) {
		this.aType = atype;
	}

	public void setIsDone(boolean newIsDone) {
		this.isDone = newIsDone;
	}

	public void setIsOnTime(boolean newIsOnTime) {
		this.isOnTime = newIsOnTime;
	}

	public void setPriority(String newPriority) {
		this.priority = newPriority;
	}

	public void setDateCreation(String date) {
		this.dateCreation = date;
	}

	public void setIndex(int newIndex) {
		this.index = newIndex;
	}

	/* 
	 * public void setDescription(String newDescription) {
	 * description = newDescription;
	 * }
	 *
	 *  public void setAlarm(int newAlarm) {
	 * alarm = newAlarm;
	 * }
	 *
	 * public void setTag(Vector<String> newTag) {
	 * tag = newTag;
	 * }
	 */

	/**************** Overriding ************************/

	public String toString() {
		return getId() + "~" + getTitle() + "~" + getAssignType() + "~" + "-" + "~" + 
				"-" + "~" + "-" + "~" + "-" + "~" + getIsDone() + 
				"~" + getIsOnTime() + "~" + getPriority();
	}
}