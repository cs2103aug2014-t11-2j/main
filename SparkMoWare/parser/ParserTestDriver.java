package parser;

public class ParserTestDriver {

	/***********Major Component***************/
	
	public static String testInputIsAdd(String testInput) {
		return InputIsAdd.refineInput(testInput).toString();
	}
	
	public static String testInputIsTentative(String testInput) {
		return InputIsTentative.refineInput(testInput).toString();
	}
	
	public static String testInputIsClear(String testInput) {
		return InputIsClear.refineInput(testInput).toString();
	}
	
	public static String testInputIsConfirm(String testInput) {
		return InputIsConfirm.refineInput(testInput).toString();
	}
	
	public static String testInputIsEdit(String testInput) {
		return InputIsEdit.refineInput(testInput).toString();
	}
	
	/***********RefineInputWithId Component***************/
	
	public static String testInputIsDelete(String testInput) {
		return RefineInputWithId.inputIsDelete(testInput).toString();
	}
	
	public static String testInputIsFinish(String testInput) {
		return RefineInputWithId.inputIsFinish(testInput).toString();
	}
	
	/***********RefineInputWithSpecial Component***************/
	
	public static String testInputIsFilter(String testInput) {
		return RefineInputWithSpecial.inputIsFilter(testInput).toString();
	}
	
	public static String testInputIsSearch(String testInput) {
		return RefineInputWithSpecial.inputIsSearch(testInput).toString();
	}
	
	public static String testInputIsSort(String testInput) {
		return RefineInputWithSpecial.inputIsSort(testInput).toString();
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
	
	//unsused test
	public static String testDetermineDateValidity(String testInput) {
		return ParserDateLocal.determineDateValidity(testInput);
	}
	
	public static boolean testDateFormatValid(String testInput) {
		return ParserDateLocal.dateFormatValid(testInput);
	}
	
	public static boolean testDateExists(int testInput) {
		return ParserDateLocal.dateExists(testInput);
	}
	
	public static String testReplaceAllDate(String testInput) {
		return ParserDateLocal.replaceAllDate(testInput);
	}
	
	public static String testExtractTentativeDates(String testInput) {
		return ParserDateLocal.extractTentativeDates(testInput).toString();
	}
	
	/************ParserTimeLocal Component**************/
	
	public static String testExtractStartTime(String testInput) {
		return ParserTimeLocal.extractStartTime(testInput);
	}
	
	public static boolean testHasTwoTimeInputs(String testInput) {
		return ParserTimeLocal.hasTwoTimeInputs(testInput);
	}

	public static String testExtractEndTime(String testInput) {
		return ParserTimeLocal.extractEndTime(testInput);
	}
	
	public static String testReplaceAllTime(String testInput) {
		return ParserTimeLocal.replaceAllTime(testInput);
	}
	
	public static String testDetermineTimeValidity(String testInput) {
		return ParserTimeLocal.determineTimeValidity(testInput);
	}
	
	public static boolean testTimeFormatValid(String testInput) {
		return ParserTimeLocal.timeFormatValid(testInput);
	}

	public static boolean testTimeExists(int testInput) {
		return ParserTimeLocal.timeExists(testInput);
	}

	public static String testExtractTentativeTimes(String testInput) {
		return ParserTimeLocal.extractTentativeTimes(testInput).toString();
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
	
	public static String testRemoveCommand(String testInput, String command) {
		return Misc.removeCommand(testInput, command);
	}
	
	public static String testExtractId(String testInput) {
		return Misc.extractId(testInput);
	}
	
	public static String testDetermineIdValidity(String testInput) {
		return Misc.determineIdValidity(testInput);
	}
	
	public static boolean testDeterminePriorityValidity(String testInput) {
		return Misc.determinePriorityValidity(testInput);
	}
	
	public static String testRemovePriority(String testInput) {
		return Misc.removePriority(testInput);
	}

	/************InvalidSpecialContent Component**************/
	
	public static boolean testContentForClear(String testInput) {
		return InvalidSpecialContent.contentForClear(testInput);
	}

	/*public static boolean testContentForSearch(String testInput) {
		return InvalidSpecialContent.contentForSearch(testInput);
	}*/
	
	/************ExtractSpecialContent Component**************/
		
	public static String testforClear(String testInput) {
		return ExtractSpecialContent.forClear(testInput);
	}

	public static String testforSearch(String testInput) {
		return ExtractSpecialContent.forSearch(testInput);
	}
	
	public static String testforSort(String testInput) {
		return ExtractSpecialContent.forSort(testInput);
	}
	
	public static String testforFilter(String testInput) {
		return ExtractSpecialContent.forFilter(testInput);
	}
}
