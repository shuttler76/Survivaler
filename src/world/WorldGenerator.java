package world;

import util.SimplexNoise;

//TODO Pass in world dimensions in generate()

public class WorldGenerator {
	private static int worldwidth=64;
	private static int worldheight=64;

	public static int[][] generate(){
		int[][] map = new int[worldwidth][worldheight];
		for(int x=0;x<worldwidth;x++){
			for(int y=0;y<worldheight;y++){
				double typeValue = Math.abs(SimplexNoise.noise((double) x / 45, (double) y / 45, 0));
				map[x][y]= (int) (typeValue * 4);
			}
		}
		return map;
	}
}


