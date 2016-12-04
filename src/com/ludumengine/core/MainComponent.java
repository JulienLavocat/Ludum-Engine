package com.ludumengine.core;

public class MainComponent {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Ludum Engine";
	
	public static void main(String args[]) {
		Window.createWindow(WIDTH, HEIGHT, "Ludum Engine");
		MainComponent game = new MainComponent();
		game.start();
	}

	public void start() {
		run();
	}
	
	public void stop() {
		
	}
	
	public void run() {
		while(!Window.isCloseRequested()) {
			render();
		}
	}
	
	public void render() {
		Window.render();
	}
	
	public void cleanUp() {
		
	}
	
}
