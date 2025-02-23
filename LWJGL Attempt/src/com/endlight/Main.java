package com.endlight;

import org.lwjgl.Version;

import com.endlight.windowing.Display;

public class Main
{
	static Display display;
	
	public static void main(String[] args)
	{
		System.out.println("Hello, World");
		System.out.println(Version.getVersion());
		
		display = new Display();
		display.setTitle("Skibidi Window");
		display.setGLVersion(4, 1);
		display.create();
		
		while (display.exists())
		{
			display.update();
		}
		
		display.dispose();
	}
}
