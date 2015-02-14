package world;

import util.SimplexNoise;

//TODO Pass in world dimensions in generate()

public class WorldGenerator {

	public static int[][] generate(int worldsize){
		int[][] map = new int[worldsize][worldsize];
		for(int x=0;x<worldsize;x++){
			for(int y=0;y<worldsize;y++){
				double typeValue = Math.abs(SimplexNoise.noise((double) x / 250, (double) y / 250, 0));
				map[x][y]= (int) (typeValue * 5);
			}
		}
		return map;
	}
}


