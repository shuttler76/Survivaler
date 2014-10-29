package main;

public class WorldGenerator {
	private static int worldwidth=64;
	private static int worldheight=64;

	public static Cube[][] generate(){
		Cube[][] map = new Cube[worldwidth][worldheight];
		for(int x=0;x<worldwidth;x++){
			for(int y=0;y<worldheight;y++){
				double typeValue =SimplexNoise.noise((double)x/45, (double)y/45, 0);
				CubeType type = getCubeType(typeValue);
				map[x][y]=new Cube(x,y,type);
			}
		}
		return map;
	}

	private static CubeType getCubeType(double typeValue){
		if(typeValue<=-0.5){
			return CubeType.STONE;
		}
		if((typeValue>-0.5)&&(typeValue<=0.1)){
			return CubeType.SAND;
		}
		if((typeValue>0.1)&&(typeValue<=1)){
			return CubeType.GRASS;
		}
		return CubeType.NEUTRAL;
	}
}


