package parser;

public class ParserTestDriver {

	public static Boolean testCheckIfAppt(String testInput) {
		return InputIsAdd.checkIfAppt(testInput);
	}
	
	public static String testGetStartDate(String testInput) {
		return InputIsAdd.extractStartDate(testInput);
	}
	
	public static String testGetEndDate(String testInput) {
		return InputIsAdd.extractEndDate(testInput);
	}
	
	public static String testGetStartTime(String testInput) {
		return InputIsAdd.extractStartTime(testInput);
	}
	
	public static Boolean testHasTwoTimeInputs(String testInput) {
		return InputIsAdd.hasTwoTimeInputs(testInput);
	}
	
	public static String testReplaceAllDate(String testInput) {
		return InputIsAdd.replaceAllDate(testInput);
	}

	public static String testGetEndTime(String testInput) {
		return InputIsAdd.extractEndTime(testInput);
	}
	
	public static String testReplaceAllTime(String testInput) {
		return InputIsAdd.replaceAllTime(testInput);
	}
	
	public static String testRefineString(String[] testInput) {
		return InputIsAdd.refineString(testInput);
	}
	
	public static String testGetTitle(String testInput) {
		return InputIsAdd.extractTitle(testInput);
	}
}
