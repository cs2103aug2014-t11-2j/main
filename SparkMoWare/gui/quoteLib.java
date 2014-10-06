package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class quoteLib {
	
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
			System.out.println("MESSAGE_FILE_INITIALISATION_ERROR");
			System.exit(0);
		}
	}
	
	public static String getQuote() {
		openFile("quoteList.txt");
		Random gen = new Random();
		System.out.println(buffer.get(gen.nextInt(buffer.size())));
		return buffer.get(gen.nextInt(buffer.size()));
	}


}
