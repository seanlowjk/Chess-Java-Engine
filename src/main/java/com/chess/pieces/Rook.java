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

public class Rook extends ChessPiece {

    public static final Coordinate[] MOVE_COORDINATES = {
            Directions.NORTH,
            Directions.WEST,
            Directions.EAST,
            Directions.SOUTH
    };

    public Rook(Coordinate position, Alliance alliance) {
        super(position, alliance, true);
    }

    public Rook(Coordinate position, Alliance alliance, boolean isFirstMove) {
        super(position, alliance, isFirstMove);
    }

    @Override
    public Rook movePiece(Move move) {
        return new Rook(move.getDestination(), move.getChessPiece().getAlliance(), false);
    }

    @Override
    public List<Move> getLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();

        for (Coordinate move : MOVE_COORDINATES) {
            Coordinate destination = this.position;

            while (Coordinate.isValidCoordinate(destination)) {
                destination = Coordinate.add(destination, move);
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
                        break;
                    }
                }
            }
        }

        return legalMoves;
    }
}
