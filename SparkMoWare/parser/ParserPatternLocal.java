//For now, this class contains all the patterns of the various user inputs.
//For now, only one word for each user input

package parser;

import java.util.regex.Pattern;

public class ParserPatternLocal {

	protected static Pattern addPattern = Pattern.compile("add|do");
	
	protected static Pattern editPattern = Pattern.compile("edit");
	
	protected static Pattern deletePattern = Pattern.compile("delete");
	
	protected static Pattern tentativePattern = Pattern.compile("tentative");
	
	protected static Pattern confirmPattern = Pattern.compile("confirm");
	
	protected static Pattern sortPattern = Pattern.compile("sort");
	
	protected static Pattern searchPattern = Pattern.compile("search");
	
	protected static Pattern filterPattern = Pattern.compile("filter");
	
	protected static Pattern clearPattern = Pattern.compile("clear");
	
	protected static Pattern undoPattern = Pattern.compile("undo");
	
	protected static Pattern redoPattern = Pattern.compile("redo");
	
	protected static Pattern statisticPattern = Pattern.compile("statistic|stat");
	
	protected static Pattern exitPattern = Pattern.compile("exit");
	
	protected static Pattern displayPattern = Pattern.compile("display");
	
	protected static Pattern helpPattern = Pattern.compile("help");
	
	protected static Pattern finishPattern = Pattern.compile("finish");
	
	protected static Pattern timePattern = Pattern.compile("([0-9]{4})");
	
	protected static Pattern datePattern = Pattern.compile("(([0-9]?[0-9])/([0-9]?[0-9])/([0-9]{4}))");
	
	/* private Pattern TimePattern = Pattern.compile("[0-2][0-3][0-5][0-9]{4}"); //no 2400	
	 * private Pattern datePattern = Pattern.compile("[0-2]?[0-9]/[0-1]?[0-9]/[0-9]{4}");
	 *
	 * Decided against using the above. Checking date and time validity should be handled elsewhere
	 * if user types date incorrectly, Parser will interpret as no date and fill it in itself or assume no input, which is incorrect in some cases
	 */
	
	protected static Pattern onPattern = Pattern.compile("on");
	
	protected static Pattern beforePattern = Pattern.compile("before");
	
	protected static Pattern betweenPattern = Pattern.compile("between");
	
	protected static Pattern rejectPattern = Pattern.compile("no|reject");
	
	protected static Pattern todayPattern = Pattern.compile("today");
}
