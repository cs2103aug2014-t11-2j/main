package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import logic.Assignment;
import logic.Print;


public class Storage {

	public static final int SYSTEM_EXIT_ERROR = 0;
	public static final String ERRORMESSAGELOADFAIL = "File loading failed!";


	public static LinkedList<Assignment> saveFile(String filePath, LinkedList<Assignment> buffer) {

		File file = new File(filePath);
		if(file.delete()) {
		} else {
			System.out.println("failed");
		}

		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for(int i = 0; i < buffer.size(); i++) {
				bw.write(buffer.get(i).toString());
				if (i<buffer.size() - 1) {
					bw.newLine(); 
				}
			}
			bw.close();

		} catch (IOException e) {
			Print.printToUser(StorageMessage.SAVE_FILE_ERROR);
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
			String line = bufferedReader.readLine();

			while (line != null ) {

				String[] lineArray = line.split("~");
				LinkedList<Assignment> newBuffer = ToBuffer.addToBuffer(lineArray);
				buffer.addAll(newBuffer);

				line = bufferedReader.readLine();
			}
			fileReader.close();
		} catch (IOException e) {

			Print.printToUser(StorageMessage.STORAGE_FILE_ERROR);
			System.exit(SYSTEM_EXIT_ERROR);
		}
		return buffer;
	}
}

//public static LinkedList<Assignment> saveFile(String filePath, LinkedList<Assignment> buffer) throws JSONException {
//		JSONArray writeBuffer = new JSONArray();
//
//		File file = new File(filePath);
//
//		if(file.delete()) {
//		} else {
//			System.out.println(ERRORMESSAGELOADFAIL);
//		}
//
//		for(int i = 0; i < buffer.size(); i++) {
//			JSONObject obj = new JSONObject();
//			if (buffer.get(i).getAssignType()== AssignmentType.ASSIGNMENT) {
//				obj.put("ID", buffer.get(i).getId());
//				obj.put("Title", buffer.get(i).getTitle());
//				obj.put("IsDone", buffer.get(i).getIsDone());
//				obj.put("IsOnTime", buffer.get(i).getIsOnTime());
//				obj.put("Priority", buffer.get(i).getPriority());
//				writeBuffer.put(obj);
//			} else if (buffer.get(i).getAssignType()== AssignmentType.TASK) {
//				obj.put("ID", buffer.get(i).getId());
//				obj.put("Title", buffer.get(i).getTitle());
//				obj.put("IsDone", buffer.get(i).getIsDone());
//				obj.put("IsOnTime", buffer.get(i).getIsOnTime());
//				obj.put("Priority", buffer.get(i).getPriority());
//				obj.put("EndDate", ((Task)buffer.get(i)).getEndDate());
//				obj.put("EndTime", ((Task)buffer.get(i)).getEndTime());
//				writeBuffer.put(obj);
//			} else if (buffer.get(i).getAssignType()== AssignmentType.APPOINTMENT) {
//				obj.put("ID", buffer.get(i).getId());
//				obj.put("Title", buffer.get(i).getTitle());
//				obj.put("IsDone", buffer.get(i).getIsDone());
//				obj.put("IsOnTime", buffer.get(i).getIsOnTime());
//				obj.put("Priority", buffer.get(i).getPriority());
//				obj.put("EndDate", ((Appointment)buffer.get(i)).getEndDate());
//				obj.put("EndTime", ((Appointment)buffer.get(i)).getEndTime());
//				obj.put("StartDate", ((Appointment)buffer.get(i)).getStartDate());
//				obj.put("StartTime", ((Appointment)buffer.get(i)).getStartTime());
//				writeBuffer.put(obj);
//				System.out.println(obj.toString());
//
//			}
//		}
//		try {
//			FileWriter fw = new FileWriter(file.getAbsoluteFile());
//			BufferedWriter bw = new BufferedWriter(fw);
//			bw.write(writeBuffer.toString());
//			System.out.println(writeBuffer.toString());
//			bw.close();
//
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		return buffer;
//	}