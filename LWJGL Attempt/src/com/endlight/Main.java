package com.endlight;

import org.lwjgl.Version;

import static org.lwjgl.glfw.GLFW.*;

import com.endlight.gfx.PerspectiveCamera;
import com.endlight.gfx.SimpleMesh;
import com.endlight.utils.Assets;
import com.endlight.utils.GlobalAccessor;
import com.endlight.windowing.Display;
import com.endlight.windowing.Keyboard;
import com.endlight.windowing.Mouse;

public class Main
{
	static Display display;
	static Keyboard keyboard;
	static SimpleMesh mesh;
	
	static int x;
	static int y;
	
	static float axis;
	
	static void load()
	{
		System.out.println(Assets.readStringFromPath("shaders/default.frag"));
		
		GlobalAccessor.addLocator(GlobalAccessor.ASPECT_RATIO, display.getAspectRatio());
		GlobalAccessor.addLocator(GlobalAccessor.DISPLAY, display);
		GlobalAccessor.addLocator(GlobalAccessor.MOUSE, new Mouse(display));
		keyboard = new Keyboard(display);
		GlobalAccessor.addLocator(GlobalAccessor.KEYBOARD, keyboard);
		
		mesh = new SimpleMesh();
	}
	
	static void update()
	{
		if (!display.focused())
			return;
		
		if (keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) dispose();
	}
	
	static void draw()
	{
		mesh.draw();
	}
	
	static void dispose()
	{
		mesh.dispose();
		
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
			//GlobalAccessor.addLocator(GlobalAccessor.ASPECT_RATIO, display.getAspectRatio());
			
			update();
			display.update();
			draw();
			display.pollWindow();
		}
		
		dispose();
	}
}
