package com.accenture.test.ui;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitActions extends WebDriverSession{
	/**
	 ****************************************************************************
	 * HIGHLIGHTS:
	 * > Base methods to perform a wait in a specific point of the steps.
	 * > It allows to indicate a wait until a expected element or element text is displayed
	 * > All Static methods so it can be used without instantiate.
	 * 	 - example: CommonActions.WaitForElement(element, 10)
	 ****************************************************************************
	 */
	
	/**
	 * OBJECTIVE: Wait for an element for specific period of time (seconds).
	 * Use in the steps and use Element and seconds to wait as inputs. 
	 */	
	public static void waitForElement(WebElement element, int seconds) {
	    WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(),seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		Assert.assertTrue(Checkpoints.elementDisplayed(element));
	}	
	
	/**
	 * OBJECTIVE: Method to wait for an element to display specific text for specific time in seconds.
	 * Use in the steps and use Element, expected text and seconds to wait as inputs. 
	 */	
	public static void waitForElementText(WebElement element, String strExpectedText, int seconds) {
	    WebDriverWait wait = new WebDriverWait(WebDriverSession.getWebDriverSession(),seconds);
		wait.until(ExpectedConditions.textToBePresentInElement(element,strExpectedText));
		Assert.assertTrue(Checkpoints.elementDisplayed(element));
	}	
	
	/**
	 *OBJECTIVE: Setup the implicit wait for the driver.
	 */	    
	public static void setImplicitWait(int waitInSseconds){
		getWebDriverSession().manage().timeouts().implicitlyWait(waitInSseconds, TimeUnit.SECONDS);
	}
	
	/**
	 * OBJECTIVE: Generic wait to wait for an specific period of time in milliseconds.	  
	 */	
	public static void genericWait(int milliseconds){
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
