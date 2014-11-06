package gui;

import java.util.Iterator;
import java.util.LinkedList;

import logic.Mission;
import logic.Output;
import logic.SparkMoVare;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * 
 * @author Zhengyang
 *
 */

public class TablerLoader {
	protected static void populateTable(Table table, Table important, LinkedList<Mission> tableBuffer) {
		//Table table = tableViewer.getTable();
		Device device = Display.getCurrent ();
		Color Pink = new Color (device, 255, 182, 193);
		Color Orange = new Color (device, 255, 153, 51);

		table.removeAll();
		Iterator<Mission> TableLoaderiterator = tableBuffer.iterator();
		while (TableLoaderiterator.hasNext()) {
			Mission appointmentToLoad = TableLoaderiterator.next();
			TableItem item = new TableItem(table,SWT.NONE);
			String[] displayFormat = new String[5];
			displayFormat[1] = Integer.toString(appointmentToLoad.getIndex());
			displayFormat[2] = appointmentToLoad.getTitle();
			if(!appointmentToLoad.getStartDate().equals("-"))	{
				displayFormat[3] = appointmentToLoad.getStartDate()+", "+appointmentToLoad.getStartTime();
			}
			if(!appointmentToLoad.getEndDate().equals("-"))	{
				displayFormat[4] = appointmentToLoad.getEndDate()+", "+appointmentToLoad.getEndTime();
			}
			item.setText(displayFormat);
			if (appointmentToLoad.getPriority().toString().equals("IMPT")) {
				//	item.setForeground(Red);
				item.setBackground(Pink);
			}
			if (appointmentToLoad.getAssignType().toString().equals("TNTV")) {
				//	item.setForeground(Red);
				item.setBackground(Orange);
			}
		}

		TableItem item = new TableItem(table,SWT.NONE);
		table.showItem(item);
		updateImportant(important);

	}

	protected static void updateImportant(Table important) {
		Device device = Display.getCurrent ();
		Color Pink = new Color (device, 255, 182, 193);
		Output test = SparkMoVare.updateImportant();
		LinkedList<Mission> tableBuffer = test.getReturnBuffer();
		important.removeAll();
		Iterator<Mission> TableLoaderiterator = tableBuffer.iterator();
		while (TableLoaderiterator.hasNext()) {
			Mission appointmentToLoad = TableLoaderiterator.next();
			if (!appointmentToLoad.getIsDone()) {
				String[] holding =(" ~" + appointmentToLoad.toString()).split("~");
				TableItem item = new TableItem(important,SWT.NONE);
				item.setBackground(Pink);
				item.setText(holding);
			}
		}		
	}

	protected static String convertDate (String date) {
		return date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4);
	}
}
