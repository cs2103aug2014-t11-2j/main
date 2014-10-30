package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class QuoteViewerManager {

	protected static Text quoteViewerSetup(Shell shell) {
		Text quoteViewer = new Text(shell, SWT.CENTER);
		quoteViewer.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		quoteViewer.setEnabled(false);
		quoteViewer.setEditable(false);
		quoteViewer.setBounds(43, 569, 921, 35);
		
		return quoteViewer;
	}

}
