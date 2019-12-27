package com.chess.board.moves;

import com.chess.pieces.properties.Coordinate;

public class NullMove extends Move {
    public NullMove() {
        super(null, null, new Coordinate(-1, -1));
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(-1, -1);
    }
}
