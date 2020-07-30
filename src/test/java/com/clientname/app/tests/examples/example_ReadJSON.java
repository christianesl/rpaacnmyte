package com.clientname.app.tests.examples;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.accenture.test.common.ReadJSON;
import com.accenture.test.common.SystemProperties;

public class example_ReadJSON {

	@Test (groups = "")
	public void testReadJsonFiles() throws Exception {
		
		JSONObject env = new JSONObject();
		JSONObject jsonFile = new JSONObject(ReadJSON.parse("Environments.json"));
		env = jsonFile.getJSONObject(SystemProperties.EXECUTION_ENVIRONMENT);
		
		System.out.println("The URL is :" + ReadJSON.getString(env, "GOOGLE"));
		System.out.println("The URL is :" + ReadJSON.getString(env, "ACCENTURE_MX"));
		
	}

	
}
