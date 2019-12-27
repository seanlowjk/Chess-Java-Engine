package com.chess.board.moves;

import com.chess.board.Board;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.Rook;
import com.chess.pieces.properties.Coordinate;

public class KingSideCastleMove extends CastleMove {
    public KingSideCastleMove(Board board, ChessPiece chessPiece, Coordinate destination,
                              Rook rook, Coordinate rookStart, Coordinate rookDestination) {
        super(board, chessPiece, destination,
                 rook, rookStart, rookDestination);
    }

    @Override
    public String toString() {
        return "0-0";
    }
}
