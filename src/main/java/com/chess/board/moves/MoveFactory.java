package com.chess.board.moves;

import com.chess.board.Board;
import com.chess.pieces.properties.Coordinate;

public class MoveFactory {

    public static final Move NULL_MOVE = new NullMove();

    public static Move createMove(Board board, Coordinate coordinate, Coordinate destination) {
        for (Move move : board.getAllLegalMoves()) {
            if (move.getCoordinate().equals(coordinate) &&
                move.getDestination().equals(destination)) {
                return move;
            }
        }

        return NULL_MOVE;
    }
}
