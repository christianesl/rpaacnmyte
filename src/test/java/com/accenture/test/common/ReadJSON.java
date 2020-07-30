package com.accenture.test.common;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadJSON {

/**
 * Reads file and converts to String.
 * 
 * @param filePath
 * @throws IOException
 */
static String readFileAsString(String filePath) throws IOException {
//	System.out.println(filePath);
	StringBuffer fileData = new StringBuffer(1000);
	BufferedReader reader = new BufferedReader(new FileReader(filePath));
	char[] buf = new char[1024];
	int numRead = 0;
	while ((numRead = reader.read(buf)) != -1) {
		String readData = String.valueOf(buf, 0, numRead);
		fileData.append(readData);
		buf = new char[1024];
	}
	reader.close();
	return fileData.toString();
} 


/**
 * Given a file name within the ./json/ folder, it will read the file and
 * return the contents as a string.
 * 
 * @param
 * @return Contents of the file as a String
 */
static public String parse(String fileName) {
	try {
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\"; 
		return readFileAsString(path + fileName);
	} catch (Exception ex) {
		throw new RuntimeException(ex);
	}
}

/**
 * Given a file name within the ./json/ folder, it will read the file and
 * return the contents as a string.
 * 
 * @param
 * @return Contents of the file as a String
 */
	static public String parse(String location, String fileName) {
		String dir = null;
		switch (location) {
		case "test resources":
			dir = "\\src\\test\\resources\\";
			break;

		}
		try {
			String path = System.getProperty("user.dir") + dir;
			return readFileAsString(path + fileName);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

/**
 * Gets the String stored in the JSONObject with the given key
 * @param obj The JSONObject to use
 * @param key The locator key to use
 * @return Value as a String
 */
static public String getString(JSONObject obj, String key){
		
	String output = "";

	
	try {
		output = obj.getString(key);
	} catch (JSONException e) {
		e.printStackTrace();
		System.out.println("Failed to locate JSON object...");
	}

	return output;
}

static public String getStringInNode(JSONObject json, String node, String key){
	JSONObject obj = null;
	String output = null;

	try {
		obj = (JSONObject) json.getJSONObject(node);
		output = obj.getString(key);
	} catch (JSONException e) {
		e.printStackTrace();
		System.out.println("Failed to locate JSON object...");
	}

	return output;
}

/**
 * Gets the Integer stored in the JSONObject with the given key
 * @param obj The JSONObject to use
 * @param key The locator key to use
 * @return Value as a Integer
 */
static public int getInt(JSONObject obj, String key) {
	int output = -1;

	try {
		output = obj.getInt(key);
	} catch (JSONException e) {
		e.printStackTrace();
		System.out.println("Failed to locate JSON object...");
	}

	return output;
}

/**
 * Gets the Boolean value stored in the JSONObject with the given key
 * @param obj The JSONObject to use
 * @param key The locator key to use
 * @return Value as a Boolean
 */
static public boolean getBoolean(JSONObject obj, String key) {
	boolean output = false;

	try {
		output = obj.getBoolean(key);
	} catch (JSONException e) {
		e.printStackTrace();
		System.out.println("Failed to locate JSON object...");
	}

	return output;
}

/**
 * Gets the JSONObject at given index within the JSONArray
 * @param array The JSONArray to use
 * @param index Index of where the JSONObject is
 * @return The JSONObject at the given index
 */
static public JSONObject getObject(JSONArray array, int index) {
	JSONObject obj = null;;

	try {
		obj = (JSONObject) array.get(index);
	} catch (JSONException e) {
		e.printStackTrace();
		System.out.println("Failed to locate JSON array object...");
	}

	return obj;
}

static public JSONObject getObject(JSONObject array, String node) {
	JSONObject obj = null;;

	try {
		obj = (JSONObject) array.getJSONObject(node);
	} catch (JSONException e) {
		e.printStackTrace();
		System.out.println("Failed to locate JSON array object...");
	}

	return obj;
}

/**
 * Gets the JSONObject at given index within the JSONArray
 * @param array The JSONArray to use
 * @param index Index of where the JSONObject is
 * @return The JSONObject at the given index
 */
static public JSONObject getStringFromArray(JSONObject obj, String node, int index) {
	JSONArray applicationLogin = new JSONArray();
	
	try {
		applicationLogin = obj.getJSONArray(node);
		obj = (JSONObject) applicationLogin.get(index);
	} catch (JSONException e) {
		e.printStackTrace();
		System.out.println("Failed to locate JSON array object...");
	}

	return obj;
}


/**
 * Gets the JSONObject at given index within the JSONArray
 * @param array The JSONArray to use
 * @param index Index of where the JSONObject is
 * @return The JSONObject at the given index
 */
static public JSONArray getArray(JSONObject array, String node) {
	JSONArray obj = null;;

	try {
		obj = (JSONArray) array.getJSONArray(node);
	} catch (JSONException e) {
		e.printStackTrace();
		System.out.println("Failed to locate JSON array object...");
	}

	return obj;
}

}
