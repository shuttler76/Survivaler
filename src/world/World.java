package world;

import graphics.Drawable;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL15;
import texture.Texture;
import texture.TextureLoader;
import util.SimplexNoise;

import java.nio.FloatBuffer;
import java.security.InvalidParameterException;

import static org.lwjgl.opengl.GL11.*;

public class World implements Drawable {
	private int[][] cubes;
    private static final int cubeDeltaX = 15;
    private static final int cubeDeltaY = 7;

	public final int worldsize;
    private int vbo;
	private boolean isWorldGenerated;
    private static final Texture cubesTexture = createTexture();

    private static Texture createTexture() {
        try {
            return TextureLoader.loadTextureFromFile("res/cubes.png", true, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	public World() {
		worldsize = 64;
		buildCubeMap();
	}

	public void buildCubeMap() {

		if(!isWorldGenerated){
            vbo = GL15.glGenBuffers();
        }

        SimplexNoise.randomizeSeed();
		this.cubes = WorldGenerator.generate();

        FloatBuffer data = BufferUtils.createFloatBuffer(cubes.length * cubes[0].length * 5 * 4);

        for(int row = 0;row < cubes.length; row++){
			for(int column = 0; column < cubes[0].length; column++) {

                float[] pos = convertWorldToIsometric(row, column, 0);
                float[] uv = getTexCoordsFromCubeType(cubes[row][column]);

                data.put(new float[]{pos[0]-16, pos[1]+16, row + column, uv[0], uv[1]});

                data.put(new float[]{pos[0]-16, pos[1]-16, row + column, uv[0], uv[1] - (1f / CubeType.NUMBERCUBETYPES)});

                data.put(new float[]{pos[0]+16, pos[1]-16, row + column, uv[0] + (1f / CubeType.NUMBERCUBETYPES), uv[1] - (1f / CubeType.NUMBERCUBETYPES)});

                data.put(new float[]{pos[0]+16, pos[1]+16, row + column, uv[0] + (1f / CubeType.NUMBERCUBETYPES), uv[1]});
			}
		}

        data.flip();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_DYNAMIC_DRAW);

        isWorldGenerated=true;
	}
	public boolean isCubeAt(double x, double y) {
        return (x > 0) && (x < worldsize) && (y > 0) && (y < worldsize);
	}

    @Override
    public void draw() {
        glEnable(GL_TEXTURE_2D);

        cubesTexture.bind();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);

        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        glEnableClientState(GL_VERTEX_ARRAY);

        glVertexPointer(3, GL_FLOAT, 20, 0);
        glTexCoordPointer(2, GL_FLOAT, 20, 12);

        glDrawArrays(GL_QUADS, 0, cubes.length * cubes[0].length);

        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);

        glDisable(GL_TEXTURE_2D);
    }

    private float[] getTexCoordsFromCubeType( int type ) {
        if (!CubeType.isCubeType(type)) {
            throw new InvalidParameterException("type parameter is not a CubeType");
        }
        return new float[]{ (float) type / (float) CubeType.NUMBERCUBETYPES, 0};
    }

    public double[] convertWorldToIsometric(double x, double y, double z) {
        return new double[]{(Display.getWidth()/2)-cubeDeltaX*x+cubeDeltaX*y, Display.getHeight()/4*3.5-cubeDeltaY*x-cubeDeltaY*y+z};
    }

    public float[] convertWorldToIsometric(float x, float y, float z) {
        return new float[]{(Display.getWidth()/2)-cubeDeltaX*x+cubeDeltaX*y, (float) (Display.getHeight()/4*3.5-cubeDeltaY*x-cubeDeltaY*y+z)};
    }
}