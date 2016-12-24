package com.ludumengine.core;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Material material;
	private Transform transform;
	private Camera camera;

	public Game() {
		
		mesh = new Mesh();
		material = new Material(ResourceLoader.loadTexture("texture.png"), new Vector3f(0, 1, 1));
		shader = new BasicShader();
		transform = new Transform();
		camera = new Camera();
		
		transform.setProjection(0.1f, 1000f, Window.getWidth(), Window.getHeight(), 70f);
		Transform.setCamera(camera);

		Vertex[] data = new Vertex[] {
				new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0,0)),
				new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f,0)),
				new Vertex(new Vector3f(1, -1, 0), new Vector2f(1.0f,0)),
				new Vertex(new Vector3f(0, -1, 1), new Vector2f(0.5f,1.0f))
		};
		
		int[] indices = new int[] {3,1,0,
								   2,1,3,
								   0,1,2,
								   0,2,3};

		mesh.addVertices(data, indices);
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
		RenderUtils.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}

}
