package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ClockAndDateManager {

	protected static Text clockDisplaySetup(Shell shell) {
		Text clockDisplay = new Text(shell, SWT.BORDER | SWT.CENTER);
		clockDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		clockDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		clockDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		clockDisplay.setEnabled(false);
		clockDisplay.setEditable(false);
		clockDisplay.setBounds(280, 30, 310, 52);
		return clockDisplay;
	}
	
	protected static Text dateDisplaySetup(Shell shell) {
		Text dateDisplay = new Text(shell, SWT.BORDER | SWT.CENTER);
		dateDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		dateDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		dateDisplay.setEnabled(false);
		dateDisplay.setEditable(false);
		dateDisplay.setBounds(763, 10, 235, 30);
		return dateDisplay;
	}
}
