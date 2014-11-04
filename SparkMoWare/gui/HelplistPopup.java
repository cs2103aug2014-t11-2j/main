package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * Utility class to display helplist
 * @author Zhengyang
 */

public class HelplistPopup {
	private static Table table;
	private static LinkedList<String> helplistBuffer = new LinkedList<String>();
	private static Vector<Image> helpImage = new Vector<Image>();
	private static int count = 0;
	private static boolean isLoaded = false;
	private static int current;
	private static String[] temp = {"",""};


	/**
	 * helplistPopup open up the helplist in a new shell
	 * @wbp.parser.entryPoint
	 */
	protected static void helplistPopup() {
		Shell helpList = new Shell();
		helpList.setSize(700, 407);
		helpList.setText("Help List");
		Image trayicon = SWTResourceManager.getImage(MainController.class, "/resource/image/SparkMoVareTrayIcon.png");
		helpList.setImage(trayicon);

		Font font = new Font(helpList.getDisplay(), "Courier New", 15, SWT.BOLD);

		helpList.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				helpList.setSize(700, 407);	// force aspect so user cannot resize	
			}
		});

		TableViewer tableViewer = new TableViewer(helpList, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setEnabled(false);
		table.setBounds(0, 0, 684, 369);
		TableColumn tc = new TableColumn(table, SWT.CENTER);
		tc.setResizable(false);
		tc.setWidth(0);
		openFile();
		Iterator<String> tableLoaderIterator = helplistBuffer.iterator();
		while (tableLoaderIterator.hasNext()) {
			String textToDisplay = tableLoaderIterator.next();
			TableItem item = new TableItem(table,SWT.LEFT);
			if (textToDisplay.contains("%")) {
				item.setImage(getImage());
				System.out.println("load image");
			}
			else {
				temp[1] = textToDisplay;
				item.setText(temp);
				if (textToDisplay.contains(":")) {
					item.setFont(font);
				}
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

	protected static void loadimage() {
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/GUI.jpg"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/addASGN.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/addTASK.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/edit.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/delete.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/clearon.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/clearbefore.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/clearbetween.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/clear.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/tentative.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/confirm.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/search.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/undo.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/redo.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/statistics.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/sort.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/sortrefined.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/sortrefineddate.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/filter.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/filterrefined.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/filterrefinebetween.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/display.png"));
		helpImage.add(SWTResourceManager.getImage(MainController.class, "/resource/image/help/finish.png"));

		isLoaded = true;
	}

	/**
	 * imageGen returns a random image generated from
	 * @return
	 */
	protected static Image getImage() {
		if (!isLoaded) {
			loadimage();
		}
		if (helpImage.size()==0) {
			return null;
		}
		else {
			current = 0;
			if (count<(helpImage.size()-1)) {
				current = count++ ;
			}

			return helpImage.get(current);
		}
	}
}
