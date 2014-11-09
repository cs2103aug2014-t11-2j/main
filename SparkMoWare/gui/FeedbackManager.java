package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

//@author A0116263M

public class FeedbackManager {

	/**
	 * Setup feedback for GUI
	 * @param shell
	 * @return Text feedback
	 */
	protected static Text feedbackSetup(Shell shell) {
		Text feedback = new Text(shell, SWT.BORDER | SWT.CENTER);
		feedback.setEnabled(false);
		feedback.setEditable(false);
		feedback.setBounds(GUISize.FB_XCOOD, GUISize.FB_YCOOD, GUISize.FB_WIDTH, GUISize.FB_HEIGHT);
		return feedback;
	}
}
