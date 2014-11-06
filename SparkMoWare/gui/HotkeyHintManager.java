package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Component class to setup the Hotkey hint for gui
 * @author Zhengyang
 */

public class HotkeyHintManager {
	
	/**
	 * Setup Hotkey hint for GUI
	 * @param shell
	 * @return Text feedback
	 */
	protected static Text hotkeySetup(Shell shell) {
		Text text = new Text(shell, SWT.CENTER);
		text.setEditable(false);
		text.setEnabled(false);
		text.setBounds(GUISize.HOTKEY_XCOOD, GUISize.HOTKEY_YCOOD, GUISize.HOTKEY_WIDTH, GUISize.HOTKEY_HEIGHT);
		//formToolkit.adapt(text, true, true);
		text.setText("F1: Help  |  F5: Refresh Interface  |  F6: Play/Stop Music  |  F12: Expand/Hide Tab  |  UP/DOWN Arrow: Command History");
		
		return text;
	}

}
