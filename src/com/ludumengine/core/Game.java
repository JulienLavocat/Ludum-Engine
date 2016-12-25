package com.ludumengine.core;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Material material;
	private Transform transform;
	private Camera camera;
	//private Mesh monkey;

	public Game() {
		
		mesh = new Mesh();
		mesh = ResourceLoader.loadMesh("untitled.obj");
		mesh = ResourceLoader.loadMesh("untitled.obj");
		material = new Material(ResourceLoader.loadTexture("texture.png"), new Vector3f(1, 1, 1));
		shader = PhongShader.getInstance();
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

		mesh.addVertices(data, indices, true);
		PhongShader.setAmbientLight(new Vector3f(0.1f,0.1f,0.1f));
		PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
	}

	public void input() {
		
		camera.input();
		
	}

	float temp = 0.0f;

	public void update() {
		temp += Time.getDelta();
		float sin = (float) Math.sin(temp);
		transform.setTranslation(0, 0, 5);
		transform.setRotation(0, -sin * 180, 0);
		transform.setScale(1.5f, 1.5f, 1.5f);
	}

	public void render() {
		//RenderUtils.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		RenderUtils.setClearColor(new Vector3f(0,0,0));
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
		//monkey.draw();
	}

}
