package com.ludumengine.core;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.HashMap;

public class Shader {
	
	private int program;
	private HashMap<String, Integer> uniforms;

	public Shader() {
		program = glCreateProgram();
		uniforms = new HashMap<String, Integer>();
		
		if(program == 0) {
			System.err.println("Shader creation failed: could not find valid memory location in constructor.");
			System.exit(1);
		}
	}
	
	public void bind() {
		glUseProgram(program);
	}
	
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material mat) {
		
	}
	
	public void addUniform(String uniform) {
		int uniformLocation = glGetUniformLocation(program, uniform);
		 if(uniformLocation == 0xFFFFFFFF) {
			 System.err.println("Error : Could not find uniform: "+uniform);
			 new Exception().printStackTrace();
		 }
		 uniforms.put(uniform, uniformLocation);
	}
	
	public void addVertexShader(String text) {
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addGeometryShader(String text) {
		addProgram(text, GL_GEOMETRY_SHADER);
	}
	
	public void addFragmentShader(String text) {
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void compileShader() {
		glLinkProgram(program);
		if(glGetShaderi(program, GL_LINK_STATUS) == 0) {
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}
		glValidateProgram(program);
		if(glGetShaderi(program, GL_VALIDATE_STATUS) == 0) {
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}
	}
	
	private void addProgram(String text, int type) {
		int shader = glCreateShader(type);
		
		if(shader == 0) {
			System.err.println("Shader creation failed: could not find valid memory location when adding shader.");
			System.exit(1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glAttachShader(program, shader);
	}
	
	public void setUniformi(String uniform, int value) {
		glUniform1i(uniforms.get(uniform), value);
	}
	
	public void setUniformf(String uniform, float value) {
		glUniform1f(uniforms.get(uniform), value);
	}
	
	public void setUniform(String uniform, Vector3f value) {
		glUniform3f(uniforms.get(uniform), value.getX(), value.getY(), value.getZ());
	}
	
	public void setUniform(String uniform, Matrix4f value) {
		glUniformMatrix4(uniforms.get(uniform), true, Utils.createFlippedBuffer(value));
	}
	
}
