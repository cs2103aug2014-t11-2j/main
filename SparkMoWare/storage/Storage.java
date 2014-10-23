package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import logic.Appointment;
import logic.Assignment;
import logic.Comparator;
import logic.Id;
import logic.Message;
import logic.Print;
import logic.Task;


public class Storage {

	public static final int SYSTEM_EXIT_ERROR = 0;
	public static final String ERRORMESSAGELOADFAIL = "File loading failed!";
	private static final int  ASSIGNMENT_LENGTH = 6;
	private static final int TASK_LENGTH = 8;
	private static final int APPOINTMENT_LENGTH = 10;

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
			System.out.println("Exception encountered while saving the textfile");
			System.exit(SYSTEM_EXIT_ERROR);
		}		
		return buffer;
	}

	//	public static LinkedList<Assignment> saveFile(String filePath, LinkedList<Assignment> buffer) throws JSONException {
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

				buffer.addAll(addToBuffer(lineArray));

				line = bufferedReader.readLine();
			}
			fileReader.close();
		} catch (IOException e) {

			Print.printToUser(Message.STORAGE_FILE_ERROR);
			System.exit(SYSTEM_EXIT_ERROR);
		}
		return buffer;
	}
	
	private static LinkedList<Assignment> addToBuffer(String[] lineArray) {

		boolean check = false;

		LinkedList<Assignment> buffer = new LinkedList<Assignment>();

		if(lineArray.length == ASSIGNMENT_LENGTH) {

			check = AssignValidCheck.checkAssignment(lineArray);

			if(check) {
				buffer.add(toBufferAssignment(lineArray));
			}
		} else if (lineArray.length == TASK_LENGTH) {

			check = AssignValidCheck.checkTask(lineArray);

			if(check) {
				buffer.add(toBufferTask(lineArray));
			}
		} else if(lineArray.length == APPOINTMENT_LENGTH) {
			
			check = AssignValidCheck.checkAppointment(lineArray);

			if(check) {
				buffer.add(toBufferAppointment(lineArray));
			}
		}
		return buffer;
	}
	
	public static Assignment toBufferAssignment(String[] lineArray) {
		
		if(Id.getLatestSerialNumber().equals("")) {
			Id.setLatestSerialNumber(lineArray[0]);
		} else if(Comparator.serialNumberComparator(lineArray[0], Id.getLatestSerialNumber())) {
			Id.setLatestSerialNumber(lineArray[0]);
		}
		// adding as Assignment
			Assignment temp = new Assignment();
			temp.setId(lineArray[0]);
			temp.setTitle(lineArray[1]);
			temp.setIsDone(Boolean.parseBoolean(lineArray[3]));
			temp.setIsOnTime(Boolean.parseBoolean(lineArray[4]));
			temp.setPriority(lineArray[5]);
			return temp;
	}
	
	private static Task toBufferTask(String[] lineArray) {
		
			// adding as Task
			Task temp = new Task();
			temp.setId(lineArray[0]);
			temp.setTitle(lineArray[1]);
			temp.setIsDone(Boolean.parseBoolean(lineArray[4]));
			temp.setIsOnTime(Boolean.parseBoolean(lineArray[5]));
			temp.setPriority(lineArray[6]);
			temp.setEndDate(lineArray[2]);
			temp.setEndTime(lineArray[3]);
			return temp;	
	}
	
	private static Appointment toBufferAppointment(String[] lineArray) {
		
			// adding as Appointment
			Appointment temp = new Appointment();
			temp.setId(lineArray[0]);
			temp.setTitle(lineArray[1]);
			temp.setIsDone(Boolean.parseBoolean(lineArray[6]));
			temp.setIsOnTime(Boolean.parseBoolean(lineArray[7]));
			temp.setPriority(lineArray[8]);
			temp.setEndDate(lineArray[4]);
			temp.setEndTime(lineArray[5]);
			temp.setStartDate(lineArray[2]);
			temp.setStartTime(lineArray[3]);
			return temp;	
	}
} 