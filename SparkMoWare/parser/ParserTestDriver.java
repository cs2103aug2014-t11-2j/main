package parser;

public class ParserTestDriver {

	public static Boolean testHasTwoDateInputs(String testInput) {
		return ParserDateLocal.hasTwoDateInputs(testInput);
	}
	
	public static String testExtractStartDate(String testInput) {
		return ParserDateLocal.extractStartDate(testInput);
	}
	
	public static String testExtractEndDate(String testInput) {
		return ParserDateLocal.extractEndDate(testInput);
	}
	
	public static String testExtractStartTime(String testInput) {
		return ParserTimeLocal.extractStartTime(testInput);
	}
	
	public static Boolean testHasTwoTimeInputs(String testInput) {
		return ParserTimeLocal.hasTwoTimeInputs(testInput);
	}
	
	public static String testReplaceAllDate(String testInput) {
		return ParserDateLocal.replaceAllDate(testInput);
	}

	public static String testExtractEndTime(String testInput) {
		return ParserTimeLocal.extractEndTime(testInput);
	}
	
	public static String testReplaceAllTime(String testInput) {
		return ParserTimeLocal.replaceAllTime(testInput);
	}
	
	public static String testRefineString(String[] testInput) {
		return Misc.refineString(testInput);
	}
	
	public static String testExtractTitle(String testInput, String command) {
		return Misc.extractTitle(testInput, command);
	}
	
	public static boolean testContentForClear(String testInput) {
		return InvalidSpecialContent.contentForClear(testInput);
	}
}
