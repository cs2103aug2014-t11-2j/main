package src;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.custom.TableCursor;

public class SparkMoWareGUI {

	protected Shell shell;
	private Text text;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SparkMoWareGUI window = new SparkMoWareGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(681, 483);
		shell.setText("SpareMoWare");
		shell.setLayout(new FormLayout());
		
		text = new Text(shell, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 379);
		fd_text.bottom = new FormAttachment(100, -35);
		fd_text.left = new FormAttachment(0, 189);
		text.setLayoutData(fd_text);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		fd_text.right = new FormAttachment(btnNewButton, -6);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(0, 377);
		fd_btnNewButton.bottom = new FormAttachment(100, -35);
		fd_btnNewButton.right = new FormAttachment(100, -78);
		fd_btnNewButton.left = new FormAttachment(0, 493);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("ENTER");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(btnNewButton, 0, SWT.RIGHT);
		fd_table.bottom = new FormAttachment(btnNewButton, -24);
		fd_table.left = new FormAttachment(text, 0, SWT.LEFT);
		fd_table.top = new FormAttachment(0, 81);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

	}
}
