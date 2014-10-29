package main;

import java.util.Comparator;

public class SpriteComparator implements Comparator<Sprite> {

	@Override
	public int compare(Sprite o1, Sprite o2) {
		if(o1.sumPosititon()>o2.sumPosititon()){
			return 1;
		} else if (o1.sumPosititon() < o2.sumPosititon()) {
			return -1;
		} else {
			return 0;
		}
	}

}
