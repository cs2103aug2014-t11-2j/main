package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * It will load and save into arraylist before printing
 * 
 */
public class HelpList {

	private static ArrayList<String> helpList = new ArrayList<String>();

	public static void openFile(String filePath) {
		try { 
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				helpList.add(line);
			}
			fileReader.close();
		} catch (IOException e) {
			System.out.println("MESSAGE_FILE_INITIALISATION_ERROR");
			System.exit(0);
		}
	}

	public static ArrayList<String> getHelpList() {
		return helpList;
	}
}