package logic;

/*
 * Comparison for Time, Date and ID.
 * returns boolean 
 * or
 * -1 (A < B), 0 (A = B), 1 (A > B)
 */
public class Comparator {

	private static int same = 0;
	private static int larger = 1;
	private static int smaller = -1;

	// compares id with nextId, return true if idA is bigger else return false the format is 250920140001
	protected static boolean serialNumberComparator(String idA, String idB) {

		int checkDate = dateComparator(idA.substring(0,8), idB.substring(0,8));

		if(checkDate == same) {
			//check Sn
			if (Integer.parseInt(idA.substring(8)) > Integer.parseInt(idB.substring(8))) {
				return true;
			}
		} else if(checkDate == larger){
			return true;
		}

		return false;
	}

	protected static int dateComparator(String dateA, String dateB) {

		int checkDate = smaller;

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
			checkDate = same;
		} else if ( Integer.parseInt(yearA) > Integer.parseInt(yearB) ) {
			System.out.println("year: " + yearA + ">" + yearB);
			return larger;
		} else if (Integer.parseInt(monthA) > Integer.parseInt(monthB) ) {
			System.out.println("month: " + monthA + ">" + monthB);
			return larger;
		} else if (Integer.parseInt(dayA) > Integer.parseInt(dayB) ) {
			System.out.println("day: " + dayA + ">" + dayB);
			return larger;
		}
		return checkDate;
	}

	protected static int timeComparator(String timeA, String timeB) {

		int checkTime = smaller;

		if(timeA.equals(timeB)) { 
			checkTime = same;
		} else if (Integer.parseInt(timeA.trim().substring(0, 2)) > Integer.parseInt(timeB.trim().substring(0, 2))) {
			checkTime = larger;
		} else if (Integer.parseInt(timeA.trim().substring(2, 4)) > Integer.parseInt(timeB.trim().substring(2, 4))) {
			checkTime = larger;
		}
		return checkTime;
	}

	private static String removeFrontZero(String input) {
		while (input.length()>0 && input.charAt(0) == '0') {
			input = input.substring(1);
		}
		return input;
	}
}
