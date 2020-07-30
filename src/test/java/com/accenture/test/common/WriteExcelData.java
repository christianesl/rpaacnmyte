package com.accenture.test.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.clientname.app.classes.Employee;

/****************************************************************************
 * Class that writes data into an Excel file.
 * 
 * @Author: christian.saldana@accenture.com
 * @Date: 5/13/2019
 ****************************************************************************/

public class WriteExcelData {

	private static String[] ReportHeaders = { "EID", "Status", "Period" , "Assignment", "Current Time Report_Hours",
			"Current Time Report_Expenses", "Projected Productivity Metrics_Chargeable",
			"Projected Productivity Metrics_Client Facing", "Projected Productivity Metrics_Market Facing",
			"Projected Productivity Metrics_Recovery", "Projected Productivity Metrics_Other",
			"Projected Productivity Metrics_Absences", "Adjustments_Current hours", "Adjustments_Expenses",
			"Total_Hours", "Total_Expenses" };

	public static String getFileName() {
		String currentDate = ("" + LocalDate.now()).replace("-", "");
		String currentTime = (("" + LocalTime.now()).substring(0, 6)).replace(":", "");
		String strFileName = "RPA_MYTE_" + currentDate + "_" + currentTime;
		String userProfile = System.getenv("USERPROFILE");
		return userProfile + "\\Desktop\\" + strFileName + ".xlsx";
	}
	
	public static void writeReport() throws IOException {

		String strFullPath = getFileName();

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "EID", "EMPLOYEE_NAME", "PROJECT", "HOURS" });
		data.put("2", new Object[] { "christian.saldana", "Christian Saldana", "Disney", 77 });
		data.put("3", new Object[] { "christian.saldana", "Christian Saldana", "Oxxo", 65 });
		data.put("4", new Object[] { "christian.saldana", "Christian Saldana", "Royal Caribbean", 43 });
		data.put("5", new Object[] { "christian.saldana", "Christian Saldana", "Qualcomm", 32 });

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(strFullPath));
			workbook.write(out);
			out.close();
			System.out.println("Excel created.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public static void writeReport(ArrayList<Employee> employeeData) throws IOException {
		if (employeeData == null || employeeData.isEmpty()) {
			System.out.println("Data is empty.");
			return;
		}

		String strFullPath = getFileName();

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		// build report header
		int iRowNum = 0;
		int iCellNum = 0;
		Row excelRow = sheet.createRow(iRowNum++);
		for(int i = 0; i < ReportHeaders.length; i++) {
			Cell cell = excelRow.createCell(iCellNum++);
			cell.setCellValue(ReportHeaders[i]);
		}
		
		// process individual employees
		for(Employee emp : employeeData) {
			String enterpriseID = emp.getEnterpriseID();
			String timesheetStatus = emp.getStatus();	
			String timesheetPeriod = emp.getPeriod();
			String[][] data = emp.getTasks();
			
			// process assignment and hours
			
			for(int row = 0; row < data.length; row++) {
				excelRow = sheet.createRow(iRowNum++);
				iCellNum = 0;
				Cell cell = excelRow.createCell(iCellNum++);
				cell.setCellValue(enterpriseID);
				cell = excelRow.createCell(iCellNum++);
				cell.setCellValue(timesheetStatus);
				cell = excelRow.createCell(iCellNum++);
				cell.setCellValue(timesheetPeriod);
				for(int col = 0; col < data[row].length; col++) {
					cell = excelRow.createCell(iCellNum++);
					cell.setCellValue(data[row][col]);
				}
			}			
		}
		
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(strFullPath));
			workbook.write(out);
			out.close();
			System.out.println("Excel created.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
