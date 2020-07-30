package com.clientname.app.tests.examples;

import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.accenture.test.reporting.Reporting;
import com.accenture.test.reporting.Reporting.status;


public class Example_SimpleTestReporter {

	@BeforeMethod (alwaysRun = true)
	 public void beforeTest(){ 
		
		Reporting.startTestExtentReport("Scenarios Reporting","Description of the test"); 

	 }
		
	 @Test (groups = {"ReportingTest", "Test2", "Smoke"})
	  public void testReporting1() throws WebDriverException, IOException {
		
		 
		 String message = "TESTING THE REPORTS !";
		 Reporting.reporter(status.PASS, message);
		 Reporting.reporter(status.INFO, message);
		 Reporting.reporter(status.ERROR, message);
		 Reporting.reporter(status.WARNING, message);
		 Reporting.reporter(status.FAIL, message);
		 Reporting.reporter(status.FATAL, message);
		 
		 
	 }
	 
	 
	 @AfterMethod(alwaysRun = true)
	  public synchronized void afterTest() {
		 Reporting.reportEndOfTest();
	  }
	 
	 
}
