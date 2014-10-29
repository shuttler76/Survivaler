package main;

import java.util.Arrays;

public class Cubes{
	private Cube[][] cubes;
	private StaticSpriteList spritelist;
	public final int worldsize;
	private boolean isWorldGenerated;

	public Cubes(StaticSpriteList spritelist) {
		worldsize = 64;
		this.spritelist = spritelist;
		buildCubeMap();
	}

	public void buildCubeMap() {
		SimplexNoise.randomizeSeed();
		if(isWorldGenerated){
			for(int row = 0;row < cubes.length; row++){
				for(int column = 0; column < cubes[0].length; column++){
					spritelist.remove(cubes[row][column]);
				}
			}
		}
		this.cubes = WorldGenerator.generate();
		for(int row = 0;row < cubes.length; row++){
			for(int column = 0; column < cubes[0].length; column++){
					spritelist.add(cubes[row][column]);
			}
		}
		isWorldGenerated=true;
	}
	public boolean isCubeAt(double manx, double many) {
		if((manx>0)&&(manx<worldsize)&&(many>0)&&(many<worldsize)){
			return true;//cubes[(int)manx][(int)many];
		} else {
			return false;
		}
	}
}
