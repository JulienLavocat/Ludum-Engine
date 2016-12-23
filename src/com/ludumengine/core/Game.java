package com.ludumengine.core;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	private Texture texture;

	public Game() {
		
		mesh = new Mesh();
		texture = ResourceLoader.loadTexture("texture.png");
		shader = new Shader();
		transform = new Transform();
		camera = new Camera();
		
		transform.setProjection(0.1f, 1000f, Window.getWidth(), Window.getHeight(), 70f);
		Transform.setCamera(camera);

		Vertex[] data = new Vertex[] {
				new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0,0)),
				new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f,0)),
				new Vertex(new Vector3f(1, -1, 0), new Vector2f(1.0f,0)),
				new Vertex(new Vector3f(0, -1, 1), new Vector2f(0,0.5f))
		};
		
		int[] indices = new int[] {0,1,3,
								   3,1,2,
								   2,1,0,
								   0,2,3};

		mesh.addVertices(data, indices);

		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.glsl"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.glsl"));
		shader.compileShader();

		shader.addUniform("transform");
	}

	public void input() {
		
		camera.input();
		
	}

	float temp = 0.0f;

	public void update() {
		temp += Time.getDelta();
		float sin = (float) Math.sin(temp);
		transform.setTranslation(0, 0, 5);
		transform.setRotation(0, sin * 180, 0);
		//transform.setScale(0.7f * sin, 0.7f * sin, 0.7f * sin);
	}

	public void render() {
		shader.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
		texture.bind();
		mesh.draw();
	}

}
