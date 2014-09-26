package logic;

public class Comparator {
	
	// compares id with nextId, return true if idA is bigger else return false the format is 250920140001
	protected static boolean serialNumberComparator(String idA, String idB) {
		
		int checkDate = dateComparator(idA.substring(0,7), idB.substring(0,7));
		
		if(checkDate == 0) {
			//check Sn
			if (Integer.parseInt(idA.substring(8)) > Integer.parseInt(idB.substring(8))) {
				return true;
			}
		}	
		return false;	
	}

	// compares id with nextId, return 1 if dateA is bigger else return -1 the format is 25092014
	protected static int dateComparator(String dateA, String dateB) {
		
		int checkDate = -1;
		
		if(dateA == dateB) {
			checkDate = 0;
		} else {
			// check year
			if (Integer.parseInt(dateA.substring(4, 8)) > Integer.parseInt(dateB.substring(4, 8))) {
				checkDate = 1;
			}
			// check month
			else if (Integer.parseInt(dateA.substring(2, 4)) > Integer.parseInt(dateB.substring(2, 4))) {
				checkDate = 1;
			}
			// check day
			else if (Integer.parseInt(dateA.substring(0, 2)) > Integer.parseInt(dateB.substring(0, 2))) {
				checkDate = 1;
			}
		}
		return checkDate;
	}
}