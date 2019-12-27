package com.chess.pieces.properties;

public class Coordinate {
    protected int rowNumber;
    protected int columnNumber;

    public Coordinate(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public static boolean isValidCoordinate(Coordinate coordinate) {
        return (0 <= coordinate.columnNumber && coordinate.columnNumber < 8)
                && (0 <= coordinate.rowNumber && coordinate.rowNumber < 8);
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public static Coordinate add(Coordinate a, Coordinate b) {
        return new Coordinate(a.getRowNumber() + b.getRowNumber(),
                a.getColumnNumber() + b.getColumnNumber());
    }

    public int toInteger() {
        return (rowNumber * 8) + columnNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate other = (Coordinate) obj;
            return this.getRowNumber() == other.getRowNumber()
                    && this.getColumnNumber() == other.getColumnNumber();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.rowNumber + "" + this.columnNumber;
    }
}
