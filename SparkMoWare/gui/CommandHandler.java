package gui;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Text;

public class CommandHandler {
	
	public static void commandHandle(Text cli, Text feedback, String userInput, TableViewer tableViewer) {
		
	userInput= cli.getText().toString();
	System.out.println(userInput);
	cli.setText("");
	feedback.setText("Task Added");
	//feedback.setText(feedback.getText().toString()+".");
	tableViewer.add(feedback.getText().toString());
	}
}
