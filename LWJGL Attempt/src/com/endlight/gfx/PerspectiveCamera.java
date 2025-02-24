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
    
    private float fov;
    private float near, far;
    private float aspectRatio;
    
    public PerspectiveCamera(float fov, float near, float far)
    {
        this.fov = fov;
        this.near = near;
        this.far = far;
        
        this.position = new Vector3f(0f, 0f, 0f);
        
        this.view = new Matrix4f();
        this.projection = new Matrix4f();
        
        update();
    }
    
    public void update()
    {
        aspectRatio = GlobalAccessor.getLocatorF(GlobalAccessor.ASPECT_RATIO);
        projection.identity().perspective((float)Math.toRadians(fov), aspectRatio, near, far);
        
        Vector3f center = new Vector3f(position).add(front);
        view.identity().lookAt(position, center, up).invert();
    }
    
    public void setPosition(float x, float y, float z) {
        this.position.set(x, y, z);
        update();
    }

    public void move(Vector3f delta) {
        this.position.add(delta);
        update();
    }

    public void setFront(Vector3f direction) {
        this.front.set(direction).normalize();
        update();
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
        return new Matrix4f(projection).mul(view);
    }
}
