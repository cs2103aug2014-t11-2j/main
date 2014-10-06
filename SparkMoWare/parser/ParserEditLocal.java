package parser;

public class ParserEditLocal {

	protected static EditType determineEditType(String attributeName){
		if (attributeName.equalsIgnoreCase("title")) {
			return EditType.TITLE;
		} else if (attributeName.equalsIgnoreCase("startdate")) {
			return EditType.START_DATE;
		} else if (attributeName.equalsIgnoreCase("starttime")) {
			return EditType.START_TIME;
		} else if (attributeName.equalsIgnoreCase("enddate")) {
			return EditType.END_DATE;
		} else if (attributeName.equalsIgnoreCase("endtime")) {
			return EditType.END_TIME;
		} else {
			return EditType.INVALID;
		}
	}
}
