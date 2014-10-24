package junit;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.LinkedList;

import logic.Appointment;
import logic.Assignment;
import logic.Id;

import org.junit.Test;

import storage.Storage;

public class StorageTest {

	@Test 
	public void test() {
		
		LinkedList<Assignment> testSaveBuffer = new LinkedList<Assignment>();
		String testString = "020819930001~Test Title~17071992~0000~28072013~2359~false~false~low";
		Assignment Test = Storage.toBufferAssignment(testString.split("~"));
		testSaveBuffer.add(Test);
		try {
			Storage.saveFile("storageTest.txt",testSaveBuffer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//test for toBufferAssignment see if the function is convert the storage string correctly
		String testString0 = "020819930001~Test Title~appointment~17071992~0000~28072013~2359~false~false~nimpt";
		Appointment Test1 = Storage.toBufferAppointment(testString0.split("~"));

		assertTrue(Test1.getId().equals("020819930001"));
		assertTrue(Test1.getTitle().equals("Test Title"));
		assertTrue(Test1.getStartDate().equals("17071992"));
		assertTrue(Test1.getStartTime().equals("0000"));
		assertTrue(Test1.getEndDate().equals("28072013"));
		assertTrue(Test1.getEndTime().equals("2359"));
		assertTrue(Test1.getIsDone()==(Boolean.parseBoolean("false")));
		assertTrue(Test1.getIsOnTime()==(Boolean.parseBoolean("false")));
		assertTrue(Test1.getPriority().equalsIgnoreCase("nimpt"));
		
		//prepping the test clear any existing files from previous test
		File file = new File("storageTest.txt");
		if(file.delete()) {
		} else {
		}

		//Testing the save and load
		String latestSerialNumber = "020819940001";
		Id.setLatestSerialNumber(latestSerialNumber);
		
		String testString1 = "020819940001~Test Title~appointment~17071992~0000~28072013~2359~false~false~NIMPT";
		String testString2 = "020819940002~Test Title~appointment~17071992~0000~28072013~2359~false~false~NIMPT";
		String testString3 = "020819940003~Test Title~appointment~17071992~0000~28072013~2359~false~false~NIMPT";
		String testString4 = "020819940004~Test Title~appointment~17071992~0000~28072013~2359~false~false~NIMPT";
		String testString5 = "020819940005~Test Title~appointment~17071992~0000~28072013~2359~false~false~NIMPT";
		
		LinkedList<Assignment> testSaveBuffer1 = new LinkedList<Assignment>();

		testSaveBuffer1.addAll(Storage.addToBuffer(testString1.split("~")));
		testSaveBuffer1.addAll(Storage.addToBuffer(testString2.split("~")));
		testSaveBuffer1.addAll(Storage.addToBuffer(testString3.split("~")));
		testSaveBuffer1.addAll(Storage.addToBuffer(testString4.split("~")));
		testSaveBuffer1.addAll(Storage.addToBuffer(testString5.split("~")));
		Storage.saveFile("storageTest.txt",testSaveBuffer1);
		LinkedList<Assignment> testLoadBuffer = new LinkedList<Assignment>();

		Storage.openFile("storageTest.txt",latestSerialNumber,testLoadBuffer);

		for(int i=0; i<testSaveBuffer1.size();i++) {
			Appointment testingFromSave =  ((Appointment) testSaveBuffer1.get(i));
			Appointment testingFromLoad =  ((Appointment) testLoadBuffer.get(i));
			
			assertTrue(testingFromSave.getId().equals(testingFromLoad.getId()));
			assertTrue(testingFromSave.getTitle().equals(testingFromLoad.getTitle()));
			assertTrue(testingFromSave.getStartDate().equals(testingFromLoad.getStartDate()));
			assertTrue(testingFromSave.getStartTime().equals(testingFromLoad.getStartTime()));
			assertTrue(testingFromSave.getEndDate().equals(testingFromLoad.getEndDate()));
			assertTrue(testingFromSave.getEndTime().equals(testingFromLoad.getEndTime()));
			assertTrue(testingFromSave.getIsDone()==(testingFromLoad.getIsDone()));
			assertTrue(testingFromSave.getIsOnTime()==(testingFromLoad.getIsOnTime()));
			assertTrue(testingFromSave.getPriority().equalsIgnoreCase(testingFromLoad.getPriority()));
		}
		System.out.println(Id.getLatestSerialNumber());
		// test extraction of latest serial number
		assertTrue(Id.getLatestSerialNumber().equals("020819940005"));
	}
}