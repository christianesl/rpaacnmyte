package com.clientname.app.steps.myte;

// general classes
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.Assertion;
// framework classes
import org.testng.asserts.SoftAssert;

// application classes
import com.accenture.test.ui.Checkpoints;
import com.accenture.test.ui.WaitActions;
import com.accenture.test.ui.WebDriverSession;
import com.clientname.app.classes.Employee;
import com.clientname.app.pages.myte.TimeSheetPage;

public class TimeSheetSteps {

	private static TimeSheetPage reviewPage = new TimeSheetPage();
	private static WaitActions wait = new WaitActions();
	private static SoftAssert softAssert = new SoftAssert();
	private static Assertion hardAssert = new Assertion();
	private static String sTimeSheetPeriod;
	
	public static void selectTab(String pTab) {

		if (pTab.equals("Summary")) {
			softAssert.assertTrue(Checkpoints.elementDisplayed(reviewPage.tabSummary));
			reviewPage.tabSummary.click();
			System.out.println("Navigating 'Summary' page.");
		} else {
			hardAssert.assertFalse(true, "Invalid tab value.");
		}

	}
	
	public static void selectPeriod(String pTimesheetPeriod) {
		System.out.println("Selecting timesheet period.");
		hardAssert.assertFalse(pTimesheetPeriod.length() < 9 || pTimesheetPeriod.length() > 11, "Invalid time period.");

		// variables
		Select period = new Select(reviewPage.lstPeriodDropdown);

		// validate the value is present and select
		ArrayList<WebElement> dropdownValues = (ArrayList<WebElement>) period.getOptions();
		for (WebElement ele : dropdownValues) {

			if (ele.getText().equals(pTimesheetPeriod)) {
				period.selectByValue(pTimesheetPeriod);
				sTimeSheetPeriod = pTimesheetPeriod;
				return;
			}

		}

		hardAssert.assertFalse(true, "Timesheet period " + pTimesheetPeriod + " not found.");

	}

	public static String retrieveTimesheetStatus() {

		String s = reviewPage.txtTimesheetStatus.getText();
		if (s != null) {
			return s;
		}

		return "";
	}

	public static ArrayList<Employee> retrieveEmployeeData() {
		System.out.println("Retrieving employee data.");
		// variables
		ArrayList<Employee> data = new ArrayList<>();
		
		int employeeTotalRows  = WebDriverSession.getWebDriverSession().findElements(By.xpath(
				"//table[@id='ctl00_ctl00_MainContentPlaceHolder_uc_DelegeteesSelector_grd_delegates']/tbody/tr"))
				.size();

		/************************************
		 * iterate employees
		 ************************************/
		for (int employeeRow = 1; employeeRow <= employeeTotalRows; employeeRow++) {
			WebElement currentEmployeeCell = (WebElement) WebDriverSession.getWebDriverSession().findElement(By.xpath(
					"//table[@id='ctl00_ctl00_MainContentPlaceHolder_uc_DelegeteesSelector_grd_delegates']/tbody/tr["
							+ employeeRow + "]/td[" + 5 + "]"));

			String sCurrentEmployee = currentEmployeeCell.getText();
			currentEmployeeCell.click();
			String sTimeSheetStatus = retrieveTimesheetStatus();
			WebDriverSession.getWebDriverSession().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			/************************************
			 * iterate timesheet
			 ************************************/
			int timesheetTotalRows = WebDriverSession.getWebDriverSession().findElements(By.xpath(
					"//table[@id='ctl00_ctl00_MainContentPlaceHolder_ContentPlaceHolder_TimeReport_grd_TimeReportSummary']/tbody/tr"))
					.size();

			String[][] assignments = new String[timesheetTotalRows][12];

			for (int timeSheetRow = 1; timeSheetRow <= timesheetTotalRows; timeSheetRow++) {
				for (int timeSheetCol = 1; timeSheetCol <= 12; timeSheetCol++) {
					WebElement currentTimeSheetCell = (WebElement) WebDriverSession.getWebDriverSession().findElement(By
							.xpath("//table[@id='ctl00_ctl00_MainContentPlaceHolder_ContentPlaceHolder_TimeReport_grd_TimeReportSummary']/tbody/tr["
									+ timeSheetRow + "]/td[" + timeSheetCol + "]"));

					String value = currentTimeSheetCell.getText();
					if (value != null) {
						assignments[timeSheetRow - 1][timeSheetCol - 1] = value;
					}

				}

			}

			data.add(new Employee(sCurrentEmployee, sTimeSheetStatus, sTimeSheetPeriod, assignments));

		}

		return data;
	}

}
