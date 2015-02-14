package entity;

import pixeltoy.PixelToy;
import graphics.Drawable;
import graphics.Drawer;
import main.Updatable;
import texture.Texture;
import texture.TextureLoader;
import world.CubeType;
import world.World;

public abstract class Entity implements Drawable, Updatable {

    public World worldObj;

    private static final Texture shadow = createShadowTexture();
    private static Texture createShadowTexture() {
        try {
            return TextureLoader.loadTextureFromFile("res/shadow.png", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public double x;
    public double y;
    public double z;

    protected boolean jump;
    protected double speed;
    public boolean isOnGround;
    public boolean isInWater;

    public Entity( World world ) {
        worldObj = world;
        x = 0;
        y = 0;
        z = 0;
    }

    protected abstract void updateEntity();
    protected abstract void drawEntity();

    @Override
    public void draw() {
        if ((worldObj.isCubeAt(x, y) && z > 16)&&(!isInWater)) {
        	double[] pos = World.convertWorldToIsometric(x, y, World.groundZ);
//        	PixelToy.graphics.drawString(pos[0], pos[1], "X IS "+x);
//        	PixelToy.graphics.drawString(pos[0]+20, pos[1]+21, "Y IS "+y);
        	Drawer.drawTexture(shadow, pos[0], pos[1], pos[2], 31, 15);
        }
        drawEntity();
    }

    @Override
    public void update() {
        speed-=0.25;
        z += speed;

        if((z<=World.groundZ)&&(z>=(World.groundZ-0.5))) {
            if (worldObj.isCubeAt(x, y)) {
                isOnGround = true;
                speed = 0;
                z = World.groundZ;
                jump = false;
            }    
        } else {
            jump=true;
            isOnGround = false;
        }
        
        if(worldObj.getCubeAt(x , y)==CubeType.WATER||worldObj.getCubeAt(x , y)==CubeType.DEEPWATER){
        	isInWater = true;
        } else {
        	isInWater = false;
        }
        updateEntity();
    }
}

