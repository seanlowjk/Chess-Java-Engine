package com.chess.player;

import java.util.ArrayList;
import java.util.List;

import com.chess.board.moves.KingSideCastleMove;
import com.chess.board.moves.Move;
import com.chess.board.moves.QueenSideCastleMove;
import com.chess.board.tiles.Tile;
import com.chess.board.Board;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.Rook;
import com.chess.pieces.properties.Alliance;
import com.chess.pieces.properties.Coordinate;

public class WhitePlayer extends Player{

    public WhitePlayer(Board board, List<Move> legalMoves, List<Move> opponentMoves) {
        super(board, legalMoves, opponentMoves);
    }

    @Override
    public List<ChessPiece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

    @Override
    public List<Move> getKingCastles(List<Move> legalMoves, List<Move> opponentMoves) {
        List<Move> kingCastles = new ArrayList<>();

        if (this.king.isFirstMove() && !this.isInCheck()) {
            if (this.board.getTile(new Coordinate(7, 5)).isTileOccupied() &&
                    this.board.getTile(new Coordinate(7, 6)).isTileOccupied()) {
                Tile rookTile = this.board.getTile(new Coordinate(7, 7));
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (calculateAttacksOnTile(new Coordinate(7, 5),
                            opponentMoves).isEmpty() &&
                            calculateAttacksOnTile(new Coordinate(7, 6),
                                    opponentMoves).isEmpty() && rookTile.getPiece().isRook()) {
                        kingCastles.add(new KingSideCastleMove(this.board, this.king,
                                new Coordinate(7, 6),
                                (Rook) rookTile.getPiece(), rookTile.getCoordinate(),
                                new Coordinate(7, 5)));
                    }
                }
            }

            //whites queen side castle
            if(!this.board.getTile(new Coordinate(7, 3)).isTileOccupied()
                    && !this.board.getTile(new Coordinate(7, 2)).isTileOccupied() &&
                    !this.board.getTile(new Coordinate(7, 1)).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(new Coordinate(7, 0));
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (calculateAttacksOnTile(new Coordinate(7, 3),
                            opponentMoves).isEmpty() &&
                            calculateAttacksOnTile(new Coordinate(7, 2),
                                    opponentMoves).isEmpty() &&
                            calculateAttacksOnTile(new Coordinate(7, 1),
                                    opponentMoves).isEmpty() && rookTile.getPiece().isRook()) {
                        kingCastles.add(new QueenSideCastleMove(this.board, this.king,
                                new Coordinate(7, 2),
                                (Rook) rookTile.getPiece(), rookTile.getCoordinate(),
                                new Coordinate(7, 3)));
                    }
                }
            }
        }

        return kingCastles;
    }
}
