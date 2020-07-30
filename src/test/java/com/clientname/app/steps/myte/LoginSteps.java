package com.clientname.app.steps.myte;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.accenture.test.ui.Checkpoints;
import com.accenture.test.ui.WaitActions;
import com.accenture.test.ui.WebDriverSession;
import com.accenture.test.ui.WebDriverSteps;
import com.clientname.app.pages.myte.LoginPage;

import org.testng.asserts.SoftAssert;

import cucumber.api.java.en.*;

public class LoginSteps {

	public static void login(String user, String password) {
		LoginPage loginPage = new LoginPage();
		WaitActions wait = new WaitActions();
		SoftAssert softAssert = new SoftAssert();
		WebDriverSteps nav = new WebDriverSteps();

		System.out.println("Wait to element be visible");
		softAssert.assertTrue(Checkpoints.elementDisplayed(loginPage.textFieldEmail));
		System.out.println("User will be added");
		loginPage.textFieldEmail.sendKeys(user);
		System.out.println("Password will be added");
		loginPage.textFieldPassword.sendKeys(password);
		System.out.println("Sign In button will be clicked");
		loginPage.buttonSignIn.click();
		System.out.println("Wait for symantec process");
		new WebDriverWait(WebDriverSession.getWebDriverSession(), 90)
				.until(ExpectedConditions.urlContains("https://myte.accenture.com"));
		
		if ( WebDriverSession.getWebDriverSession().findElements(By.id("ctl00_ctl00_TutorialPopUpControl_btnClose")).size() > 0) {
			System.out.println("click on close popup");	
			loginPage.closePopUp.click();
		}

	}

}
