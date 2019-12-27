package com.chess.gui;

import java.util.Collections;
import java.util.List;

public class FlippedBoardDirection implements BoardDirection {

    @Override
    public List<Table.TilePanel> traverse(List<Table.TilePanel> tiles) {
        Collections.reverse(tiles);
        return tiles;
    }

    @Override
    public BoardDirection makeOpposite() {
        return new NormalBoardDirection();
    }

    @Override
    public String toString() {
        return "Flipped";
    }
}