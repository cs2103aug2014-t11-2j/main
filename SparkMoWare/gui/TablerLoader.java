package gui;

import java.util.LinkedList;

import logic.Appointment;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TablerLoader {
	protected static void populateTable(TableViewer tableViewer, LinkedList<Appointment> buffer) {
		Table table = tableViewer.getTable();
		Device device = Display.getCurrent ();
		Color Red = new Color (device, 255, 0, 0);

		for (int i=0; i<buffer.size();i++) {
			String[] holding = buffer.get(i).toString().split("~");
			System.out.println(buffer.size());
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
