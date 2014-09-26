package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Id {
	
	// only work till Sn 9999 otherwise the year will be corrupted
	// Malfunctions if latestSerialNumber is in the future (in theory should not happen? unless system clock change)
	public static String serialNumGen() {
		
		String serialNum="";
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		//get current date time with Date()
		Date todayDate = new Date();
		
		serialNum += dateFormat.format(todayDate);
		serialNum += "0001";
		
		if(latestSerialNumber.equals("") || serialNumberComparator(serialNum,latestSerialNumber)) {
			return serialNum;
		} else {
			Long Sn = Long.parseLong(latestSerialNumber);
			System.out.println("test" + Sn.toString());
			Sn++;
			
			return Long.toString(Sn);
		}
	}

}
