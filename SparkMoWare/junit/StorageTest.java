package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.LinkedList;

import logic.Assignment;
import logic.Id;

import org.junit.Test;

import storage.Storage;

public class StorageTest {

	@Test 
	public void test() {

		//test for toBufferAssignment see if the function is convert the storage string correctly
		String testString = "020819930001~Test Title~1~17071992~0000~28072013~2359~false~false~low";
		Assignment Test = Storage.toBufferAssignment(testString);
		assertTrue(Test.getId().equals("020819930001"));
		assertTrue(Test.getTitle().equals("Test Title"));
		assertEquals(Test.getType(),1);
		assertTrue(Test.getStartDate().equals("17071992"));
		assertTrue(Test.getStartTime().equals("0000"));
		assertTrue(Test.getEndDate().equals("28072013"));
		assertTrue(Test.getEndTime().equals("2359"));
		assertTrue(Test.getIsDone()==(Boolean.parseBoolean("false")));
		assertTrue(Test.getIsOnTime()==(Boolean.parseBoolean("false")));
		assertTrue(Test.getPriority().equals("low"));
		
		//prepping the test clear any existing files from previous test
		File file = new File("storageTest.txt");
		if(file.delete()) {
		} else {
		}

		//Testing the save and load
		String latestSerialNumber="020819940000";
		
		String testString1 = "020819940001~Test Title~1~17071992~0000~28072013~2359~false~false~low";
		String testString2 = "020819940002~Test Title~1~17071992~0000~28072013~2359~false~false~low";
		String testString3 = "020819940003~Test Title~1~17071992~0000~28072013~2359~false~false~low";
		String testString4 = "020819940004~Test Title~1~17071992~0000~28072013~2359~false~false~low";
		String testString5 = "020819940005~Test Title~1~17071992~0000~28072013~2359~false~false~low";
		LinkedList<Assignment> testSaveBuffer = new LinkedList<Assignment>();

		testSaveBuffer.add(Storage.toBufferAssignment(testString1));
		testSaveBuffer.add(Storage.toBufferAssignment(testString2));
		testSaveBuffer.add(Storage.toBufferAssignment(testString3));
		testSaveBuffer.add(Storage.toBufferAssignment(testString4));
		testSaveBuffer.add(Storage.toBufferAssignment(testString5));
		Storage.saveFile("storageTest.txt",testSaveBuffer);
		LinkedList<Assignment> testLoadBuffer = new LinkedList<Assignment>();

		Storage.openFile("storageTest.txt",latestSerialNumber,testLoadBuffer);

		for(int i=0; i<testSaveBuffer.size();i++) {
			assertTrue(testSaveBuffer.get(i).getId().equals(testLoadBuffer.get(i).getId()));
			assertTrue(testSaveBuffer.get(i).getTitle().equals(testLoadBuffer.get(i).getTitle()));
			assertEquals(testSaveBuffer.get(i).getType(),testLoadBuffer.get(i).getType());
			assertTrue(testSaveBuffer.get(i).getStartDate().equals(testLoadBuffer.get(i).getStartDate()));
			assertTrue(testSaveBuffer.get(i).getStartTime().equals(testLoadBuffer.get(i).getStartTime()));
			assertTrue(testSaveBuffer.get(i).getEndDate().equals(testLoadBuffer.get(i).getEndDate()));
			assertTrue(testSaveBuffer.get(i).getEndTime().equals(testLoadBuffer.get(i).getEndTime()));
			assertTrue(testSaveBuffer.get(i).getIsDone()==(testLoadBuffer.get(i).getIsDone()));
			assertTrue(testSaveBuffer.get(i).getIsOnTime()==(testLoadBuffer.get(i).getIsOnTime()));
			assertTrue(testSaveBuffer.get(i).getPriority().equals(testLoadBuffer.get(i).getPriority()));
		}

		// test extraction of latest serial number
		assertTrue(Id.getLatestSerialNumber().equals("020819940005"));



	}

}
