package utilities;

import java.io.File;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelOperations {
    
    String filePath;
    Sheet sh;
    
    // Constructor to initialize the Excel sheet
    public ExcelOperations(String sheetName) {
        try {
            
        	filePath = System.getProperty("user.dir")+PropertiesOperations.getPropertyValueByKey("testDataLocation");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Open the file and workbook
        File testDataFile = new File(filePath);
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(testDataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Load the specified sheet
        sh = wb.getSheet(sheetName);
    }
    
    // Get test data from the Excel sheet for a specific row and put it in a HashMap
    @SuppressWarnings("deprecation")
    public HashMap<String, String> getTestDataInMap(int rowNum) throws Exception {
        // Initialize a HashMap to store the key-value pairs of test data
        HashMap<String, String> hm = new HashMap<>();
        
        // Iterate over each cell in the row and add it to the HashMap
        for (int i = 0; i < sh.getRow(0).getLastCellNum(); i++) {
            // Set the cell type to string to avoid any issues with different cell types
            sh.getRow(rowNum).getCell(i).setCellType(CellType.STRING);
            hm.put(sh.getRow(0).getCell(i).toString(), sh.getRow(rowNum).getCell(i).toString());
        }
        
        return hm; // Return the populated HashMap
    }
    
    // Modified getRowCount method to count only rows with data
    public int getRowCount() {
        int totalRows = 0;

        // Iterate over the rows in the sheet
        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            Row row = sh.getRow(i);
            
            // Check if the row is not null and the first cell contains data
            if (row != null && row.getCell(0) != null && !row.getCell(0).toString().isEmpty()) {
                totalRows++; // Increment the count if data exists in the first cell
            }
        }
        
        return totalRows; // Return the number of valid rows
    }
    
    // Get column count (remains unchanged)
    public int getColCount() {
        return sh.getRow(0).getLastCellNum();
    }
}
