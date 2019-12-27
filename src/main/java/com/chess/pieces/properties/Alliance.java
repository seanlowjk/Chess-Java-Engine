package com.chess.pieces.properties;

import com.chess.player.Player;

public enum Alliance {
    BLACK,
    WHITE;

    public Player choosePlayer(Player whitePlayer, Player blackPlayer) {
        if (this == WHITE) {
            return whitePlayer;
        } else {
            return blackPlayer;
        }
    }

    public boolean isPawnPromotionSquare(Coordinate coordinate) {
        if (this == WHITE) {
            return coordinate.getRowNumber() == 0;
        } else {
            return coordinate.getRowNumber() == 7;
        }
    }

    @Override
    public String toString() {
        if (this == WHITE) {
            return "W";
        } else {
            return "B";
        }
    }
}
