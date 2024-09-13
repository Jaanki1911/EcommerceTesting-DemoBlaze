package testscripts;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testbase.BasePage;
import utilities.ExcelOperations;

public class LoginTestCase extends BasePage {
	@Test(priority = 1)
	public void signInFunc() throws Exception{
		lp.signIn();
	}
	@DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        ExcelOperations excel = new ExcelOperations("logindata");
        int rows = excel.getRowCount();
        Object[][] data = new Object[rows][1];

        for (int i = 1; i <= rows; i++) { 
            HashMap<String, String> rowData = excel.getTestDataInMap(i);
            data[i-1][0] = rowData;
        }
        return data;
    }

	@Test(priority = 2,dataProvider = "loginData")
    public void loginTest(HashMap<String, String> testData) throws Exception {
        lp.enterLoginData(testData);
    }
}
