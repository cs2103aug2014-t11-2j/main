package logic;

import java.util.LinkedList;


import storage.Storage;

/*
 * Use 2 different stacks to redo or undo the actions respectively
 */
public class RedoUndo {

	public static String undo() {

		if (InternalStorage.getHistory().empty()) {
			return Message.UNABLE_TO_UNDO;
		} else {
			LinkedList<Assignment> newEntry = new LinkedList<Assignment>();
			newEntry = InternalStorage.getBuffer();
			
			InternalStorage.pushFuture(newEntry);
			deepCopyLL(InternalStorage.getBuffer(),InternalStorage.popHistory());
						
			Print.printList(InternalStorage.getBuffer());
			Storage.saveFile(InternalStorage.getFilePath(), InternalStorage.getBuffer());
			
			return Message.UNDO;
		}
	}

	public static String redo() {

		if (InternalStorage.getFuture().empty()) {
			return Message.UNABLE_TO_REDO;
		} else {
			InternalStorage.pushHistory(InternalStorage.peekFuture());
			deepCopyLL(InternalStorage.getBuffer(), InternalStorage.popFuture());
			Storage.saveFile(InternalStorage.getFilePath(), InternalStorage.getBuffer());
			
			return Message.REDO;
		}
	}
	
	private static void deepCopyLL(LinkedList<Assignment> toOverRide, LinkedList<Assignment> copyFrom) {

		toOverRide.clear();
		
		for (int listCount = 0; listCount < copyFrom.size(); listCount++) {
			toOverRide.add(listCount, copyFrom.get(listCount));
		}
	}
}