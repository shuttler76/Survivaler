package util;

import events.EventDispatcher;
import events.EventType;
import pixeltoy.PixelToy;
import texture.Texture;
import texture.TextureLoader;

public class TextButton extends Button {

	private String string;
	private Texture buttonpic;
	private double offsety;
	private double offsetx;

	public TextButton(double x, double y, double width, double height,String string, EventType event, EventDispatcher eventDispatcher) {
		super(x, y, width, height, event, eventDispatcher);
		this.string = string;
        try {
            this.buttonpic = TextureLoader.loadTextureFromFile("res/button.png", true, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.offsetx = 2;
		this.offsety = 5;
	}
	
	@Override
	public void draw(){
		buttonpic.draw(x+(width/2), y+(height/2), width, height);
		PixelToy.graphics.useColour(0, 0, 0);
		PixelToy.graphics.drawString(x+offsetx, y+offsety, string);
	}
	public void setButtonTexture(Texture button){
		buttonpic = button;
	}
	public void setTextOffset(double x,double y){
		offsetx= x;
		offsety= y;
	}
}
