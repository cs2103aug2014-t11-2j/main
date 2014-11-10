package logic;

import java.util.LinkedList;

import logic.Assignment.AssignmentType;
import parser.EnumGroup.CommandType;
import parser.RefinedUserInput;

//@author A0111572R

public class FutureHistory {
	
	private CommandType command; 
	private LinkedList<Assignment> clearedHistory = new LinkedList<Assignment>();
	
	private AssignmentType assignType;
	private Assignment assignment = new Assignment();
	private Task task = new Task();
	private Appointment appointment = new Appointment();
	private Tentative tentative = new Tentative();
	private int position;
	
	private RefinedUserInput userInput = new RefinedUserInput();
	
	private int serial = 0;
	
	public CommandType getCommand() {
		return this.command;
	}
	
	public LinkedList<Assignment> getClearedHistory() {
		return this.clearedHistory;
	}
	
	public int getSerial() {
		return this.serial;
	}
	
	public int getPosition() {
		return this.position;
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
	
	public AssignmentType getAssignType() {
		return this.assignType;
	}
	
	public void setAssignType(AssignmentType assignType) {
		this.assignType = assignType;
	}
	
	public void setCommand(CommandType commandIn) {
		this.command = commandIn;
	}
	public void addClearedHistory(LinkedList<Assignment> history) {
		this.clearedHistory = history;
	}
	
	public void setSerial(int serialNumber) {
		this.serial = serialNumber;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}
	
	public void setAppointment(Appointment appointmentIn) {
		this.appointment = appointmentIn;
	}
	
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
	
	public void setTentative(Tentative tentative) {
		this.tentative = tentative;
	}
	
	public void setUserInput(RefinedUserInput userInput) {
		this.userInput = userInput;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
}