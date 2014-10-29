package inventory;

import java.util.ArrayList;

public class Inventory {
	public ArrayList<Slot> inventory;
	private boolean isActive;
	
	public Inventory(){
		inventory = new ArrayList<Slot>();
		isActive = false;
	}
	
	public void update() {
		if (!isActive){
			return;
		}

		for (Slot slot : inventory) {
			if (slot.getItemStack() != null && slot.getItemStack().getStacksize() <= 0) {
				slot.setItemStack(null);
			}
		}
	}
	
	public void draw(){
		if (!isActive){
			return;
		}
		for(Slot slot : inventory){
			slot.draw();
		}
	}
	public void toggle(){
		isActive = !isActive;
	}
	protected Slot addSlot(double x, double y){
		Slot s = new Slot(x,y);
		inventory.add(s);
		return s;
	}
	public Slot getClicked(){
		if (!isActive){
			return null;
		}
		
		for(Slot slot : inventory){
			if(slot.isClicked()){
				return slot;
			}
		}
		return null;
	}
}
