package com.chess.board.tiles;


import com.chess.pieces.ChessPiece;
import com.chess.pieces.properties.Coordinate;

public class EmptyTile implements Tile {
    protected Coordinate coordinate;

    public EmptyTile(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public ChessPiece getPiece() {
        return null;
    }

    @Override
    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}
