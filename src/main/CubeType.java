package main;

import texture.Texture;
import texture.TextureLoader;

public enum CubeType {
	GRASS(TextureLoader.loadTextureFromFile("res/grasscube.PNG", true, 1)),
	STONE(TextureLoader.loadTextureFromFile("res/stonecube.PNG", true, 1)), 
	SAND(TextureLoader.loadTextureFromFile("res/sandcube.PNG", true, 1)), 
	NEUTRAL(TextureLoader.loadTextureFromFile("res/cube.PNG", true, 1));
	
	public final Texture cubeTexture;

	private CubeType(Texture texture) {
		this.cubeTexture = texture;
	}
}
