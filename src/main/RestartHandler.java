package main;

import events.Event;
import events.EventDispatcher;
import events.EventHandler;
import events.EventType;

public class RestartHandler implements EventHandler {
	
	private Man man;
	private GUI gui;
	private Button button;
	private PlayerDeath playerdeath;
	private Cubes cubes;
	private EventDispatcher eventDispatcher;

	public RestartHandler(Man man,GUI gui,Button button, PlayerDeath playerdeath, Cubes cubes, EventDispatcher event) {
		this.man = man;
		this.gui = gui;
		this.button = button;
		this.playerdeath = playerdeath;
		this.cubes = cubes;
		eventDispatcher = event;
		eventDispatcher.addEventListener(this, EventType.RESTART);
	}

	@Override
	public void handleEvent(Event<?> event) {
		cubes.buildCubeMap();
		man.reset();
		gui.removeButton(button);
		playerdeath.reset();
	}
}
