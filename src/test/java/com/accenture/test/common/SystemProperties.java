package com.accenture.test.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import com.google.common.io.Resources;

/**
 ****************************************************************************
 * HIGHLIGHTS: 
 *  > Used when it is required to have variables in a system.properties file 
 *  > The variables that can keep could be: 
 *     - Application environments, drivers location, browser type
 ****************************************************************************
 */
public class SystemProperties {
	
    private static final String[] DEFAULT_RESOURCE_PATHS = {null, "src/main/resources", "src/test/resources"};
    private static Properties prop;

    /**
     * OBJECTIVE: Load all variables from system.properties.
     */
    private static Properties getProp() {

        if (prop == null) {
            prop = new Properties();
            InputStream input = null;

            try {
            	System.out.println(getResource("system.properties").getPath());
                input = new FileInputStream(new File(Resources.getResource("system.properties").toURI()));                
                System.out.println(getResource("system.properties").getPath());	
                prop.load(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return prop;
    }

    /**
     * OBJECTIVE: Get the system.properties file location.
     */
    public static URL getResource(String propFile) {
    	System.out.println(DEFAULT_RESOURCE_PATHS);
    	URL resource = Thread.currentThread().getContextClassLoader().getResource(propFile);

        if (resource == null) {
        	System.out.println(DEFAULT_RESOURCE_PATHS);
            for (String resourcePath : DEFAULT_RESOURCE_PATHS) {
                File resFile = new File(resourcePath, propFile);
                if (resFile.isFile() || resFile.isDirectory()) {
                    try {
                        resource = resFile.toURI().toURL();
                        break;
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException("Unable to locate the csv file");
                    }
                }
            }
        }
        return resource;
    }

    public static final Boolean REMOTE = new Boolean(getProp().getProperty("selenium.remote"));
    public static final String BROWSER = getProp().getProperty("selenium.browser");
    public static final String SELENIUM_GRID_URL = getProp().getProperty("selenium.gridurl");
 //    public static final Boolean DEMO = new Boolean(getProp().getProperty("project.demo"));

    public static final String HOST = getProp().getProperty("aut.server");
    public static final Boolean USE_SSL = new Boolean(getProp().getProperty("aut.useSSL"));

 //   public static final String MOBILE_DEVICE = "Galaxy S4";
 //   public static final int MOBILE_WIDTH = 200;
 //   public static final int MOBILE_HEIGHT = 600;

    public static final String CHROME_WEBDRIVER = getProp().getProperty("webdriver.chrome.driver");
    public static final String IE_WEBDRIVER = getProp().getProperty("webdriver.ie.driver");

 //  public static final String CONFLUENCE_USER = getProp().getProperty("confluence.user");
 //  public static final String CONFLUENCE_PASSWORD = getProp().getProperty("confluence.password");

    public static final String EXECUTION_ENVIRONMENT = getProp().getProperty("execution.environment");
    public static final String NEW_LINE = System.lineSeparator();

 //  public static final int ELEMENT_TIMEOUT_MILLIS = Integer.parseInt(getProp().getProperty("selenium.elementtimeout"));
 // public static final int PAGE_TIMEOUT_MILLIS = Integer.parseInt(getProp().getProperty("selenium.pagetimeout"));
    public static final boolean SET_BROWSERMOB_PROXY = true;
    
    public static final boolean EXTENT_REPORT = new Boolean(getProp().getProperty("reporting.extent"));
    public static final boolean TESTNG_REPORT = new Boolean(getProp().getProperty("reporting.testng"));
    public static final boolean LOG4J_REPORT = new Boolean(getProp().getProperty("reporting.log4j"));
    
}
