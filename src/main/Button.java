package main;

import core.Input;
import events.Event;
import events.EventDispatcher;
import events.EventType;


public abstract class Button {
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	private boolean wasSelected;
	protected EventType eventtype;
	private EventDispatcher dispatch;

	public Button(double x, double y, double width,double height,EventType event, EventDispatcher dispatcher){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.wasSelected = false;
		this.eventtype = event;
		this.dispatch = dispatcher;
	}
	public abstract void draw();
	
	public void update(){
		boolean mouseDown = Input.isLeftMouseDown();
		int mousex = Input.getMouseX();
		int mousey = Input.getMouseY();
		if((mousex>x)&&(mousex<x+width)&&(mousey>y)&&(mousey<y+height)){
			if((!mouseDown)&&(wasSelected)){
				dispatch.dispatchEvent(new Event<Object>(eventtype));
			}
		}
		wasSelected = mouseDown;
	}
}
