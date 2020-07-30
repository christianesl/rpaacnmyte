package com.clientname.app.pages.myte;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.accenture.test.ui.WebDriverSession;

/*
 ****************************************************************************
 * HIGHLIGHTS: Example of page object model (google search page)
 * > Class Example
 * > com.projectname.pages package to be used as Object Repository Location
 * > Create one class for each automated application page (easy to find and maintain)
 * > Create a class for sections that are displayed in multiple pages (footers, menus...)
 * > Extend the WebDrierInstance so the driver can be used by objects.
 * > Each page object model will have the same structure.
 * > Use page factory, private web elements, use getters and setters.
 ****************************************************************************
 */

public class LoginPage extends WebDriverSession{

    public LoginPage() {

        PageFactory.initElements(getWebDriverSession(), this);

    }
	
    @FindBy(id = "userNameInput")
    public WebElement textFieldEmail;
    
    @FindBy(id = "passwordInput")
    public WebElement textFieldPassword;
    
    @FindBy(id = "submitButton")
    public WebElement buttonSignIn;
         
    @FindBy(id = "ctl00_ctl00_TutorialPopUpControl_btnClose")
    public WebElement closePopUp;
    
    
	   
}
