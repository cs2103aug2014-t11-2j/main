package gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import logic.Message;
import logic.SparkMoVare;

import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

import storage.Storage;

public class MainController {
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text cli;
	private static Text clockDisplay;
	private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private static DateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy");
	private static Date date = new Date();
	private static Shell shell;
	static Timer clockUpdater = new Timer("clockUpdater", true);
	private Text dateDisplay;
	private static Scanner sc = new Scanner(System.in);
	private Text quoteViewer;

	

	public MainController(Display display) {
		shell = new Shell(display);
		shell.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				shell.setSize(1024, 768);	// force aspect so user cannot resize	
			}
		});
		shell.setSize(1024, 768);
		shell.setText("SparkMoVare");
			

		/**
		 * Enter button
		 */
		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.setBounds(874, 640, 90, 30);
		btnEnter.setText("Enter");
		btnEnter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("x marks the spot");
			}
		});

		/**
		 * Command Line Interface
		 */
		cli = formToolkit.createText(shell, "", SWT.NONE);
		cli.setBounds(43, 644, 809, 26);
		cli.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
			//	sc.nextLine();
			}
		});

//		/**
//		 * List view
//		 */
//		TextViewer textViewer = new TextViewer(shell, SWT.BORDER | SWT.V_SCROLL);
//		List list = listViewer.getList();
//		list.setBounds(43, 167, 921, 454);
	
		/**
		 * Date Display
		 */
		dateDisplay = new Text(shell, SWT.BORDER | SWT.CENTER);
		dateDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		dateDisplay.setEnabled(false);
		dateDisplay.setEditable(false);
		dateDisplay.setBounds(761, 10, 235, 30);
		dateDisplay.setText(dateFormat.format(date).toString());
		
		/**
		 * Clock Display
		 */
		clockDisplay = formToolkit.createText(shell, "New Text", SWT.CENTER);
		clockDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		clockDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		clockDisplay.setEnabled(false);
		clockDisplay.setEditable(false);
		clockDisplay.setBounds(344, 82, 310, 52);
		clockDisplay.setText(timeFormat.format(date).toString());
		
		/**
		 * textViewr
		 */
		TextViewer textViewer = new TextViewer(shell, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();
		styledText.setBounds(43, 151, 921, 472);
		formToolkit.paintBordersFor(styledText);
		
		
		/**
		 * qouteViewer
		 */
		quoteViewer = formToolkit.createText(shell, "New Text", SWT.CENTER);
		quoteViewer.setEnabled(false);
		quoteViewer.setEditable(false);
		quoteViewer.setBounds(43, 676, 921, 26);
		quoteViewer.setText(quoteLib.getQuote());

		
		shell.open();
		
		/**
		 * Update Clock
		 */
		clockUpdater.schedule(new UpdateTimerTask(), 1000, 1000);
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}        
	}


	public static void main(String[] args) {
		Message.printToUser(Message.WELCOME);
		Storage.openFile(SparkMoVare.getfilePath(),SparkMoVare.getLatestSerialNumber(), SparkMoVare.getBuffer());

		Display display = new Display();
		new MainController(display);
		
		display.dispose();
		SparkMoVare.toDoManager();

	}
	
	private class UpdateTimerTask extends TimerTask
	{
	  @Override
	  public void run()
	  {
	    // Timer task runs in a background thread, 
	    // so use Display.asyncExec to run SWT code in UI thread
	    Display.getDefault().asyncExec(new Runnable() 
	     {
	       @Override
	       public void run()
	       {
	    	   date=new Date();
	    	   clockDisplay.setText(timeFormat.format(date).toString());
	    	   dateDisplay.setText(dateFormat.format(date).toString());
	       }
	     });  
	  }
	}
}