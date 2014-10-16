package logic;

/*
 * Allows the user to create a number of tentative dates 
 * before confirming to have only one date for user's appointment
 * Title will be attached with the word [tentative]
 * userInput must be in this format:
 * <title><ddmmyyyy><hhmm><ddmmyyyy><hhmm>
 */

public class SetTentative {
	
	public static String addTentative(String numOfTentative, String tentativeTitle) {
		
		Tentative newTentative = new Tentative();
		
		int tentativeNum = Integer.parseInt(numOfTentative);
		String tentativeIdGenerated = Id.serialNumGen();
		
		newTentative.setId(tentativeIdGenerated);
		newTentative.setTitle(tentativeTitle);
		newTentative.setPriority(Assignment.PRIORITY_NONE);
		
		addTentativeAppt(newTentative, tentativeNum, tentativeIdGenerated, tentativeTitle);
		
		addTentativeToBuffer(newTentative);
		
		return Message.TENTATIVE_ADDED;
	}
	
	private static void addTentativeAppt(Tentative newTentative, int tentativeNum, 
			String tentativeIdGenerated, String tentativeTitle) {
		
		Print.printToUser(Message.TENTATIVE);
		
		for(int tentativeCount = 1; tentativeCount <= tentativeNum; tentativeCount++) {
			
			String[] inputArray = InternalStorage.getScanner().nextLine().split(";");
			
			if(inputArray.length != 4) {
				tentativeCount--;
				
			} else {
				
				String endTime = inputArray[inputArray.length - 1];
				String endDate = inputArray[inputArray.length - 2];
				String startTime = inputArray[inputArray.length - 3];
				String startDate = inputArray[inputArray.length - 4];

				newTentative.setStartDate(startDate);
				newTentative.setStartTime(startTime);
				newTentative.setEndDate(endDate);
				newTentative.setEndTime(endTime);
			}
		}
	}
	
	private static void addTentativeToBuffer(Tentative newTentative) {
		
		if (InternalStorage.getLineCount() == 0) {
			InternalStorage.addBuffer(newTentative);
			
		} else if (Comparator.dateComparator(newTentative.getEndDate(),
					InternalStorage.getBuffer().get(InternalStorage.getLineCount() - 1).getEndDate()) == 1) {
			InternalStorage.addBuffer(newTentative);
			
		} else {
			int bufferCount;
			
			for (bufferCount = InternalStorage.getLineCount() - 1; bufferCount > 0 && 
					(Comparator.dateComparator(newTentative.getEndDate(), 
					InternalStorage.getBuffer().get(bufferCount - 1).getEndDate()) == -1); bufferCount--);
			InternalStorage.addBuffer(bufferCount, newTentative);
		} 
	}
}