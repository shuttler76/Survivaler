package main;

import org.lwjgl.opengl.GL11;

public class StaticSpriteList extends SpriteList {
	private int identification; 
	private boolean isCompiled;
	@Override
	public void draw(){
		if (!isCompiled){
			identification = GL11.glGenLists(1);
			GL11.glNewList(identification, GL11.GL_COMPILE);
			super.draw();
			GL11.glEndList();
			isCompiled = true;			
		}
		GL11.glCallList(identification);
	}
}
