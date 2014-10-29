package inventory;

import init.Items;

public class PlayerInventory extends Inventory {
	public PlayerInventory(){
		super();
		addSlot(400,400).setItemStack(new ItemStack(Items.wood, 32));
	}
}
