package inventory;

import pixeltoy.PixelToy;

public class ItemStack {
	public static final int itemsize = 64;
	private int stacksize;
	private Item item;
	
	public ItemStack(Item item, int stacksize) {
		this.stacksize = stacksize;
		this.item = item;
	}

	public ItemStack(Item item) {
		this(item, 1);
	}

	public void draw(double x, double y){
		item.ItemTexture().draw(x, y, itemsize, itemsize);
		if(stacksize>1){
			PixelToy.graphics.useColour(0, 0, 0);
			PixelToy.graphics.drawString(x, y, String.valueOf(stacksize));
		}
	}
	public void transfer(ItemStack to, int amount){
		amount = stacksize < amount ? stacksize : amount;
		addTo(-amount);
		to.addTo(amount);
	}
	public void addTo(int amount){
		stacksize += amount;
		
		stacksize = item.getMaxstacksize() < stacksize ? item.getMaxstacksize() : stacksize;
	}
	public void split(ItemStack with){
		with.addTo(stacksize/2);
		if(!(stacksize % 2 == 0)){
			with.addTo(1);
		}
		stacksize/=2;
	}
	public int getStacksize(){
		return stacksize;
	}
}
