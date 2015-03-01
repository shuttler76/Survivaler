package world;

import util.SimplexNoise;

public class WorldGenerator {

	public static int[][] generate(int worldsize){
		int[][] map = new int[worldsize][worldsize];

        float[][] totalNoise = new float[worldsize][worldsize];
        int octaves = 3;
        float roughness = 0.2f;
        float layerFrequency = 0.005f;
        float layerWeight = 1;
        float weightSum = 0;

        for (int octave = 0; octave < octaves; octave++) {
            for (int x = 0; x < worldsize; x++) {
                for (int y = 0; y < worldsize; y++) {
                    totalNoise[x][y] += (float)SimplexNoise.noise((double) x * layerFrequency, (double) y * layerFrequency, layerWeight);
                }
            }

            layerFrequency *= 2;
            weightSum += layerWeight;
            layerWeight *= roughness;
        }

        for (int x = 0; x < worldsize; x++) {
            for (int y = 0; y < worldsize; y++) {

                if ((totalNoise[x][y] < -0.4)) {
                    map[x][y] = CubeType.DEEPWATER;
                }
                else if (totalNoise[x][y] < 0) {
                    map[x][y] = CubeType.WATER;
                }
                else if ((totalNoise[x][y] < 0.1)) {
                    map[x][y] = CubeType.SAND;
                }
                else if ((totalNoise[x][y] < 1)) {
                    map[x][y] = CubeType.GRASS;
                }
                else {
                    map[x][y] = CubeType.STONE;
                }
            }
        }
		return map;
	}
}


