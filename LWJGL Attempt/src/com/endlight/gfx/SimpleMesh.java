package com.endlight.gfx;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import com.endlight.utils.GlobalAccessor;

import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class SimpleMesh
{
	int vao, pbo, ibo;
	Shader shader;
	
	float[] vertices =
		{
			-0.5f, -0.5f, 0.0f,  // Bottom Left - 0
			-0.5f, 0.5f, 0.0f,   // Top Left - 1
			0.5f, 0.5f, 0.0f,    // Top Right - 2
			0.5f, -0.5f, 0.0f    // Bottom Right - 3
		};
	
	int[] indices =
		{
				0, 1, 2,
				2, 3, 0
		};
	
	public SimpleMesh()
	{
		// define the Shader here
		shader = new Shader("shaders/default.vert", "shaders/default.frag");
		
		// Create the Box for our graphics data
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		// create the vertices position buffer
		pbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, pbo);
		glBufferData(GL_ARRAY_BUFFER, storeDataInFloatBuffer(vertices), GL_STATIC_DRAW);
		
		// Assign in it the shader
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		// Index Buffer creation
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, storeDataInIntegerBuffer(indices), GL_STATIC_DRAW);
		
		// Use the shader Here
		shader.start();
		
		// unbind it (is a must)
		unbind();
	}
	
	public void draw()
	{
		shader.start();
		glBindVertexArray(vao);
		glEnableVertexAttribArray(0);
		
		glDrawElements(GL_TRIANGLES, vertices.length, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		shader.stop();
	}
	
	public void dispose()
	{
		glBindVertexArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		shader.dispose();
		glDeleteBuffers(pbo);
		glDeleteBuffers(ibo);
		glDeleteVertexArrays(vao);
	}
	
	void unbind()
	{
		glBindVertexArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glDisableVertexAttribArray(0);
	}
	
	FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	IntBuffer storeDataInIntegerBuffer(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}

















