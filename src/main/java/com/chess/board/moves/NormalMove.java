package com.chess.board.moves;

import com.chess.board.Board;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.properties.Coordinate;

public class NormalMove extends Move {
    public NormalMove(Board board, ChessPiece chessPiece, Coordinate destination) {
        super(board, chessPiece, destination);
    }
}
