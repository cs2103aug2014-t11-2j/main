package logic;

import java.util.LinkedList;

/*
 * Use 2 different stacks to redo or undo the actions respectively
 */
public class RedoUndo {

	public static String undo() {

		if (SparkMoVare.actionHistory.empty()) {
			return Message.UNABLE_TO_UNDO;
		} else {
			SparkMoVare.actionFuture.push(SparkMoVare.actionHistory.peek());
			deepCopyLL(SparkMoVare.buffer,SparkMoVare.actionHistory.pop());
			SparkMoVare.saveFile(SparkMoVare.getfilePath());
			
			return Message.UNDO;
		}
	}

	public static String redo() {

		if (SparkMoVare.actionFuture.empty()) {
			return Message.UNABLE_TO_REDO;
		} else {
			SparkMoVare.actionHistory.push(SparkMoVare.actionFuture.peek());
			deepCopyLL(SparkMoVare.buffer, SparkMoVare.actionFuture.pop());
			SparkMoVare.saveFile(SparkMoVare.getfilePath());
			
			return Message.REDO;
		}
	}
	
	private static void deepCopyLL(LinkedList<Assignment> toOverRide, LinkedList<Assignment> copyFrom) {
		toOverRide.clear();
		for (int i=0;i<copyFrom.size()-1;i++) {
			toOverRide.add(i,copyFrom.get(i));
		}
	}
}
