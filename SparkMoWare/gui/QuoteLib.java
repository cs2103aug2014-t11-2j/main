package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;

/**
 * Utility class to load motivational quotes from resourcepath
 * 
 * @author Zhengyang
 */

public class QuoteLib {
	
	private static LinkedList<String> buffer = new LinkedList<String>();
	
	/**
	 * openFile loads the help list from file
	 */
	protected static void openFile() {
		try { 
			InputStream in = QuoteLib.class.getResourceAsStream("/resource/text/quoteList.txt");
			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = input.readLine()) != null) {
				buffer.add(line);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("MESSAGE_FILE_INITIALISATION_ERROR");
		}
	}
	/**
	 * getQuote returns a random quote loaded buy QuoteLib
	 * @return String of random quote
	 */
	public static String getQuote() {
		openFile();
		Random gen = new Random();
		//System.out.println(buffer.get(gen.nextInt(buffer.size())));
		return buffer.get(gen.nextInt(buffer.size()));
	}


}
