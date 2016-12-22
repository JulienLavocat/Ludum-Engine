package com.ludumengine.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ResourceLoader {

	public static String loadShader(String fileName) {

		StringBuilder sb = new StringBuilder();
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader("./res/shaders/"+fileName));
			String line;
			while((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		return sb.toString();
	}

	public static Mesh loadMesh(String fileName) {
		String[] splitArray = fileName.split("\\.");
		String ext = splitArray[splitArray.length -1];

		if(!ext.equals("obj")) {
			System.err.println("Error : Mesh file format not supported "+ext);
			new Exception().printStackTrace();
			System.exit(1);
		}

		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("./res/models/"+fileName));
			String line;
			while((line = br.readLine()) != null) {
				String[] s = line.split(" ");
				s = Utils.removeEmptyString(s);
				if(s.length == 0 || s[0].equals("#"))
					continue;
				else if(s[0].equals("v"))
					vertices.add(new Vertex(new Vector3f(Float.valueOf(s[1]), Float.valueOf(s[2]), Float.valueOf(s[3]))));
				else if(s[0].equals("f")) {
					indices.add(Integer.parseInt(s[1].split("/")[0]) - 1);
					indices.add(Integer.parseInt(s[2].split("/")[0]) - 1);
					indices.add(Integer.parseInt(s[3].split("/")[0]) - 1);
					
					if(s.length > 4) {
						indices.add(Integer.parseInt(s[1].split("/")[0]) - 1);
						indices.add(Integer.parseInt(s[3].split("/")[0]) - 1);
						indices.add(Integer.parseInt(s[4].split("/")[0]) - 1);
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		Mesh res = new Mesh();
		Vertex[] vertexData = new Vertex[vertices.size()];
		vertices.toArray(vertexData);
		Integer[] indicesData = new Integer[indices.size()];
		indices.toArray(indicesData);
		res.addVertices(vertexData, Utils.toIntArray(indicesData));
		return res;
	}

}
