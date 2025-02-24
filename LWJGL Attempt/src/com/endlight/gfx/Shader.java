package com.endlight.gfx;

import com.endlight.utils.Assets;

import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

public class Shader
{
	int program;
	
	public Shader(String vertexPath, String fragmentPath)
	{
		final String vertexCode = Assets.readStringFromPath(vertexPath);
		final String fragmentCode = Assets.readStringFromPath(fragmentPath);
		
		// Vertex code compilation
		int vert = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vert, vertexCode);
		glCompileShader(vert);
		int vertexSuccess = glGetShaderi(vert, GL_COMPILE_STATUS);
		if (vertexSuccess == GL_FALSE)
		{
			String infoLog = glGetShaderInfoLog(vert);
			System.err.println("Vertex Shader:\n" + infoLog);
		}
		
		// Fragment code compilation
		int frag = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(frag, fragmentCode);
		glCompileShader(frag);
		int fragmentSuccess = glGetShaderi(frag, GL_COMPILE_STATUS);
		if (fragmentSuccess == GL_FALSE)
		{
			String infoLog = glGetShaderInfoLog(frag);
			System.err.println("Fragment Shader:\n" + infoLog);
		}
		
		// create the Shader program
		
		program = glCreateProgram();
		
		glAttachShader(program, vert);
		glAttachShader(program, frag);
		
		glLinkProgram(program);
		
		glDetachShader(program, vert);
		glDetachShader(program, frag);
		
		glDeleteShader(vert);
		glDeleteShader(frag);
	}
	
	public int getUniformLoc(String uniform)
	{
		int loc = glGetUniformLocation(program, uniform);
		if (loc == -1)
		{
			System.err.println("Uniform " + uniform + ", has not been found!");
			return -1;
		}
		
		return loc;
	}
	
	public void setUniform4x4f(int loc, Matrix4f data, boolean transpose)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		data.get(buffer);
		//buffer.flip();
		
		GL20.glUniformMatrix4fv(loc, transpose, buffer);
	}
	
	public void setUniformf(int loc, float value)
	{		
		GL20.glUniform1f(loc, value);
	}
	
	public void start()
	{
		glUseProgram(program);
	}
	
	public void stop()
	{
		glUseProgram(0);
	}
	
	public void dispose()
	{
		stop();
		
		glDeleteProgram(program);
	}
}















