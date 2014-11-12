package main;

import entity.Man;
import events.Event;
import events.EventDispatcher;
import events.EventHandler;
import events.EventType;
import util.Button;
import world.World;

public class RestartHandler implements EventHandler {
	
	private Man man;
	private GUI gui;
	private Button button;
	private PlayerDeath playerdeath;
	private World world;
	private EventDispatcher eventDispatcher;

	public RestartHandler(Man man,GUI gui,Button button, PlayerDeath playerdeath, World world, EventDispatcher event) {
		this.man = man;
		this.gui = gui;
		this.button = button;
		this.playerdeath = playerdeath;
		this.world = world;
		eventDispatcher = event;
		eventDispatcher.addEventListener(this, EventType.RESTART);
	}

	@Override
	public void handleEvent(Event<?> event) {
		world.buildCubeMap();
		man.reset();
		gui.removeButton(button);
		playerdeath.reset();
	}
}
