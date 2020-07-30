package com.clientname.app.tests;

import java.io.IOException;
// aux
import java.util.ArrayList;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// application classes
import com.accenture.test.common.Credentials;
// steps
import com.accenture.test.common.WriteExcelData;
import com.accenture.test.ui.WebDriverSteps;
import com.clientname.app.classes.Employee;
import com.clientname.app.steps.myte.LoginSteps;
import com.clientname.app.steps.myte.TimeSheetSteps;

/**
 ****************************************************************************
 * HIGHLIGHTS:
 *
 * Carlos Fernando Dueñas Ramirez
 * 
 * > SCRIPT NAME: RPA Linkedin Search > DESCRIPTION: Search Resources in
 * Linkedin with specific information > DATA: data collected during process
 ****************************************************************************
 */
public class RPA_MYTE {

	String user = "";
	String password = "1";
	String passwordConfirmation = "2";
	String location = "";
	String tab = "";
	String TimesheetPeriod = "";
	int resourceNumber = 0;
	String skill = "";
	String directoryName = "";
	Credentials credentials = new Credentials();
	
	@BeforeMethod(alwaysRun = true)
	public void beforeTest() throws IOException {
//		Credentials credentials = new Credentials();
		WebDriverSteps nav = new WebDriverSteps();

		user = credentials.typeUser();
		do {
			password = credentials.typePassword();
			passwordConfirmation = credentials.capturePasswordConfirmation();	
		} while(!password.equals(passwordConfirmation));
		
//		TimesheetPeriod = credentials.typeTimeSheetPeriod();
		tab = credentials.selectTab();

		// Launch MYTE page
		nav.navigateToApplication("MYTE_" + tab);

	}

	@Test(groups = { "ALLProcesses" })
	public void MYTE_RPA() throws IOException {

		// STEPS
		LoginSteps.login(user, password);
		TimesheetPeriod = credentials.typeTimeSheetPeriod();
		TimeSheetSteps.selectPeriod(TimesheetPeriod);
		TimeSheetSteps.selectTab("Summary");
		ArrayList<Employee> employeInformation = TimeSheetSteps.retrieveEmployeeData();
		WriteExcelData.writeReport(employeInformation);

	}

	@AfterMethod(alwaysRun = true)
	public synchronized void afterTest() {
		WebDriverSteps.closeTheBrowser();
	}

}
