package com.chess.pieces.properties;

public class Directions {
    public static final Coordinate SOUTH = new Coordinate(-1, 0);
    public static final Coordinate SOUTH_EAST = new Coordinate(-1, 1);
    public static final Coordinate EAST = new Coordinate(0, 1);
    public static final Coordinate NORTH_EAST = new Coordinate(1, 1);
    public static final Coordinate NORTH = new Coordinate(1, 0);
    public static final Coordinate NORTH_WEST = new Coordinate(1, -1);
    public static final Coordinate WEST = new Coordinate(0, -1);
    public static final Coordinate SOUTH_WEST = new Coordinate(-1, -1);

    public static final Coordinate TOP_LEFT = new Coordinate(-1, 2);
    public static final Coordinate UPPER_LEFT = new Coordinate(-2, 1);
    public static final Coordinate LOWER_LEFT = new Coordinate(-2, -1);
    public static final Coordinate BOTTOM_LEFT = new Coordinate(-1, -2);
    public static final Coordinate TOP_RIGHT = new Coordinate(1, 2);
    public static final Coordinate UPPER_RIGHT = new Coordinate(2, 1);
    public static final Coordinate LOWER_RIGHT = new Coordinate(2, -1);
    public static final Coordinate BOTTOM_RIGHT = new Coordinate(1, -2);

    public static final Coordinate BLACK_PAWN_MOVE = Coordinate.add(NORTH, NORTH);
    public static final Coordinate WHITE_PAWN_MOVE = Coordinate.add(SOUTH, SOUTH);
}
