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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;

/**
 * Utility class to display helplist
 * @author Zhengyang
 */

public class HelpListNew {
	private static Table table;
	static LinkedList<String> helplistBuffer = new LinkedList<String>();

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

		final TabFolder tabFolder = new TabFolder(helpList, SWT.BORDER);
		tabFolder.setSize(682, 360);
		Text text = new Text(tabFolder, SWT.BORDER);
		text.setEditable(false);
		tabFolder.setLocation(0, 0);
		//overview
		TabItem overview = new TabItem(tabFolder, SWT.NULL);
		overview.setText("Overview");
		text.setText("This is page " + 1);
		overview.setControl(text);

		helpList.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				helpList.setSize(700, 407);	// force aspect so user cannot resize	
			}
		});


		helplistBuffer.clear();
		helpList.open();

	}
}
