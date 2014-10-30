package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CLImanager {
	
	protected static Text cliSetup(Shell shell) {
		Text cli = new Text(shell, SWT.NONE);
		cli.setBounds(53, 527, 809, 26);
		return cli;
	}

}
