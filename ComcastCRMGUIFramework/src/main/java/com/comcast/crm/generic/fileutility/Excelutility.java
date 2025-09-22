package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelutility {
	public String getDataFromExcel(String sheet, int rowno, int cellno) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./testData/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data= wb.getSheet(sheet).getRow(rowno).getCell(cellno).getStringCellValue();
		wb.close();
		return data;
	
	}
	
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./testData/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount= wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return rowCount;
		
	}
	
	public void setdataintoExcel(String sheet, int rowno, int cellno, String data) throws IOException {
		FileInputStream fis = new FileInputStream("./testData/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		 wb.getSheet(sheet).getRow(rowno).createCell(cellno);
		FileOutputStream fos= new FileOutputStream("./testData/testdata.xlsx");
		wb.write(fos);
		wb.close();
		

}
}
