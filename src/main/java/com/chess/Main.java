package com.chess;

import com.chess.gui.Table;
import com.chess.board.Board;

public class Main {
    public static void main(String[ ] args)  {
        Board board = Board.createStandardBoard();
        Table table = new Table();
    }
}
