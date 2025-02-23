package com.endlight;

import org.lwjgl.Version;

import static org.lwjgl.glfw.GLFW.*;

import com.endlight.utils.GlobalAccessor;
import com.endlight.windowing.Display;
import com.endlight.windowing.Keyboard;
import com.endlight.windowing.Mouse;

public class Main
{
	static Display display;
	static Keyboard keyboard;
	
	static int x;
	static int y;
	
	static float axis;
	
	static void load()
	{
		GlobalAccessor.addLocator(GlobalAccessor.DISPLAY, display);
		GlobalAccessor.addLocator(GlobalAccessor.MOUSE, new Mouse(display));
		keyboard = new Keyboard(display);
		GlobalAccessor.addLocator(GlobalAccessor.KEYBOARD, keyboard);
	}
	
	static void update()
	{
		if (!display.focused())
			return;
		
		if (keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) dispose();
		
		if (keyboard.isKeyDown(Keyboard.KEY_ENTER))
		{
			System.out.println("Yuppie! :3");
		}
	}
	
	static void draw()
	{
		
	}
	
	static void dispose()
	{
		display.dispose();
		GlobalAccessor.clear();
		System.out.println("Disposed!");
		System.exit(0);
	}
	
	public static void main(String[] args)
	{
		System.out.println("Hello, World");
		System.out.println(Version.getVersion());
		
		GlobalAccessor.addLocator(GlobalAccessor.VERSION, "v0.0.01");
		
		display = new Display();
		display.setTitle("Skibidi Window " + GlobalAccessor.getLocatorS(GlobalAccessor.VERSION));
		display.setGLVersion(4, 1);
		display.setFullscreen(false);
		display.setResizable(true);
		display.create();
		
		load();
		
		while (display.exists())
		{	
			update();
			display.update();
			draw();
			display.pollWindow();
		}
		
		dispose();
	}
}
