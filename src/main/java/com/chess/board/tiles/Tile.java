package com.chess.board.tiles;

import com.chess.pieces.ChessPiece;
import com.chess.pieces.properties.Coordinate;

public interface Tile {

    public static Tile createTile(Coordinate coordinate, ChessPiece chessPiece) {
        if (chessPiece == null) {
            return new EmptyTile(coordinate);
        } else {
            return new OccupiedTile(coordinate, chessPiece);
        }
    }

    public boolean isTileOccupied();

    public ChessPiece getPiece();

    public Coordinate getCoordinate();
}
