package com.watch.utilities;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Why JSON?
 * Separation of Concerns:
   With @FindBy: Locators are tightly coupled with the Java code.
   With JSON locators: All locators are externalized, making the test code clean and purely focused on behavior.
 * Easier Maintenance:
   If locators change frequently (as in dynamic UIs), it's easier to edit the JSON file than to recompile the code.
   No need to touch Java classes — just update values in the locatorsRepo.json
 * Non-technical Collaboration:
   QA analysts or non-developer team members can update JSON files without touching code
 */

public class ReadLoactors {
	
	/*
	 * What is JsonNode?
	 * It's a class from the Jackson library that represents a tree structure of a JSON file.
	 * It allows easy navigation of JSON using methods like .get("key"), .asText(), etc.
	 */
	private static JsonNode rootNode = null;
	
	private static void loadLocatorFile() {
		if(rootNode == null) {
			try {
				File file = new File(System.getProperty("user.dir")+"/src/test/resources/locators/locator_repo.json");
				String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8); //Uses Apache Commons IO's FileUtils to read the file as a full string in one go. Ensures it is read using UTF-8 encoding (a standard encoding for text files).
				rootNode = new ObjectMapper().readTree(json); //ObjectMapper is part of the Jackson library, used to parse JSON. .readTree(json) parses the raw JSON string and returns a JsonNode tree. This tree is stored in rootNode for easy navigation.
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getType(String pageName, String locatorName) {
		loadLocatorFile();
		try {
			return rootNode.get(pageName).get(locatorName).get("type").asText();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getValue(String pageName, String locatorName) {
		loadLocatorFile();
		try {
			return rootNode.get(pageName).get(locatorName).get("value").asText();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
