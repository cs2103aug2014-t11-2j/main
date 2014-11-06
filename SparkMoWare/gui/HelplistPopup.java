package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Utility class to create a popup and display helplist
 * @author Zhengyang
 */

public class HelplistPopup {
	private static Table table;
	private static LinkedList<String> helplistBuffer = new LinkedList<String>();

	/**
	 * helplistPopup open up the helplist in a new shell
	 * @wbp.parser.entryPoint
	 */
	protected static void helplistPopup() {
		Shell helpList = new Shell(SWT.CLOSE);
		helpList.setSize(GUISize.HELP_SHELL_WIDTH, GUISize.HELP_SHELL_HEIGHT);
		helpList.setText("Help List");
		Image trayicon = SWTResourceManager.getImage(MainController.class, "/resource/image/SparkMoVareTrayIcon.png");
		helpList.setImage(trayicon);

		Font title = new Font(helpList.getDisplay(), "Papyrus", 13, SWT.BOLD );

		TableViewer tableViewer = new TableViewer(helpList, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBounds(GUISize.HELP_XCOOD, GUISize.HELP_YCOOD, GUISize.HELP_WIDTH, GUISize.HELP_HEIGHT);
		openFile();
		Iterator<String> tableLoaderIterator = helplistBuffer.iterator();
		while (tableLoaderIterator.hasNext()) {
			String textToDisplay = tableLoaderIterator.next();
			TableItem item = new TableItem(table,SWT.LEFT);
			if (textToDisplay.contains(":")) {
				item.setFont(title);
			}
			item.setText(textToDisplay);

		}
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
