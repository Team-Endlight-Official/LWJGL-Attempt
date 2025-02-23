package com.endlight.utils;

import java.util.HashMap;

public class GlobalAccessor
{
	// Predefined Tags
	public static final String DISPLAY = "display";
	public static final String KEYBOARD = "keyboard";
	public static final String MOUSE = "mouse";
	public static final String MAIN_CAMERA = "main_camera";
	public static final String VERSION = "version";
	
	static HashMap<String, IAccessorLocator> locators = new HashMap<String, IAccessorLocator>();
	static HashMap<String, Float> floatLocators = new HashMap<String, Float>();
	static HashMap<String, Integer> intLocators = new HashMap<String, Integer>();
	static HashMap<String, String> stringLocators = new HashMap<String, String>();
	
	public static void addLocator(String tag, IAccessorLocator value)
	{
		locators.put(tag, value);
	}
	
	public static void addLocator(String tag, float value)
	{
		floatLocators.put(tag, value);
	}
	
	public static void addLocator(String tag, int value)
	{
		intLocators.put(tag, value);
	}
	
	public static void addLocator(String tag, String value)
	{
		stringLocators.put(tag, value);
	}
	
	public static IAccessorLocator getLocator(String tag)
	{
		if (locators.isEmpty())
			return null;
		
		return locators.get(tag);
	}
	
	public static float getLocatorF(String tag)
	{
		if (floatLocators.isEmpty())
			return 0;
		
		return floatLocators.get(tag);
	}
	
	public static int getLocatorI(String tag)
	{
		if (intLocators.isEmpty())
			return 0;
		
		return intLocators.get(tag);
	}
	
	public static String getLocatorS(String tag)
	{
		if (stringLocators.isEmpty())
			return null;
		
		return stringLocators.get(tag);
	}
	
	public static void clear()
	{
		locators.clear();
		floatLocators.clear();
		intLocators.clear();
		stringLocators.clear();
	}
}
