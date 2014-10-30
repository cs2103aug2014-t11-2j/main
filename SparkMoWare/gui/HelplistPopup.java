package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Utility class to display helplist
 * @author Zhengyang
 */

public class HelplistPopup {
	private static Table table;
	static LinkedList<String> helplistBuffer = new LinkedList<String>();

	/**
	 * helplistPopup open up the helplist in a new shell
	 * @wbp.parser.entryPoint
	 */
	protected static void helplistPopup() {
		Shell helpList = new Shell();
		helpList.setSize(550, 400);
		helpList.setText("Help List");
		Image trayicon = SWTResourceManager.getImage(MainController.class, "/resource/image/SparkMoVareTrayIcon.png");
		helpList.setImage(trayicon);
		
		helpList.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				helpList.setSize(550, 400);	// force aspect so user cannot resize	
			}
		});
		
		Font font = new Font(helpList.getDisplay(), "Courier New", 15, SWT.BOLD);

		
		TableViewer tableViewer = new TableViewer(helpList, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBounds(10, 10, 514, 342);
		openFile();
		Iterator<String> tableLoaderIterator = helplistBuffer.iterator();
		while (tableLoaderIterator.hasNext()) {
			String textToDisplay = tableLoaderIterator.next();
			TableItem item = new TableItem(table,SWT.NONE);
			item.setText(textToDisplay);
			if (textToDisplay.contains(":")) {
				item.setFont(font);
			}
		}
		helplistBuffer.clear();
		helpList.open();
		
	}
	
	/**
	 * openFile loads the help list from file
	 */
	protected static void openFile() {
		try { 
			InputStream in = HelplistPopup.class.getResourceAsStream("/resource/text/HelpList.txt");
			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = input.readLine()) != null) {
				helplistBuffer.add(line);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("MESSAGE_FILE_INITIALISATION_ERROR");
		}
	}
}
