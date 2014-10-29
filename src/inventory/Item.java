package inventory;

import texture.Texture;

public abstract class Item {
	protected int maxstacksize;
	public abstract Texture ItemTexture();
	public int getMaxstacksize() {
		return maxstacksize;
	}
	public void setMaxstacksize(int maxstacksize) {
		this.maxstacksize = maxstacksize;
	}
	
}
