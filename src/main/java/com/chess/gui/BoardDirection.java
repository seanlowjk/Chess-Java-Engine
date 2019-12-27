package com.chess.gui;

import java.util.List;

public interface BoardDirection {

    public List<Table.TilePanel> traverse(List<Table.TilePanel> tiles);

    public BoardDirection makeOpposite();
}
