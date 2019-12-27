package com.chess.board.moves;

import com.chess.pieces.ChessPiece;
import com.chess.pieces.Rook;
import com.chess.board.Board;
import com.chess.board.GameBuilder;
import com.chess.pieces.properties.Coordinate;

public abstract class CastleMove extends Move {

    protected Rook rook;
    protected Coordinate rookStart;
    protected Coordinate rookDestination;

    public CastleMove(Board board, ChessPiece chessPiece, Coordinate destination,
                      Rook rook, Coordinate rookStart, Coordinate rookDestination) {
        super(board, chessPiece, destination);
        this.rook = rook;
        this.rookStart = rookStart;
        this.rookDestination = rookDestination;
    }

    public Rook getRook() {
        return this.rook;
    }

    @Override
    public boolean isCastleMove() {
        return true;
    }

    @Override
    public Board execute() {
        GameBuilder builder = new GameBuilder();
        for (ChessPiece piece : board.getCurrentPlayer().getActivePieces()) {
            if (!this.chessPiece.equals(piece) && !this.rook.equals(piece)) {
                builder.setPiece(piece);
            }
        }

        for (ChessPiece piece : board.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }

        builder.setPiece(this.chessPiece.movePiece(this));
        builder.setPiece(new Rook(this.rook.getPosition(), this.rook.getAlliance(), false));
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());

        return builder.build();
    }

}
