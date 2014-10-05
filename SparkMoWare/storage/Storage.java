package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import logic.Assignment;
import logic.Comparator;
import logic.Message;

public class Storage {
	
	public static final int SYSTEM_EXIT_ERROR = 0;

	public static LinkedList<Assignment> saveFile(String filePath, LinkedList<Assignment> buffer) {

		File file = new File(filePath);
		if(file.delete()){
		}else{
			System.out.println("failed");
		}

		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for(int i = 0; i< buffer.size(); i++){
				bw.write(buffer.get(i).toString());
				if (i<buffer.size() - 1){
					bw.newLine(); 
				}
			}
			bw.close();
			
		} catch (IOException e) {
			System.out.println("Exception encountered while saving the textfile");
			System.exit(SYSTEM_EXIT_ERROR);
		}
		
		return buffer;
	}
	
	public static LinkedList<Assignment> openFile(String filePath, String latestSerialNumber, LinkedList<Assignment> buffer) {

		try { // check if file exist if not create a new file with that name
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;


			while ((line = bufferedReader.readLine())  != null ) {

				String lineArray[] = line.split("~");
				Assignment temp = new Assignment();

				temp.setId(lineArray[0]);
				temp.setTitle(lineArray[1]);
				temp.setType(Integer.parseInt(lineArray[2]));
				temp.setStartDate(lineArray[3]);
				temp.setStartTime(lineArray[4]);
				temp.setEndDate(lineArray[5]);
				temp.setEndTime(lineArray[6]);
				temp.setIsDone(Boolean.parseBoolean(lineArray[7]));
				temp.setIsOnTime(Boolean.parseBoolean(lineArray[8]));
				temp.setPriority(Integer.parseInt(lineArray[9]));
				//temp.setAlarm(Integer.parseInt(lineArray[7]));
				// tags to be done
				// updates the latest serial number

				if( latestSerialNumber.equals("")) {
					latestSerialNumber = lineArray[0];
				} else {
					if(Comparator.serialNumberComparator(lineArray[0], latestSerialNumber)) {

						latestSerialNumber = lineArray[0];
					}
				}
				buffer.add(temp);
			}
			fileReader.close();
		} catch (IOException e) {

			Message.printToUser(Message.STORAGE_FILE_ERROR);
			System.exit(SYSTEM_EXIT_ERROR);
		}
		return buffer;
	}
	
}
