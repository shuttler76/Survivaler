package main;

import texture.Texture;

public class Cube extends Sprite {

	public Cube(double x, double y, CubeType type) {
		super(type.cubeTexture, x, y, 0);
		this.setSpritesize(32, 32);
	}

}
