package logic;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class InternalStorage {
	
	public static LinkedList<Assignment> buffer = new LinkedList<Assignment>();
	protected static String filePath = "Storage.txt";

	protected static int counter = 0;
	protected static int size = 0;

	protected static Stack< LinkedList<Assignment>> actionHistory = new Stack< LinkedList<Assignment>>();
	protected static Stack< LinkedList<Assignment>> actionFuture = new Stack< LinkedList<Assignment>>();

	public static Scanner scanner = new Scanner(System.in);
	
	public static int getBufferPosition(String id) {
		counter = 0;
		size = buffer.size();

		while(counter < size && !buffer.get(counter).getId().contentEquals(id)){
			counter++;
		}	
		return counter;
	}
	
	public static void addBuffer(Assignment assignment) {
		buffer.add(assignment);
	}
	
	public static void addBuffer(int position, Assignment assignment) {
		buffer.add(position, assignment);
	}
	
	public static int getLineCount() {
		return buffer.size();
	}

	public static String getFilePath(){
		return filePath;
	}
	
	public static LinkedList<Assignment> getBuffer() {
		return buffer;
	}
	
	public static Stack <LinkedList<Assignment>> getHistory() {
		return actionHistory;
	}
	
	public static Stack <LinkedList<Assignment>> getFuture() {
		return actionFuture;
	}
	
	public static void pushHistory(LinkedList<Assignment> buffered) {
		actionHistory.push(buffered);
	}
	
	public static void pushFuture(LinkedList<Assignment> buffered) {
		actionFuture.push(buffered);
	}
	
	public static LinkedList<Assignment> peekHistory() {
		return actionHistory.peek();
	}
	
	public static LinkedList<Assignment> peekFuture() {
		return actionFuture.peek();
	}
	
	public static LinkedList<Assignment> popHistory() {
		return actionHistory.pop();
	}
	
	public static LinkedList<Assignment> popFuture() {
		return actionFuture.pop();
	}
}