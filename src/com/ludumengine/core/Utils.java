package com.ludumengine.core;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Utils {

	public static FloatBuffer createFloatBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}

	public static FloatBuffer
	createFlippedBuffer(Vertex[] vertices) {
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
		for(int i = 0; i < vertices.length; i++) {
			buffer.put(vertices[i].getPos().getX());
			buffer.put(vertices[i].getPos().getY());
			buffer.put(vertices[i].getPos().getZ());
		}
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer createFlippedBuffer(Matrix4f m) {
		FloatBuffer buffer = createFloatBuffer(16);
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				buffer.put(m.get(i, j));
		buffer.flip();
		return buffer;
	}

}
