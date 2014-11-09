package logic;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Logic: Internal storage to store action stacks, scanner and buffer
 * @author Teck Zhi
 */

public class InternalStorage {

	private static LinkedList<Assignment> buffer = new LinkedList<Assignment>();
	private static String filePath = "Storage.txt";

	private static Stack<FutureHistory> actionHistory = new Stack<FutureHistory>();
	private static Stack<FutureHistory> actionFuture = new Stack<FutureHistory>();

	private static Scanner scanner = new Scanner(System.in);

	public static Scanner getScanner() {
		return scanner;
	}

	public static int getBufferPosition(int id) {
		int counter = 0;
		int size = buffer.size();

		while(counter < size && !(buffer.get(counter).getIndex() == id)) {
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

	public static void addBufferFirst(Assignment assignment) {
		buffer.addFirst(assignment);
	}
	
	public static void setBuffer(LinkedList<Assignment> bufferIn) {
		buffer = bufferIn;
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

	public static Stack<FutureHistory> getHistory() {
		return actionHistory;
	}

	public static Stack<FutureHistory> getFuture() {
		return actionFuture;
	}

	public static void pushHistory(FutureHistory history) {
		actionHistory.push(history);
	}

	public static void pushFuture(FutureHistory future) {
		actionFuture.push(future);
	}

	public static FutureHistory peekHistory() {
		return actionHistory.peek();
	}

	public static FutureHistory peekFuture() {
		return actionFuture.peek();
	}

	public static FutureHistory popHistory() {
		return actionHistory.pop();
	}

	public static FutureHistory popFuture() {
		return actionFuture.pop();
	}
}