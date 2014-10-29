package main;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import events.Event;
import events.EventDispatcher;
import events.EventType;
import pixeltoy.PixelToy;

public class Game {
	public final SpriteList spritelist;
	public final StaticSpriteList staticSL;
	public final Cubes worldcubes;
	public GUI gui;
	public Man man;
	public Shadow shadow;
	private final ArrayList<Update> gameObjects = new ArrayList<Update>();
	public PlayerDeath playerdeath;
	private HUD HUD;
	public EventDispatcher eventdispatcher;
	public float zoom = 0.5f;
	public Game(){
		PixelToy.drawing.drawString(0,0,"");
		this.staticSL= new StaticSpriteList();
		this.eventdispatcher = new EventDispatcher();
		this.spritelist = new SpriteList();
		this.worldcubes = new Cubes(staticSL);
		this.man = new Man(worldcubes);
		this.shadow = new Shadow(this.man, worldcubes);
		gameObjects.add(shadow);
		spritelist.add(man);
		spritelist.add(shadow);
		this.gui = new GUI();
		this.playerdeath = new PlayerDeath(this);
		this.HUD = new HUD(this);
		
	}
	public void update(){
		
		while (Mouse.next()) {
			int mouse = Mouse.getEventButton();
			boolean state = Mouse.getEventButtonState();
			
			if (mouse == 0) {
				if (!state) 
				{
					eventdispatcher.dispatchEvent(new Event(EventType.LEFT_MOUSE_RELEASED));
				}
			}
		}
		while (Keyboard.next()){
			int key = Keyboard.getEventKey();
			boolean state = Keyboard.getEventKeyState();
			
			if (key == Keyboard.KEY_E) {
				if(!state) {
					eventdispatcher.dispatchEvent(new Event(EventType.INVENTORY_BUTTON_CLICKED));
				}
			}
		}
		man.update();
		HUD.update(); 			
		playerdeath.update();
		gui.update();
		
		for(Update update: gameObjects){
			update.update();
		}
		
		
		zoom+=Mouse.getDWheel()/1000f;
		GL11.glPushMatrix();
		GL11.glTranslated(Display.getWidth()/2,Display.getHeight()/2,0);
		GL11.glScalef(zoom,zoom,1f);
		GL11.glTranslated(-Display.getWidth()/2,-Display.getHeight()/2,0);
		staticSL.draw();
		spritelist.draw();
		GL11.glPopMatrix();
		HUD.draw();
		gui.draw();
	}
}
