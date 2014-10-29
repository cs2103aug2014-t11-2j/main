package logic;

import java.util.LinkedList;
import parser.EnumGroup.CommandType;
import parser.RefinedUserInput;

public class FutureHistory {
	
	private CommandType command; 
	private LinkedList<Assignment> clearedHistory = new LinkedList<Assignment>();
	private LinkedList<Assignment> clearedFuture = new LinkedList<Assignment>();
	
	private Assignment assignment = new Assignment();
	private Task task = new Task();
	private Appointment appointment = new Appointment();
	private Tentative tentative = new Tentative();
	
	private RefinedUserInput userInput = new RefinedUserInput();
	
	private String serial = "31122013";
	
	public CommandType getCommand() {
		return this.command;
	}
	
	public LinkedList<Assignment> getClearedHistory() {
		return this.clearedHistory;
	}
	
	public LinkedList<Assignment> getClearedFuture() {
		return this.clearedFuture;
	}
	
	public String getSerial() {
		return this.serial;
	}
	
	public RefinedUserInput getUserInput() {
		return this.userInput;
	}
	
	public Task getTask() {
		return this.task;
	}
	
	public Assignment getAssignment() {
		return this.assignment;
	}
	
	public Appointment getAppointment() {
		return this.appointment;
	}
	
	public Tentative getTentative() {
		return this.tentative;
	}
	
	public void setCommand(CommandType commandIn) {
		this.command = commandIn;
	}
	public void addClearedHistory(LinkedList<Assignment> history) {
		this.clearedHistory.addAll(history);
	}
	
	public void addClearedFuture(LinkedList<Assignment> future) {
		this.clearedFuture.addAll(future);
	}
	
	public void setSerial(String serialNumber) {
		this.serial = serialNumber;
	}
	
	public void setTask(Task taskIn) {
		this.task = taskIn;
	}
	
	public void setAppointment(Appointment appointmentIn) {
		this.appointment = appointmentIn;
	}
	
	public void setAssignment(Assignment assignmentIn) {
		this.assignment = assignmentIn;
	}
	
	public void setTentative(Tentative tentativeIn) {
		this.tentative = tentativeIn;
	}
	
	public void setUserInput(RefinedUserInput userInputIn) {
		this.userInput = userInputIn;
	}
}