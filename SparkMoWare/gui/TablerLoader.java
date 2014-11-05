package gui;

import java.util.Iterator;
import java.util.LinkedList;

import logic.Mission;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

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
			if (!appointmentToLoad.getIsDone()){
				String[] holding = (" ~"+appointmentToLoad).toString().split("~");
				TableItem item = new TableItem(table,SWT.NONE);
				//temp measure
				String[] displayFormat = new String[5];
				displayFormat[1] = holding[0];
				displayFormat[2] = holding[1];
				if(!holding[3].equals("-"))	{
					displayFormat[3] = convertDate(holding[3])+", "+holding [4];
				}
				if(!holding[4].equals("-"))	{
					displayFormat[4] = convertDate(holding[5])+", "+holding [6];
				}
				//end temp measure
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
