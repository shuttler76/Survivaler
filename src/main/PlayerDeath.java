package main;

import events.EventType;
import util.TextButton;

public class PlayerDeath {
	private Game game;
	private TextButton button;
	private boolean hasDied;

	public PlayerDeath(Game game) throws Exception{
		this.game = game;
		this.button = new TextButton(100,50,75,20,"Restart?",EventType.RESTART,game.eventdispatcher);
		//button.addHandlers(new RestartHandler(game.man,game.gui,button, this ,game.world));
		new RestartHandler(game.man,game.gui,button, this ,game.world, game.eventdispatcher);
	}
	public void update(){
		if((game.man.isDead())&&(!hasDied)){
			game.gui.addButton(button);
			hasDied = true;
		}
	}
	public void reset(){
		hasDied = false;
	}
}
