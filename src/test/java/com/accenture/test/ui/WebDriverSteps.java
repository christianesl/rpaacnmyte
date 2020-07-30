package com.accenture.test.ui;

import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;

import com.accenture.test.common.JavaScriptActions;
import com.accenture.test.common.ReadJSON;
import com.accenture.test.common.SystemProperties;
import com.clientname.app.data.Environments;

import cucumber.api.java.en.*;

/**
 ****************************************************************************
 * HIGHLIGHTS:
 * > Methods reusable for all projects.
 * > Methods that apply action on the driver. Like navigating to a URL.
 * > Not reference to application objects. 
 * > The URL to use is project specific and can be obtained from package com.projectname.data
 ****************************************************************************
 */

public class WebDriverSteps extends WebDriverSession{
		
	private String url;
	   
    public String getUrl() {
		return url;		
	}

	public void setUrl(String url) {
		this.url = url;
		System.out.println("URL SET: " + this.url);
	}

	/**
	 * OBJECTIVE: Steps to launch the driver and navigate to a url
	 * Input: name of the environment to be used. The url need to be in the com.projectname.data.Environments
	 */
	
	@Given("User navigates to \"([^\"]*)\"$" )	
	//@Given("User navigates to application")
	public void navigateToApplication(String appName) {
		System.out.println("Start Opening The Application");
		//String url = Environments.valueOf(SystemProperties.EXECUTION_ENVIRONMENT.toUpperCase()).getAppUrl();
		String url = getURL(appName);
		System.out.println("Environment utilized: " + url);
		//setUrl(Environments.getBaseUrl(strApplicationName));
		setUrl(url);
	 	System.out.println("Start the loading of the application's page");
    	System.out.println("URL to be used: " + this.url);
    	WebDriverSession.getWebDriverSession().get(this.url);
    	WaitActions.genericWait(5000);
		try {
			JavaScriptActions.HandleCertificateError(WebDriverSession.getWebDriverSession());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("URL opened");
    }	
	
	public String getURL(String appName) {
		
		JSONObject env = new JSONObject();
		JSONObject jsonFile = new JSONObject(ReadJSON.parse("Environments.json"));
		env = jsonFile.getJSONObject("DEMO");
		
		System.out.println("The URL is :" + ReadJSON.getString(env, appName));

		return ReadJSON.getString(env, appName);
	}
	
	/**
	 * OBJECTIVE: Use this method to switch to an iframe.
	 */		
	public static void switchToIframe(String txt){
		getWebDriverSession().switchTo().frame(txt);
	}

	/**
	OBJECTIVE: Use this method to switch back from iframe.
	*/		
	public static void switchBackFromIframe(){
		getWebDriverSession().switchTo().defaultContent();
	}

	/**
	OBJECTIVE: Use this method when steps flow open a new window.
	*/	
	public static String switchToNewWindow() throws InterruptedException {
		String winHandleBefore = getWebDriverSession().getWindowHandle();
		Thread.sleep(5000);
		for (String winHandle : getWebDriverSession().getWindowHandles()){
			getWebDriverSession().switchTo().window(winHandle);
		}

		return winHandleBefore;
	}

	/**
	OBJECTIVE: Use this method to go back to the original window when switching to a new one.
	*/	
	public static void switchBackFromNewWindow(String window){
		getWebDriverSession().close();
		getWebDriverSession().switchTo().window(window);

	}

	/**
	OBJECTIVE: Use this method to go back to the original window when switching to a new one.
	*/	
	public static void closeNewWindow(String origWindow){
		  
		while (getWebDriverSession().getWindowHandles().size() > 1) {
			WebDriverSteps.switchBackFromNewWindow(origWindow);	         				  	
		}	
	  }

	/**
	OBJECTIVE: Use this method to go to bottom of pages.
	*/	
	public void scrollToBottom(){

		JavascriptExecutor js = ((JavascriptExecutor) getWebDriverSession());
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
	}
	
	/**
	OBJECTIVE: Use this method to close browser for each scenario.
	*/	
	@Given("Close the browser")
	public static void closeTheBrowser() {
		WebDriverSession.getWebDriverSession().quit();
		map.clear();		    	
    }	
    
}
