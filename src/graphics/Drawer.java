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

    public static void drawTexture( Texture texture, float x, float y, float z, float width, float height) {
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

            glVertexPointer(2, GL_FLOAT, 16, 0);
            glTexCoordPointer(2, GL_FLOAT, 16, 8);

            glDrawArrays(GL_QUADS, 0, 4);

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        glPopMatrix();

        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);

        glDisable(GL_TEXTURE_2D);
    }

    public static void drawTexture( Texture texture, float x, float y, float z, float width, float height, float u, float v, float u2, float v2) {

        FloatBuffer data = BufferUtils.createFloatBuffer(2);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        
        data.put(new float[]{u, v}); data.flip();
        GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 8, data);

        data.put(new float[]{u2, v}); data.flip();
        GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 24, data);

        data.put(new float[]{u2, v2}); data.flip();
        GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 40, data);

        data.put(new float[]{u, v2}); data.flip();
        GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 56, data);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        drawTexture(texture, x, y, z, width, height);
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
}