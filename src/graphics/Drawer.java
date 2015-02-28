package graphics;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import texture.Texture;

import java.nio.FloatBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

public class Drawer {

    private static int vbo;
    private static HashMap<Texture, Boolean> compiled = new HashMap<Texture, Boolean>();

    public static void drawTexture( Texture texture, double x, double y, double z, double width, double height, double u, double v, double u2, double v2) {
    	drawTexture(texture, (float)x, (float)y+5, (float)z, (float)width, (float)height, (float)u, (float)v, (float)u2, (float)v2);
    }
    public static void drawTexture( Texture texture, double x, double y, double z, double width, double height) {
    	drawTexture(texture, x, y+5, z, width, height, 0, 0, 1, 1);
    }
    public static void drawTexture( Texture texture, float x, float y, float z, float width, float height) {
        drawTexture(texture, x, y+5, z, width, height, 0, 0, 1, 1);
    }

    public static void drawTexture( Texture texture, float x, float y, float z, float width, float height, float u, float v, float u2, float v2) {

        FloatBuffer data = BufferUtils.createFloatBuffer(2);

        if (compiled.get(texture) == null) {
            compile();
            compiled.put(texture, true);
        }
        glEnable(GL_TEXTURE_2D);

        texture.bind();
        glColor3f(1, 1, 1);

        glPushMatrix();
        glTranslatef(x, y, z);
        glScalef(width, height, 1);

            glEnableClientState(GL_TEXTURE_COORD_ARRAY);
            glEnableClientState(GL_VERTEX_ARRAY);

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
            
            data.put(new float[]{u, v}); data.flip();
            GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 8, data);

            data.put(new float[]{u2, v}); data.flip();
            GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 24, data);

            data.put(new float[]{u2, v2}); data.flip();
            GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 40, data);

            data.put(new float[]{u, v2}); data.flip();
            GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 56, data);
            
            glVertexPointer(2, GL_FLOAT, 16, 0);
            glTexCoordPointer(2, GL_FLOAT, 16, 8);

            glDrawArrays(GL_QUADS, 0, 4);

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        glPopMatrix();

        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);

        glDisable(GL_TEXTURE_2D);
    }

    private static void compile() {
        vbo = GL15.glGenBuffers();
        FloatBuffer data = BufferUtils.createFloatBuffer(4 * 4);

        data.put(new float[]{-0.5f, -0.5f, 0, 0});

        data.put(new float[]{0.5f, -0.5f, 1, 0});

        data.put(new float[]{0.5f, 0.5f, 1, 1});

        data.put(new float[]{-0.5f, 0.5f, 0, 1});

        data.flip();
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_DYNAMIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    
    public static void drawString(Font font, String text, double x, double y) { drawString(font, text, x, y, 12);}
    
    public static void drawString(Font font, String text, double x, double y, int size) {
    	int widthsadded = 0;
    	for (char c : text.toCharArray()) {
    		int i = font.alphabet.indexOf(c);
    		
    		int [] XBounds = font.characterXBounds[i];
    		
    		int width = size / (XBounds[1] - XBounds[0]);
    		
    		drawTexture(font.glyphs, widthsadded + width /2, y = size /2, 0, width, size, XBounds[0] / font.glyphs.getWidth(), 0, XBounds[1] / font.glyphs.getWidth(),1);
    		
    		widthsadded += width;
    	}
    }
}
