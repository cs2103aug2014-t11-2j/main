package logic;

/**
 * Logic: Id component to set the latest index and the give the
 * 		  next serial number.
 * @author Teck Zhi
 */

public class Id {

	protected static int latestSerialNumber = 0;

	public static void setLatestSerialNumber(int newSn) {
		
		if(newSn > latestSerialNumber) {
			latestSerialNumber = newSn;
		}
	}

	public static int getLatestSerialNumber(){
		return latestSerialNumber;
	}

	protected static int serialNumGen() {
		latestSerialNumber += 1;
		
		return latestSerialNumber;
	}
}
