package com.ludumengine.core;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

public class Utils {

	public static FloatBuffer createFloatBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}

	public static IntBuffer createIntBuffer(int size) {
		return BufferUtils.createIntBuffer(size);
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertex) {
		FloatBuffer buffer = createFloatBuffer(vertex.length * Vertex.SIZE);
		for(int i = 0; i < vertex.length; i++) {
			buffer.put(vertex[i].getPos().getX());
			buffer.put(vertex[i].getPos().getY());
			buffer.put(vertex[i].getPos().getZ());
			buffer.put(vertex[i].getTextCoord().getX());
			buffer.put(vertex[i].getTextCoord().getY());
		}
		buffer.flip();
		return buffer;
	}
	
	public static IntBuffer createFlippedBuffer(int... indices) {
		IntBuffer buffer = createIntBuffer(indices.length);
		buffer.put(indices);
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

	public static String[] removeEmptyString(String[] s) {
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < s.length; i++)
			if(!s[i].equals(""))
				result.add(s[i]);
		String[] res = new String[result.size()];
		return result.toArray(res);
	}

	public static int[] toIntArray(Integer[] indicesData) {
		int[] result = new int[indicesData.length];
		for(int i = 0; i < indicesData.length; i++) {
			result[i] = indicesData[i].intValue();
		}
		return result;
	}

}
