package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

//@author A0116263M

public class BtnEnterManager {
	
	/**
	 * Setup "Enter" Button for GUI
	 * @param shell
	 * @return Button btnEnter
	 */
	protected static Button butEnterSetup(Shell shell) {
		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.setBounds(GUISize.BTN_ENTER_XCOOD, GUISize.BTN_ENTER_YCOOD, GUISize.BTN_ENTER_WIDTH, GUISize.BTN_ENTER_HEIGHT);
		btnEnter.setText("Enter");
		return btnEnter;
	}
}
