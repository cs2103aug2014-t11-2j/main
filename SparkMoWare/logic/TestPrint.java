package logic;

import logic.Assignment;
import java.util.LinkedList;

public class TestPrint {

	public static void printList(LinkedList<Assignment> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
}
