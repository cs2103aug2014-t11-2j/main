package logic;

public class redoUndo {
	
	// undo using stack, can be done before stack overflows
	public static String undo() {

		if (actionHistory.empty()) {
			return "Undo Error, no moves to undo";
		} else {
			actionFuture.push(actionHistory.pop());
			buffer=actionHistory.peek();
			
			return "Last action has been undo-ed";
		}
	}

	// re-do using stack, can be done before stack overflows
	public static String redo() {

		if (actionFuture.empty()) {
			return "Redo Error, no moves to redo";
		} else {
			actionHistory.push(actionFuture.pop());
			buffer=actionHistory.peek();
			
			return "Last action has been redo-ed";
		}
	}

}
