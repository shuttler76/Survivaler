package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import org.lwjgl.opengl.GL11;
import pixeltoy.PixelToy;

public class ProgramMain {
	public static void main(String[] args) {
		PixelToy.init();
		try {
			Display.setDisplayMode(new DisplayMode(1000,600));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		PixelToy.graphics.drawString(0, 0, "");
		Game game = new Game();
		
		while(true) {
			PixelToy.newFrame();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
            GL11.glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 5000, -5000);

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            game.update();
		}
	}

}
