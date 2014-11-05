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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tray;

import parser.EnumGroup;
import parser.RefinedUserInput;

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
	private boolean isExpose = true;
	private static MediaPlayer mediaPlayer;
	private Table table_1;
	private Table table_2;

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
		shell.setSize(1100, 686);
		Image background = SWTResourceManager.getImage(MainController.class, "/resource/image/wallpaper1.jpg");
		shell.setBackgroundImage(background);
		shell.setText("SparkMoVare");

		//tet
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(855, 0, 244, 658);
		composite.setLayout(new GridLayout(1, false));

		DateTime calendar = new DateTime (composite, SWT.CALENDAR | SWT.BORDER);

		table_2 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table_2 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table_2.heightHint = 115;
		table_2.setLayoutData(gd_table_2);
		table_2.setHeaderVisible(false);
		table_2.setLinesVisible(true);
		TableColumn dummy = new TableColumn(table_2, SWT.CENTER);
		dummy.setResizable(false);
		TableColumn tc = new TableColumn(table_2, SWT.CENTER);
		tc.setResizable(false);
		TableColumn tc1 = new TableColumn(table_2, SWT.CENTER);
		tc1.setResizable(false);
		dummy.setWidth(0);
		tc.setWidth(118);
		tc1.setWidth(117);
		TableItem commandtype = new TableItem(table_2,SWT.CENTER);
		TableItem title = new TableItem(table_2,SWT.CENTER);
		TableItem startDate = new TableItem(table_2,SWT.CENTER);
		TableItem startTime = new TableItem(table_2,SWT.CENTER);
		TableItem endDate = new TableItem(table_2,SWT.CENTER);
		TableItem endTime = new TableItem(table_2,SWT.CENTER);
		TableItem priority = new TableItem(table_2,SWT.CENTER);
		commandtype.setText(1,"Command Type");
		title.setText(1, "Title");
		startDate.setText(1, "Start Date");
		startTime.setText(1, "Start Time");
		endDate.setText(1, "End Date");
		endTime.setText(1, "End Time");
		priority.setText(1,"Prority");

		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table_1.widthHint = 80;
		gd_table_1.heightHint = 335;
		table_1.setLayoutData(gd_table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(false);

		TableColumn dummy1 = new TableColumn(table_1, SWT.CENTER);
		dummy1.setResizable(false);
		TableColumn id = new TableColumn(table_1, SWT.CENTER);
		id.setResizable(false);
		id.setText("Serial");
		TableColumn title1 = new TableColumn(table_1, SWT.CENTER);
		title1.setResizable(false);
		dummy1.setWidth(0);
		id.setWidth(50);
		title1.setWidth(179);
		title1.setText("Title");

		//	        GridLayout gridLayout = new GridLayout();
		//	        gridLayout.numColumns = 1;
		//	        parent.setLayout(gridLayout);
		//	        DateTime calendar = new DateTime(parent, SWT.CALENDAR);
		//	        DateTime date = new DateTime(parent, SWT.DATE);
		//	        DateTime time = new DateTime(parent, SWT.TIME);
		//	        // Date Selection as a drop-down
		//	        DateTime dateD = new DateTime(parent, SWT.DATE | SWT.DROP_DOWN);
		//
		//	        shell.pack();
		//	        shell.open();
		//	        while (!shell.isDisposed()) {
		//	            if (!display.readAndDispatch())
		//	                display.sleep();
		//	        }


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
		//TablerLoader.populateTable(table,SparkMoVare.storageSetup().getReturnBuffer());
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
						shell.setSize(1100, 681);
						isExpose = true;
					}
					else {
						shell.setSize(855, 681);
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
					commandtype.setText(2,input.getCommandType().toString());
					// title
					if (input.getTitle().equalsIgnoreCase("default")){
						title.setText(2, "" );
					}
					else {
						title.setText(2, input.getTitle());
					}
					//start date
					if (input.getStartDate().equalsIgnoreCase("default")){
						startDate.setText(2, "" );
					}
					else {
						startDate.setText(2, input.getStartDate());
					}
					//start time
					if (input.getStartTime().equalsIgnoreCase("default")){
						startTime.setText(2, "" );
					}
					else {
						startTime.setText(2, input.getStartTime());
					}
					//end date
					if (input.getEndDate().equalsIgnoreCase("default")){
						endDate.setText(2, "" );
					}
					else {
						endDate.setText(2, input.getEndDate());
					}
					//end tiem
					if (input.getEndTime().equalsIgnoreCase("default")){
						endTime.setText(2, "" );
					}
					else {
						endTime.setText(2, input.getEndTime());
					}
					priority.setText(2, input.getPriority());
				} else {
					commandtype.setText(2,"");
					title.setText(2, "");
					startDate.setText(2, "");
					startTime.setText(2, "");
					endDate.setText(2, "");
					endTime.setText(2, "");
					priority.setText(2, "");
				}
				
			}
		});
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
				}else if (e.keyCode == SWT.F12 && cli.getText().equals("testing")) {
					StatsPopup.statsAppear(12, 10, 2);
					//easter egg
					feedback.setText("TEST MODE!");
					quoteViewer.setText("It’s not enough to be busy, so are the ants. The question is, what are we busy about? ~ Henry David Thoreau");
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
		composite.pack();
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