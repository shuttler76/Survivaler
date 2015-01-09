package inventory;

import init.Items;

public class PlayerInventory extends Inventory {
	public PlayerInventory(){
		super();
		addSlot(400,400).setItemStack(new ItemStack(Items.wood, 32));
		addSlot(464,400).setItemStack(new ItemStack(Items.wood, 32));
		addSlot(464,464).setItemStack(new ItemStack(Items.wood, 32));
		addSlot(400,464).setItemStack(new ItemStack(Items.wood, 32));
	}
}
