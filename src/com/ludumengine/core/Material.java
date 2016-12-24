package com.ludumengine.core;

public class Material {
	
	private Texture texture;
	private Vector3f color;
	
	public Material(Texture texture, Vector3f color) {
		this.texture = texture;
		this.color = color;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexute(Texture texute) {
		this.texture = texute;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

}
