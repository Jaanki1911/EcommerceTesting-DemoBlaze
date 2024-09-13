package testscripts;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testbase.BasePage;
import utilities.ExcelOperations;

public class SignUpTestCase extends BasePage{
	@Test(priority = 1)
	public void registerFunc() throws InterruptedException{
		sp.register();
	}
	@DataProvider(name = "signUpData")
    public Object[][] getcontactData() throws Exception {
        ExcelOperations excel = new ExcelOperations("signupdata");
        int rows = excel.getRowCount();
        Object[][] data = new Object[rows][1];

        for (int i = 1; i <= rows; i++) { 
            HashMap<String, String> rowData = excel.getTestDataInMap(i);
            data[i-1][0] = rowData;
        }
        return data;
    }

	@Test(priority = 2,dataProvider = "signUpData")
    public void contactTest(HashMap<String, String> testData) throws Exception {
        sp.enterSignUpData(testData);
    }
}
