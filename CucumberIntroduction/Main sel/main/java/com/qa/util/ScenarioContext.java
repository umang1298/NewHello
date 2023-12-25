package com.qa.util;


import java.util.HashMap;
import java.util.Map;
import com.qa.util.Context;

public class ScenarioContext {
	private static Map<String, Object> scenarioContext = new HashMap<String, Object>();

	public ScenarioContext() {
		// This is a HasMap object which stores the
		// information in the Key-Value pair. Key type is String and Value can be of any
		// Object Type
		scenarioContext = new HashMap<String, Object>();
	}

//This method takes two parameters,  key as String and value as object. Key is nothing but a Context enum.
	public static void setContext(Object key, Object value) {
		scenarioContext.put(key.toString(), value);
	}


	
//This method performs a check on the complete Map that if it contains the key or not.
	public static Boolean isContains(Context key) {
		return scenarioContext.containsKey(key.toString());
	}

	public static void setContext(String key, String value) {
		// TODO Auto-generated method stub
		scenarioContext.put(key.toString(), value);
	}
	//This method takes the key as a parameter and returned the object which matches the key.
	public static Object getContext(Object key) {
		// TODO Auto-generated method stub
		return scenarioContext.get(key.toString());
	}
	

}

