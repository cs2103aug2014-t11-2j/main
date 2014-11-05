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
	
	protected static Pattern finishPattern = Pattern.compile("finish|fin|Finish|Fin|"
														   + "done|Done");
	
	protected static Pattern timePattern = Pattern.compile("([0-9]{4})");
	
	protected static Pattern datePattern = Pattern.compile("(([0-9]?[0-9])/([0-9]?[0-9])/([0-9]{2}))|"
														 + "(([0-9]?[0-9])_([0-9]?[0-9])_([0-9]{2}))|"
														 + "(([0-9]?[0-9])-([0-9]?[0-9])-([0-9]{2}))");
														// + "(([0-9]?[0-9]) ([0-9]?[0-9]) ([0-9]{2}))|"
														// + "([0-9])([0-9])([0-9])([0-9])([0-9])([0-9]))"
	
	protected static Pattern onPattern = Pattern.compile("on|On");
	
	protected static Pattern beforePattern = Pattern.compile("before|Before");
	
	protected static Pattern betweenPattern = Pattern.compile("between|Between");
	
	//unused pattern
	protected static Pattern rejectPattern = Pattern.compile("no|reject|No|Reject");
	
	protected static Pattern todayPattern = Pattern.compile("today|Today");
	
	protected static Pattern importantPattern = Pattern.compile("important|IMPT|Impt|impt|Important");

    protected static Pattern notImportantPattern = Pattern.compile("NIMPT|nIMPT|Nimportant|nimportant|"
    															 + "nimpt|NIMPORTANT");

    protected static Pattern startDatePattern = Pattern.compile("start date|start_date|start-date|-sd|"
    														  + "Start Date|Start_Date|Start-Date|-SD|"
    														  + "Start date|Start_date|Start-date|-Sd|"
    														  + "start Date|start_Date|start-Date|-sD|"
    														  + "startdate|STARTDATE|Startdate|StartDate");
    
    protected static Pattern startTimePattern = Pattern.compile("start time|start_time|start-time|-st|"
    														  + "Start Time|Start_Time|Start-Time|-ST|"
    														  + "Start time|Start_time|Start-time|-St|"
    														  + "start Time|start_Time|start-Time|-sT|"
    														  + "starttime|STARTTIME|Starttime|StartTime");
    
    protected static Pattern endDatePattern = Pattern.compile("end date|end_date|end-date|-ed|"
			  												+ "End Date|End_Date|End-Date|-ED|"
			  												+ "End date|End_date|End-date|-Ed|"
			  												+ "end Date|end_Date|end-Date|-eD|"
			  												+ "enddate|ENDDATE|Enddate|EndDate");
    
    protected static Pattern endTimePattern = Pattern.compile("end time|end_time|endtime|-et|"
    														+ "End Time|End_Time|End-Time|-ET|"
    														+ "End time|End_time|End-time|-Et|"
  														  	+ "end Time|end_Time|end-Time|-eT|"
  														  	+ "endtime|ENDTIME|Endtime|EndTime");
    
    protected static Pattern titlePattern = Pattern.compile("title|name|Title|Name");
    
    protected static Pattern priorityPattern = Pattern.compile("priority|Priority|-pri|-Pri");
    
    
    private static String symbols = Pattern.quote(")");
    protected static Pattern symbolsPattern = Pattern.compile(symbols);
}