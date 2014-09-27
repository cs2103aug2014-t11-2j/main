package logic;

/*
 * Use 2 different stacks to redo or undo the actions respectively
 */
public class RedoUndo {

	public static String undo() {

		if (SparkMoVare.actionHistory.empty()) {
			return Message.UNABLE_TO_UNDO;
		} else {
			SparkMoVare.actionFuture.push(SparkMoVare.actionHistory.pop());
			SparkMoVare.buffer = SparkMoVare.actionHistory.peek();
			
			return Message.UNDO;
		}
	}

	public static String redo() {

		if (SparkMoVare.actionFuture.empty()) {
			return Message.UNABLE_TO_REDO;
		} else {
			SparkMoVare.actionHistory.push(SparkMoVare.actionFuture.pop());
			SparkMoVare.buffer = SparkMoVare.actionHistory.peek();
			
			return Message.REDO;
		}
	}
}
