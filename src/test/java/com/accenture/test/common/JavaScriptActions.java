package com.accenture.test.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.accenture.test.ui.WebDriverSession;

public class JavaScriptActions {

	/**
	 * OBJECTIVE: Method to use the Java Executor to perform an action using java script.	  
	 */	
	public static void runJavascriptClick(WebElement element){
		
		System.out.println("Start - Javascript Executor");
		JavascriptExecutor executor = (JavascriptExecutor) WebDriverSession.getWebDriverSession();
		
		executor.executeScript("arguments[0].scrollIntoView(true);", element);		
		executor.executeScript("arguments[0].click();", element);
		System.out.println("Complete - Javascript Executor");
	}
	
	/**
	 * Handle certificate error in Internet Explorer
	 * 
	 * @param driver
	 *            WebDriver instance
	 * */
	public static void HandleCertificateError(WebDriver driver)
			throws InterruptedException {
		String PageTitle = driver.getTitle();
		while (PageTitle.equals("Certificate Error: Navigation Blocked")) {
			driver.navigate()
					.to("javascript:document.getElementById('overridelink').click()");
			PageTitle = driver.getTitle();
		}
	}
	
	
	
	public static void handleAjaxLoader(int toAppear, int maxTimeout) {
		try {
			int i = 0;
			boolean flag = false;

			while (true) {
				Boolean spinnerIsFound = (Boolean) ((JavascriptExecutor) WebDriverSession.getWebDriverSession())
						.executeScript("return $('.ajax_loader').is(':visible') == true");
				if (spinnerIsFound) {
					//ReportingTools.printMessage("=====Ajax loading started=====");
					flag = true;
					break;
				}
				i++;
				if (i > toAppear) {
					//ReportingTools.printMessage("No Ajax loader found");
					break;
				}
				System.out.println(i + "==Looking for Ajax loader==" + i);
				Thread.sleep(1000);
			}
			i = 0;
			if (flag) {
				while (true) {

					Boolean spinnerIsComplete = (Boolean) ((JavascriptExecutor) WebDriverSession.getWebDriverSession())
							.executeScript("return $('.ajax_loader').is(':visible') == false");
					if (spinnerIsComplete) {
						//ReportingTools.printMessage("Ajax loading is done");
						break;
					}
					i++;
					if (i > maxTimeout)
						Assert.fail("Infinite loading");
					System.out.println(i + "==Ajax Loading==" + i);
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			//ReportingTools.printMessage("Ajax loader is not defined in the Page");
		}
	}

	/**
	OBJECTIVE: Use this method to go to bottom of pages.
	*/	
	public void scrollToBottom(){

		JavascriptExecutor js = ((JavascriptExecutor) WebDriverSession.getWebDriverSession());
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
	}

	public void waitForJQuery() {

		Boolean isJqueryUsed = (Boolean) ((JavascriptExecutor) WebDriverSession.getWebDriverSession())
				.executeScript("return (typeof(jQuery) != 'undefined')");
		if (isJqueryUsed) {
			while (true) {
				// JavaScript test to verify jQuery is active or not
				Boolean ajaxIsComplete = (Boolean) (((JavascriptExecutor) WebDriverSession.getWebDriverSession())
						.executeScript("return jQuery.active == 0"));
				if (ajaxIsComplete) {
					//ReportingTools.printStep("******No Ajax Loader*****");
					break;
				}
				try {
					//ReportingTools.printStep("******JQuery Active**** So waiting*****");
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
