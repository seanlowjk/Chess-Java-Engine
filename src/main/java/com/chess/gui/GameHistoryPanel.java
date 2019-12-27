package com.chess.gui;

import java.awt.*;
import javax.swing.*;

import com.chess.board.moves.Move;
import com.chess.board.Board;
import com.chess.pieces.properties.Alliance;

public class GameHistoryPanel extends JPanel {

    public static final int ROW_HEIGHT = 15;
    public static final Dimension HISTORY_PANEL_DIMENSION = new Dimension(100, 400);

    protected DataModel model;
    protected JScrollPane scrollPane;

    public GameHistoryPanel() {
        this.setLayout(new BorderLayout());
        this.model = new DataModel();
        JTable table = new JTable(model);

        table.setRowHeight(ROW_HEIGHT);
        this.scrollPane = new JScrollPane(table);
        scrollPane.setColumnHeaderView(table.getTableHeader());
        scrollPane.setPreferredSize(HISTORY_PANEL_DIMENSION);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    protected void redo(Board board, MoveLogger moveLogger) {
        int currentRow = 0;

        this.model.clear();

        for (Move move : moveLogger.getMoves()) {
            String moveText = move.toString();
            if (move.getChessPiece().getAlliance() == Alliance.WHITE) {
                this.model.setValueAt(moveText, currentRow, 0);
            } else {
                this.model.setValueAt(moveText, currentRow, 1);
                currentRow++;
            }
        }

        if (moveLogger.getMoves().size() > 0) {
            Move lastMove = moveLogger.getMoves().get(moveLogger.getMoves().size() - 1);
            String lastMoveText = lastMove.toString();

            if (lastMove.getChessPiece().getAlliance() == Alliance.WHITE) {
                this.model.setValueAt(lastMoveText + calculateCheckAndCheckmateHash(board), currentRow,  0);
            } else {
                this.model.setValueAt(lastMoveText + calculateCheckAndCheckmateHash(board), currentRow - 1,  1);
            }
        }

        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    protected String calculateCheckAndCheckmateHash(Board board) {
        if(board.getCurrentPlayer().isInCheckmate()) {
            return "#";
        } else if (board.getCurrentPlayer().isInCheck()) {
            return "+";
        } else {
            return "";
        }
    }
}
