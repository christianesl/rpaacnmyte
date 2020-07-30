package com.accenture.test.reporting;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.Reporter;

import com.accenture.test.ui.WebDriverSession;

public class Screenshots {

	private static final int FAIL = 2;
	private static final int PASS = 1;
	private static final int WARN = 3;
	
	private static int screenshotNumber = 0;
	private static String href = "/logs/Screenshots/screen" + screenshotNumber + ".png";
	private static final int BROWSER_SCREENSHOT = 1;
	private static final int DESKTOP_SCREENSHOT = 0;
	

	private static Rectangle screenshotFrame = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize().width,
			Toolkit.getDefaultToolkit().getScreenSize().height - 75);

	///**********************************************************************************
	///*************** TAKE SCREENSHOT ***************************************************
	///**********************************************************************************
	
	/**
	 * Take screenshot of Web browser and print message to Report
	 * 
	 * @param message
	 *            Message to be printed to the report
	 * @throws IOException 
	 * @throws WebDriverException 
	 */
	public static void takeScreenshot(String message) throws WebDriverException, IOException {
		Screenshots.saveScreenshot(message, PASS, BROWSER_SCREENSHOT);
		
		//LOGGER.info("TakeScreenshot Executed. Message - " + message + "\n");
	}
	
	
	static void saveScreenshot(String message, int status,int screenshotType) throws WebDriverException, IOException {
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		String textColour = ColorHex.WHITE;
		String bgColor = ColorHex.GREEN;
		
		if (status == FAIL) {
			bgColor = ColorHex.RED;
		} else if (status == WARN) {
			bgColor = ColorHex.YELLOW;
			textColour = ColorHex.BLACK;
		}

		++screenshotNumber;
		
		String toLog = "<div id='screenshot" + status + "' style='background-color: " + bgColor + "; color: "
				+ textColour + "; font-size: xx-small; border: " + ColorHex.SILVER + " 2px solid; padding: 5px;'>"
				+ "<div style='border:" + ColorHex.SILVER + ";'><table><tr><td style='width:85px;'>Validation"
				+ "</td><td>:</td><td id='message'>" + message + "</td></tr></table></div>";

		
		if (WebDriverSession.getWebDriverSession() == null) {
			screenshotType = DESKTOP_SCREENSHOT;
			Reporting.reporter(Reporting.status.INFO,"Screenshot type changed to Desktop Screenshot because driver is null");
			//LOGGER.info("Screenshot type changed to Desktop Screenshot because driver is null");
		}
		
		switch(screenshotType){
		case DESKTOP_SCREENSHOT: 
			FileUtils.copyFile(
					((TakesScreenshot) WebDriverSession.getWebDriverSession()).getScreenshotAs(OutputType.FILE),
					new File(System.getProperty("user.dir") + href));
					System.out.println(System.getProperty("user.dir") + href);
					Reporting.reporter(Reporting.status.INFO,"Browser Screenshot taken - " + href);
					//LOGGER.info("Browser Screenshot taken - " + href);
			
		case BROWSER_SCREENSHOT: 
			if (!new File(System.getProperty("user.dir")
					+ "/Screenshots").exists()) {
				FileUtils.forceMkdir(new File(System
						.getProperty("user.dir")
						+ "/Screenshots"));
			}
			try {
				BufferedImage image = new Robot()
						.createScreenCapture(screenshotFrame);

				File scrLocation = new File(
						System.getProperty("user.dir")
								+ href);
				ImageIO.write(image, "png", scrLocation);
				Reporting.reporter(Reporting.status.INFO,"Desktop Screenshot taken - " + href);
				//LOGGER.info("Desktop Screenshot taken - " + href);
				
			} catch (Exception e) {
				Reporting.reporter(Reporting.status.WARNING,"Taking Desktop screenshot failed " + e);
				//LOGGER.warn("Taking Desktop screenshot failed", e);
			}
		default: Reporting.reporter(Reporting.status.WARNING,"Invalid Screen type " + screenshotType);
		//LOGGER.warn("Invalid Screen type " + screenshotType);
			
			
			toLog = toLog
					+ "<br/>"
					+ "<img src='.."
					+ href
					+ "' alt='screenshot' border='0'/><br/><br/></div><br/><br/>";
			
			Reporter.log(toLog);
		}
		
		
	}
	
}
