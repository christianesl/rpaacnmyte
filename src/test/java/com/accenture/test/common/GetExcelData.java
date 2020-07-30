package com.accenture.test.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 ****************************************************************************
 * HIGHLIGHTS:
 * > Methods used to get data from an excel file. 
 * > It returns the data as a string that can be saved on a variable.
 * This class requires the "org.apache.poi" libraries.
 ****************************************************************************
 */

public class GetExcelData {
	/**
   	 * OBJECTIVE: Get the value of an excel file cell.
	 * DESCRIPTION: Use it when it is required to store data on excel that will be used as input on steps. 
	 * INPUT: row (this can involve for where called). This can work with a tot rows to perform a loop.
	 *        filePath: example "C://accenture//Confidential//Automation//MyProjects//" + strFileName + ".xlsx";  
	 * 		  strSheetName: name of the excel sheet to be checked. Like global and local data tables in UFT.
	 *        strColName: name of the column to be checked.
	 * OUTPUT: string with the value from the cell.
	 */

	public static String getCellValue(int row, String strFilePath,String strSheetName, String strColName) throws IOException{
		
		//Call reusable methods to open input stream and excel sheet
		FileInputStream fis = openInputStream(strFilePath);				
		XSSFSheet MySheet = openExcelSheet(fis, strSheetName);
		
		Row myRowCols = MySheet.getRow(0);
		String myDataValue = null;
		for (int j = 0; j < myRowCols.getLastCellNum(); j++) {
			String curColName = myRowCols.getCell(j).getStringCellValue();
			if (curColName.contentEquals(strColName)){
				Row myRow = MySheet.getRow(row);
				int intCellType = myRow.getCell(j).getCellType();
				if (intCellType==0){
				int myIntDataValue = (int) myRow.getCell(j).getNumericCellValue();
				myDataValue = Integer.toString(myIntDataValue);
			}else{myDataValue = myRow.getCell(j).getStringCellValue();}break;
			}
		}
		fis.close();
		return myDataValue;		
	}	
		
	/**
	* OBJECTIVE: Get the total of iterations to be used on a loop. 
	* DESCRIPTION: Used it when the test flow is driven by an an excel file. Like the UFT data table.
	* INPUT: strFilePath: example "C://accenture//Confidential//Automation//MyProjects//" + strFileName + ".xlsx";  
	* 		  strSheetName: name of the excel sheet to be checked. Like global and local data tables in UFT.
	* OUTPUT: string with the value from the cell.
	*/

	public static int getTotIterations(String strFilePath, String strSheetName) throws IOException{

		//Call reusable methods to open input stream and excel sheet			
		FileInputStream fis = openInputStream(strFilePath);				
		XSSFSheet excelSheet = openExcelSheet(fis, strSheetName);
		
		int count = excelSheet.getLastRowNum();			
		fis.close();
		
		return count;
	}	

	/**
	* OBJECTIVE: Open input stream. 
	* DESCRIPTION: Input stream is reusable among methods. Then use this to method to open the fis.
	* INPUT: strFilePath: example "C://accenture//Confidential//Automation//MyProjects//" + strFileName + ".xlsx";  
	* OUTPUT: file input stream
	*/
	public static FileInputStream openInputStream(String strFilePath) throws IOException{

		System.out.println("Path: " + strFilePath);
		File fis = new File(strFilePath);				
		FileInputStream excelFile = new FileInputStream(fis);				
		System.out.println("Input Srteam open");
											
		return excelFile;		
	}

	/**
	* OBJECTIVE: Open excel sheet. 
	* DESCRIPTION: Code to open excel sheet is reusable, so use this code.
	* INPUT: file input stream, use the "openInputStream" to create it and provide sheet name.
	* OUTPUT: excel file object open
	*/
	public static XSSFSheet openExcelSheet(FileInputStream fileInputStream, String strSheetName) throws IOException{
			
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		    	
		System.out.println("Workbook created");
				
		XSSFSheet excelSheet = workbook.getSheet(strSheetName);
				
		System.out.println("Sheet created");
				
		return excelSheet;		
	}	

	/**
	* OBJECTIVE: Close the file input stream. 
	* DESCRIPTION: Code to close excel sheet is reusable, so use this code.
	* INPUT: file input stream, use the "openInputStream" to create it.
	* OUTPUT: None (file will be closed)
	*/
	public static void closeExcelSheet(FileInputStream strFilePath) throws IOException{
		strFilePath.close();	
	}	
		

}
