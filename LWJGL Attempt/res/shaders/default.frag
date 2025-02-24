#version 410 core

in vec3 _color;

out vec4 OUT;

void main()
{
    OUT = vec4(_color.x + 0.5, _color.y + 0.5, _color.z + 0.5, 1.0);
}