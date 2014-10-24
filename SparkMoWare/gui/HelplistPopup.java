package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class HelplistPopup {
	private static Table table;
	static LinkedList<String> helplistBuffer = new LinkedList<String>();
	

	/**
	 * @wbp.parser.entryPoint
	 */
	protected static void helplistPopup() {
		Shell helpList = new Shell();
		helpList.setSize(550, 400);
		helpList.setText("Help List");
		Image trayicon = SWTResourceManager.getImage(MainController.class, "/resource/image/SparkMoVareTrayIcon.png");
		helpList.setImage(trayicon);
		
		Font font = new Font(helpList.getDisplay(), "Courier New", 15, SWT.BOLD);

		
		TableViewer tableViewer = new TableViewer(helpList, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBounds(10, 10, 514, 342);
		openFile();
		for (int i=0; i<helplistBuffer.size();i++) {
			TableItem item = new TableItem(table,SWT.NONE);
			item.setText(helplistBuffer.get(i));
			if (helplistBuffer.get(i).contains(":")) {
				item.setFont(font);
			}
		}
		helpList.open();
	}
	
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
