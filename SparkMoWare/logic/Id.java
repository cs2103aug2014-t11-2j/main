package logic;

//@author A0111572R

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
