package main;

import java.util.ArrayList;
import java.util.Collections;

public class SpriteList {
	private final ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	public void remove(Sprite sprite) {
		sprites.remove(sprite);

	}
	public void add(Sprite sprite) {
		sprites.add(sprite);
	}
	public void draw() {
		Collections.sort(sprites, new SpriteComparator());
		for(Sprite sprite : sprites){
			sprite.draw();
		}
	}
}
