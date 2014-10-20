package logic;

/*
 * Comparison for Time, Date and ID.
 * returns boolean 
 * or
 * -1 (A < B), 0 (A = B), 1 (A > B)
 */
public class Comparator {

	private static final int SMALLER = -1;
	private static final int SAME = 0;
	private static final int LARGER = 1;

	// compares id with nextId, return true if idA is bigger else return false
	// the format is 250920140001
	public static boolean serialNumberComparator(String idA, String idB) {

		int checkDate = dateComparator(idA.substring(0, 8), idB.substring(0, 8));
		boolean serialCheck = false;

		if (checkDate == SAME) {
			// check Sn
			idA = Id.removeFrontZero(idA.substring(8));
			idB = Id.removeFrontZero(idB.substring(8));

			if (Integer.parseInt(idA) > Integer.parseInt(idB)) {
				serialCheck = true;
			}
		} else if (checkDate == LARGER) {
			serialCheck = true;
		}
		return serialCheck;
	}

	protected static int dateComparator(String dateA, String dateB) {

		String yearA = dateA.trim().substring(4, 8);
		String yearB = dateB.trim().substring(4, 8);

		String monthA = dateA.trim().substring(2, 4);
		String monthB = dateB.trim().substring(2, 4);

		String dayA = dateA.trim().substring(0, 2);
		String dayB = dateB.trim().substring(0, 2);

		yearA = Id.removeFrontZero(yearA);
		yearB = Id.removeFrontZero(yearB);

		monthA = Id.removeFrontZero(monthA);
		monthB = Id.removeFrontZero(monthB);

		dayA = Id.removeFrontZero(dayA);
		dayB = Id.removeFrontZero(dayB);

		if (dateA.equals(dateB)) {
			return SAME;
		} else if (Integer.parseInt(yearA) > Integer.parseInt(yearB)) {
			return LARGER;
		} else if (Integer.parseInt(yearA) < Integer.parseInt(yearB)) {
			return SMALLER;
		} else if (Integer.parseInt(monthA) > Integer.parseInt(monthB)) {
			return LARGER;
		} else if (Integer.parseInt(monthA) < Integer.parseInt(monthB)) {
			return SMALLER;
		} else if (Integer.parseInt(dayA) > Integer.parseInt(dayB)) {
			return LARGER;
		}
		return SMALLER;
	}

	protected static int timeComparator(String timeA, String timeB) {

		String hourA = timeA.trim().substring(0, 2);
		String hourB = timeA.trim().substring(0, 2);

		String minA = timeB.trim().substring(2, 4);
		String minB = timeB.trim().substring(2, 4);

		hourA = Id.removeFrontZero(hourA);
		hourB = Id.removeFrontZero(hourB);

		minA = Id.removeFrontZero(minA);
		minB = Id.removeFrontZero(minB);

		if (timeA.equals(timeB)) {
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

}
