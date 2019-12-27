package com.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.board.moves.Move;
import com.chess.board.moves.NormalMove;
import com.chess.board.tiles.Tile;
import com.chess.pieces.properties.Alliance;
import com.chess.board.Board;
import com.chess.board.moves.AttackMove;
import com.chess.pieces.properties.Coordinate;
import com.chess.pieces.properties.Directions;

public class Knight extends ChessPiece {

    public static final Coordinate[] MOVE_COORDINATES = {
            Directions.TOP_RIGHT,
            Directions.UPPER_RIGHT,
            Directions.LOWER_RIGHT,
            Directions.BOTTOM_RIGHT,
            Directions.TOP_LEFT,
            Directions.UPPER_LEFT,
            Directions.LOWER_LEFT,
            Directions.BOTTOM_LEFT
    };

    public Knight(Coordinate position, Alliance alliance) {
        super(position, alliance, true);
    }

    public Knight(Coordinate position, Alliance alliance, boolean isFirstMove) {
        super(position, alliance, isFirstMove);
    }

    @Override
    public Knight movePiece(Move move) {
        return new Knight(move.getDestination(), move.getChessPiece().getAlliance(), false);
    }

    @Override
    public List<Move> getLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();

        for (Coordinate move : MOVE_COORDINATES) {
            Coordinate destination = Coordinate.add(this.position, move);
            if (Coordinate.isValidCoordinate(destination)) {
                Tile destinationTile = board.getTile(destination);
                if (!destinationTile.isTileOccupied()) {
                    legalMoves.add(new NormalMove(board, this, destination));
                } else {
                    ChessPiece attackedPiece = destinationTile.getPiece();
                    Alliance pieceAlliance = attackedPiece.getAlliance();
                    if (pieceAlliance != this.alliance) {
                        legalMoves.add(new AttackMove(board, this, destination, attackedPiece));
                    }
                }
            }
        }

        return legalMoves;
    }
}
