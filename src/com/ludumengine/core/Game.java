package com.ludumengine.core;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;

	public Game() {

		mesh = ResourceLoader.loadMesh("untitled.obj");
		shader = new Shader();
		transform = new Transform();

		/*Vertex[] data = new Vertex[] {
				new Vertex(new Vector3f(-1, -1, 0)),
				new Vertex(new Vector3f(0, 1, 0)),
				new Vertex(new Vector3f(1, -1, 0)),
				new Vertex(new Vector3f(0, -1, 1))
		};
		
		int[] indices = new int[] {0,1,3,
								   3,1,2,
								   2,1,0,
								   0,2,3};

		mesh.addVertices(data, indices);*/

		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.glsl"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.glsl"));
		shader.compileShader();

		shader.addUniform("transform");
	}

	public void input() {
	}

	float temp = 0.0f;

	public void update() {
		temp += Time.getDelta();
		float sin = (float) Math.sin(temp);
		transform.setTranslation(sin, 0, 0);
		transform.setRotation(0, sin * 180, sin * 180);
		transform.setScale(0.7f * sin, 0.7f * sin, 0.7f * sin);
	}

	public void render() {
		shader.bind();
		shader.setUniform("transform", transform.getTransformation());
		mesh.draw();
	}

}
