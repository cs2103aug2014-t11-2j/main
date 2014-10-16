package Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class logger {
	
	private static LinkedList<String> buffer = new LinkedList<String>();

	private static void openFile(String filePath) {
		try { 
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				buffer.add(line);
			}
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Exception encountered while initalising the textfile");
			System.exit(0);
		}
	}

	/**
	 * This operation is used save any changes made to the file
	 */
	private static void saveFile(String filePath) {
		File file = new File(filePath);
		file.delete();
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(int i=0; i< buffer.size(); i++){
				bw.write(buffer.get(i));
				if (i<buffer.size()-1){
					bw.newLine(); 
				}
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Exception encountered while saving the textfile");
			System.exit(0);
		}
	}
	
	public static void log(String logText) {
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String filePath="log.txt";
		openFile(filePath);
		String textToLog = dateFormat.format(date).toString() +", " + timeFormat.format(date).toString() + ":" + logText;
		buffer.add(textToLog);
		saveFile(filePath);
	}

}
