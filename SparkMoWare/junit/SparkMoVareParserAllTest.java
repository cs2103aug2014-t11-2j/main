
package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SparkMoVareCheckIfApptTest.class,
		SparkMoVareGetStartTimeTest.class,
		SparkMoVareHasTwoTimeInputTest.class,
		SparkMoVareReplaceAllDateTest.class,
		SparkMoVareReplaceAllTimeTest.class,
		
		SparkMoVareGetStartDateTest.class,
		SparkMoVareGetEndDateTest.class,
		
		SparkMoVareGetStartTimeTest.class,
		SparkMoVareGetEndTimeTest.class,
		
		SparkMoVareGetTitleTest.class
		 })
public class SparkMoVareParserAllTest {

	/* Note: tests that return date are currently unrefined.
	 * instead of the ideal version DDMMYYYY (eg.12052014)
	 * currently they are returning DD/MM/YYYY (eg. 12/05/2014) 
	 */
}
