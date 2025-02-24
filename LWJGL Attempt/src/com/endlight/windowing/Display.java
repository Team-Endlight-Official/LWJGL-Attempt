package com.endlight.windowing;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import com.endlight.utils.GlobalAccessor;
import com.endlight.utils.IAccessorLocator;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.DoubleBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Display implements IAccessorLocator
{
	int width = 640;
	int height = 480;
	String title;
	int minorGL;
	int majorGL;
	boolean fullscreen;
	boolean resizable;
	
	float aspectRatio;
	
	double mouseX;
	double mouseY;
	
	long window;
	GLFWVidMode videoMode;
	GLFWWindowSizeCallback sizeCallback;
	
	public Display()
	{
		minorGL = 3;
		majorGL = 3;
	}
	
	public void setTitle(String newTitle)
	{
		title = newTitle;
		if (window != NULL)
		{
			glfwSetWindowTitle(window, title);
		}
	}
	
	public void setFullscreen(boolean fullscreen)
	{
		this.fullscreen = fullscreen;
	}
	
	public void setResizable(boolean resizable)
	{
		this.resizable = resizable;
	}
	
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void setGLVersion(int major, int minor)
	{
		majorGL = major;
		minorGL = minor;
	}
	
	public void create()
	{
		if (!glfwInit())
		{
			throw new IllegalStateException("Could not Initialize GLFW!");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, majorGL);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, minorGL);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		if (window == NULL)
		{
			throw new RuntimeException("Window could not be created!");
		}
		
		if (fullscreen)
		{
			glfwSetWindowMonitor(window, glfwGetPrimaryMonitor(), 0, 0, videoMode.width(), videoMode.height(), videoMode.refreshRate());
		}
		
		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		
		sizeCallback = GLFWWindowSizeCallback.create(this::windowSizeCallback);
		glfwSetWindowSizeCallback(window, sizeCallback);
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		
		glfwShowWindow(window);
		
		GL.createCapabilities();
		
		glViewport(0, 0, width, height);
	}
	
	void windowSizeCallback(long window, int width, int height)
	{
		this.width = width;
		this.height = height;
		
		glViewport(0, 0, width, height);
	}
	
	public void update()
	{
		// Calculate Aspect Ratio
		aspectRatio = (float)width / (float)height;
		
		// Mouse Position Calculation
		DoubleBuffer mouseXBuffer = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer mouseYBuffer = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, mouseXBuffer, mouseYBuffer);
		mouseXBuffer.rewind();
		mouseYBuffer.rewind();
		
		mouseX = mouseXBuffer.get();
		mouseY = mouseYBuffer.get();
		
		// Clearing (will soon be removed)
		glClear(GL_COLOR_BUFFER_BIT);
		glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
	}
	
	public void pollWindow()
	{
		glfwSwapBuffers(window);
		glfwPollEvents();
	}
	
	public void dispose()
	{
		glfwSetWindowSizeCallback(window, sizeCallback).free();
		if (window != NULL) glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	public boolean isKeyDown(int keyCode)
	{
		if (!focused())
			return false;
		
		if (glfwGetKey(window, keyCode) == GLFW_PRESS)
		{
			return true;
		}
		else return false;
	}
	
	public boolean isKeyReleased(int keyCode)
	{
		if (!focused())
			return false;
		
		if (glfwGetKey(window, keyCode) == GLFW_RELEASE)
		{
			return true;
		}
		else return false;
	}
	
	public boolean isMousePressed(int mouseButton)
	{
		if (!focused())
			return false;
		
		if (glfwGetMouseButton(window, mouseButton) == GLFW_PRESS)
		{
			return true;
		}
		else return false;
	}
	
	public boolean isMouseReleased(int mouseButton)
	{
		if (!focused())
			return false;
		
		if (glfwGetMouseButton(window, mouseButton) == GLFW_RELEASE)
		{
			return true;
		}
		else return false;
	}
	
	public boolean exists()
	{
		return !glfwWindowShouldClose(window);
	}
	
	public boolean focused()
	{
		if (!exists())
			return false;
		
		if (glfwGetWindowAttrib(window, GLFW_FOCUSED) == GLFW_TRUE)
		{
			return true;
		}
		else return false;
	}
	
	public int currentMonitorWidth()
	{
		if (!exists())
			return 0;
		
		return videoMode.width();
	}
	
	public int currentMonitorHeight()
	{
		if (!exists())
			return 0;
		
		return videoMode.height();
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public double getMouseX()
	{
		return mouseX;
	}
	
	public double getMouseY()
	{
		return mouseY;
	}
	
	public float getAspectRatio()
	{
		return aspectRatio;
	}
	
	public double getTime()
	{
		if (!exists())
			return 0;
		
		return glfwGetTime();
	}
}


















