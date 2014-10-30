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
import logic.Appointment;
import logic.Id;
import logic.InternalStorage;
import logic.Message;
import logic.SparkMoVare;

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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import storage.Storage;

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
	private Text feedback;
	private Table table;
	private boolean isPlaying = false;
	private boolean isReady = false;
	private static MediaPlayer mediaPlayer;
	private Text text;
	
	/**
	 *  Sets up the GUI for user
	 * @param display
	 */
	public MainController(Display display) {
		//ImageGetter.loadimage();
		shell = new Shell(display);
		////logger.log("GUI, setting up shell");
		
		
		shell.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				shell.setSize(1025, 681);	// force aspect so user cannot resize	
			}
		});
		shell.setSize(1025, 681);
		////logger.log("GUI, importing background");
		Image background = SWTResourceManager.getImage(MainController.class, "/resource/image/wallpaper1.jpg");
		//logger.log("GUI, setting Background");
		shell.setBackgroundImage(background);
		shell.setText("SparkMoVare");

		/**
		 * Setting to tray and minimising to tray
		 */
		try {
			@SuppressWarnings("unused")
			JFXPanel fxPanel = new JFXPanel();
			File f = new File("soundtrack.mp3");
			Media hit = new Media(f.toURI().toString());
			mediaPlayer = new MediaPlayer(hit);
			isReady=true;
			f.deleteOnExit();

		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception");
		}


		/**
		 * Setting to tray and minimising to tray
		 */
		////logger.log("GUI, setting up trayicon");

		final Tray tray = display.getSystemTray();
		if (tray == null) {
			System.out.println("The system tray is not available");
		} else {
			final TrayItem item = new TrayItem(tray, SWT.NONE);
			item.setToolTipText("SparkMoVare");
			Image trayicon = SWTResourceManager.getImage(MainController.class, "/resource/image/SparkMoVareTrayIcon.png");
			item.setImage(trayicon);
			shell.setImage(trayicon);
			item.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					if(shell.getVisible() == false) {
						shell.setVisible(true);
					}
					shell.setFocus();
					shell.setActive();
				}
			});
			final Menu menu = new Menu(shell, SWT.POP_UP);
			MenuItem miHide = new MenuItem(menu, SWT.PUSH);
			miHide.setText("Hide" );
			miHide.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					if(shell.getVisible() == true) {
						shell.setVisible(false);
					}
				}
			});
			MenuItem miRestore = new MenuItem(menu, SWT.PUSH);
			miRestore.setText("Restore" );
			miRestore.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					if(shell.getVisible() == false) {
						shell.setVisible(true);
					}
					shell.setFocus();
					shell.setActive();
				}
			});
			MenuItem miExit = new MenuItem(menu, SWT.PUSH);
			miExit.setText("Exit" );
			miExit.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					System.exit(0);
				}
			});
			item.addListener(SWT.MenuDetect, new Listener() {
				public void handleEvent(Event event) {
					menu.setVisible(true);
				}
			});
		}
		shell.getDisplay().addFilter(SWT.KeyUp, new Listener()
		{
			@Override
			public void handleEvent(Event e) {
				if (e.keyCode == SWT.ESC) {
					if(shell.getVisible() == true) {
						shell.setVisible(false);
					}
				} else if (e.keyCode == SWT.F1) {
					HelplistPopup.helplistPopup();
					feedback.setText("Help List Selected");
				} else if (e.keyCode == SWT.F5) {
					shell.setBackgroundImage(ImageGetter.imageGen());
					quoteViewer.setText(QuoteLib.getQuote());
					feedback.setText("User Interface Refreshed");
				} else if (e.keyCode == SWT.F6) {

					if (!isPlaying && isReady) {
						mediaPlayer.play();
						isPlaying = true;
						feedback.setText("Playing Music");
					}
					else if (isPlaying && isReady) {
						mediaPlayer.stop();
						isPlaying = false;
						feedback.setText("Music Stopped!");
					}

				}
			}
		});

		/**
		 * TableViewer
		 */
		text = new Text(shell, SWT.CENTER);
		text.setEditable(false);
		text.setEnabled(false);
		text.setBounds(0, 615, 1008, 21);
		formToolkit.adapt(text, true, true);
		text.setText("F1: Help | F5: Refresh Interface | F6: Play/Stop Music | UP/DOWN Arrow: Command History");
		////logger.log("GUI, setting up table");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		//table = tableViewer.getTable();
		table.setBounds(43, 140, 921, 371);
		table.setLinesVisible(true);
		formToolkit.paintBordersFor(table);
		TableColumn tc0 = new TableColumn(table, SWT.CENTER);
		tc0.setResizable(false);
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
		tc0.setText("Creation");
		tc1.setText("Serial");
		tc2.setText("Type");
		tc3.setText("Title");
		tc4.setText("Start Date");
		tc5.setText("Start Time");
		tc6.setText("End Date");
		tc7.setText("End Time");
		tc0.setWidth(57);
		tc1.setWidth(50);
		tc2.setWidth(70);
		tc3.setWidth(395);
		tc4.setWidth(91);
		tc5.setWidth(81);
		tc6.setWidth(91);
		tc7.setWidth(81);
		table.setHeaderVisible(true);

		/**
		 * For display purpose during launch
		 **/

		TablerLoader.populateTable(table,SparkMoVare.storageSetup().getReturnBuffer());

		
		/**
		 * Command Line Interface
		 */
		////logger.log("GUI, setting up CLI");

		final Text cli = new Text(shell, SWT.NONE);
		cli.setBounds(53, 527, 809, 26);
		cli.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.LF) {
					CommandHistory.addCmd(cli.getText());
					CommandHandler.commandHandle(cli, feedback, table);
				}else if (e.keyCode == SWT.ARROW_UP) {
					String commandCheck = CommandHistory.getPrevCmd();
					if (!commandCheck.equals("")) {
						cli.setText(commandCheck);
					}
				}else if (e.keyCode == SWT.ARROW_DOWN) {
					String commandCheck = CommandHistory.getNextCmd();
					if (!commandCheck.equals("")) {
						cli.setText(commandCheck);
					}
				}else if (e.keyCode == SWT.F12 && cli.getText().equals("di")) {
					table.removeAll();
					TextToAppointment.loadDI(table);
					feedback.setText("DI TEST MODE!");
				}else if (e.keyCode == SWT.F12 && cli.getText().equals("testing")) {
					StatsPopup.statsAppear(12, 10, 2);
					//easter egg
					feedback.setText("TEST MODE!");
					quoteViewer.setText("Whenever you are asked if you can do a job, tell 'em, 'Certainly I can!' Then get busy and find out how to do it. ~ Theodore Roosevelt");
					//					try {
					//						JFXPanel fxPanel = new JFXPanel();
					//						URL url = this.getClass().getResource("Tangerine Kitty - Dumb Ways To Die.mp3");
					//						//File f = new File(url.toURI());
					//
					//						File f = new File("Tangerine Kitty - Dumb Ways To Die.mp3");
					//					
					//						Media hit = new Media(f.toURI().toString());
					//						MediaPlayer mediaPlayer = new MediaPlayer(hit);
					//						mediaPlayer.play();
					//
					//					} catch(Exception ex) {
					//						ex.printStackTrace();
					//						System.out.println("Exception");
					//					}
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});

		/**
		 * Enter button
		 */
		////logger.log("GUI, setting up enter button");

		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.setBounds(868, 527, 96, 26);
		btnEnter.setText("Enter");
		btnEnter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CommandHandler.commandHandle(cli, feedback, table);
			}
		});

		/**
		 * Date Display
		 */
		////logger.log("GUI, setting up date");

		dateDisplay = new Text(shell, SWT.BORDER | SWT.CENTER);
		dateDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		dateDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		dateDisplay.setEnabled(false);
		dateDisplay.setEditable(false);
		dateDisplay.setBounds(763, 10, 235, 30);
		dateDisplay.setText(dateFormat.format(date).toString());

		/**
		 * Clock Display
		 */
		////logger.log("GUI, setting up clock");

		clockDisplay = formToolkit.createText(shell, "New Text", SWT.CENTER);
		clockDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		clockDisplay.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		clockDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		clockDisplay.setEnabled(false);
		clockDisplay.setEditable(false);
		clockDisplay.setBounds(344, 38, 310, 52);
		clockDisplay.setText(timeFormat.format(date).toString());


		/**
		 * feedbackDisplay
		 */
		////logger.log("GUI, setting up feedback");

		feedback = new Text(shell, SWT.BORDER | SWT.CENTER);
		feedback.setEnabled(false);
		feedback.setEditable(false);
		feedback.setBounds(344, 96, 310, 26);
		formToolkit.adapt(feedback, true, true);

		/**
		 * quoteViewer
		 */
		//logger.log("GUI, setting up qoute viewer");

		quoteViewer = formToolkit.createText(shell, "New Text", SWT.CENTER);
		quoteViewer.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		quoteViewer.setEnabled(false);
		quoteViewer.setEditable(false);
		quoteViewer.setBounds(43, 569, 921, 35);
		quoteViewer.setText(QuoteLib.getQuote());
		
		/**
		 * help text on gui
		 */

		/**
		 * Update Clock
		 */
		//logger.log("GUI, start thread to update clock");

		clockUpdater.schedule(new UpdateTimerTask(), 1000, 1000);


		
		shell.open();


		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}        
	}


	public static void main(String[] args) {

		//logger.log("~~NEW LAUNCH~~");

		System.out.println(Message.WELCOME);

		Display display = new Display();
		new MainController(display);

		display.dispose();
		mediaPlayer.stop();
		mediaPlayer.dispose();
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