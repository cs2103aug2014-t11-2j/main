package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
/**
 * Dependency injection for storage for op2 to load table from text file
 * @author Zhengyang
 *
 */

public class TextToAppointment {

	private static LinkedList<String> stringBuffer = new LinkedList<String>();

	/**
	 * openFile loads the help list from file
	 */
	private static void openFile() {
		try { 
			File file = new File("DI.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.add(line);
			}
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Exception encountered while initalising the textfile");
			System.exit(0);
		}
	}

	protected static void loadDI(Table table) {

		Device device = Display.getCurrent ();
		Color Red = new Color (device, 255, 0, 0);
		TableItem item = new TableItem(table,SWT.NONE);
		
		table.removeAll();
		
		openFile();
		Iterator<String> buffIter =  stringBuffer.iterator();
		while( buffIter.hasNext()) {
			String[] holding = buffIter.next().split("~");
			item = new TableItem(table,SWT.NONE);
			String temp ="";
			// swap title and type for better user aesthetic
			temp = holding[2];
			holding[2] = holding[3];
			holding[3] = temp;
			item.setText(holding);
			if (holding[1].equals("TTV")||holding[1].equals("2")) {
				item.setForeground(Red);
			}

		}
		stringBuffer.clear();
	}
}