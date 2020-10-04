package util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class ExcelOperation {
	static private Workbook myworkbook;
	
	static private void loadExcelSheet(String path) {
		File file = new File(path);
		try {
			FileInputStream inputStream = new FileInputStream(file);
			myworkbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static public Object[][] getAllRows(String filepath, String sheetName){
		loadExcelSheet(filepath);
		Sheet sheet = myworkbook.getSheet(sheetName);
		
		int totalRows = sheet.getLastRowNum(); // 1 // 4.1.2 [index]
		int totalCols = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum();
		Object[][] data = new Object[totalRows][totalCols]; // [1][3]
		
		for(int rowIndex=1;rowIndex<=totalRows;rowIndex++) {
			for(int colIndex=0;colIndex<totalCols;colIndex++) {
				Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
				DataFormatter dataFormatter = new DataFormatter();
				try {
					if(CellType.NUMERIC == cell.getCellType())
						data[rowIndex-1][colIndex] = dataFormatter.formatCellValue(cell);
					else if(CellType.STRING == cell.getCellType())
						data[rowIndex-1][colIndex] = cell.getStringCellValue();
					else
						data[rowIndex-1][colIndex] = "";
				}catch(NullPointerException ne) {
						data[rowIndex-1][colIndex] = "";
				}
			}
		}
		return data;
	}
	
	/*static public Object[][] getRowAtIndex(String path, int index){
		loadExcelSheet(path);
	}
	
	static public int getTotalRows(String path) {
		loadExcelSheet(path);
	}
	
	static boolean isColPresent(String path) {
		return true;
	}*/
}
