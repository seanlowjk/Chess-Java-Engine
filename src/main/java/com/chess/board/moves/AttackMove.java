package com.chess.board.moves;

import com.chess.pieces.ChessPiece;
import com.chess.board.Board;
import com.chess.pieces.properties.Coordinate;

public class AttackMove extends Move {
    protected ChessPiece attackedPiece;

    public AttackMove(Board board, ChessPiece chessPiece, Coordinate destination, ChessPiece attackedPiece) {
        super(board, chessPiece, destination);
        this.attackedPiece = attackedPiece;
    }

    @Override
    public boolean isAttackMove() {
        return true;
    }

    @Override
    public ChessPiece getAttackedChessPiece() {
        return this.attackedPiece;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + this.attackedPiece.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(this instanceof AttackMove)) {
            return false;
        }

        return super.equals(obj) && this.getAttackedChessPiece().equals(((AttackMove) obj).getAttackedChessPiece());
    }
}
