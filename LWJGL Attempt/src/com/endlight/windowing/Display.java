package com.endlight.windowing;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;

public class Display
{
	int width = 640;
	int height = 480;
	String title;
	int minorGL;
	int majorGL;
	
	long window;
	
	public Display()
	{
		minorGL = 3;
		majorGL = 3;
	}
	
	public void setTitle(String newTitle)
	{
		title = newTitle;
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
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, majorGL);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, minorGL);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		if (window == NULL)
		{
			throw new RuntimeException("Window could not be created!");
		}
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		
		
		glfwShowWindow(window);
		
		GL.createCapabilities();
	}
	
	public void update()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glClearColor(0.2f, 0.075f, 0.45f, 1.0f);
		
		glfwSwapBuffers(window);
		glfwPollEvents();
	}
	
	public void dispose()
	{
		if (window != NULL) glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	public boolean exists()
	{
		return !glfwWindowShouldClose(window);
	}
}


















