package testscripts;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testbase.BasePage;
import utilities.ExcelOperations;

public class CartTestCase extends BasePage {
	@DataProvider(name = "cartData")
	public Object[][] getLoginData() throws Exception {
		ExcelOperations excel = new ExcelOperations("cartdata");
		int rows = excel.getRowCount();
		Object[][] data = new Object[rows][1];

		for (int i = 1; i <= rows; i++) { 
			HashMap<String, String> rowData = excel.getTestDataInMap(i);
			data[i - 1][0] = rowData;
		}
		return data;
	}

	@Test(dataProvider = "cartData")
	public void CartTest(HashMap<String, String> testData) throws Exception {
		ct.enterCartData(testData);
		
	}
}
