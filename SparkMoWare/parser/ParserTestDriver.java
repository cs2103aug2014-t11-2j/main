package parser;

public class ParserTestDriver {

	public static Boolean testAppointmentChecker(String testInput) {
		return InputIsAdd.checkIfAppt(testInput);
	}
	
	public static String testGetStartDate(String testInput) {
		return InputIsAdd.getStartDate(testInput);
	}
	public static String testGetEndDate(String testInput) {
		return InputIsAdd.getEndDate(testInput);
	}
}
