package com.chess.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.chess.board.moves.Move;

public class DataModel extends DefaultTableModel {

    protected List<Row> values;
    protected static final String[] NAMES = {"WHITE", "BLACK"};

    public DataModel() {
        this.values = new ArrayList<>();
    }

    public void clear() {
        this.values.clear();
        setRowCount(0);
    }

    @Override
    public int getRowCount() {
        if(this.values == null) {
            return 0;
        }
        return this.values.size();
    }

    @Override
    public int getColumnCount() {
        return NAMES.length;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        final Row currentRow = this.values.get(row);
        if(col == 0) {
            return currentRow.getWhiteMove();
        } else if (col == 1) {
            return currentRow.getBlackMove();
        }
        return null;
    }

    @Override
    public void setValueAt(final Object aValue,
                           final int row,
                           final int col) {
        final Row currentRow;
        if(this.values.size() <= row) {
            currentRow = new Row();
            this.values.add(currentRow);
        } else {
            currentRow = this.values.get(row);
        }
        if(col == 0) {
            currentRow.setWhiteMove((String) aValue);
            fireTableRowsInserted(row, row);
        } else  if(col == 1) {
            currentRow.setBlackMove((String)aValue);
            fireTableCellUpdated(row, col);
        }
    }

    @Override
    public Class<?> getColumnClass(final int col) {
        return Move.class;
    }

    @Override
    public String getColumnName(final int col) {
        return NAMES[col];
    }
}
