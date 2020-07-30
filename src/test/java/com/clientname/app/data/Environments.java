package com.clientname.app.data;

/**
 ****************************************************************************
 *HIGHLIGHTS:
 * > Class Example 
 * > Class to be used as repository of URL. This is project specific.
 * > Based on project put the name of the env as a case option and then the url
 * > See below only as an example. Then update with the project info
 * > Use enum to represent the fixed value for environments in the project.
 ****************************************************************************
 */

public enum Environments {
	MYTE("MYTE",
			"https://myte.accenture.com/"),
	MYTE_LOGIN("MYTE_LOGIN",
			"https://federation-sts.accenture.com/adfs/ls/?wa=wsignin1.0&wtrealm=https%3a%2f%2fmyte.accenture.com%2fogte%2f&wctx=rm%3d0%26id%3dpassive%26ru%3d%252fOGTE%252fHome.aspx&wct=2019-04-01T19%3a30%3a15Z"),
	MYTE_review("MYTE_Review",
			"https://myte.accenture.com/OGTE/secure/TimesheetPage.aspx?action=Review"),
	MYTE_represent("MYTE_Review",
			"https://myte.accenture.com/OGTE/secure/TimesheetPage.aspx?action=Represent");
  
  
	private String key;
    private String appUrl;

    Environments (String key, String url){

           this.key = key;
           this.appUrl = url;

    }    

    public String getAppUrl() {
        return appUrl;
    }

	 public void setAppUrl(String appUrl) {
	        this.appUrl = appUrl;
	 }
	
	 public String getKey() {
	        return key;
	 }
	
	 public void setKey(String key) {
	        this.key = key;
	 }
	
	 public static String getKey(String key) {
	     for (Environments value : values()) {
	            if (value.getKey().equals(key)) {
	                  return value.getKey();
	            }
	     }
	     throw new IllegalArgumentException("Incorrect Key value: " + key);
	}
	
	public static String getAppUrl(String key) {
	     for (Environments value : values()) {
	            if (value.getKey().equals(key)) {
	                  return value.getAppUrl();
	            }
	     }
	     throw new IllegalArgumentException("Incorrect URL value: " + key);
	}
        
}
