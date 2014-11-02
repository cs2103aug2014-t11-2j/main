package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class TableManager {
	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected static Table setupTable(Shell shell) {
		/**
		 * TableViewer
		 */
		Table table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		//table = tableViewer.getTable();
		table.setBounds(43, 140, 921, 371);
		table.setLinesVisible(true);
		//formToolkit.paintBordersFor(table);
		TableColumn tc0 = new TableColumn(table, SWT.CENTER);
		tc0.setResizable(false);
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setResizable(false);
		TableColumn tc2 = new TableColumn(table, SWT.CENTER);
		tc2.setResizable(false);
		TableColumn tc3 = new TableColumn(table, SWT.CENTER);
		tc3.setResizable(false);
		TableColumn tc4 = new TableColumn(table, SWT.CENTER);
		tc4.setResizable(false);
		TableColumn tc5 = new TableColumn(table, SWT.CENTER);
		tc5.setResizable(false);
		TableColumn tc6 = new TableColumn(table, SWT.CENTER);
		tc6.setResizable(false);
		TableColumn tc7 = new TableColumn(table, SWT.CENTER);
		tc7.setResizable(false);
		tc0.setText("Creation");
		tc1.setText("Serial");
		tc2.setText("Type");
		tc3.setText("Title");
		tc4.setText("Start Date");
		tc5.setText("Start Time");
		tc6.setText("End Date");
		tc7.setText("End Time");
		tc0.setWidth(0);
		tc1.setWidth(55+57);
		tc2.setWidth(70);
		tc3.setWidth(390);
		tc4.setWidth(91);
		tc5.setWidth(81);
		tc6.setWidth(91);
		tc7.setWidth(81);
		table.setHeaderVisible(true);
		
		return table;
	}

}
