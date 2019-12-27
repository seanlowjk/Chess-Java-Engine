package com.chess.gui;

import java.util.ArrayList;
import java.util.List;

import com.chess.board.moves.Move;

public class MoveLogger {

    protected List<Move> moves;

    public MoveLogger() {
        this.moves = new ArrayList<>();
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public void clearMoves() {
        this.moves = new ArrayList<>();
    }

    public void addMove(Move move) {
        this.moves.add(move);
    }

    public int getNumberOfMoves() {
        return this.moves.size();
    }

    public Move removeMove(int index) {
        return this.moves.remove(index);
    }

    public boolean removeMove(Move move) {
        return this.moves.remove(move);
    }
}
