package main;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import pixeltoy.PixelToy;

public class Camera {
	private double camerax;
	private double cameray;
	private double zoom;
	private boolean updated=false;
	private double prevY;
	private double prevX;
	public Camera(){
		
	}
	
	public void update(double x,double y){
		prevY = Display.getHeight()/4*3.5;
		prevX = (Display.getWidth()/2);
		double cameraOffsetX = (prevX-x)*zoom;
		double cameraOffsetY = (prevY-y)*zoom;
		GL11.glTranslated(cameraOffsetX, cameraOffsetY, 0);
		GL11.glTranslated(prevX,prevY,0);
		GL11.glScaled(zoom,zoom,1f);
		GL11.glTranslated(-prevX,-prevY,0);
	}
}
