package com.accenture.test.ui;

import static com.accenture.test.ui.WebDriverSession.getWebDriverSession;

import org.junit.Assert;
import org.openqa.selenium.WebElement;


/**
 ****************************************************************************
 * HIGHLIGHTS:
 * > Base methods to perform checkpoints on the test steps. 
 * > These return a True or false to the steps where they are called to perform validation.
 * > ALL Static methods that can be called without instantiate the class.
 * 	 - example in steps use: Checkpoints.elementDisplayed(WebElement)
 * > Use the "soft assert" in the steps to perform multiple validations and not stop execution until the asserALL is run. 
 *   - Example, in steps use below to check all tabs displayed in a page. 
 *      softAssert.assertTrue(Checkpoints.elementDisplayed(searchPage.getTodosTab()));
 *      softAssert.assertTrue(Checkpoints.elementDisplayed(searchPage.getNoticiasTab()));
 *      softAssert.assertAll();
 ****************************************************************************
 */

public class Checkpoints {
	/**
   	 * OBJECTIVE: Check whether an element is displayed.
	 * Use this method on the steps classes and use an element as input. 
	 * It also prints in the log a message to visualize the status PASSED/FAILED		
	 */
	public static boolean elementDisplayed(WebElement myElement){ 	    		
		try{myElement.isDisplayed();
	       	System.out.println("PASSED-element displayed: " + myElement.getText());
			//ReportingTools.printMessage("PASSED-element displayed: " + myElement.getText());	       	
	       	return true;	       	
			}catch(org.openqa.selenium.NoSuchElementException e)
	        {System.out.println("FAILED-element NOT displayed");
	        //Screenshots.takeScreenshot("FAILED-element displayed: " + myElement.getText());
			//ReportingTools.takeScreenshot("FAILED-element NOT displayed");
			//ReportingTools.printMessage("FAILED-element NOT displayed");	       		        
	        return false;
	        }
	    }

	/**
     * OBJECTIVE: Check two strings and see if the expected is IN the actual string displayed.
	 * Use this method on the steps classes and use a "text" or an "element.getText()" as input. 
	 * It also prints in the log a message to visualize the status PASSED/FAILED and the compared strings. 
	 */
	public static boolean inString(String strActual, String strExpected){
	     try{Assert.assertTrue(strActual.contains(strExpected));
	       	System.out.println("PASSED-strings match actual/expected: " + strActual + " / " + strExpected);
	       	return true;
	       	}catch(AssertionError e)
	        {System.out.println("FAILED-strings mismatch actual/expected: " + strActual + " / " + strExpected);	     
	        return false;
	        }  	       
	    }

	/**
	 * OBJECTIVE: Check whether a New Tab is opened after a click on a link.
	 * Check that when user clicks on a link then a new Tab is open with the correct URL.
	 * Use on the steps classes and use the link element and the expected URL to be in the opened window as inputs. 
	 */
	public static boolean checkLinkOpenNewTab(WebElement link, String strURLtoOpen) throws InterruptedException{
		WaitActions.waitForElement(link, 10);
		link.click();
	    String origWindow = null;
	    String url = null;
	    boolean bln;
	    
	    origWindow = WebDriverSteps.switchToNewWindow();
	       
	    for(int i=1; i<10; i++){
	  	     url = getWebDriverSession().getCurrentUrl();
		     System.out.println("***Try #" + i);       	    	        	       		       
		     bln = Checkpoints.inString(url, strURLtoOpen);		     
		     if(bln==true){break;
		       }else{Thread.sleep(5000);}		       
	    }	       

	    WebDriverSteps.closeNewWindow(origWindow);
/*	    while (getWebDriverSession().getWindowHandles().size() > 1) {
	    	WebDriverSteps.switchBackFromNewWindow(origWindow);	         		
	  	}*/	
            
        return Checkpoints.inString(url, strURLtoOpen);
	}	
	
	/**
	 * OBJECTIVE: Check whether a New Tab is opened. Click or navigation should occur before
	 */
	  public static boolean checkNewWindowURL(String strExpectedURL) throws InterruptedException {
	       String origWindow = null;
	       Thread.sleep(1500);
	       origWindow = WebDriverSteps.switchToNewWindow();
	       
	       String url = WebDriverSession.getWebDriverSession().getCurrentUrl();
	       System.out.println("Actual URL obtained: " + url);       	 
	       
	       boolean bln = inString(url, strExpectedURL);
          
	       WebDriverSteps.closeNewWindow(origWindow);
	       
		return bln;
		}
	  	

	
}
