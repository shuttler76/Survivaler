package inventory;

import texture.Texture;

public abstract class Item {
	protected final int maxstacksize;
	public abstract Texture ItemTexture();
	public Item(int size){
		maxstacksize = size;
	}
	public int getMaxstacksize() {
		return maxstacksize;
	}
}
