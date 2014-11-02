package parser;

import java.util.regex.Matcher;

public class ParserIdLocal {
	protected static String extractId(String userInput) {
		Matcher idMatcher = ParserPatternLocal.idPattern.matcher(userInput);
		String id = new String();
		String datePortion = new String();
;		String index = new String();
		
		if(idMatcher.find()) {
			String checker = idMatcher.group(0);

			if(checker.matches("^\\d+?$")) {
				return id;
			}
		}
		
		idMatcher.reset();
		
		if(idMatcher.find()) {
			datePortion = idMatcher.group(2);
			index = idMatcher.group(3);
		}
		
		if(ParserDateLocal.dateFormatValid(datePortion)) {
			id = refineId(datePortion, index);
		}
		
		return id;
	}
	
	protected static String refineId(String creationDate, String index) {
		String id= new String();
		
		if(index.length() > 4) {
			return "";
		} else  if(index.length() == 4) {
			id = creationDate.concat(index);
		} else if (index.length() == 3) {
			id = creationDate.concat("0");
			id = id.concat(index);
		} else if (index.length() == 2) {
			id = creationDate.concat("00");
			id = id.concat(index);
		} else if (index.length() == 1) {
			id = creationDate.concat("000");
			id = id.concat(index);
		}
		
		return id;
	}
	
	protected static String removeId(String input) {
		Matcher idMatcher = ParserPatternLocal.idPattern.matcher(input);
		
		if(idMatcher.find()) {
			input = idMatcher.replaceFirst("");
		}
		return input.trim();
	}
	
	protected static String removeFrontZero(String input) {
		while (input.length() > 0 && input.charAt(0) == '0') {
			input = input.substring(1);
		}
		return input;
	}
}
