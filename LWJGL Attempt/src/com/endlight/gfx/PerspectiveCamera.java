package com.endlight.gfx;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.endlight.utils.GlobalAccessor;
import com.endlight.utils.IAccessorLocator;

public class PerspectiveCamera implements IAccessorLocator
{
	private Matrix4f view;
	private Matrix4f projection;
	
	private Vector3f position;
	
	private final Vector3f front = new Vector3f(0f, 0f, -1f);
	private final Vector3f up = new Vector3f(0f, 1f, 0f);
	private final Vector3f zero = new Vector3f(0, 0, 0);
	
	private float fov;
	private float near, far;
	private float aspectRatio;
	
	public PerspectiveCamera(float fov, float near, float far)
	{
		this.fov = fov;
		this.near = near;
		this.far = far;
		
		position = new Vector3f(0f, 0f, 0f);
		
		view = new Matrix4f();
		projection = new Matrix4f();
	}
	
	public void update()
	{
		aspectRatio = GlobalAccessor.getLocatorF(GlobalAccessor.ASPECT_RATIO);
		projection.perspective((float)Math.toRadians(fov), aspectRatio, near, far);
		Vector3f center = position;
		center.add(front);
		view.lookAt(position, center, up);
	}
	
	public Matrix4f getView()
	{
		return view;
	}
	
	public Matrix4f getProjection()
	{
		return projection;
	}
	
	public Matrix4f getTransformation()
	{
		Matrix4f transform = new Matrix4f();
		transform.set(projection).mul(view);
		return transform;
	}
}






