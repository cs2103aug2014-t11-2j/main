//For now, this class contains all the patterns of the various user inputs.
//For now, only one word for each user input

package parser;

import java.util.regex.Pattern;

public class ParserPatternLocal {

	protected static Pattern addPattern = Pattern.compile("add|Add");
	
	protected static Pattern editPattern = Pattern.compile("edit|Edit");
	
	protected static Pattern deletePattern = Pattern.compile("delete|Delete");
	
	protected static Pattern tentativePattern = Pattern.compile("tentative|Tentative");
	
	protected static Pattern confirmPattern = Pattern.compile("confirm|Confirm");
	
	protected static Pattern sortPattern = Pattern.compile("sort|Sort");
	
	protected static Pattern searchPattern = Pattern.compile("search|Search");
	
	protected static Pattern filterPattern = Pattern.compile("filter|Filter");
	
	protected static Pattern clearPattern = Pattern.compile("clear|Clear");
	
	protected static Pattern undoPattern = Pattern.compile("undo|Undo");
	
	protected static Pattern redoPattern = Pattern.compile("redo|Redo");
	
	protected static Pattern statisticPattern = Pattern.compile("statistic|stat|Statistic|Stat|"
															+ "statistics|stats|Statistics|Stats");
	
	protected static Pattern exitPattern = Pattern.compile("exit|Exit");
	
	protected static Pattern displayPattern = Pattern.compile("display|Display");
	
	protected static Pattern helpPattern = Pattern.compile("help|Help");
	
	protected static Pattern finishPattern = Pattern.compile("finish|fin|Finish|Fin");
	
	protected static Pattern timePattern = Pattern.compile("([0-9]{4})");
	
	protected static Pattern datePattern = Pattern.compile("(([0-9]?[0-9])/([0-9]?[0-9])/([0-9]{4}))|"
														 + "(([0-9]?[0-9])-([0-9]?[0-9])-([0-9]{4}))|"
														 + "(([0-9]?[0-9]).([0-9]?[0-9]).([0-9]{4}))");
	
	/* private Pattern TimePattern = Pattern.compile("[0-2][0-3][0-5][0-9]{4}"); //no 2400	
	 * private Pattern datePattern = Pattern.compile("[0-2]?[0-9]/[0-1]?[0-9]/[0-9]{4}");
	 *
	 * Decided against using the above. Checking date and time validity should be handled elsewhere
	 * if user types date incorrectly, Parser will interpret as no date and fill it in itself or assume no input, which is incorrect in some cases
	 */
	
	protected static Pattern onPattern = Pattern.compile("on|On");
	
	protected static Pattern beforePattern = Pattern.compile("before|Before");
	
	protected static Pattern betweenPattern = Pattern.compile("between|Between");
	
	protected static Pattern rejectPattern = Pattern.compile("no|reject|No|Reject");
	
	protected static Pattern todayPattern = Pattern.compile("today|Today");
	
	protected static Pattern importantPattern = Pattern.compile("important|IMPT|Impt|impt|Important");

	protected static Pattern notImportantPattern = Pattern.compile("NMPT|nMPT");
}
