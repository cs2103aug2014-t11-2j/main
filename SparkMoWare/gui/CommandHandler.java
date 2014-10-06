package gui;

import org.eclipse.swt.widgets.Text;

public class CommandHandler {
	
	public static void commandHandle(Text cli, String userInput) {
		
	userInput= cli.getText().toString();
	System.out.println(userInput);
	cli.setText("");
	
	}
}
