package com.chess.pieces;

import java.util.List;

import com.chess.board.moves.Move;
import com.chess.pieces.properties.Alliance;
import com.chess.board.Board;
import com.chess.pieces.properties.Coordinate;


public abstract class ChessPiece {
    protected final Coordinate position;
    protected final Alliance alliance;
    protected boolean isFirstMove;

    public static final String EMPTY_PIECE_STRING = "-";

    public ChessPiece(Coordinate position, Alliance alliance, boolean isFirstMove) {
        this.position = position;
        this.alliance = alliance;
        this.isFirstMove = isFirstMove;
    }

    public abstract List<Move> getLegalMoves(Board board);

    public abstract ChessPiece movePiece(Move move);

    public Coordinate getPosition() {
        return this.position;
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public boolean isWhitePiece() {
        return this.alliance == Alliance.WHITE;
    }

    public boolean isKing() {
        return this instanceof  King;
    }

    public boolean isQueen() {
        return this instanceof Queen;
    }

    public boolean isRook() {
        return this instanceof Rook;
    }

    public boolean isKnight() {
        return this instanceof Knight;
    }

    public boolean isBishop() {
        return this instanceof Bishop;
    }

    public boolean isPawn() {
        return this instanceof  Pawn;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public int getPieceValue() {
        if (isPawn()) {
            return 1;
        } else if (isKnight()) {
            return 10;
        } else if (isBishop()) {
            return 20;
        } else if (isRook()) {
            return 30;
        } else if (isQueen()) {
            return 100;
        } else {
            return 1000;
        }
    }

    @Override
    public String toString() {
        String string;
        if (isBishop()) {
            string = "B";
        } else if (isKing()) {
            string = "K";
        } else if (isKnight()) {
            string = "N";
        } else if (isPawn()) {
            string = "P";
        } else if (isQueen()) {
            string = "Q";
        } else if (isRook()) {
            string = "R";
        } else {
            string = "X";
        }

        if (isWhitePiece()) {
            return string;
        } else {
            return string.toLowerCase();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ChessPiece) {
            ChessPiece chessPiece = (ChessPiece) obj;

            return this.toString().equals(chessPiece.toString())
                    && this.getAlliance() == chessPiece.getAlliance()
                    && this.getPosition().equals(chessPiece.getPosition())
                    && this.isFirstMove() == chessPiece.isFirstMove();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int primeNumber = 31;
        int result = this.toString().hashCode();

        result = primeNumber * result + alliance.hashCode();
        result = primeNumber * result + position.hashCode();
        result = primeNumber * result + (isFirstMove ? 1 : 0);
        return result;
    }
}
