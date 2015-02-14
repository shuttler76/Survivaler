package entity;

import core.Input;
import graphics.Drawer;
import texture.Texture;
import texture.TextureLoader;
import world.World;

public class Man extends Entity {
	private float animState;
	private float animSpeed=1;
	private double manSpeed=0.1;
	private double offset=20;
	private double cutoff=0;
	private double manheight=32;
	private static final Texture manTexture = createTexture();

	private static Texture createTexture() {
        try {
            return TextureLoader.loadTextureFromFile("res/spritesheet.png", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	public Man(World world) {
        super(world);
        reset();
        
	}

    @Override
    protected void updateEntity() {
        if(Input.isKeyDown("s")){
            x+=manSpeed;
            y+=manSpeed;
        }
        if(Input.isKeyDown("d")){
            y+=manSpeed;
            x-=manSpeed;
        }
        if(Input.isKeyDown("w")){
            x-=manSpeed;
            y-=manSpeed;
        }
        if(Input.isKeyDown("a")){
            y-=manSpeed;
            x+=manSpeed;
        }
        if(Input.isKeyDown("r")){
           manSpeed=5;
        } else {
        	manSpeed=0.1;
        }
        if(Input.isKeyDown("SPACE") && !jump){
            jump=true;
            speed = 5;
        }

        offset = 15;
		cutoff = 0;
		manheight = 32;        		
		
        if ((isOnGround)) {
        	if(isInWater){
        		manheight = 16;
        		offset = 10;
        		cutoff = 0.5;
//        		manSpeed=0.06;
        		animSpeed = 0.5f;
        		animState+=animSpeed;
            	if(animState>18){
            		animState=0;
            	}
        	} else {
        		animState=0;
        	}
        } else {
        	animSpeed = 1;
        	animState+=animSpeed;
        	if(animState>18){
        		animState=0;
        	}
        }
    }

    @Override
    protected void drawEntity() {
        double[] pos = World.convertWorldToIsometric(x, y, z+offset);
        Drawer.drawTexture(manTexture, (float)pos[0], (float)pos[1], (float)pos[2], 32, manheight, ((int)animState)/18f, cutoff, ((int)animState+1)/18f, 1);
    }

    public void reset(){
		x = 10;
		y = 10;
		z = World.groundZ;
		speed = 0;
	}

	public boolean isDead(){
		return(z<-800);
	}
}
