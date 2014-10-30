package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class FeedbackManager {

	protected static Text feedbackSetup(Shell shell) {
		Text feedback = new Text(shell, SWT.BORDER | SWT.CENTER);
		feedback.setEnabled(false);
		feedback.setEditable(false);
		feedback.setBounds(344, 96, 310, 26);
		return feedback;
	}

}
