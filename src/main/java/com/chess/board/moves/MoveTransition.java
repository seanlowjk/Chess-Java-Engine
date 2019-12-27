package com.chess.board.moves;

import com.chess.board.Board;

public class MoveTransition {

    protected Board board;
    protected Move move;
    protected MoveStatus moveStatus;

    public MoveTransition(Board board, Move move, MoveStatus moveStatus) {
        this.board = board;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public String toString() {
        return this.move + " : " + this.moveStatus;
    }
}
