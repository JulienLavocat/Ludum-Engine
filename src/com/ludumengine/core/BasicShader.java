package com.ludumengine.core;

public class BasicShader extends Shader {
	
	private static final BasicShader instance = new BasicShader();
	
	public static BasicShader getInstance() {
		return instance;
	}
	
	private BasicShader() {
		super();
		addVertexShader(ResourceLoader.loadShader("basicVertex.glsl"));
		addFragmentShader(ResourceLoader.loadShader("basicFragment.glsl"));
		compileShader();
		addUniform("transform");
		addUniform("color");
	}
	
	@Override
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material mat) {
		setUniform("transform", projectedMatrix);
		
		if(mat.getTexture() != null)
			mat.getTexture().bind();
		else
			RenderUtils.unBindTextures();
		setUniform("color", mat.getColor());
	}

}
