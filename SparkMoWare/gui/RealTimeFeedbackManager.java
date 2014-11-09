package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

//@author A0116263M

public class RealTimeFeedbackManager {

	private static TableItem commandtype;
	private static TableItem title;
	private static TableItem startDate;
	private static TableItem startTime;
	private static TableItem endDate;
	private static TableItem endTime;
	private static TableItem priority;

	/**
	 * Setup RealTimeFeedbac for GUI
	 * @param composite
	 * @return Table realTimeFeedBack
	 */
	protected static Table RealTimeFeedbackSetup(Composite composite) {
		Table realTimeFeedBack = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		realTimeFeedBack.setEnabled(false);
		GridData gd_table_2 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table_2.heightHint = 115;
		realTimeFeedBack.setLayoutData(gd_table_2);
		realTimeFeedBack.setHeaderVisible(false);
		realTimeFeedBack.setLinesVisible(true);
		TableColumn dummy = new TableColumn(realTimeFeedBack, SWT.CENTER);
		dummy.setResizable(false);
		TableColumn tc = new TableColumn(realTimeFeedBack, SWT.CENTER);
		tc.setResizable(false);
		TableColumn tc1 = new TableColumn(realTimeFeedBack, SWT.CENTER);
		tc1.setResizable(false);
		dummy.setWidth(0);
		tc.setWidth(118);
		tc1.setWidth(117);
		commandtype = new TableItem(realTimeFeedBack, SWT.CENTER);
		title = new TableItem(realTimeFeedBack, SWT.CENTER);
		startDate = new TableItem(realTimeFeedBack, SWT.CENTER);
		startTime = new TableItem(realTimeFeedBack, SWT.CENTER);
		endDate = new TableItem(realTimeFeedBack, SWT.CENTER);
		endTime = new TableItem(realTimeFeedBack, SWT.CENTER);
		priority = new TableItem(realTimeFeedBack, SWT.CENTER);
		commandtype.setText(1, "Command Type");
		title.setText(1, "Title");
		startDate.setText(1, "Start Date");
		startTime.setText(1, "Start Time");
		endDate.setText(1, "End Date");
		endTime.setText(1, "End Time");
		priority.setText(1,"Prority");
		return realTimeFeedBack;
	}
	/**
	 * 
	 * @return commandtype
	 */
	protected static TableItem getCommandtype() {
		return commandtype;
	}
	/**
	 * 
	 * @return title
	 */
	protected static TableItem getTitle() {
		return title;
	}
	/**
	 * 
	 * @return startDate
	 */
	protected static TableItem getStartDate() {
		return startDate;
	}
	/**
	 * 
	 * @return startTime
	 */
	protected static TableItem getStartTime() {
		return startTime;
	}
	/**
	 * 
	 * @return endDate
	 */
	protected static TableItem getEndDate() {
		return endDate;
	}
	/**
	 * 
	 * @return endTime
	 */
	protected static TableItem getEndTime() {
		return endTime;
	}
	/**
	 * 
	 * @return priority
	 */
	protected static TableItem getPriority() {
		return priority;
	}
}
