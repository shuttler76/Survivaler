package inventory;

import org.lwjgl.input.Mouse;

import events.Event;
import events.EventHandler;
import events.EventType;


public class InventoryManager implements EventHandler{

	private PlayerInventory playerinventory;
	private Slot playerslot;

	public InventoryManager(){
		this.playerinventory = new PlayerInventory();
		playerslot = new Slot();
	}
	
	public void update(){
		playerslot.setPosition(Mouse.getX(), Mouse.getY());
	}
	public void draw(){
		playerinventory.draw();
		ItemStack playerstack = playerslot.getItemstack();
		if(playerstack != null){
			playerstack.draw(playerslot.getX(), playerslot.getY());
		}
	}

	@Override
	public void handleEvent(Event<?> event) {
		
			if(event.eventType == EventType.INVENTORY_BUTTON_CLICKED){
				playerinventory.toggle();
			}
			if(event.eventType == EventType.LEFT_MOUSE_RELEASED){
				Slot selectedSlot = playerinventory.getClicked();
				if(selectedSlot != null){
					playerslot.exchange(selectedSlot);
				}
			}
	}
}
