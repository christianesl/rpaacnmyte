package com.accenture.test.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;


/**
 ****************************************************************************
 * HIGHLIGHTS:
 * > Reusable actions applied for Drop Down objects.
 ****************************************************************************
 */

public class DropdownActions {
	
	/**
	 * OBJECTIVE: Method to select a drop down option using the visible text.	  
	 */	
	public static void selectByVisibleText(WebElement dropdownElement, String optionVisibleText ){
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText(optionVisibleText);		
	}	   	
	
	/**
	 * OBJECTIVE: Method to select a drop down option using the index.	  
	 */		
	public static void selectByIndex(WebElement dropdownElement, int index ){
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByIndex(index);;		
	}
	
	/**
	 * OBJECTIVE: Method to select a drop down option going through all options from the list.	  
	 */	
	public static void selectByGoingThroughList(WebElement dropdownElement, String expectedOptionText){
		
		Select selectList = new Select(dropdownElement);
				
		int selectSize = selectList.getOptions().size();
		System.out.println("Size of list is " + selectSize );
		
		for (int i = 0; i <= selectSize; i++) {
			
			selectList.selectByIndex(i);
			
			String selectedOption = selectList.getFirstSelectedOption().getText();

			System.out.println("Selected Item " + selectedOption );			
			
			if (selectedOption.trim().equals(expectedOptionText.trim())) {
				System.out.println("Value Matched");			
				break;
			} else {System.out.println("Value Not Matched");										
			 
				if ( i == selectSize ){
					System.out.println("Value NOT FOUND in List " + expectedOptionText);
				}
						
			}
		}
	}

}
