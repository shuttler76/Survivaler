package main;

import core.Input;
import texture.Texture;

public class Man extends Sprite{
	private static final Texture manTexture = Texture.load("res/spritesheet.png", true, 18);
	private boolean jump;
	private double speed;
	private Cubes cubes;
	public boolean isOnGround;
	private static final double groundZ = 17;

	
	public Man(Cubes cubes) {
		super(manTexture, 10, 10, groundZ);
		this.cubes = cubes;
		this.setSpritesize(32, 32);
		this.changeOffset(-0.5,-0.5,0);
	}
	public void reset(){
		x = 10;
		y = 10;
		z = groundZ;
		speed = 0;
	}
	public void update() {
		if(Input.isKeyDown("s")){
			x+=0.1;
		}
		if(Input.isKeyDown("d")){
			y+=0.1;
		}
		if(Input.isKeyDown("w")){
			x-=0.1;
		}
		if(Input.isKeyDown("a")){
			y-=0.1;
		}
		if(Input.isKeyDown("SPACE") && jump == false){
			//counter+=0.05;
			jump=true;
			speed = 5;
		}
		speed-=0.25;				
		z += speed;
		if((z<=groundZ)&&(z>=(groundZ-0.5))){
			isOnGround = true;
			if((x<this.cubes.worldsize)&&(x>0)&&(y<this.cubes.worldsize)&&(y>0)){
				if(cubes.isCubeAt(x,y)){
					speed=0;
					z = groundZ;
					jump=false;
					setAnimateSprite(false);
					resetAnimationStage();
				}
			}
		}
		else{
			jump=true;
			isOnGround = false;
			setAnimateSprite(true);
		}
	}
	public boolean isDead(){
		return(z<-800);
	}
}
