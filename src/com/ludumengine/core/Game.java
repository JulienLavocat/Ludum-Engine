package com.ludumengine.core;

import org.lwjgl.input.Keyboard;

public class Game {

	public Game() {
	}

	public void input() {
		if(Input.getKeyDown(Keyboard.KEY_UP))
			System.out.println("You just pressed UP");
		if(Input.getKeyUp(Keyboard.KEY_UP))
			System.out.println("You just released UP");
		if(Input.getMouseDown(1))
			System.out.println("You just right clicked at "+Input.getMousePosition().normalize());
		if(Input.getMouseUp(1))
			System.out.println("You just released right click");
	}

	public void update() {

	}

	public void render() {

	}

}
