package parser;

//@author A0110788B

/**
 * Class containing all relevant methods involving the index.
 */
public class ParserIndexLocal {
	
	/**
	 * Method extracts index from String input. If no index is found, -1 is returned to
	 * indicate no index found.
	 * 
	 * @param input String to have index extracted from.
	 * @param command String to be removed.
	 * @return index int.
	 */
	protected static int extractIndex(String input, String command) {
		int index = -1;
		
		input = Misc.removeCommand(input, command).trim();
		input = ParserDateLocal.replaceAllDate(input);
		input = ParserTimeLocal.replaceAllTime(input);
		
		input = input.replaceAll("[a-zA-Z]+", "").trim();
		
		if(input.matches("[0-9]+")) {
			index = Integer.parseInt(input);
		}
		return index;
	}
		
	/**
	 * Method to remove index from input String.
	 * 
	 * @param input String to be changed.
	 * @return String with index removed.
	 */
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
	
	/**
	 * Method determines if index exists.
	 * 
	 * @param input String to be checked.
	 * @return true if index exists.
	 */
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
