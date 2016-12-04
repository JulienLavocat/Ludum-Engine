package com.ludumengine.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	public static void createWindow(int width, int height, String title) {
		try {
			Display.setTitle(title);
			Display.setDisplayMode(new DisplayMode(width, height));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void render() {
		
	}

}
