package gui;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import logic.Assignment;
import logic.Id;
import logic.Message;
import logic.SparkMoVare;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import storage.Storage;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainController {

	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private static Text clockDisplay;
	private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private static DateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy");
	private static Date date = new Date();
	private static Shell shell;
	static Timer clockUpdater = new Timer("clockUpdater", true);
	private Text dateDisplay;
	private Text quoteViewer;
	private String userInput="";
	private Text feedback;
	private Table table;
	private LinkedList<Assignment> buffer = SparkMoVare.getBuffer();

	public MainController(Display display) {
		shell = new Shell(display);
		shell.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				shell.setSize(1024, 768);	// force aspect so user cannot resize	
			}
		});
		shell.setSize(1024, 768);
		Image bg_Image = new Image(display, "wallpaper1.jpg");
		shell.setBackgroundImage(bg_Image);
		shell.setText("SparkMoVare");	

		/**
		 * TableViewer
		 */
		final TableViewer tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setBounds(43, 151, 921, 472);
		table.setLinesVisible(true);
		formToolkit.paintBordersFor(table);
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setResizable(false);
		TableColumn tc2 = new TableColumn(table, SWT.CENTER);
		tc2.setResizable(false);
		TableColumn tc3 = new TableColumn(table, SWT.CENTER);
		tc3.setResizable(false);
		TableColumn tc4 = new TableColumn(table, SWT.CENTER);
		tc4.setResizable(false);
		TableColumn tc5 = new TableColumn(table, SWT.CENTER);
		tc5.setResizable(false);
		TableColumn tc6 = new TableColumn(table, SWT.CENTER);
		tc6.setResizable(false);
		TableColumn tc7 = new TableColumn(table, SWT.CENTER);
		tc7.setResizable(false);
		tc1.setText("Serial Number");
		tc2.setText("Type");
		tc3.setText("Title");
		tc4.setText("Start Date");
		tc5.setText("Start Time");
		tc6.setText("End Date");
		tc7.setText("End Time");
		tc1.setWidth(107);
		tc2.setWidth(70);
		tc3.setWidth(390);
		tc4.setWidth(90);
		tc5.setWidth(80);
		tc6.setWidth(90);
		tc7.setWidth(80);
		table.setHeaderVisible(true);

		/**
		 * For display purpose during launch
		 **/
		TablerLoader.populateTable(tableViewer,buffer);

		/**
		 * Command Line Interface
		 */
		final Text cli = new Text(shell, SWT.NONE);
		cli.setBounds(43, 644, 809, 26);
		cli.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.LF) {
					CommandHandler.commandHandle(cli, feedback, userInput, tableViewer);
				}
				else if (e.keyCode == SWT.ESC && cli.getText().equals("DUMBWAYSTODIE")) {
					//easter egg
					feedback.setText("ACHIEVEMENT UNLOCK : Dumb Ways to Die!");
					try {
						JFXPanel fxPanel = new JFXPanel();
						File f = new File("Tangerine Kitty - Dumb Ways To Die.mp3");
						Media hit = new Media(f.toURI().toString());
						MediaPlayer mediaPlayer = new MediaPlayer(hit);
						mediaPlayer.play();

					} catch(Exception ex) {
						ex.printStackTrace();
						System.out.println("Exception");
					}
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});

				/**
				 * Enter button
				 */
				Button btnEnter = new Button(shell, SWT.NONE);
				btnEnter.setBounds(874, 640, 90, 30);
				btnEnter.setText("Enter");
				btnEnter.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						CommandHandler.commandHandle(cli, feedback, userInput, tableViewer);
					}
				});

				/**
				 * Date Display
				 */
				dateDisplay = new Text(shell, SWT.BORDER | SWT.CENTER);
				dateDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
				dateDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
				dateDisplay.setEnabled(false);
				dateDisplay.setEditable(false);
				dateDisplay.setBounds(761, 10, 235, 30);
				dateDisplay.setText(dateFormat.format(date).toString());

				/**
				 * Clock Display
				 */
				clockDisplay = formToolkit.createText(shell, "New Text", SWT.CENTER);
				clockDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
				clockDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
				clockDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				clockDisplay.setEnabled(false);
				clockDisplay.setEditable(false);
				clockDisplay.setBounds(344, 41, 310, 52);
				clockDisplay.setText(timeFormat.format(date).toString());


				/**
				 * feedbackDisplay
				 */
				feedback = new Text(shell, SWT.BORDER | SWT.CENTER);
				feedback.setEnabled(false);
				feedback.setEditable(false);
				feedback.setBounds(344, 99, 310, 26);
				formToolkit.adapt(feedback, true, true);

				/**
				 * quoteViewer
				 */
				quoteViewer = formToolkit.createText(shell, "New Text", SWT.CENTER);
				quoteViewer.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
				quoteViewer.setEnabled(false);
				quoteViewer.setEditable(false);
				quoteViewer.setBounds(43, 676, 921, 26);
				quoteViewer.setText(quoteLib.getQuote());

				/**
				 * Update Clock
				 */
				clockUpdater.schedule(new UpdateTimerTask(), 1000, 1000);

				shell.open();

				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}        
	}


	public static void main(String[] args) {

		System.out.println(Message.WELCOME);
		Storage.openFile(SparkMoVare.getfilePath(),Id.getLatestSerialNumber(), SparkMoVare.getBuffer());

		Display display = new Display();
		new MainController(display);

		display.dispose();
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