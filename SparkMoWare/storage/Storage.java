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

//@author A0116263M

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

	public static LinkedList<Assignment> openFile(String filePath, LinkedList<Assignment> buffer) {

		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();

			while (line != null ) {

				String[] lineArray = line.split("~");

				buffer.addAll(ToBuffer.addToBuffer(lineArray));

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