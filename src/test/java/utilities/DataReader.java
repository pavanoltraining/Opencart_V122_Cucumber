package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * DataReader Utility Class:
 * This class is responsible for reading data from Excel files (.xlsx) and 
 * converting it into a list of hash maps for easy access during test execution.
 */
public class DataReader {

    // A shared HashMap to store values globally if needed
    public static HashMap<String, String> storeValues = new HashMap<>();

    /**
     * Reads data from the specified Excel file and sheet, and returns it as a list
     * of HashMaps, where each row is represented as a HashMap with column headers as keys.
     *
     * @param filepath  The path to the Excel file
     * @param sheetName The name of the sheet to read data from
     * @return List of HashMaps containing the Excel data
     * @throws IOException If the file cannot be read
     */
    public static List<HashMap<String, String>> data(String filepath, String sheetName) throws IOException {

        // List to store data for all rows in the sheet
        List<HashMap<String, String>> mydata = new ArrayList<>();

        // Open the Excel file as a stream
        FileInputStream file = new FileInputStream(filepath);

        // Create a workbook instance for the file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        // Get the specified sheet by name
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Get the total number of rows in the sheet
        int totalRows = sheet.getLastRowNum();

        // Get the header row (first row, index 0)
        XSSFRow headerRow = sheet.getRow(0);

        // Loop through each row starting from the second row (index 1)
        for (int i = 1; i <= totalRows; i++) {
            XSSFRow currentRow = sheet.getRow(i); // Get the current row

            // Create a HashMap to store key-value pairs for the current row
            HashMap<String, String> currentHash = new HashMap<>();

            // Loop through each cell in the row
            for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                XSSFCell currentCell = currentRow.getCell(j); // Get the current cell
                // Map the header cell (key) to the current cell's value
                currentHash.put(headerRow.getCell(j).toString(), currentCell.toString());
            }

            // Add the current row's data (as a HashMap) to the list
            mydata.add(currentHash);
        }

        // Close the file stream to release resources
        file.close();

        // Return the list containing all rows' data
        return mydata;
    }
}
