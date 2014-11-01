package logic;

public class Assignment {

	/************** Data members **********************/

	private String title;
	private String id; 
	private String dateCreation;
	private int index;
	private boolean isDone;
	private boolean isOnTime;
	private String priority; 
	private AssignmentType assignType;

	/************** Constants **********************/

	public enum AssignmentType {
		TASK, APPOINTMENT, TENTATIVE, ASSIGNMENT, DEFAULT;
	}

	protected static final String DEFAULT_STRING = "DEFAULT";
	protected static final String DEFAULT = "010120140001";

	protected static final String TYPE_TASK = "TASK";
	protected static final String TYPE_APPOINTMENT = "APPT";
	protected static final String TYPE_TENTATIVE = "TNTV";

	protected static final String PRIORITY_NONE = "NIMPT";
	protected static final String PRIORITY_IMPT = "IMPT";

	/************** Constructors **********************/

	public Assignment() {
		this(DEFAULT, DEFAULT_STRING, false, false, PRIORITY_NONE,
				AssignmentType.ASSIGNMENT);
	}

	public Assignment(String id, String title, boolean isDone,
			boolean isOnTime, String priority, AssignmentType atype) {

		setId(id);
		setDateCreation(id.substring(0, 8));
		setIndex(Integer.parseInt(Id.removeFrontZero(id.substring(8))));
		setTitle(title);
		setIsDone(isDone);
		setIsOnTime(isOnTime);
		setPriority(priority);
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

	public AssignmentType getAssignType() {
		return this.assignType;
	}

	/**************** Mutators ************************/

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public void setId(String newId) {
		this.id = newId;
	}

	public void setAssignType(AssignmentType atype) {
		this.assignType = atype;
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

	/**************** Overriding ************************/

	public String toString() {
		return getId() + "~" + getTitle() + "~" + getAssignType() + "~"
				+ getIsDone() + "~" + getIsOnTime() + "~" + getPriority();
	}
}