package inventory;

import core.Input;
import graphics.Drawer;
import org.lwjgl.input.Mouse;
import texture.Texture;
import texture.TextureLoader;

public class Slot {
	private double x;
	private double y;
	private ItemStack itemstack = null;
	private static Texture invframe;
    static {
        try {
            invframe = TextureLoader.loadTextureFromFile("res/invbox.png", false, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void draw(){

		Drawer.drawTexture(invframe, (float)x, (float)y, -100, ItemStack.itemsize, ItemStack.itemsize);
		if(itemstack != null)
			itemstack.draw(x, y);
	}
	public void setItemStack(ItemStack itemstack){
		this.itemstack=itemstack;
	}
	public Slot(double x, double y){
		this.x=x;
		this.y=y;
	}
	public Slot(){
		this(0,0);
	}
	public ItemStack getItemStack(){
		return itemstack;
	}
	public void exchange(Slot with){
		ItemStack temp=with.itemstack;
		with.itemstack=itemstack;
		itemstack = temp;
	}
	public void split(Slot with){
		prepareForInteraction(with);
		itemstack.split(with.itemstack);
	}
	public void addTo(Slot to, int amount){
		prepareForInteraction(to);
		itemstack.addTo(to.itemstack, amount);
	}
	private void prepareForInteraction(Slot slot) {
		if ((slot.itemstack == null)&&(itemstack != null)) {
			slot.itemstack = new ItemStack(itemstack.getItem(), 0);
		}
	}
	public boolean isClicked(){
		if (Math.abs(Mouse.getX() - x) < ItemStack.maxStacksize/2) {
			if (Math.abs(Mouse.getY() - y) < ItemStack.maxStacksize/2) {
				return true;
			}
		}
		return false;
	}
	public void setPosition(double x, double y){
		this.x=x;
		this.y=y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public ItemStack getItemstack() {
		return itemstack;
	}
	public void clear(){
		itemstack = null;
	}
}
