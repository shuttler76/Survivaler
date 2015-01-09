package entity;

import core.Input;
import graphics.Sprite;
import texture.Texture;
import texture.TextureLoader;
import world.World;

public class Man extends Sprite {
	private static final Texture manTexture = createTexture();
	private boolean jump;
	private double speed;
	private World world;
	public boolean isOnGround;
	private static final double groundZ = 17;

	private static Texture createTexture() {
        try {
            return TextureLoader.loadTextureFromFile("res/spritesheet.png", true, 18);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	public Man(World world) {
		super(manTexture, 10, 10, groundZ);
		this.world = world;
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
			if((x<this.world.worldsize)&&(x>0)&&(y<this.world.worldsize)&&(y>0)){
				if(world.isCubeAt(x,y)){
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
