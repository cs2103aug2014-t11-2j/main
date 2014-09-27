package logic;

/*
 * Use 2 different stacks to redo or undo the actions respectively
 */
public class redoUndo {
	
	protected static final String MESSAGE_UNABLE_TO_REDO = "Error: Unable to redo";
	protected static final String MESSAGE_UNABLE_TO_UNDO = "Error: Unable to undo";
	
	protected static final String MESSAGE_REDO = "Forward action has been reverted";
	protected static final String MESSAGE_UNDO = "Last action has been reverted";
	
	// undo using stack, can be done before stack overflows
	public static String undo() {

		if (SparkMoVare.actionHistory.empty()) {
			return MESSAGE_UNABLE_TO_UNDO;
		} else {
			SparkMoVare.actionFuture.push(SparkMoVare.actionHistory.pop());
			SparkMoVare.buffer = SparkMoVare.actionHistory.peek();
			
			return MESSAGE_UNDO;
		}
	}

	// re-do using stack, can be done before stack overflows
	public static String redo() {

		if (SparkMoVare.actionFuture.empty()) {
			return MESSAGE_UNABLE_TO_REDO;
		} else {
			SparkMoVare.actionHistory.push(SparkMoVare.actionFuture.pop());
			SparkMoVare.buffer = SparkMoVare.actionHistory.peek();
			
			return MESSAGE_REDO;
		}
	}
}
