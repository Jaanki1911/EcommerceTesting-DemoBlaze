package testscripts;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testbase.BasePage;
import utilities.ExcelOperations;

public class ContactTest extends BasePage {
	@Test(priority = 1)
	public void cLinkCheck() throws InterruptedException {
		cp.cLink();
	}

	@DataProvider(name = "contactData")
	public Object[][] getcontactData() throws Exception {
		ExcelOperations excel = new ExcelOperations("contactdata");
		int rows = excel.getRowCount();
		Object[][] data = new Object[rows][1];

		for (int i = 1; i <= rows; i++) { // starting from row 1 (skip header)
			HashMap<String, String> rowData = excel.getTestDataInMap(i);
			data[i - 1][0] = rowData;
		}
		return data;
	}

	@Test(priority = 2, dataProvider = "contactData")
	public void contactTest(HashMap<String, String> testData) throws Exception {
		cp.entertContactData(testData);
	}

	@Test(priority = 3)
	public void terminateCheck() {
		cp.terminate();
	}
}
