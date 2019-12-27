package com.chess.board.moves;
import com.chess.board.Board;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.properties.Coordinate;

public class PawnAttackMove extends AttackMove {
    public PawnAttackMove(Board board, ChessPiece chessPiece, Coordinate destination, ChessPiece attackedPiece) {
        super(board, chessPiece, destination, attackedPiece);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (this instanceof PawnAttackMove) {
            return super.equals(obj);
        } else {
            return false;
        }
    }
}
