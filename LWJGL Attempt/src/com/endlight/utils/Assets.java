package com.endlight.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Assets
{
	public static final String RES_PATH = "res/";
	
	public static String readStringFromPath(String path)
	{
		final String finalPath = RES_PATH + path;
		
		File file = new File(finalPath);
		if (file.exists())
		{
			try 
			{
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuilder builder = new StringBuilder();
				
				String line;
				
				while ((line = bufferedReader.readLine()) != null)
				{
					builder.append(line).append("\n");
				}
				
				bufferedReader.close();
				fileReader.close();
				return builder.toString();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return e.getMessage();
			}
		}
		else
		{
			return "File has been not found";
		}
	}
}
