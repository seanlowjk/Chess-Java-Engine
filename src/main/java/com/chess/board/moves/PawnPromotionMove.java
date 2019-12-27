package com.chess.board.moves;

import com.chess.board.Board;
import com.chess.board.GameBuilder;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.Pawn;

public class PawnPromotionMove extends PawnMove {
    final Move decoratedMove;
    final Pawn promotedPawn;

    public PawnPromotionMove(final Move decoratedMove) {
        super(decoratedMove.getBoard(), decoratedMove.getChessPiece(), decoratedMove.getDestination());
        this.decoratedMove = decoratedMove;
        this.promotedPawn = (Pawn) decoratedMove.getChessPiece();
    }

    @Override
    public int hashCode() {
        return decoratedMove.hashCode() + (31 * promotedPawn.hashCode());
    }

    @Override
    public Board execute() {
        final Board pawnMovedBoard = this.decoratedMove.execute();
        final GameBuilder builder = new GameBuilder();
        for (final ChessPiece piece : pawnMovedBoard.getCurrentPlayer().getActivePieces()) {
            if (!this.promotedPawn.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        for (final ChessPiece piece : pawnMovedBoard.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }

        ChessPiece queen = this.promotedPawn.getPromotedPawn();

        builder.setPiece(queen.movePiece(this));
        builder.setMoveMaker(pawnMovedBoard.getCurrentPlayer().getAlliance());
        return builder.build();
    }

    @Override
    public boolean isAttackMove() {
        return this.decoratedMove.isAttackMove();
    }

    @Override
    public ChessPiece getAttackedChessPiece() {
        return this.decoratedMove.getAttackedChessPiece();
    }
}
