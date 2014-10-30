package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

public class BtnEnterManager {
	
	protected static Button butEnterSetup(Shell shell) {
		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.setBounds(868, 527, 96, 26);
		btnEnter.setText("Enter");
		return btnEnter;
	}

}
