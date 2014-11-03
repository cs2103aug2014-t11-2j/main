package logic;

public class Id {

	protected static int latestSerialNumber = 0;

	public static void setLatestSerialNumber(int newSn) {
		latestSerialNumber = newSn;
	}

	public static int getLatestSerialNumber(){
		return latestSerialNumber;
	}

	protected static int serialNumGen() {
		latestSerialNumber += 1;
		
		return latestSerialNumber;
	}
}
