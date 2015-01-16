package world;

import util.SimplexNoise;

//TODO Pass in world dimensions in generate()

public class WorldGenerator {

	public static int[][] generate(int worldsize){
		int[][] map = new int[worldsize][worldsize];
		for(int x=0;x<worldsize;x++){
			for(int y=0;y<worldsize;y++){
				double typeValue = Math.abs(SimplexNoise.noise((double) x / 45, (double) y / 45, 0));
				map[x][y]= (int) (typeValue * 4) + 1;
			}
		}
		return map;
	}
}


