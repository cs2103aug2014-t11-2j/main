package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

//@author A0116263M

public class TrayIconManager {

	/**
	 * Setup trayicon for GUI
	 * @param shell
	 * @param display
	 * @return Tray tray
	 */
	protected static Tray trayIconSetup(Shell shell, Display display) {
		
		Tray tray = display.getSystemTray();
		if (tray == null) {
			System.out.println("The system tray is not available");
		} else {
			final TrayItem item = new TrayItem(tray, SWT.NONE);
			item.setToolTipText("SparkMoVare");
			Image trayicon = SWTResourceManager.getImage(MainController.class, "/resource/image/SparkMoVareTrayIcon.png");
			item.setImage(trayicon);
			shell.setImage(trayicon);
			item.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					if(shell.getVisible() == false) {
						shell.setVisible(true);
					}
					shell.setFocus();
					shell.setActive();
				}
			});
			final Menu menu = new Menu(shell, SWT.POP_UP);
			MenuItem miHide = new MenuItem(menu, SWT.PUSH);
			miHide.setText("Hide" );
			miHide.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					if(shell.getVisible() == true) {
						shell.setVisible(false);
					}
				}
			});
			MenuItem miRestore = new MenuItem(menu, SWT.PUSH);
			miRestore.setText("Restore" );
			miRestore.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					if(shell.getVisible() == false) {
						shell.setVisible(true);
					}
					shell.setFocus();
					shell.setActive();
				}
			});
			MenuItem miExit = new MenuItem(menu, SWT.PUSH);
			miExit.setText("Exit" );
			miExit.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					System.exit(0);
				}
			});
			item.addListener(SWT.MenuDetect, new Listener() {
				public void handleEvent(Event event) {
					menu.setVisible(true);
				}
			});
		}
		
		return tray;
	}
}
