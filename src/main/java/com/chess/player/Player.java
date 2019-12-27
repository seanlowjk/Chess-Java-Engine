package com.chess.player;

import java.util.ArrayList;
import java.util.List;

import com.chess.board.moves.Move;
import com.chess.board.moves.MoveStatus;
import com.chess.board.moves.MoveTransition;
import com.chess.board.Board;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.King;
import com.chess.pieces.properties.Alliance;
import com.chess.pieces.properties.Coordinate;


public abstract class Player {

    protected Board board;
    protected King king;
    protected List<Move> legalMoves;

    protected boolean isInCheck;

    protected Player(Board board, List<Move> legalMoves, List<Move> opponentMoves) {
        this.board = board;
        this.king = makeKing();
        this.legalMoves = calculateAllLegalMoves(legalMoves, opponentMoves);

        this.isInCheck = !Player.calculateAttacksOnTile(this.king.getPosition(), opponentMoves).isEmpty();
    }

    protected List<Move> calculateAllLegalMoves(List<Move> legalMoves, List<Move> opponentMoves) {
        List<Move> allLegalMoves = new ArrayList<>();
        allLegalMoves.addAll(getKingCastles(legalMoves, opponentMoves));
        allLegalMoves.addAll(legalMoves);
        return allLegalMoves;
    }

    protected King makeKing() {
        for (ChessPiece piece : getActivePieces()) {
            if (piece instanceof King) {
                return (King) piece;
            }
        }

        return null;
    }

    public King getKing() {
        return this.king;
    }

    public List<Move> getLegalMoves() {
        return this.legalMoves;
    }

    protected static List<Move> calculateAttacksOnTile(Coordinate coordinate, List<Move> opponentMoves) {
        List<Move> attackMoves = new ArrayList<>();

        for (Move move : opponentMoves) {
            if (coordinate.equals(move.getDestination())) {
                attackMoves.add(move);
            }
        }

        return attackMoves;
    }

    public boolean isLegalMove(Move move) {
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public boolean isInCheckmate() {
        return isInCheck() && !hasEscapeMoves();
    }

    public boolean isInStalemate() {
        return !isInCheck() && !hasEscapeMoves();
    }

    protected boolean hasEscapeMoves() {
        for (Move move : legalMoves) {
            MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                return true;
            }
        }

        return false;
    }

    public MoveTransition makeMove(Move move) {

        if (!isLegalMove(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL);
        }

        Board board = move.execute();

        List<Move> kingAttacks = Player.calculateAttacksOnTile(
                board.getCurrentPlayer().getOpponent().getKing().getPosition(),
                board.getCurrentPlayer().getLegalMoves());

        if (!kingAttacks.isEmpty()) {
            return new MoveTransition(this.board, move, MoveStatus.CHECKED);
        }

        return new MoveTransition(board, move, MoveStatus.DONE);
    }

    public abstract List<ChessPiece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    public abstract List<Move> getKingCastles(List<Move> legalMoves, List<Move> opponentMoves);
}
