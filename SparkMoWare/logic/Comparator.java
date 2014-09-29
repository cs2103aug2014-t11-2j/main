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

		if(dateA.equals(dateB)) {
			checkDate = same;
		} else if (Integer.parseInt(dateA.trim().substring(4, 8)) > Integer.parseInt(dateB.trim().substring(4, 8))) {
			checkDate = larger;
		} else if (Integer.parseInt(dateA.trim().substring(2, 4)) > Integer.parseInt(dateB.trim().substring(2, 4))) {
			checkDate = larger;
		} else if (Integer.parseInt(dateA.trim().substring(0, 2)) > Integer.parseInt(dateB.trim().substring(0, 2))) {
			checkDate = larger;
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
}
