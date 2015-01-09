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
		double isometricX = -15*x+15*y;
		double isometricY = -7*x-7*y;
	
		prevY = Display.getHeight()/4*3.5;
		prevX = (Display.getWidth()/2);
		
		zoom+=Mouse.getDWheel()/1000f;
		if(zoom <= 0.1){
			zoom = 0.1;
		}
		if(zoom >= 2){
			zoom = 2;
		}
		double cameraOffsetX = (prevX-isometricX)*zoom;
		double cameraOffsetY = (prevY-isometricY)*zoom;
		GL11.glTranslated(cameraOffsetX, cameraOffsetY, 0);
		GL11.glTranslated(prevX,prevY,0);
		GL11.glScaled(zoom,zoom,1f);
		GL11.glTranslated(-prevX,-prevY,0);
	}
}
