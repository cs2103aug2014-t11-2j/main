package gui;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import logic.Appointment;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TablerLoader {
	protected static void populateTable(Table table, LinkedList<Appointment> tableBuffer) {
		//Table table = tableViewer.getTable();
		Device device = Display.getCurrent ();
		Color Pink = new Color (device, 255, 182, 193);
		Color Orange = new Color (device, 255, 153, 51);

		table.removeAll();
		Iterator<Appointment> TableLoaderiterator = tableBuffer.iterator();
		while (TableLoaderiterator.hasNext()) {
			Appointment appointmentToLoad = TableLoaderiterator.next();
			if (!appointmentToLoad.getIsDone()){
				String[] holding = (" ~"+appointmentToLoad).toString().split("~");
				TableItem item = new TableItem(table,SWT.NONE);
				//temp measure
				String[] displayFormat = new String[5];
				displayFormat[1] = holding[0];
				displayFormat[1] = holding[1] +"-"+holding [2];
				displayFormat[2] = holding[4];
				if(!holding[5].equals("-"))	{
					displayFormat[3] = convertDate(holding[5])+", "+holding [6];
				}
				if(!holding[7].equals("-"))	{
					displayFormat[4] = convertDate(holding[7])+", "+holding [8];
				}
				//end temp measure
				item.setText(displayFormat);
				if (holding[holding.length-1].equals("IMPT")) {
					//	item.setForeground(Red);
					item.setBackground(Pink);
				}
				if (appointmentToLoad.getAssignType().toString().equals("TNTV")) {
					//	item.setForeground(Red);
					item.setBackground(Orange);
				}
			}
		}
		TableItem item = new TableItem(table,SWT.NONE);
		table.showItem(item);

	}
	
	private static String convertDate(String date) {
		String newDate = "";
		newDate = date.substring(0, 2) + "/" + date.substring(2, 4)+ "/" + date.substring(4);
		return newDate;
	}
}
