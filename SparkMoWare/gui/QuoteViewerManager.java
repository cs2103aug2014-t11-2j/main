package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Component class to setup the QuoteViewer for gui
 * @author Zhengyang
 */

public class QuoteViewerManager {

	/**
	 * Setup Quoteviewer for GUI
	 * @param shell
	 * @return Text quoteviewer
	 */
	protected static Text quoteViewerSetup(Shell shell) {
		Text quoteViewer = new Text(shell, SWT.CENTER);
		quoteViewer.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		quoteViewer.setEnabled(false);
		quoteViewer.setEditable(false);
		quoteViewer.setBounds(GUISize.QUOTE_VIEWER_XCOOD, GUISize.QUOTE_VIEWER_YCOOD, GUISize.QUOTE_VIEWER_WIDTH, GUISize.QUOTE_VIEWER_HEIGHT);
		
		return quoteViewer;
	}

}
