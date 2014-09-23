package junit;

import static org.junit.Assert.*;

import java.util.Vector;

import logic.SparkMoVare;
import logic.Assignment;

import org.junit.Test;

public class SparkMoVareTest {

	@Test
	public void test() {
		// add status
		assertEquals(SparkMoVare.addTask("666","testing adding",1,"010101","1010",
				"101010","2359",false, new Vector<String>() ),"666*testing adding*1*010101*1010*101010*2359*false");
		//saving
		SparkMoVare.display();
	}
	
	

}
