package entity;

import core.Input;
import graphics.Drawer;
import texture.Texture;
import texture.TextureLoader;
import world.World;

public class Man extends Entity {
	private static final Texture manTexture = createTexture();

	private static Texture createTexture() {
        try {
            return TextureLoader.loadTextureFromFile("res/spritesheet.png", true, 18);
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
        if(Input.isKeyDown("SPACE") && !jump){
            //counter+=0.05;
            jump=true;
            speed = 5;
        }

//        if (isOnGround) {
//            setAnimateSprite(false);
//            resetAnimationStage();
//        }
//        else {
//            setAnimateSprite(true);
//        }
    }

    @Override
    protected void drawEntity() {
        double[] pos = World.convertWorldToIsometric(x, y, z+ 32);
        System.out.println(pos[2]);
        Drawer.drawTexture(manTexture, (float)pos[0], (float)pos[1], (float)pos[2], 32, 32, 0,0, 1f/18f, 1);
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
