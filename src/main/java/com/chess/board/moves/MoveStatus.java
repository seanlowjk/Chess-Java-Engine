package com.chess.board.moves;

public enum MoveStatus {
    CHECKED,
    DONE,
    ILLEGAL;

    public boolean isDone() {
        return this == DONE;
    }
}
