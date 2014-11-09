package gui;

import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * Utility class to create a popup and display Statistics
 * @author Zhengyang
 */


public class StatsPopup {
	private static Table table;
	private static Text Percentage;
	private static Text txtUserStatistics;
	private static Text QuoteFeedback;
	
	/**
	 * statsAppear takes in the inputs and popup a summary of how the user has been performing
	 * @param totalTask
	 * @param completedTask
	 * @param onTimeTask
	 */
	protected static void statsAppear(int totalTask, int completedTask, int onTimeTask){
		
		//ensure valid stats are displayed
	    assert (totalTask >= completedTask) : "Value of " + totalTask + " < " + completedTask + " is too large to add.";
	    assert (totalTask >= onTimeTask) : "Value of " + totalTask + " < " + onTimeTask + " is too large to add.";
	    assert (onTimeTask <= completedTask) : "Value of " + onTimeTask + " < " + completedTask + " is too large to add.";

		int percentageOntime = (int)(((float)onTimeTask/completedTask)*100);
		Shell popup = new Shell(SWT.CLOSE);
		popup.setText("User Statistics");
		Image background = SWTResourceManager.getImage(MainController.class, "/resource/image/wallpaper.jpg");
		Image trayicon = SWTResourceManager.getImage(MainController.class, "/resource/image/SparkMoVareTrayIcon.png");
		popup.setImage(trayicon);
		popup.setBackgroundImage(background);
		popup.setSize(GUISize.STAT_SHELL_WIDTH, GUISize.STAT_SHELL_HEIGHT);
		
		ProgressBar progressBar = new ProgressBar(popup, SWT.NONE);
		progressBar.setBounds(GUISize.STAT_BAR_XCOOD, GUISize.STAT_BAR_YCOOD, GUISize.STAT_BAR_WIDTH, GUISize.STAT_BAR_HEIGHT);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setSelection((int)(((float)onTimeTask/totalTask)*100));
		
		table = new Table(popup, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(GUISize.STAT_PROGRESS_XCOOD, GUISize.STAT_PROGRESS_YCOOD, GUISize.STAT_PROGRESS_WIDTH, GUISize.STAT_PROGRESS_HEIGHT);
		TableColumn tc0 = new TableColumn(table, SWT.CENTER);
		tc0.setResizable(false);
		tc0.setWidth(GUISize.STAT_COL1);
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setResizable(false);
		tc1.setWidth(GUISize.STAT_COL2);
		table.setHeaderVisible(false);
		table.setLinesVisible(true);
		
		Percentage = new Text(popup, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		Percentage.setBounds(GUISize.STAT_PERCENT_XCOOD, GUISize.STAT_PERCENT_YCOOD, GUISize.STAT_PERCENT_WIDTH, GUISize.STAT_PERCENT_HEIGHT);
		Percentage.setText(Integer.toString(percentageOntime)+"%");
		
		txtUserStatistics = new Text(popup, SWT.BORDER | SWT.READ_ONLY);
		txtUserStatistics.setFont(SWTResourceManager.getFont("Showcard Gothic", 16, SWT.BOLD));
		txtUserStatistics.setText("User Statistics");
		txtUserStatistics.setBounds(86, 32, 251, 33);
		
		QuoteFeedback = new Text(popup, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		QuoteFeedback.setBounds(10, 205, 438, 27);
	    if (totalTask==0) {
			QuoteFeedback.setText("You have yet to set any item, start one today!");
	    } else if (percentageOntime<33) {
			QuoteFeedback.setText("You have much room for improvement, keep trying!");
		} else if (percentageOntime<66) {
			QuoteFeedback.setText("You are doing decently but there is still room for improvment, keep trying!");
		} else {
			QuoteFeedback.setText("Well done! You are doing a great job! keep it up!");
		}
		
		String totalTaskString = "Total number of Assignments: ~ "+ Integer.toString(totalTask);
		String lateTaskString = "Number of Completed Assignments: ~"+ Integer.toString(completedTask);
		String onTimeTaskString = "Number of on time Assignments: ~ "+ Integer.toString(onTimeTask);
		
		LinkedList<String> stringToDisplay = new LinkedList<String>();
		stringToDisplay.add(totalTaskString);
		stringToDisplay.add(lateTaskString);
		stringToDisplay.add(onTimeTaskString);
		
		
		for (int i=0; i<stringToDisplay.size();i++) {
			String[] holding = stringToDisplay.get(i).toString().split("~");
			TableItem item = new TableItem(table,SWT.NONE);
			item.setText(holding);
		}
		
		popup.open();
		
	}
}
