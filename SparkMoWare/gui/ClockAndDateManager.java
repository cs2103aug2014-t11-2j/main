package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

//@author A0116263M

public class ClockAndDateManager {
	
	/**
	 * Setup Clock and date for GUI
	 * @param shell
	 * @return Text clock and date display
	 */
	protected static Text clockDisplaySetup(Shell shell) {
		Text clockDisplay = new Text(shell, SWT.BORDER | SWT.CENTER);
		clockDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		clockDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		clockDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		clockDisplay.setEnabled(false);
		clockDisplay.setEditable(false);
		clockDisplay.setBounds(GUISize.CND_XCOOD, GUISize.CND_YCOOD, GUISize.CND_WIDTH, GUISize.CND_HEIGHT);
		return clockDisplay;
	}
}
