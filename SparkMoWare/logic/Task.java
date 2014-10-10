package logic;

import java.util.Vector;

public class Task extends Assignment{
	
	/************** Data members **********************/

	/************** Constants **********************/

	/************** Constructors **********************/
	// Default constructor
	public Task() {
		this(DEFAULT, DEFAULT_STRING, -1, DEFAULT, DEFAULT, false, false, null,
				new Vector<String>());
	}

	public Task(String id, String title, int type, String endDate,
			String endTime, boolean isDone, boolean isOnTime, String priority,
			Vector<String> tag) {

		setId(id);
		setTitle(title);
		setType(type);
		setEndDate(endDate);
		setEndTime(endTime);
		setIsDone(isDone);
		setIsOnTime(isOnTime);
		setPriority(priority);
		//setTag(tag);

	}

	/**************** Accessors ***********************/
	
	/**************** Mutators ************************/
	
	/**************** Overriding ************************/

	@Override
	public String toString() {
		return getId() + "~" + getTitle() + "~" + getType() + "~"
				+ getEndDate() + "~" + getEndTime() + "~" + getIsDone() + "~"
				+ getIsOnTime() + "~" + getPriority();
	}

}
