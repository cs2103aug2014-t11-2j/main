package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.ParserDateLocal;
import parser.ParserTestDriver;

public class ParserAllTest {

	@Test
	public void test() {
		//Test extractEndDate

		//confirm method returns date input
		assertEquals("09091234", ParserTestDriver.testExtractEndDate("add 09/09/1234"));

		//confirm method returns second date input
		assertEquals("02032345", ParserTestDriver.testExtractEndDate("add 09/09/1234 2/3/2345"));

		//confirm method fills in today's date if no date is detected
		//change date input accordingly
		//assertEquals("16102014", ParserTestDriver.testExtractEndDate("add"));

		//following test case is "pointless" since it assumes that the dateString
		//method already works and returns current date.
		assertEquals(ParserDateLocal.dateString(), ParserTestDriver.testExtractEndDate("add"));

		//assertEquals("2/3/2345", ParserTestDriver.testExtractEndDate("add 09/"));
		
		
		
		//Test extractEndTime
		
		//confirm method returns second time input
		assertEquals("0800", ParserTestDriver.testExtractEndTime("add 09/09/1234 0900 2/3/2345 0800"));
		
		//Test extractStartDate
		
		//confirm method returns first date input
		assertEquals("09091234", ParserTestDriver.testExtractStartDate("add 09/09/1234 2/3/2345"));
		
		
		
		//Test extractStartdate
		
		//confirm method returns first date input
		assertEquals("09091234", ParserTestDriver.testExtractStartDate("add 09/09/1234 2/3/2345"));
		
		
		
		//Test extractStartTime
		
		//confirm method returns time input
		assertEquals("0900", ParserTestDriver.testExtractStartTime("0900"));

		//confirm method returns first time input
		assertEquals("0900", ParserTestDriver.testExtractStartTime("add 0900 0800"));
		assertEquals("0900", ParserTestDriver.testExtractStartTime("add void 0900 nada 0800 nothing"));

		//confirm method returns time input instead of the year 2345
		assertEquals("0900", ParserTestDriver.testExtractStartTime("add 12/11/2345 0900"));
		
		
		
		//Test extractTitle
		
		//confirm method returns nothing with only date and time input and add command
		assertEquals(null, ParserTestDriver.testExtractTitle("add 12/23/1234 0900"));

		//confirm method returns title
		assertEquals("work to do", ParserTestDriver.testExtractTitle("add 12/23/1234 0900 work to do"));

		//confirm method returns title in appropriate form
		assertEquals("work to do", ParserTestDriver.testExtractTitle("add work 12/23/1234 to 0900 do"));


		
		//Test hasTwoDateInputsTest

		//confirm method returns true if 2 date inputs
		assertTrue(ParserTestDriver.testHasTwoDateInputs("09/09/1234 2/3/2345"));

		//confirm method returns true if 2 date inputs with additional portions
		assertTrue(ParserTestDriver.testHasTwoDateInputs("add 09/09/1234 nothing 2/3/2345 works"));
		assertTrue(ParserTestDriver.testHasTwoDateInputs("add 09/09/1234 nothing 2/3/2345"));

		//confirm method returns false if only 1 date input
		assertFalse(ParserTestDriver.testHasTwoDateInputs("09/08/1245"));


		/* Methods should change the date input from whatever format
		 * for eg. 12/3/2014 or 12-3-2014 to 12032014 
		 */

		
		
		//Test hasTwoTimeInputsTest	

		//confirm method returns true if 2 time inputs
		assertEquals(true, ParserTestDriver.testHasTwoTimeInputs("0900 0800"));

		//confirm method returns true if 2 time inputs with additional portions
		assertEquals(true, ParserTestDriver.testHasTwoTimeInputs("add 0900 nothing 0800 work"));

		//confirm method returns false if only 1 date input
		assertEquals(false, ParserTestDriver.testHasTwoTimeInputs("0900"));

		

		//Test replaceAllDate

		//confirm method replaces date input
		assertEquals("", ParserTestDriver.testReplaceAllDate("09/08/1223"));

		//confirm method replaces date input with additional portions
		//note: each space/symbol around the date is left behind
		assertEquals("add  work", ParserTestDriver.testReplaceAllDate("add 09/08/1223 work"));
		assertEquals("add~%work", ParserTestDriver.testReplaceAllDate("add~09/08/1223%work"));

		//confirm method replaces date input according to actual ideal user add appointment input
		assertEquals("add  0900  0800 work", ParserTestDriver.testReplaceAllDate("add 09/08/1223 0900 2/3/2345 0800 work"));

		
		
		//Test replaceAllTime

		//confirm method replaces time input
		assertEquals("", ParserTestDriver.testReplaceAllTime("0900"));

		//confirm method replaces date input with additional portions
		//note: each space/symbol around the date is left behind
		assertEquals("add  work", ParserTestDriver.testReplaceAllTime("add 0900 work"));
		assertEquals("add~%work", ParserTestDriver.testReplaceAllTime("add~0900%work"));

		//confirm method replaces date input according to actual ideal user add appointment input
		//assertEquals("add 09/08/1223  2/3/2345  work", ParserTestDriver.testReplaceAllTime("add 09/08/1223 0900 2/3/2345 0800 work"));
		//Test fails
		//reason: pattern detects the year portion of date inut as time
		// pattern cannot differentiate between them

		//Negative test: So long as this test returns true, design flaw has not been addressed
		assertEquals("90 21", ParserTestDriver.testReplaceAllTime("1234567890 0987654321"));

		
		
		//Test refineString

		String[] testArray = {"work ", "to  ", "do "};

		//Confirm method returns string with only single spaces
		assertEquals("work to do", ParserTestDriver.testRefineString(testArray));


		
		//Test invalidSpecialContent

		//confirm method returns true for ideal cases
		assertTrue(ParserTestDriver.testContentForClear("on "));
		assertTrue(ParserTestDriver.testContentForClear(" before"));
		assertTrue(ParserTestDriver.testContentForClear("between"));

		//confirm method can and does return false
		assertFalse(ParserTestDriver.testContentForClear("o n"));

		//confirm method will not return true if word is in a long sequence
		assertFalse(ParserTestDriver.testContentForClear("ion"));
		assertFalse(ParserTestDriver.testContentForClear("beforetime"));
		assertFalse(ParserTestDriver.testContentForClear("isbetween"));
	}

}
