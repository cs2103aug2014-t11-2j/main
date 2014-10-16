package gui;

import logic.Output;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Text;

public class CommandHandler {

	public static void commandHandle(Text cli, Text feedback, String userInput, TableViewer tableViewer) {

		userInput= cli.getText().toString();
		System.out.println(userInput);
		Output output = logic.SparkMoVare.executeCommand(userInput);
		cli.setText("");
		feedback.setText(output.getFeedback());
		//feedback.setText(feedback.getText().toString()+".");
		if(output.getIsStats()) {
			StatsPopup.statsAppear(output.getTotalAssignment(), output.getTotalCompleted(), output.getTotalOnTime());
		} else {
			TablerLoader.populateTable(tableViewer, output.getReturnBuffer());
		}
	}
}
