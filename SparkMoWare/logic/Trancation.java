package logic;

import java.util.LinkedList;

public class Trancation {

	// overloading trancateList method for 2 sets of parameters
	public static LinkedList<Assignment> trancateList(
			LinkedList<Assignment> list, String endDate, String startDate) {

		LinkedList<Assignment> trancatedList = new LinkedList<Assignment>();
		trancatedList = list;

		int lowerLimit = trancatedList.indexOf(startDate);
		for (int i = 0; i < lowerLimit; i++) {
			trancatedList.remove(i);
		}

		// cannot just search an attribute need to search the element
		int upperLimit = trancatedList.lastIndexOf(endDate);
		for (int j = trancatedList.size(); j > upperLimit; j--) {
			trancatedList.remove(j);
		}

		return trancatedList;
	}

	public static LinkedList<Assignment> trancateList(String endDate,
			String startDate) {

		LinkedList<Assignment> trancatedList = new LinkedList<Assignment>();
		trancatedList = SparkMoVare.buffer;

		int lowerLimit = trancatedList.indexOf(startDate);
		for (int i = 0; i < lowerLimit; i++) {
			trancatedList.remove(i);
		}

		// cannot just search an attribute need to search the element
		int upperLimit = trancatedList.lastIndexOf(endDate);
		for (int j = trancatedList.size(); j > upperLimit; j--) {
			trancatedList.remove(j);
		}
		return trancatedList;
	}
	
}
