package com.chess.board.moves;

import com.chess.board.Board;
import com.chess.board.GameBuilder;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.properties.Coordinate;

public abstract class Move {

    protected Board board;
    protected ChessPiece chessPiece;
    protected Coordinate destination;

    protected Move(Board board, ChessPiece chessPiece, Coordinate destination) {
        this.board = board;
        this.chessPiece = chessPiece;
        this.destination = destination;
    }

    public Board getBoard() {
        return this.board;
    }

    public Coordinate getCoordinate() {
        return this.chessPiece.getPosition();
    }

    public Coordinate getDestination() {
        return this.destination;
    }

    public ChessPiece getChessPiece() {
        return this.chessPiece;
    }

    public boolean isAttackMove() {
        return this instanceof AttackMove
                || this instanceof PawnAttackMove;
    }

    public boolean isCastleMove() {
        return this instanceof KingSideCastleMove
                || this instanceof QueenSideCastleMove;
    }

    public ChessPiece getAttackedChessPiece() {
        return null;
    }

    public Board execute() {

        GameBuilder builder = new GameBuilder();
        for (ChessPiece piece : board.getCurrentPlayer().getActivePieces()) {
            if (!this.chessPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }

        for (ChessPiece piece : board.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }

        builder.setPiece(this.chessPiece.movePiece(this));
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());

        return builder.build();
    }

    @Override
    public int hashCode() {
        final int primeNumber = 31;
        int result = 1;

        result = primeNumber * result + getCoordinate().toInteger();
        result = primeNumber * result + getDestination().toInteger();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (this instanceof Move) {
            Move move = (Move) obj;
            return this.getDestination().equals(move.getDestination()) &&
                    this.getChessPiece().equals(move.getChessPiece());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.chessPiece.toString() + this.getDestination();
    }
}
