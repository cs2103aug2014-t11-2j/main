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

	public static final String SONGNAME = "soundtrack.mp3";

	private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss ");
	private static DateFormat dateFormat = new SimpleDateFormat("EEEE, d MMM yyyy");
	private static Date date = new Date();
	private static Shell shell;
	static Timer clockUpdater = new Timer("clockUpdater", true);
	private Text dateDisplay;
	private Text clockDisplay;
	private Text cli;
	private Text quoteViewer;
	private Text feedback;
	private Text hotkeyGuide;
	private Button btnEnter;
	private Table table;
	private Tray tray;
	private boolean isPlaying = false;
	private boolean isReady = false;
	private static MediaPlayer mediaPlayer;

	public static void main(String[] args) {

		System.out.println(Message.WELCOME);

		Display display = new Display();
		new MainController(display);

		display.dispose();
		mediaPlayer.stop();
		mediaPlayer.dispose();
	}

	/**
	 *  Sets up the GUI for user
	 * @param display
	 */
	public MainController(Display display) {

		shell = new Shell(display);
		shell.setSize(808, 681);
		Image background = SWTResourceManager.getImage(MainController.class, "/resource/image/wallpaper1.jpg");
		shell.setBackgroundImage(background);
		shell.setText("SparkMoVare");

		//Setting up the various components of GUI
		hotkeyGuide = HotkeyHintManager.hotkeySetup(shell);
		table = TableManager.setupTable(shell);
		cli = CLImanager.cliSetup(shell);
		btnEnter = BtnEnterManager.butEnterSetup(shell);
		quoteViewer = QuoteViewerManager.quoteViewerSetup(shell);
		feedback = FeedbackManager.feedbackSetup(shell);
		clockDisplay = ClockAndDateManager.clockDisplaySetup(shell);
		//dateDisplay = ClockAndDateManager.dateDisplaySetup(shell);	
		tray = TrayIconManager.trayIconSetup(shell, display);

		//initial loading
		TablerLoader.populateTable(table,SparkMoVare.storageSetup().getReturnBuffer());
		quoteViewer.setText(QuoteLib.getQuote());
		clockDisplay.setText(timeFormat.format(date));
		//dateDisplay.setText(dateFormat.format(date));

		// detecting MP3 and update option
		try {
			new JFXPanel();
			File f = new File(SONGNAME);
			Media hit = new Media(f.toURI().toString());
			mediaPlayer = new MediaPlayer(hit);
			isReady=true;
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Song not found");
		}

		// activate thread to run clock
		clockUpdater.schedule(new UpdateTimerTask(), 1000, 1000);

		// set listeners for resizing and stop them
		shell.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				shell.setSize(870, 681);	// force aspect so user cannot resize	
			}
		});

		// set listeners for hotkeys
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

		// set cli listener
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
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});

		// set button listener
		btnEnter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CommandHandler.commandHandle(cli, feedback, table);
			}
		});

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}        
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
					clockDisplay.setText(dateFormat.format(date).toString()+", "+ timeFormat.format(date).toString());
					//dateDisplay.setText(dateFormat.format(date).toString());
				}
			});  
		}
	}
}