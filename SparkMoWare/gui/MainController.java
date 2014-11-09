package gui;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import logic.Message;
import logic.SparkMoVare;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tray;

import parser.EnumGroup;
import parser.RefinedUserInput;

/**
 * Controller for GUI
 * @author Zhengyang
 */

public class MainController {

	public static final String SONGNAME = "soundtrack.mp3";
	public static final int DELAY = 1000;

	private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss ");
	private static DateFormat dateFormat = new SimpleDateFormat("EEEE, d MMM yyyy");
	private static Date date = new Date();
	private static Shell shell;
	static Timer clockUpdater = new Timer("clockUpdater", true);
	private Text clockDisplay;
	private Text cli;
	private Text quoteViewer;
	private Text feedback;
	@SuppressWarnings("unused")
	private Text hotkeyGuide;
	private Button btnEnter;
	private Table table;
	private Table imptDisplay;
	@SuppressWarnings("unused")
	private Table  realTimeFeedback;
	@SuppressWarnings("unused")
	private Tray tray;
	private boolean isPlaying = false;
	private boolean isReady = false;
	private boolean isExpose = true;
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

		shell = new Shell(display, SWT.CLOSE);
		shell.setSize(GUISize.MAIN_EXTENDED_WIDTH, GUISize.MAIN_HEIGHT);
		Image background = SWTResourceManager.getImage(MainController.class, "/resource/image/wallpaper1.jpg");
		shell.setBackgroundImage(background);
		shell.setText("SparkMoVare");

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(GUISize.COMPOSITE_XCOOD ,GUISize.COMPOSITE_YCOOD ,GUISize.COMPOSITE_WIDTH ,GUISize.COMPOSITE_HEIGHT);
		composite.setLayout(new GridLayout(1, false));

		new DateTime (composite, SWT.CALENDAR | SWT.BORDER);

		realTimeFeedback = RealTimeFeedbackManager.RealTimeFeedbackSetup(composite);
		TableItem r_commandtype = RealTimeFeedbackManager.getCommandtype();
		TableItem r_title =  RealTimeFeedbackManager.getTitle();
		TableItem r_startDate =  RealTimeFeedbackManager.getStartDate();
		TableItem r_startTime =  RealTimeFeedbackManager.getStartTime();
		TableItem r_endDate =  RealTimeFeedbackManager.getEndDate();
		TableItem r_endTime =  RealTimeFeedbackManager.getEndTime();
		TableItem r_priority =  RealTimeFeedbackManager.getPriority();


		imptDisplay = ImportantManager.imptDisplaySetup(composite);

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
		TablerLoader.populateTable(table,imptDisplay,SparkMoVare.storageSetup().getReturnBuffer());
		quoteViewer.setText(QuoteLib.getQuote());
		clockDisplay.setText(timeFormat.format(date));

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
		clockUpdater.schedule(new UpdateTimerTask(), DELAY, DELAY);

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

				} else if (e.keyCode == SWT.F12) {
					if (!isExpose) {
						shell.setSize(GUISize.MAIN_EXTENDED_WIDTH, GUISize.MAIN_HEIGHT);
						isExpose = true;
					}
					else {
						shell.setSize(GUISize.MAIN_WIDTH , GUISize.MAIN_HEIGHT);
						isExpose = false;
					}
				}
			}
		});

		// set cli listener
		cli.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent event) {
				RefinedUserInput input = SparkMoVare.parse(cli.getText());
				if (input.getCommandType() != EnumGroup.CommandType.INVALID_FORMAT ) {
					r_commandtype.setText(2,input.getCommandType().toString());
					// title
					if (input.getTitle().equalsIgnoreCase("default")){
						r_title.setText(2, "" );
					}
					else {
						r_title.setText(2, input.getTitle());
					}
					//start date
					if (input.getStartDate().equalsIgnoreCase("default")){
						r_startDate.setText(2, "" );
					}
					else {
						r_startDate.setText(2, input.getStartDate());
					}
					//start time
					if (input.getStartTime().equalsIgnoreCase("default")){
						r_startTime.setText(2, "" );
					}
					else {
						r_startTime.setText(2, input.getStartTime());
					}
					//end date
					if (input.getEndDate().equalsIgnoreCase("default")){
						r_endDate.setText(2, "" );
					}
					else {
						r_endDate.setText(2, input.getEndDate());
					}
					//end time
					if (input.getEndTime().equalsIgnoreCase("default")){
						r_endTime.setText(2, "" );
					}
					else {
						r_endTime.setText(2, input.getEndTime());
					}
					r_priority.setText(2, input.getPriority());
				} else {
					r_commandtype.setText(2,"");
					r_title.setText(2, "");
					r_startDate.setText(2, "");
					r_startTime.setText(2, "");
					r_endDate.setText(2, "");
					r_endTime.setText(2, "");
					r_priority.setText(2, "");
				}

			}
		});
		cli.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.LF) {
					CommandHistory.addCmd(cli.getText());
					CommandHandler.commandHandle(cli, feedback, table, imptDisplay);
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
				}
			}
			public void keyPressed(KeyEvent e) {
			}
		});

		// set button listener
		btnEnter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CommandHandler.commandHandle(cli, feedback, table, imptDisplay);
			}
		});
		composite.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}        
	}
	
	/**
	 * enables async threading for update of time and date
	 * @author Zhengyang
	 *
	 */
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
					date = new Date();
					clockDisplay.setText(dateFormat.format(date).toString()+", "+ timeFormat.format(date).toString());
				}
			});  
		}
	}
}