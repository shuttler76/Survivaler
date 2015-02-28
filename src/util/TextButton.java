package util;

import events.EventDispatcher;
import events.EventType;
import graphics.Drawer;
import graphics.Font;
import main.Game;
import pixeltoy.PixelToy;
import texture.Texture;
import texture.TextureLoader;

public class TextButton extends Button {

	private String string;
	private Texture buttonpic;
	private double offsety;
	private double offsetx;


	public TextButton(double x, double y, double width, double height,String string, EventType event, EventDispatcher eventDispatcher) throws Exception {
		super(x, y, width, height, event, eventDispatcher);
		this.string = string;
        try {
            this.buttonpic = TextureLoader.loadTextureFromFile("res/button.png", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.offsetx = 2;
		this.offsety = 5;
	}
	
	@Override
	public void draw(){
		Drawer.drawTexture(buttonpic, x+(width/2), y+(height/2), 0, width, height);
		Drawer.drawString(Game.font, string, x + offsetx, y + offsety, 20);
	}
	public void setButtonTexture(Texture button){
		buttonpic = button;
	}
	public void setTextOffset(double x,double y){
		offsetx= x;
		offsety= y;
	}
}
