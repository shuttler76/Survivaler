package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import pixeltoy.PixelToy;

public class ProgramMain {
	public static void main(String[] args) {
		
		try {
			Display.create();
			Mouse.create();
			Keyboard.create();
			Display.setDisplayMode(new DisplayMode(1000,600));
			Display.setResizable(true);
			Display.setTitle("Survivaler: Now with moar bugs!");
		} catch (LWJGLException e1) {
			e1.printStackTrace();
			Display.destroy();
            System.exit(1);
		}
		
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 5000, -5000);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glViewport(0, 0, Display.getWidth(), Display.getHeight());
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glEnable(GL_ALPHA_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
		Game game = new Game();
		
		while(!Display.isCloseRequested()) {
			Display.update();
	        Display.sync(60);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glClearColor(0, 0.5f, 1, 1);
			
			if (Display.wasResized()) {
				glMatrixMode(GL_PROJECTION);
		        glLoadIdentity();
		        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 5000, -5000);
		        glMatrixMode(GL_MODELVIEW);
		        glViewport(0, 0, Display.getWidth(), Display.getHeight());
			}

            game.update();
		}
		Display.destroy();
	}

}
