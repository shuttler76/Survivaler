package graphics;


import org.lwjgl.opengl.Display;

import texture.Texture;

public class Sprite implements Drawable{
	private final Texture texture;
	public double x;
	public double y;
	public double z = 0;
	private static final int cubeDeltaX = 15;
	private static final int cubeDeltaY = 7;
	private double height;
	private double width;
	private double offsetX;
	private double offsetY;
	private double offsetZ;
	private boolean visible;
	private boolean animated;
	public Sprite(Texture texture,double x, double y, double z){
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.z = z;
		this.height = height;
		this.width = width;
		this.offsetX = 0;
		this.offsetY = 0;
		this.offsetZ = 0;
		this.visible = true;
		this.animated = false;
	}
	protected void setSpritesize(double x, double y){
		this.height = y;
		this.width = x;
	}
	
	public void draw(){
		if(visible){
			double isometricX = (Display.getWidth()/2)-cubeDeltaX*(x+offsetX)+cubeDeltaX*(y+offsetY);
			double isometricY = Display.getHeight()/4*3.5-cubeDeltaY*(x+offsetX)-cubeDeltaY*(y+offsetY)+z;
			if(animated){
				texture.nextAnimationFrame();
			}
			texture.draw(isometricX, isometricY, this.width, this.height);
		}
	}

	public double getZordering(){
		return x+y+z;
	}
	
	protected double getX(){
		return x;
	}
	protected double getY(){
		return y;
	}
	protected double getZ(){
		return z;
	}
	protected void changeOffset(double ox,double oy,double oz){
		offsetX = ox;
		offsetY = oy;
		offsetZ = oz;
	}
	protected void setVisibility(boolean visibility){
		visible = visibility;
	}
	protected void setAnimateSprite(boolean isAnimated){
		animated = isAnimated;
	}
	protected void resetAnimationStage(){
		texture.resetAnimation();
	}
}
