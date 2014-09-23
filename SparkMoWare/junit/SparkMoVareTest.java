package junit;

import static org.junit.Assert.*;
import logic.SparkMoVare;

import org.junit.Test;

public class SparkMoVareTest {

	@Test
	public void test() {
		assertEquals(SparkMoVare.executeCommand("add hellow"),"added to Storage: \"hellow\"");
	}

}
