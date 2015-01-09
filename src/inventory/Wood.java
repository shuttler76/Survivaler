package inventory;

import texture.Texture;

public class Wood extends Item{
	public Wood(int size) {
		super(size);
	}
	private Texture wood = Texture.load("res/wood.png", false, 1);
	@Override
	public Texture ItemTexture() {
		return wood;
	}
}
