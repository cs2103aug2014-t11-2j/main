package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * Component class to setup main display table for gui
 * @author Zhengyang
 *
 */

public class TableManager {
	
	/**
	 * Setup main table for GUI
	 * @param shell
	 * @return table table
	 */
	protected static Table setupTable(Shell shell) {
		Table table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		//table = tableViewer.getTable();
		table.setBounds(GUISize.MAIN_TABLE_XCOOD, GUISize.MAIN_TABLE_YCOOD, GUISize.MAIN_TABLE_WIDTH, GUISize.MAIN_TABLE_HEIGHT);
		table.setLinesVisible(true);
		//formToolkit.paintBordersFor(table);
		TableColumn tc = new TableColumn(table, SWT.CENTER);
		tc.setResizable(false);
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setResizable(false);
		TableColumn tc2 = new TableColumn(table, SWT.CENTER);
		tc2.setResizable(false);
		TableColumn tc3 = new TableColumn(table, SWT.CENTER);
		tc3.setResizable(false);
		TableColumn tc4 = new TableColumn(table, SWT.CENTER);
		tc4.setResizable(false);
		tc1.setText("SIN");
		tc2.setText("Title");
		tc3.setText("Start");
		tc4.setText("End");
		tc.setWidth(0);
		tc1.setWidth(100);
		tc2.setWidth(360);
		tc3.setWidth(150);
		tc4.setWidth(150);
		table.setHeaderVisible(true);
		
		return table;
	}

}
