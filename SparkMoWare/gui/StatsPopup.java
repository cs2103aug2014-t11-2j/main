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

public class StatsPopup {
	private static Table table;
	private static Text Percentage;
	private static Text txtUserStatistics;
	private static Text QuoteFeedback;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected static void statsAppear(int totalTask, int lateTask, int onTimeTask){
		
		//ensure valid stats are displayed
	    assert (totalTask >= lateTask) : "Value of " + totalTask + " < " + lateTask + " is too large to add.";
	    assert (totalTask >= onTimeTask) : "Value of " + totalTask + " < " + onTimeTask + " is too large to add.";
	    assert (totalTask == (lateTask+onTimeTask)) : "Value of " + totalTask + " < " + lateTask + " is too large to add.";

		int percentageOntime = (int)(((float)onTimeTask/totalTask)*100);
		Shell popup = new Shell();
		popup.setText("User Statistics");
		Image background = SWTResourceManager.getImage(MainController.class, "/resource/image/wallpaper.jpg");
		popup.setBackgroundImage(background);
		popup.setSize(474, 285);
		
		ProgressBar progressBar = new ProgressBar(popup, SWT.NONE);
		progressBar.setBounds(86, 83, 202, 21);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setSelection((int)(((float)onTimeTask/totalTask)*100));
		
		table = new Table(popup, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(64, 110, 305, 63);
		TableColumn tc0 = new TableColumn(table, SWT.CENTER);
		tc0.setResizable(false);
		tc0.setWidth(200);
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setResizable(false);
		tc1.setWidth(100);
		table.setHeaderVisible(false);
		table.setLinesVisible(true);
		
		Percentage = new Text(popup, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		Percentage.setBounds(294, 83, 46, 21);
		Percentage.setText(Integer.toString(percentageOntime)+"%");
		
		txtUserStatistics = new Text(popup, SWT.BORDER | SWT.READ_ONLY);
		txtUserStatistics.setFont(SWTResourceManager.getFont("Showcard Gothic", 16, SWT.BOLD));
		txtUserStatistics.setText("User Statistics");
		txtUserStatistics.setBounds(133, 32, 178, 33);
		
		QuoteFeedback = new Text(popup, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		QuoteFeedback.setBounds(10, 179, 438, 27);
		if (percentageOntime<33) {
			QuoteFeedback.setText("You have so much more room to improve, keep trying!");
		} else if (percentageOntime<66) {
			QuoteFeedback.setText("You are doing decently but there is still room for improvment, keep trying!");
		} else {
			QuoteFeedback.setText("Well done! You are doing a great job! keep it up!");
		}
		
		String totalTaskString = "Total number of Assignments: ~ "+ Integer.toString(totalTask);
		String lateTaskString = "Number of Late Assignments: ~"+ Integer.toString(lateTask);
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