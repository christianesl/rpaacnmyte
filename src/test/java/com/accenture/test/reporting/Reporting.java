package com.accenture.test.reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.testng.Reporter;

import com.accenture.test.common.SystemProperties;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporting {

	public static enum status{
		PASS,FAIL,ERROR,FATAL,WARNING,INFO
	}
				
	private static final Logger LOGGER = Logger.getLogger(Reporting.class);



	/**
	 * Main reporter method, this method is to use the TestNG. 
	 * The ways to report is driven by the system.properties reporting variables booleans:
	 * 	reporting.extent=${reporting.extent} / reporting.testng=${reporting.extent} / reporting.log4j=${reporting.extent}
	 * @param status: One of the available status enum: PASS,FAIL,ERROR,FATAL,WARNING,INFO
	 * @param message: Message to display in the report.
	 * 	
	 */
	public static void reporter(status status, String message){
		
		if(SystemProperties.TESTNG_REPORT){
			reporterTestNG(status, message);
		}
		
		if(SystemProperties.EXTENT_REPORT){
			reporterExtent(status, message);
		}
		
		if(SystemProperties.LOG4J_REPORT){
			reporterLog4J(status, message);			
		}
		
	}		

	/**
	 * TestNG reporter method, this method used to sent customized messages to the TestNG report. 
	 * Utilized in the main "reporter" method.
	 * @param status: One of the available status enum: PASS,FAIL,ERROR,FATAL,WARNING,INFO
	 * @param message: Message to display in the report.	
	 */
	public static void reporterTestNG(status status, String message) {

		switch (status) {
		case PASS:
			printOnTestNGReport(ColorHex.GREEN, ColorHex.WHITE, message, "Validation");
			break;
		case FAIL:
			printOnTestNGReport(ColorHex.RED, ColorHex.WHITE, message, "Validation");
			break;
		case ERROR:
			printOnTestNGReport(ColorHex.RED, ColorHex.WHITE, message, "Validation");
			break;
		case WARNING:
			printOnTestNGReport(ColorHex.YELLOW, ColorHex.BLACK, message, "Validation");
			break;
		case INFO:
			printOnTestNGReport(ColorHex.SILVER, ColorHex.BLACK, message, "Validation");
			break;
		case FATAL:
			printOnTestNGReport(ColorHex.BLACK, ColorHex.RED, message, "Validation");
			break;
		}
	}

	/**
	* Print to TestNG Report following the indicated formatting
	* @param bgColor, textColor, message, messageType
	*/
	private static void printOnTestNGReport(String bgColor, String textColor, String message, String messageType) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("<div id='message" + messageType + "' style='background-color: " + bgColor + "; color: "
				+ textColor + "; font-size: xx-small; border: " + ColorHex.SILVER + " 2px solid; padding: 5px;'>"
				+ "<table><tr><td style='width:85px;'>" + messageType + "</td><td>:</td><td id='message'>" + message
				+ "</td></tr></table></div><br/><br/>");
	}
		
	/**
	 * Log4J reporter method, this method used to sent customized messages to the Log4J logs. 
	 * Utilized in the main "reporter" method.
	 * @param status: One of the available status enum: PASS,FAIL,ERROR,FATAL,WARNING,INFO
	 * @param message: Message to display in the report.	
	 */	
	public static void reporterLog4J(status status, String message) {
		switch (status) {
		case PASS:
			LOGGER.info("PrintMessage Executed. Message - " + message);
			break;
		case FAIL:
			LOGGER.error("PrintMessage Executed. Message - " + message);
			break;
		case ERROR:
			LOGGER.error("PrintMessage Executed. Message - " + message);
			break;
		case WARNING:
			LOGGER.warn("PrintMessage Executed. Message - " + message);
			break;
		case INFO:
			LOGGER.info("PrintMessage Executed. Message - " + message);
			break;
		case FATAL:
			LOGGER.fatal("PrintMessage Executed. Message - " + message);
			break;
		}
	}
	
	
	///**********************************************************************************
	///*************** EXTENT REPORTS ***************************************************
	///**********************************************************************************
		
	private static ExtentReports extentReport;	

	private static ExtentTest extentTest;	
	
	public static ExtentReports getExtentReport() {
		return extentReport;
	}

	public static ExtentTest ExtentTest() {
		return extentTest;
	}

	public static void configureExtentResults() {
				
		if(SystemProperties.EXTENT_REPORT){
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HH-mm");  
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");  
			LocalDateTime now = LocalDateTime.now();  
			String date= dtf.format(now); 
			System.out.println("Extent reports enabled");
			System.out.println("Date of report: " + date);
			  
			ExtentReports extentHTMLReport = new ExtentReports(
			System.getProperty("user.dir") + "/test-output/extentreports/ExtentReport_" + date + ".html", false);
			extentReport = extentHTMLReport;
			
		}				

	}

	/**
	 * Start the extent report creation generation. 
	 * Should be always used when extent reports is enabled.	
	 */	
	public static void startTestExtentReport(String strScriptName, String strDescription) {
		
		configureExtentResults();
		
		if(SystemProperties.EXTENT_REPORT){
			System.out.println("START of SCRIPT");
			//ExtentReports extent = getExtentReport();
			extentTest = extentReport.startTest(strScriptName, strDescription);
		}				

	}

	/**
	 * Extent reporter method, this method used to sent customized messages to the Log4J logs. 
	 * Utilized in the main "reporter" method.
	 * @param status: One of the available status enum: PASS,FAIL,ERROR,FATAL,WARNING,INFO
	 * @param message: Message to display in the report.	
	 */	
	public static void reporterExtent(status status, String message) {

		switch (status) {
		case PASS:
			extentTest.log(LogStatus.PASS, "Completed Successfully - " + message);
			break;
		case FAIL:
			extentTest.log(LogStatus.FAIL, "Failed - " + message);
			break;
		case ERROR:
			extentTest.log(LogStatus.ERROR, "Error - " + message);
			break;
		case WARNING:
			extentTest.log(LogStatus.WARNING, "Warning - " + message);
			break;
		case INFO:
			extentTest.log(LogStatus.INFO, "Info - " + message);
			break;
		case FATAL:
			extentTest.log(LogStatus.FATAL, "Fatal Error - " + message);
			break;
					
		}		

	}

	public static void reportEndOfTest() {
		extentReport.endTest(extentTest);
		extentReport.flush();
		//extentReport.close();
	}
	
	
}
