package com.chess.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.pieces.ChessPiece;
import com.chess.pieces.properties.Alliance;

public class GameBuilder {

    protected Map<Integer, ChessPiece> boardState;
    protected Alliance nextMoveMaker;

    public GameBuilder() {
        this.boardState = new HashMap<>();
        this.nextMoveMaker = Alliance.WHITE;
    }

    public void setPiece(ChessPiece piece) {
        this.boardState.put(piece.getPosition().toInteger(), piece);
    }

    public void setMoveMaker(Alliance nextMoveMaker) {
        this.nextMoveMaker = nextMoveMaker;
    }

    public Board build()  {
        return new Board(this);
    }
}
