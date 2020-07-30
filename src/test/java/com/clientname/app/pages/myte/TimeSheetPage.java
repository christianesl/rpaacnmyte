package com.clientname.app.pages.myte;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.accenture.test.ui.WebDriverSession;

public class TimeSheetPage extends WebDriverSession {

	public TimeSheetPage() {

		PageFactory.initElements(getWebDriverSession(), this);

	}

	@FindBy(xpath = "//*[@id=\"ctl00_ctl00_MainContentPlaceHolder_tpcTimesheet_dropdownTimePeriod\"]")
	public WebElement lstPeriodDropdown;

	@FindBy(xpath = "//*[@id=\"ctl00_ctl00_MainContentPlaceHolder_lnkTimeReportStatus\"]")
	public WebElement txtTimesheetStatus;

	@FindBy(xpath = "//*[@id=\"ctl00_ctl00_MainContentPlaceHolder_Summary\"]")
	public WebElement tabSummary;

	@FindBy(xpath = "//*[@id=\"ctl00_ctl00_MainContentPlaceHolder_uc_DelegeteesSelector_grd_delegates\"]/tbody/tr")
	public WebElement tableDelegates;
	
	@FindBy(xpath = "//*[@id=\"ctl00_ctl00_MainContentPlaceHolder_ContentPlaceHolder_TimeReport_grd_TimeReportSummary\"]")
	public WebElement tableAssignments;
	
}
