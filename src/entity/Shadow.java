package entity;

import graphics.Sprite;
import main.Updatable;
import texture.Texture;
import texture.TextureLoader;
import world.World;

public class Shadow extends Sprite implements Updatable {
	private static final Texture shadow = createTexture();

    private static Texture createTexture() {
        try {
            return TextureLoader.loadTextureFromFile("res/shadow.png", true, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	private final Man man;
	private final World world;
	public Shadow(Man man, World world) {
		super(shadow, 0, 0, 16);
		this.setSpritesize(32, 15);
		this.man = man;
		this.world = world;
	}
	@Override
	public void update() {
		x = this.man.x;
		y = this.man.y;
		if((world.isCubeAt(x,y))&&(this.man.z>16)){
			this.setVisibility(true);
		}else{
			this.setVisibility(false);
		}
		
	}
}
