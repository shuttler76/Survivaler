package world;

public final class CubeType {
    public static final int DEEPWATER = 0;
    public static final int WATER = 1;
    public static final int SAND = 2;
    public final static int GRASS = 3;
    public final static int STONE = 4;

    public static final int NUMBERCUBETYPES = 5;

    public static boolean isCubeType( int type ) {
        return type >= 0 && type < NUMBERCUBETYPES;
    }
}
