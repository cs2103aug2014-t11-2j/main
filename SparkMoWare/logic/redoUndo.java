package logic;

public class redoUndo {
	
	// undo using stack, can be done before stack overflows
	public static String undo() {

		if (SparkMoVare.actionHistory.empty()) {
			return "Undo Error, no moves to undo";
		} else {
			SparkMoVare.actionFuture.push(SparkMoVare.actionHistory.pop());
			SparkMoVare.buffer = SparkMoVare.actionHistory.peek();
			
			return "Last action has been undo-ed";
		}
	}

	// re-do using stack, can be done before stack overflows
	public static String redo() {

		if (SparkMoVare.actionFuture.empty()) {
			return "Redo Error, no moves to redo";
		} else {
			SparkMoVare.actionHistory.push(SparkMoVare.actionFuture.pop());
			SparkMoVare.buffer = SparkMoVare.actionHistory.peek();
			
			return "Last action has been redo-ed";
		}
	}

}
