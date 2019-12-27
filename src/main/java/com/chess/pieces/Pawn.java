package com.chess.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.board.moves.Move;
import com.chess.board.moves.NormalMove;
import com.chess.board.moves.PawnPromotionMove;
import com.chess.board.tiles.Tile;
import com.chess.pieces.properties.Alliance;
import com.chess.board.Board;
import com.chess.board.moves.AttackMove;
import com.chess.pieces.properties.Coordinate;
import com.chess.pieces.properties.Directions;

public class Pawn extends ChessPiece {

    public static final Coordinate[] BLACK_MOVE_COORDINATES = {
            Directions.BLACK_PAWN_MOVE,
            Directions.NORTH,
            Directions.NORTH_EAST,
            Directions.NORTH_WEST
    };

    public static final Coordinate[] WHITE_MOVE_COORDINATES = {
            Directions.WHITE_PAWN_MOVE,
            Directions.SOUTH,
            Directions.SOUTH_EAST,
            Directions.SOUTH_WEST
    };

    public Pawn(Coordinate position, Alliance alliance) {
        super(position, alliance, true);
    }

    public Pawn(Coordinate position, Alliance alliance, boolean isFirstMove) {
        super(position, alliance, isFirstMove);
    }

    public ChessPiece getPromotedPawn() {
        return new Queen(this.position, this.alliance, false);
    }

    @Override
    public Pawn movePiece(Move move) {
        return new Pawn(move.getDestination(), move.getChessPiece().getAlliance(), false);
    }

    @Override
    public List<Move> getLegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        if (isWhitePiece()) {
            if (position.getRowNumber() == 6) {
                for (int i = 0; i < WHITE_MOVE_COORDINATES.length; i++) {
                    Coordinate destination = Coordinate.add(this.position, WHITE_MOVE_COORDINATES[i]);
                    if (Coordinate.isValidCoordinate(destination)) {
                        Tile destinationTile = board.getTile(destination);
                        if (!destinationTile.isTileOccupied()) {
                            if (i == 0 || i == 1) {
                                if (this.getAlliance().isPawnPromotionSquare(destination)) {
                                    legalMoves.add(new
                                            PawnPromotionMove(new NormalMove(board, this, destination)));
                                } else {
                                    legalMoves.add(new NormalMove(board, this, destination));
                                }
                            }
                        } else {
                            ChessPiece attackedPiece = destinationTile.getPiece();
                            Alliance pieceAlliance = attackedPiece.getAlliance();
                            if (pieceAlliance != this.alliance) {
                                if (i == 2 || i == 3) {
                                    if (this.getAlliance().isPawnPromotionSquare(destination)) {
                                        legalMoves.add(new PawnPromotionMove(new
                                                AttackMove(board, this, destination, attackedPiece)));
                                    } else {
                                        legalMoves.add(new AttackMove(board, this, destination, attackedPiece));
                                    }
                                }
                            }
                        }
                    }
                }

            } else {
                for (int i = 1; i < WHITE_MOVE_COORDINATES.length; i++) {
                    Coordinate destination = Coordinate.add(this.position, WHITE_MOVE_COORDINATES[i]);
                    if (Coordinate.isValidCoordinate(destination)) {
                        Tile destinationTile = board.getTile(destination);
                        if (!destinationTile.isTileOccupied()) {
                            if (i == 1) {
                                if (this.getAlliance().isPawnPromotionSquare(destination)) {
                                    legalMoves.add(new
                                            PawnPromotionMove(new NormalMove(board, this, destination)));
                                } else {
                                    legalMoves.add(new NormalMove(board, this, destination));
                                }
                            }
                        } else {
                            ChessPiece attackedPiece = destinationTile.getPiece();
                            Alliance pieceAlliance = attackedPiece.getAlliance();
                            if (pieceAlliance != this.alliance) {
                                if (i == 2 || i == 3) {
                                    if (this.getAlliance().isPawnPromotionSquare(destination)) {
                                        legalMoves.add(new PawnPromotionMove(new
                                                AttackMove(board, this, destination, attackedPiece)));
                                    } else {
                                        legalMoves.add(new AttackMove(board, this, destination, attackedPiece));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (position.getRowNumber() == 1) {
                for (int i = 0; i < BLACK_MOVE_COORDINATES.length; i++) {
                    Coordinate destination = Coordinate.add(this.position, BLACK_MOVE_COORDINATES[i]);
                    if (Coordinate.isValidCoordinate(destination)) {
                        Tile destinationTile = board.getTile(destination);
                        if (!destinationTile.isTileOccupied()) {
                            if (i == 0 || i == 1) {
                                if (this.getAlliance().isPawnPromotionSquare(destination)) {
                                    legalMoves.add(new
                                            PawnPromotionMove(new NormalMove(board, this, destination)));
                                } else {
                                    legalMoves.add(new NormalMove(board, this, destination));
                                }
                            }
                        } else {
                            ChessPiece attackedPiece = destinationTile.getPiece();
                            Alliance pieceAlliance = attackedPiece.getAlliance();
                            if (pieceAlliance != this.alliance) {
                                if (i == 2 || i == 3) {
                                    if (this.getAlliance().isPawnPromotionSquare(destination)) {
                                        legalMoves.add(new PawnPromotionMove(new
                                                AttackMove(board, this, destination, attackedPiece)));
                                    } else {
                                        legalMoves.add(new AttackMove(board, this, destination, attackedPiece));
                                    }
                                }
                            }
                        }
                    }
                }

            } else {
                for (int i = 1; i < BLACK_MOVE_COORDINATES.length; i++) {
                    Coordinate destination = Coordinate.add(this.position, BLACK_MOVE_COORDINATES[i]);
                    if (Coordinate.isValidCoordinate(destination)) {
                        Tile destinationTile = board.getTile(destination);
                        if (!destinationTile.isTileOccupied()) {
                            if (i == 1) {
                                if (this.getAlliance().isPawnPromotionSquare(destination)) {
                                    legalMoves.add(new
                                            PawnPromotionMove(new NormalMove(board, this, destination)));
                                } else {
                                    legalMoves.add(new NormalMove(board, this, destination));
                                }
                            }
                        } else {
                            ChessPiece attackedPiece = destinationTile.getPiece();
                            Alliance pieceAlliance = attackedPiece.getAlliance();
                            if (pieceAlliance != this.alliance) {
                                if (i == 2 || i == 3) {
                                    if (this.getAlliance().isPawnPromotionSquare(destination)) {
                                        legalMoves.add(new PawnPromotionMove(new
                                                AttackMove(board, this, destination, attackedPiece)));
                                    } else {
                                        legalMoves.add(new AttackMove(board, this, destination, attackedPiece));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return legalMoves;
    }
}
