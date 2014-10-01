package logic;

/*
 * Allows the user to create a number of tentative dates 
 * before confirming to have only one date for user's appointment
 * Title will be attached with the word [tentative]
 * userInput must be in this format:
 * <title><ddmmyyyy><hhmm><ddmmyyyy><hhmm>
 */

public class Tentative {

	public static String addTentative(String numOfTentative, String tentativeTitle) {
				
		int tentativeNum = Integer.parseInt(numOfTentative);
		String tentativeIdGenerated = Id.serialNumGen();
		
		tentativeTitle += " [tentative]";
		
		for(int tentativeCount = 1; tentativeCount <= tentativeNum; tentativeCount++) {

			String[] inputArray = SparkMoVare.scanner.nextLine().split(";");
			
			if(inputArray.length != 5) {
				tentativeCount--;
				
			} else {
				
				String endTime = inputArray[inputArray.length - 1];
				String endDate = inputArray[inputArray.length - 2];
				String startTime = inputArray[inputArray.length - 3];
				String startDate = inputArray[inputArray.length - 4];

				//addTask(ID, title, type, startDate, startTime, endDate, endTime, isCompletion, isOnTime)
				Add.addAssignment(tentativeIdGenerated, tentativeTitle, Assignment.TYPE_TENTATIVE, 
						startDate, startTime, endDate, endTime, false, null);
			}
		}
		return Message.TENTATIVE_ADDED;
	}
}