package inventory;

import graphics.Drawer;
import pixeltoy.PixelToy;

public class ItemStack {
	public static final int maxStacksize = 64;
	public static final int itemsize=64;
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
		Drawer.drawTexture(item.ItemTexture(), x, y, 0, itemsize, itemsize);
		if(stacksize>1){
//			PixelToy.graphics.useColour(0, 0, 0);
//			PixelToy.graphics.drawString(x+8, y-28, String.valueOf(stacksize));
		}
	}
	public void transfer(ItemStack to){
		addTo(to, maxStacksize);
	}

	public void addTo(ItemStack to, int amount) {
		int remainingspace = maxStacksize - to.stacksize;
		int possibleTransfer = Math.min(stacksize, remainingspace);
		amount = Math.min(possibleTransfer, amount);
		to.stacksize+=amount;
		stacksize-=amount;
	}
	
	public void split(ItemStack with){
		if(stacksize % 2 == 1){
			addTo(with,1);
		}
		addTo(with,stacksize/2);
	}
	public int getStacksize(){
		return stacksize;
	}
	public Item getItem(){
		return item;
	}
}
