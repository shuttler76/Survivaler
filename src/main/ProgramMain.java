package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import pixeltoy.PixelToy;

public class ProgramMain {
	public static final int cubeDeltaX = 15;
	public static final int cubeDeltaY = 7;
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
			game.update();
		}
	}

}
