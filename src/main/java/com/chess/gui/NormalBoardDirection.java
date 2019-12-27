package com.chess.gui;

import java.util.List;

public class NormalBoardDirection implements BoardDirection {

    @Override
    public List<Table.TilePanel> traverse(List<Table.TilePanel> tiles) {
        return tiles;
    }

    @Override
    public BoardDirection makeOpposite() {
        return new FlippedBoardDirection();
    }

    @Override
    public String toString() {
        return "Normal";
    }
}
