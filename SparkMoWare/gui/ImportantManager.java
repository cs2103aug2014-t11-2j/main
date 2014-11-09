package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * Component class to setup the enter butter for Important table
 * @author Zhengyang
 */


public class ImportantManager {

	/**
	 * Setup Important table for GUI
	 * @param composite
	 * @return Important tabl
	 */
	protected static Table imptDisplaySetup(Composite composite) {
			
		Table imptDisplay = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table_1 = new GridData(SWT.NONE, SWT.FILL, true, true, 1, 1);
		gd_table_1.widthHint = GUISize.IMPT_WIDTHHINT;
		gd_table_1.heightHint = GUISize.IMPT_HEIGHTHINT;
		imptDisplay.setLayoutData(gd_table_1);
		imptDisplay.setHeaderVisible(true);
		imptDisplay.setLinesVisible(false);

		TableColumn dummy1 = new TableColumn(imptDisplay, SWT.CENTER);
		dummy1.setResizable(false);
		TableColumn id = new TableColumn(imptDisplay, SWT.CENTER);
		id.setResizable(false);
		id.setText("Serial");
		TableColumn title1 = new TableColumn(imptDisplay, SWT.CENTER);
		title1.setResizable(false);
		dummy1.setWidth(0);
		id.setWidth(50);
		title1.setWidth(179);
		title1.setText("Important Task");
		
		return imptDisplay;
	}
}
