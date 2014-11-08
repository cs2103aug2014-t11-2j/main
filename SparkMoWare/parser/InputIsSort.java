package parser;

/**
 * 
 * @author Matthew Song
 *
 */
public class InputIsSort {
	
	static final String START_DATE= "01/01/01";
	static final String END_DATE = "31/12/99";

	/**
	 * Method creates a RefinedUserInput for the sort command. If no date or special content is
	 * found, an Invalid Format CommandType is returned.
	 * 
	 * @param userInput the String with all the relevant information to be extracted.
	 * @return a RefinedUserInput object for the filter command.
	 */

	protected static RefinedUserInput refineInput(String userInput) {
		RefinedUserInput inputSort = new RefinedUserInput();
		String specialContent = ExtractSpecialContent.forSort(userInput);

		if(ParserDateLocal.hasTwoDateInputs(userInput)) {
			String startDate = ParserDateLocal.extractStartDate(userInput);
			String endDate = ParserDateLocal.extractEndDate(userInput);

			if(startDate.isEmpty() || endDate.isEmpty()) {
				inputSort.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputSort;
			}

			if (ParserDateLocal.dateComparator(startDate, endDate) == 1) {
				String temp = "";
				temp = startDate;
				startDate = endDate;
				endDate = temp;
			}
			
			inputSort.setCommandType(EnumGroup.CommandType.SORT);
			inputSort.setStartDate(startDate);
			inputSort.setEndDate(endDate);
			inputSort.setSpecialContent(specialContent);
			
			return inputSort;

		} else {

			if(specialContent.isEmpty()) {
				inputSort.setCommandType(EnumGroup.CommandType.INVALID_FORMAT);
				return inputSort;
			}

			inputSort.setCommandType(EnumGroup.CommandType.SORT);
			inputSort.setStartDate(START_DATE);
			inputSort.setEndDate(END_DATE);
			inputSort.setSpecialContent(specialContent);

			return inputSort;
		}
	}
}
