package com.ludumengine.core;

import java.io.BufferedReader;
import java.io.FileReader;

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

}
