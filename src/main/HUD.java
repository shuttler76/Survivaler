package main;

import events.EventType;
import inventory.InventoryManager;
import texture.Texture;
import texture.TextureLoader;
import util.TextButton;

public class HUD {
	private TextButton textbutton;
	private Texture hudbutton;
	private InventoryManager inventorymanager;

	public HUD(Game game) throws Exception{
        try {
            hudbutton = TextureLoader.loadTextureFromFile("res/unselectedgui.png", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.textbutton= new TextButton(20, 50, 64, 64,"I", EventType.INVENTORY_BUTTON_CLICKED, game.eventdispatcher);
		inventorymanager = new InventoryManager();
		game.eventdispatcher.addEventListener(inventorymanager, EventType.INVENTORY_BUTTON_CLICKED);
		game.eventdispatcher.addEventListener(inventorymanager, EventType.LEFT_MOUSE_RELEASED);
		game.eventdispatcher.addEventListener(inventorymanager, EventType.RIGHT_MOUSE_RELEASED);
		textbutton.setButtonTexture(hudbutton);
		textbutton.setTextOffset(30, 25);
		game.gui.addButton(textbutton);		
	}
	public void draw(){
		inventorymanager.draw();
	}
	public void update(){
		inventorymanager.update();
	}
}
