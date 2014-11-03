package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class HotkeyHintManager {
	
	protected static Text hotkeySetup(Shell shell) {
		Text text = new Text(shell, SWT.CENTER);
		text.setEditable(false);
		text.setEnabled(false);
		text.setBounds(0, 615, 808, 21);
		//formToolkit.adapt(text, true, true);
		text.setText("F1: Help | F5: Refresh Interface | F6: Play/Stop Music | UP/DOWN Arrow: Command History");
		
		return text;
	}

}
