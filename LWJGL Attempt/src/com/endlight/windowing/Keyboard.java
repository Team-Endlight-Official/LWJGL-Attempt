package com.endlight.windowing;

import com.endlight.utils.IAccessorLocator;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard implements IAccessorLocator
{
	final Display display;
	
	// Keys
	// Alphabetical
	public static final int KEY_A = GLFW_KEY_A;
	public static final int KEY_B = GLFW_KEY_B;
	public static final int KEY_C = GLFW_KEY_C;
	public static final int KEY_D = GLFW_KEY_D;
	public static final int KEY_E = GLFW_KEY_E;
	public static final int KEY_F = GLFW_KEY_F;
	public static final int KEY_G = GLFW_KEY_G;
	public static final int KEY_H = GLFW_KEY_H;
	public static final int KEY_I = GLFW_KEY_I;
	public static final int KEY_J = GLFW_KEY_J;
	public static final int KEY_K = GLFW_KEY_K;
	public static final int KEY_L = GLFW_KEY_L;
	public static final int KEY_M = GLFW_KEY_M;
	public static final int KEY_N = GLFW_KEY_N;
	public static final int KEY_O = GLFW_KEY_O;
	public static final int KEY_P = GLFW_KEY_P;
	public static final int KEY_Q = GLFW_KEY_Q;
	public static final int KEY_R = GLFW_KEY_R;
	public static final int KEY_S = GLFW_KEY_S;
	public static final int KEY_T = GLFW_KEY_T;
	public static final int KEY_U = GLFW_KEY_U;
	public static final int KEY_V = GLFW_KEY_V;
	public static final int KEY_W = GLFW_KEY_W;
	public static final int KEY_X = GLFW_KEY_X;
	public static final int KEY_Y = GLFW_KEY_Y;
	public static final int KEY_Z = GLFW_KEY_Z;
	// Special
	public static final int KEY_ESCAPE = GLFW_KEY_ESCAPE;
	public static final int KEY_ENTER = GLFW_KEY_ENTER;
	
	public static final int KEY_RIGHT = GLFW_KEY_RIGHT;
	public static final int KEY_LEFT = GLFW_KEY_LEFT;
	public static final int KEY_UP = GLFW_KEY_UP;
	public static final int KEY_DOWN = GLFW_KEY_DOWN;
	
	public Keyboard(Display display)
	{
		this.display = display;
	}
	
	/**
	 * Checks whether a keyboard key is being held down.
	 * @param keyCode
	 * @return if this key is being held down.
	 */
	public boolean isKeyDown(int keyCode)
	{
		return display.isKeyDown(keyCode);
	}
	
	/**
	 * Checks whether a keyboard key is not being held.
	 * @param keyCode
	 * @return if this key is not being held.
	 */
	public boolean isKeyUp(int keyCode)
	{
		return display.isKeyReleased(keyCode);
	}
	
	/**
	 * Gets a float number dependant on what key you press.
	 * @param posKey
	 * @param negKey
	 * @return the float value based on the current held keys 'posKey' and 'negKey'
	 */
	public float keyPoweredAxis(int posKey, int negKey)
	{
		if (display.isKeyDown(posKey))
		{
			return 1;
		}
		else if (display.isKeyDown(negKey))
		{
			return -1;
		}
		else return 0;
	}
}





















