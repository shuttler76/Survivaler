package texture;

import org.lwjgl.opengl.GL11;

public class Texture {
	
	private int id;
	private int width;
	private int height;
	
	Texture(int id, int w, int h){
		this.id = id;
		width = w;
		height = h;
	}
	
	public void bind(){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
}
