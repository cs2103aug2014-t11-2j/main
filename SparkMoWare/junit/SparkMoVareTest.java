package junit;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Vector;

import logic.Appointment;
import logic.Assignment;
import logic.InternalStorage;
import logic.LogicTestDrive;
import logic.Output;
import logic.Task;
import parser.Interpreter;
import parser.RefinedUserInput;

//@author A0117057J

public class SparkMoVareTest {
	
	LinkedList<Assignment> bufferTest = new LinkedList<Assignment>();
	RefinedUserInput userInput = new RefinedUserInput();
	
	Vector<RefinedUserInput> multiUserInput = new Vector<RefinedUserInput>();
	
	Assignment testAssignment = new Assignment();
	Task testTask = new Task();
	Appointment testAppointment = new Appointment();
	
	Output testOutput = new Output();
	
	public void test() {
		
		userInput = Interpreter.reader("add buy fish");
		multiUserInput.add(userInput);
		
		userInput = Interpreter.reader("add buy chicken 09/09/2014 2359");
		multiUserInput.add(userInput);
		
		userInput = Interpreter.reader("add buy pork 01/09/2014 0700 01/09/2014 1000");
		multiUserInput.add(userInput);
		
		for(int i = 0; i < multiUserInput.size(); i++) {
			LogicTestDrive.addSomething(multiUserInput.get(i));
		}
		bufferTest = InternalStorage.getBuffer();
		
		assertEquals(3, bufferTest.size());
		
		
	}
}