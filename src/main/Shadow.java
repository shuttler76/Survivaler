package main;

import texture.Texture;

public class Shadow extends Sprite implements Update{
	private static final Texture shadow = Texture.load("res/shadow.png", true, 1);
	private final Man man;
	private final Cubes cubes;
	public Shadow(Man man, Cubes cubes) {
		super(shadow, 0, 0, 16);
		this.setSpritesize(32, 15);
		this.man = man;
		this.cubes = cubes;
	}
	@Override
	public void update() {
		x = this.man.x;
		y = this.man.y;
		if((cubes.isCubeAt(x,y))&&(this.man.z>16)){
			this.setVisibility(true);
		}else{
			this.setVisibility(false);
		}
		
	}
}
