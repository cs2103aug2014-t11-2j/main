package gui;

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
		Color Red = new Color (device, 255, 0, 0);
		
		table.removeAll();
		
		Iterator<Appointment> TableLoaderiterator = tableBuffer.iterator();
		while (TableLoaderiterator.hasNext()) {
		  Appointment appointmentToLoad = TableLoaderiterator.next();
		  String[] holding = appointmentToLoad.toString().split("~");
		  TableItem item = new TableItem(table,SWT.NONE);
			String temp ="";
			// swap title and type for better user aesthetic
			temp = holding[1];
			holding[1] = holding[2];
			holding[2] = temp;
			item.setText(holding);
			if (holding[1].equals("TTV")||holding[1].equals("2")) {
				item.setForeground(Red);
			}
		}
	}
}
