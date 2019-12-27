package com.chess.board.tiles;

import com.chess.pieces.ChessPiece;
import com.chess.pieces.properties.Coordinate;

public class OccupiedTile implements Tile {
    protected Coordinate coordinate;

    protected ChessPiece chessPiece;

    public OccupiedTile(Coordinate coordinate, ChessPiece chessPiece) {
        this.coordinate = coordinate;
        this.chessPiece = chessPiece;
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public ChessPiece getPiece() {
        return chessPiece;
    }

    @Override
    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}
