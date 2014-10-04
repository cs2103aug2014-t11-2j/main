package logic;

import java.util.LinkedList;
import java.util.Stack;

import storage.Storage;

/*
 * Use 2 different stacks to redo or undo the actions respectively
 */
public class RedoUndo {

	public static String undo(String filePath, LinkedList<Assignment> buffer, Stack<LinkedList<Assignment>> actionHistory,
			Stack<LinkedList<Assignment>> actionFuture) {

		if (actionHistory.empty()) {
			return Message.UNABLE_TO_UNDO;
		} else {
			LinkedList<Assignment> newEntry = new LinkedList<Assignment>();
			deepCopyLL(newEntry,actionHistory.peek());
			actionFuture.push(newEntry);
			deepCopyLL(buffer,actionHistory.pop());
			Storage.saveFile(filePath, buffer);
			
			return Message.UNDO;
		}
	}

	public static String redo(String filePath, LinkedList<Assignment> buffer, Stack<LinkedList<Assignment>> actionHistory,
			Stack<LinkedList<Assignment>> actionFuture) {

		if (actionFuture.empty()) {
			return Message.UNABLE_TO_REDO;
		} else {
			actionHistory.push(actionFuture.peek());
			deepCopyLL(buffer, actionFuture.pop());
			Storage.saveFile(filePath, buffer);
			
			return Message.REDO;
		}
	}
	
	private static void deepCopyLL(LinkedList<Assignment> toOverRide, LinkedList<Assignment> copyFrom) {
		toOverRide.clear();
		for (int i=0;i<copyFrom.size();i++) {
			toOverRide.add(i,copyFrom.get(i));
		}
	}
}
