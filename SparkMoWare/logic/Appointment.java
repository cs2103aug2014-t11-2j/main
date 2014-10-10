package logic;

import java.util.Vector;

public class Appointment extends Assignment {

	/************** Data members **********************/
	
	/************** Constants **********************/
	
	/************** Constructors **********************/
	// Default constructor
	public Appointment() {
		this(DEFAULT, DEFAULT_STRING, -1, DEFAULT, DEFAULT, DEFAULT, DEFAULT, false, false, null, new Vector<String>());
	}
	

	public Appointment(String id, String title, int type, String startDate, String startTime,
			String endDate, String endTime, boolean isDone, boolean isOnTime, String priority, Vector<String> tag) {
		
		setId(id);
		setTitle(title);
		setType(type);
		setStartDate(startDate);
		setStartTime(startTime);
		setEndDate(endDate);
		setEndTime(endTime);
		setIsDone(isDone);
		setIsOnTime(isOnTime);
		setPriority(priority);
		//setTag(tag);
		numAppointment++;
	}

	/**************** Accessors ***********************/


	/**************** Mutators ************************/
	
	
	
	/**************** Overriding ************************/
	
}
