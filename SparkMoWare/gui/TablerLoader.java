package gui;

import java.util.Iterator;
import java.util.LinkedList;

import logic.Appointment;
import logic.Mission;

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
	protected static void populateTable(Table table, LinkedList<Mission> tableBuffer) {
		//Table table = tableViewer.getTable();
		Device device = Display.getCurrent ();
		Color Pink = new Color (device, 255, 182, 193);
		Color Orange = new Color (device, 255, 153, 51);

		table.removeAll();
		Iterator<Mission> TableLoaderiterator = tableBuffer.iterator();
		while (TableLoaderiterator.hasNext()) {
			Mission appointmentToLoad = TableLoaderiterator.next();
			String[] holding = appointmentToLoad.toString().split("~");
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
			if (appointmentToLoad.getPriority().toString().equals("TNTV")) {
				//	item.setForeground(Red);
				item.setBackground(Orange);
			}
		}

		TableItem item = new TableItem(table,SWT.NONE);
		table.showItem(item);

	}

	protected static String convertDate(String date) {
		String newDate = "";
		newDate = date.substring(0, 2) + "/" + date.substring(2, 4)+ "/" + date.substring(4);
		return newDate;
	}
}
