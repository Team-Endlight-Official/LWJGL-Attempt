#version 410 core

layout (location = 0) in vec3 aPos;

out vec3 _color;

void main()
{
    gl_Position = vec4(aPos, 1.0);

    _color = aPos * 2;
}