package inventory;

import texture.Texture;
import texture.TextureLoader;

public class Wood extends Item{
	private Texture wood;
    public Wood(){
    	super(64);
        try {
            wood = TextureLoader.loadTextureFromFile("res/wood.png", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	@Override
	public Texture ItemTexture() {
		return wood;
	}
}
