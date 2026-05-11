package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtility {

    XSSFWorkbook workbook;
    XSSFSheet sheet;

    // ✅ Constructor (file load karega)
    public ExcelUtility(String path) 
    {
        try 
        {
            FileInputStream fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // ✅ Row count
    public int getRowCount(String sheetName) 
    {
    	{
    	    sheet = workbook.getSheet(sheetName);

    	    if (sheet == null) {
    	        System.out.println("Sheet not found: " + sheetName);
    	        return 0;
    	    }

    	    return sheet.getPhysicalNumberOfRows();
    	}
    }

    // ✅ Cell data
    public String getCellData(String sheetName, int row, int col)
    {
        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            System.out.println("Sheet not found: " + sheetName);
            return "";
        }

        XSSFRow rowData = sheet.getRow(row);

        if (rowData == null) {
            return "";
        }

        Cell cell = rowData.getCell(col);

        if (cell == null) {
            return "";
        }

        return cell.toString();
    }
}