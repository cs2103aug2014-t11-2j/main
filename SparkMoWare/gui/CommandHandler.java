package gui;

import logic.Output;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

//@author A0116263M

public class CommandHandler {

	/**
	 * commandHandle collect the user input from cli and send the string to logic for processing
	 * It will also handle the Output from logic accordingly and call for functions to perform
	 * respective action related to the command detected.
	 * 
	 * @param cli 
	 * 				SWT textbox which the GUI get the user input
	 * @param feedback
	 * 				Display the feedback from logic to user in this text
	 * @param tableViewer
	 * 				Display the table from logic to user in this tableviewer
	 */
	public static void commandHandle(Text cli, Text feedback, Table table, Table important) {
		String userInput= cli.getText().toString();
		System.out.println(userInput);
		Output output = logic.SparkMoVare.executeCommand(userInput);
		cli.setText("");
		feedback.setText(output.getFeedback());
		if(output.getIsStats()) {
			StatsPopup.statsAppear(output.getTotalAssignment(), output.getTotalCompleted(), output.getTotalOnTime());
		} else {
			TablerLoader.populateTable(table,important, output.getReturnBuffer());
		}
	}
}
