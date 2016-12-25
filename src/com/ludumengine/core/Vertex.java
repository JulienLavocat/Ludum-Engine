package com.ludumengine.core;

public class Vertex {
	
	public static final int SIZE = 8;
	
	private Vector3f pos;
	private Vector3f normal;
	private Vector2f textCoord;

	public Vertex(Vector3f pos) {
		this(pos, new Vector2f(0,0));
	}
	
	public Vertex(Vector3f pos, Vector2f textCoord) {
		this(pos, textCoord, new Vector3f(0,0,0));
	}
	
	public Vertex(Vector3f pos, Vector2f textCoord, Vector3f normal) {
		this.pos = pos;
		this.textCoord = textCoord;
		this.normal = normal;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector2f getTextCoord() {
		return textCoord;
	}

	public void setTextCoord(Vector2f textCoord) {
		this.textCoord = textCoord;
	}
	
	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}
	
	public Vector3f getNormal() {
		return normal;
	}

}
