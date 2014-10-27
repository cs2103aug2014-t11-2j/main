package parser;

public class ParserTestDriver {

	/***********Major Component***************/
	
	public static String testInputIsAdd(String testInput) {
		return InputIsAdd.refineInput(testInput).toString();
	}
	
	/************ParserDateLocal Component**************/
	public static Boolean testHasTwoDateInputs(String testInput) {
		return ParserDateLocal.hasTwoDateInputs(testInput);
	}
	
	public static String testExtractStartDate(String testInput) {
		return ParserDateLocal.extractStartDate(testInput);
	}
	
	public static String testExtractEndDate(String testInput) {
		return ParserDateLocal.extractEndDate(testInput);
	}
	
	public static String testDetermineDateValidity(String testInput) {
		return ParserDateLocal.determineDateValidity(testInput);
	}
	
	public static boolean testDateFormatValid(String testInput) {
		return ParserDateLocal.dateFormatValid(testInput);
	}
	
	public static boolean testDateExists(int testInput) {
		return ParserDateLocal.dateExists(testInput);
	}
	
	public static String testDateString(String testInput) {
		return ParserDateLocal.dateString(testInput);
	}
	
	public static String testReplaceAllDate(String testInput) {
		return ParserDateLocal.replaceAllDate(testInput);
	}
	
	/************ParserTimeLocal Component**************/
	
	public static String testExtractStartTime(String testInput) {
		return ParserTimeLocal.extractStartTime(testInput);
	}
	
	public static Boolean testHasTwoTimeInputs(String testInput) {
		return ParserTimeLocal.hasTwoTimeInputs(testInput);
	}

	public static String testExtractEndTime(String testInput) {
		return ParserTimeLocal.extractEndTime(testInput);
	}
	
	public static String testReplaceAllTime(String testInput) {
		return ParserTimeLocal.replaceAllTime(testInput);
	}
	
	/************Misc Component**************/
	
	public static String testRefineString(String[] testInput) {
		return Misc.refineString(testInput);
	}
	
	public static String testExtractTitle(String testInput, String command) {
		return Misc.extractTitle(testInput, command);
	}
	
		public static boolean testIsFloatingAssignment(String testInput) {
		return Misc.isFloatingAssignment(testInput);
	}
	
		public static String testExtractPriority(String testInput) {
		return Misc.extractPriority(testInput);
	}
	
	/************ExtractSpecialContent Component**************/
		
	
	
	/************InvalidSpecialContnet Component**************/
	
	public static boolean testContentForClear(String testInput) {
		return InvalidSpecialContent.contentForClear(testInput);
	}


	

}
