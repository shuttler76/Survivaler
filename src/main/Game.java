package main;

import java.util.ArrayList;

import entity.Man;
import graphics.Drawable;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import events.Event;
import events.EventDispatcher;
import events.EventType;
import pixeltoy.PixelToy;
import world.World;

public class Game {
	public final ArrayList<Drawable> drawables;
	public final World world;
	public GUI gui;
	public Man man;
	private final ArrayList<Updatable> gameObjects = new ArrayList<Updatable>();
	public PlayerDeath playerdeath;
	private HUD HUD;
	public EventDispatcher eventdispatcher;
	public float zoom = 0.5f;
	private Camera camera;
	public Game() throws Exception{

		this.drawables= new ArrayList<Drawable>();
		this.eventdispatcher = new EventDispatcher();
		this.world = new World();
		this.man = new Man(world);
		this.gui = new GUI();
		this.playerdeath = new PlayerDeath(this);
		this.HUD = new HUD(this);
		this.camera = new Camera();
		
		

        drawables.add(world);
        drawables.add(man);
	}
	public void update(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		while (Mouse.next()) {
			int mouse = Mouse.getEventButton();
			boolean state = Mouse.getEventButtonState();
			
			if (mouse == 0) {
				if (!state) 
				{
					eventdispatcher.dispatchEvent(new Event(EventType.LEFT_MOUSE_RELEASED));
				}
			}
			if (mouse == 1) {
				if (!state) 
				{
					eventdispatcher.dispatchEvent(new Event(EventType.RIGHT_MOUSE_RELEASED));
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
		
		for(Updatable updatable : gameObjects){
			updatable.update();
		}

		zoom+=Mouse.getDWheel()/1000f;
		if(zoom>2){
			zoom = 2;
		}
		if(zoom<0.1f){
			zoom = 0.1f;
		}
		
		GL11.glPushMatrix();

		double[] manPos = world.convertWorldToIsometric(man.x, man.y, man.z);
		GL11.glTranslated(Display.getWidth()/2,Display.getHeight()/2,0);
		GL11.glScalef(zoom,zoom,1f);
		GL11.glTranslated(-manPos[0], -manPos[1], 0);
		for (Drawable sprite : drawables) {
            sprite.draw();
        }
		GL11.glPopMatrix();
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		HUD.draw();
		gui.draw();
	}
}
