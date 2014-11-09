package logic;

/**
 * Logic: Assignment class to store floating task
 * @author Teck Zhi
 */

public class Assignment {

	/************** Data members **********************/

	private String title;
	private int index;
	private boolean isDone;
	private boolean isOnTime;
	private String priority; 
	private AssignmentType assignType;

	/************** Constants **********************/

	public enum AssignmentType {
		TASK, APPT, TNTV, ASGN, DEFAULT;
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
		this(1, DEFAULT_STRING, false, false, PRIORITY_NONE,
				AssignmentType.ASGN);
	}

	public Assignment(int index, String title, boolean isDone,
			boolean isOnTime, String priority, AssignmentType atype) {

		setIndex(index);
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

	public boolean getIsDone() {
		return this.isDone;
	}

	public boolean getIsOnTime() {
		return this.isOnTime;
	}

	public String getPriority() {
		return this.priority;
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

	public void setIndex(int newIndex) {
		this.index = newIndex;
	}

	/**************** Overriding ************************/

	public String toString() {
		return getIndex() + "~" + getAssignType() + "~" + getTitle() + "~"
				+ getIsDone() + "~" + getIsOnTime() + "~" + getPriority();
	}
}