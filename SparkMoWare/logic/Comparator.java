package logic;

/*
 * Comparison for Time, Date and ID.
 * returns boolean 
 * or
 * -1 (A < B), 0 (A = B), 1 (A > B)
 */
public class Comparator {

	private static final int SAME = 0;
	private static final int LARGER = 1;
	private static final int SMALLER = -1;

	// compares id with nextId, return true if idA is bigger else return false the format is 250920140001
	public static boolean serialNumberComparator(String idA, String idB) {

		int checkDate = dateComparator(idA.substring(0,8), idB.substring(0,8));

		if(checkDate == SAME) {
			//check Sn
			if (Integer.parseInt(idA.substring(8)) > Integer.parseInt(idB.substring(8))) {
				return true;
			}
		} else if(checkDate == LARGER){
			return true;
		}

		return false;
	}

	public static int dateComparator(String dateA, String dateB) {

		String yearA =dateA.trim().substring(4, 8);
		String yearB =dateB.trim().substring(4, 8);
		String monthA = dateA.trim().substring(2, 4);
		String monthB =dateB.trim().substring(2, 4);
		String dayA =dateB.trim().substring(0, 2);
		String dayB = dateA.trim().substring(0, 2);

		yearA = removeFrontZero(yearA);
		yearB = removeFrontZero(yearB);
		monthA = removeFrontZero(monthA);
		monthB = removeFrontZero(monthB);
		dayA = removeFrontZero(dayA);
		dayB = removeFrontZero(dayB);

		if(dateA.equals(dateB)) {
			return SAME;
		} else if ( Integer.parseInt(yearA) > Integer.parseInt(yearB) ) {
			return LARGER;
		} else if (Integer.parseInt(yearA) < Integer.parseInt(yearB)) {
			return SMALLER;
		} else if (Integer.parseInt(monthA) > Integer.parseInt(monthB) ) {
			return LARGER;
		} else if (Integer.parseInt(monthA) < Integer.parseInt(monthB)) {
			return SMALLER;
		} else if (Integer.parseInt(dayA) > Integer.parseInt(dayB) ) {
			return LARGER;
		}
		return SMALLER;
	}

	protected static int timeComparator(String timeA, String timeB) {

		String hourA =timeA.trim().substring(0, 2);
		String hourB = timeA.trim().substring(0, 2);
		String minA = timeB.trim().substring(2, 4);
		String minB = timeB.trim().substring(2, 4);

		if(timeA.equals(timeB)) { 
			return SAME;
		} else if (Integer.parseInt(hourA) > Integer.parseInt(hourB)) {
			return LARGER;
		} else if (Integer.parseInt(hourA) < Integer.parseInt(hourB)) {
			return SMALLER;
		} else if (Integer.parseInt(minA) > Integer.parseInt(minB)) {
			return LARGER;
		}
		return SMALLER;
	}

	private static String removeFrontZero(String input) {
		while (input.length()>0 && input.charAt(0) == '0') {
			input = input.substring(1);
		}
		return input;
	}
}
