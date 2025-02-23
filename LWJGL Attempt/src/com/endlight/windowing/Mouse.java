package com.endlight.windowing;

import com.endlight.utils.IAccessorLocator;

import static org.lwjgl.glfw.GLFW.*;

public class Mouse implements IAccessorLocator
{
	final Display display;
	
	public static final int BUTTON_LEFT = GLFW_MOUSE_BUTTON_LEFT;
	public static final int BUTTON_RIGHT = GLFW_MOUSE_BUTTON_RIGHT;
	public static final int BUTTON_MIDDLE = GLFW_MOUSE_BUTTON_MIDDLE;
	
	public Mouse(Display display)
	{
		this.display = display;
	}
	
	/**
	 * Gets the X location of the Mouse
	 * @return the X location in screen coords.
	 */
	public double x()
	{
		return display.mouseX;
	}
	
	/**
	 * Gets the Y location of the Mouse
	 * @return the Y location in screen coords.
	 */
	public double y()
	{
		return display.mouseY;
	}
	
	/**
	 * Checks whether a mouse button has been held down.
	 * 
	 * 
	 * @param The mouse button you want to specify
	 * @return if a button has been held down.
	 */
	public boolean isDown(int button)
	{
		return display.isMousePressed(button);
	}
	
	/**
	 * Checks whether a mouse button has been released.
	 * 
	 * @param The mouse button you want to specify
	 * @return if a button has been released.
	 */
	public boolean isReleased(int button)
	{
		return display.isMouseReleased(button);
	}
}
