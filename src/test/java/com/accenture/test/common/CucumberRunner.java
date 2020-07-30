package com.accenture.test.common;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
/**
 ****************************************************************************
 * HIGHLIGHTS for the Test Runner
 *  > Allows to run cucumber features and debug the steps (breakpoints)
 *  > Enables running in groups, all suite or specific script. 
 *  > Only required when using Cucumber approach
 *  > It needs to be in the parent package to be able to take all steps
 *  > Else specify the glue attribute with the multiple package for the steps 
 ****************************************************************************
 */
@RunWith(Cucumber.class)

//Use below line to run specific script
//@CucumberOptions(features={"src\\test\\resources\\com\\clientName\\tests\\features\\SimpleTest3.feature"} 

@CucumberOptions(features={"classpath:com\\clientName\\tests"} 

//Use below line to run all script located in the indicated path (entire suite)
//@CucumberOptions(features={"classpath:clientname/com"}
//Use below line to run all script that have a specific tag
,tags={"@AllTests"} //Used to specify a group of scripts to run

,plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json" },monochrome = true


//Use below to glue the steps, this is used when the runner is using steps not located in same package
,glue={"com.accenture.test.ui","com.clientname.app.steps"}

)
public class CucumberRunner {

}