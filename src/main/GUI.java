package main;

import java.util.ArrayList;

public class GUI {
	private ArrayList<Button> listofbuttons;
	public GUI(){
		this.listofbuttons = new ArrayList<Button>();
		}
	public void addButton(Button buttons){
		this.listofbuttons.add(buttons);
	}
	public void removeButton(Button buttons){
		this.listofbuttons.remove(buttons);
		
	}
	public void update(){
		for(int i = listofbuttons.size()-1;i >=0; i--){
			listofbuttons.get(i).update();
		}
	}
	public void draw(){
		for(Button button : listofbuttons){
			button.draw();
		}
	}
}
