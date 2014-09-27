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

		int checkDate = dateComparator(idA.substring(0,7), idB.substring(0,7));

		if(checkDate == same) {
			//check Sn
			if (Integer.parseInt(idA.substring(8)) > Integer.parseInt(idB.substring(8))) {
				return true;
			}
		}	
		return false;	
	}

	// compares id with nextId, return 1 if dateA is bigger else return -1 the format is 25092014
	protected static int dateComparator(String dateA, String dateB) {

		int checkDate = smaller;

		if(dateA == dateB) {
			checkDate = same;
		} else if (Integer.parseInt(dateA.substring(4, 8)) > Integer.parseInt(dateB.substring(4, 8))) {
			checkDate = larger;
		} else if (Integer.parseInt(dateA.substring(2, 4)) > Integer.parseInt(dateB.substring(2, 4))) {
			checkDate = larger;
		} else if (Integer.parseInt(dateA.substring(0, 2)) > Integer.parseInt(dateB.substring(0, 2))) {
			checkDate = larger;
		}
		return checkDate;
	}
	
	protected static int timeComparator(String timeA, String timeB) {
		
		int checkTime = smaller;
		
		if(timeA == timeB) {
			checkTime = same;
		} else if (Integer.parseInt(timeA.substring(0, 2)) > Integer.parseInt(timeB.substring(0, 2))) {
			checkTime = larger;
		} else if (Integer.parseInt(timeA.substring(2, 4)) > Integer.parseInt(timeB.substring(2, 4))) {
			checkTime = larger;
		}
		return checkTime;
	}
}
