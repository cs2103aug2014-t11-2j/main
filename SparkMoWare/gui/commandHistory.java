package gui;

import java.util.Stack;

public class CommandHistory {
	protected static Stack<String> history = new Stack<String>();
	protected static Stack<String> future = new Stack<String>();
	
	protected static void addCmd(String input) {
		history.add(input);
		//System.out.println("added ~ "+input);
	}
	
	protected static String getNextCmd() {
		if (future.isEmpty()) {
		//	System.out.println("empty future");
			return "";
		}
		else {
			history.push(future.peek());
		//	System.out.println("returned ~ "+future.peek());
			return future.pop();
		}
	}
	
	protected static String getPrevCmd(){
		if (history.isEmpty()) {
			System.out.println("empty history");

			return "";
		}
		else {
			future.push(history.peek());
			System.out.println("returned ~ "+history.peek());

			return history.pop();
		}
	}
}
