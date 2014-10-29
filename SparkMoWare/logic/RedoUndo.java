package logic;

public class RedoUndo {
		
	public static void undo() {
		
		pushIntoFuture();
	}
	
	public static void pushIntoFuture() {
		
	}
	
	public static void redo() {
		
	}
}
/*
add -> store serial -> delete
done -> store serial -> check undone
edit -> store assignment -> swap back
delete -> store assignment -> add back
clear -> store linkedlist -> add back
tentative -> store serial -> delete
confirm -> store tentative -> add back
*/