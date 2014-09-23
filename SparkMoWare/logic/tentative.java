package logic;


public class tentative {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static final int TYPE_TENTATIVE = 2;
	
	private static tentative(String userInput) {
		
	    String[] stringArray = userCommand.split(" ", 2);
        String numOfTentative = stringArray[1];
        int tentativeNum = Integer.parseInt(numOfTentative);
        
        for(int tentativeCount = 1; tentativeCount < tentativeNum; tentativeCount++) {
        	
        	String[] inputArray = scanner.nextLine().split(" ");
        	
        	String endTime = inputArray[inputArray.length - 1];
        	String endDate = inputArray[inputArray.length - 2];
        	String startTime = inputArray[inputArray.length - 3];
        	String startDate = inputArray[inputArray.length - 4];
        	
        	StringBuilder title = new StringBuilder(inputArray[0]);
        	
        	for(int titleLength = 1; titleLength < inputArray.length - 4; titleLength++) {
        		title.append(inputArray[titleLength]);
        		if(titleLength == inputArray.length-5) {
        			title.append("[tentative]");
        		}
        			
        	}
        	//addTask(ID, title, type, startDate, startTime, endDate, endTime, isCompletion)
        	addTask(TYPE_TENTATIVE, title, startDate, endDate, startTime, endTime);
        }
	}
}
