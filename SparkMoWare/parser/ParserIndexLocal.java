package parser;

public class ParserIndexLocal {
	
	protected static int extractIndex(String input, String command) {
		int index = -1;
		
		input = Misc.removeCommand(input, command).trim();
		input = ParserDateLocal.replaceAllDate(input);
		input = ParserTimeLocal.replaceAllTime(input);
		
		input = input.replaceAll("[a-zA-Z]+", "").trim();
		
		if(input.matches("[0-9]+")) {
			index =  Integer.parseInt(input);
		}
		return index;
	}
		
	protected static String removeIndex(String input) {
		
		if(indexExists(input)) {
			int i = 0;
			int length = input.length();
			String temp = new String();
			
			while (!Character.isDigit(input.charAt(i))) {
				i++;
			}
			
			int j = i;
			
			while ( j != length && Character.isDigit(input.charAt(j))) {
				j++;
			}
			temp = input.substring(i, j);
			
			input = input.replaceFirst(temp, "");
		}
		
		return input.trim();
	}
	
	protected static boolean indexExists(String input) {
		input = ParserDateLocal.replaceAllDate(input);
		input = ParserTimeLocal.replaceAllTime(input);
		
		int length = input.length();
		
		for(int i=0; i < length;  i++) {
			if(Character.isAlphabetic(input.charAt(i))) {
				input =  input.replace(input.charAt(i), ' ');
			}
		}
		
		if(input.matches(".*\\d+.*"))    {
			return true;
		} else {
			return false;
		}
	}
}
