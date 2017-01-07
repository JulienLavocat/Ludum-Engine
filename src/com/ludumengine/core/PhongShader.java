package com.ludumengine.core;

public class PhongShader extends Shader {
	
	private static final PhongShader instance = new PhongShader();
	
	private static Vector3f ambientLight = new Vector3f(0.1f,0.1f,0.1f);
	private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0), new Vector3f(0,0,0));
	
	public static PhongShader getInstance() {
		return instance;
	}
	
	private PhongShader() {
		super();
		addVertexShader(ResourceLoader.loadShader("phongVertex.glsl"));
		addFragmentShader(ResourceLoader.loadShader("phongFragment.glsl"));
		compileShader();
		addUniform("transform");
		addUniform("projected");
		addUniform("baseColor");
		addUniform("ambientLight");
		
		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");
		
		addUniform("directionalLight.base.color");
		addUniform("directionalLight.base.intensity");
		addUniform("directionalLight.direction");

	}
	
	@Override
	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
		if(material.getTexture() != null)
			material.getTexture().bind();
		else
			RenderUtils.unBindTextures();
		setUniform("baseColor", material.getColor());
		setUniform("transform", worldMatrix);
		setUniform("projected", projectedMatrix);
		setUniform("ambientLight", ambientLight);
		setUniform("directionalLight", directionalLight);
		setUniformf("specularIntensity", material.getSpecularIntensity());
		setUniformf("specularPower", material.getSpecularPower());
		setUniform("eyePos", Transform.getCamera().getPos());
	}

	public static Vector3f getAmbientLight() {
		return ambientLight;
	}

	public static void setAmbientLight(Vector3f ambientLight) {
		PhongShader.ambientLight = ambientLight;
	}
	
	public static DirectionalLight getDirectionalLight() {
		return directionalLight;
	}

	public static void setDirectionalLight(DirectionalLight directonalLight) {
		PhongShader.directionalLight = directonalLight;
	}

	public void setUniform(String uniform, BaseLight light) {
		setUniform(uniform + ".color", light.getColor());
		setUniformf(uniform + ".intensity", light.getIntensity());
	}
	
	public void setUniform(String uniform, DirectionalLight light) {
		setUniform(uniform + ".base", light.getBase());
		setUniform(uniform + ".direction", light.getDirection());
	}

}
