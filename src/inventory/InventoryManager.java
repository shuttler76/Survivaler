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
		if((playerslot.getItemstack() != null)&&(playerslot.getItemstack().getStacksize() <= 0)){
			playerslot.clear();
		}
		playerinventory.update();
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
					if((selectedSlot.getItemstack() != null)&&(playerslot.getItemstack() != null)&&(selectedSlot.getItemstack().getItem() == playerslot.getItemstack().getItem())){
						playerslot.getItemstack().transfer(selectedSlot.getItemstack());
					}else{
						playerslot.exchange(selectedSlot);
					}
				}
			}
			if(event.eventType == EventType.RIGHT_MOUSE_RELEASED){
				Slot selectedSlot = playerinventory.getClicked();
				if(selectedSlot != null){
					if(playerslot.getItemstack() == null){
						if(selectedSlot.getItemstack() != null){
							selectedSlot.split(playerslot);
						}
					} else {
						playerslot.addTo(selectedSlot, 1);
					}
				}
			}
	}
}
