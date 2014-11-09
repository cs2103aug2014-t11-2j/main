package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

//@author A0116263M

public class CLImanager {
	
	/**
	 * Setup CLI for GUI
	 * @param shell
	 * @return Text cli
	 */
	protected static Text cliSetup(Shell shell) {
		Text cli = new Text(shell, SWT.NONE);
		cli.setBounds(GUISize.CLI_XCOOD, GUISize.CLI_YCOOD, GUISize.CLI_WIDTH, GUISize.CLI_HEIGHT);
		return cli;
	}

}
