package parser;

public class ParserEditLocal {

	protected static EnumGroup.EditType determineEditType(String attributeName){
		if (attributeName.equalsIgnoreCase("title")) {
			return EnumGroup.EditType.TITLE;
		} else if (attributeName.equalsIgnoreCase("startdate")) {
			return EnumGroup.EditType.START_DATE;
		} else if (attributeName.equalsIgnoreCase("starttime")) {
			return EnumGroup.EditType.START_TIME;
		} else if (attributeName.equalsIgnoreCase("enddate")) {
			return EnumGroup.EditType.END_DATE;
		} else if (attributeName.equalsIgnoreCase("endtime")) {
			return EnumGroup.EditType.END_TIME;
		} else {
			return EnumGroup.EditType.INVALID;
		}
	}
}
