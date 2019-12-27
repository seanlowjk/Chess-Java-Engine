package com.chess.board;

import java.util.ArrayList;
import java.util.List;

import com.chess.board.moves.Move;
import com.chess.board.tiles.Tile;
import com.chess.pieces.Bishop;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.King;
import com.chess.pieces.Knight;
import com.chess.pieces.Pawn;
import com.chess.pieces.Queen;
import com.chess.pieces.Rook;
import com.chess.pieces.properties.Alliance;
import com.chess.pieces.properties.Coordinate;
import com.chess.player.BlackPlayer;
import com.chess.player.Player;
import com.chess.player.WhitePlayer;

public class Board {

    public static int MAX_ROWS = 8;
    public static int MAX_COLUMNS = 8;
    public static int MAX_TILES = 64;

    protected List<Tile> gameBoard;
    protected List<ChessPiece> whitePieces;
    protected List<ChessPiece> blackPieces;

    protected List<Move> whiteLegalMoves;
    protected List<Move> blackLegalMoves;

    protected WhitePlayer whitePlayer;
    protected BlackPlayer blackPlayer;

    protected Player currentPlayer;

    public Board(GameBuilder builder)  {
        this.gameBoard = createGameBoard(builder);

        this.whitePieces = calculateRemainingPieces(gameBoard, Alliance.WHITE);
        this.blackPieces = calculateRemainingPieces(gameBoard, Alliance.BLACK);

        this.whiteLegalMoves = calculateAllLegalMoves(this.whitePieces);
        this.blackLegalMoves = calculateAllLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteLegalMoves, blackLegalMoves);
        this.blackPlayer = new BlackPlayer(this, blackLegalMoves, whiteLegalMoves);

        this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
    }

    public List<ChessPiece> getBlackPieces() {
        return this.blackPieces;
    }

    public List<ChessPiece> getWhitePieces() {
        return this.whitePieces;
    }

    public List<ChessPiece> getAllPieces() {
        List<ChessPiece> chessPieces = new ArrayList<>();
        chessPieces.addAll(this.whitePieces);
        chessPieces.addAll(this.blackPieces);
        return chessPieces;
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public Player getBlackPlayer() {
        return this.blackPlayer;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public List<Move> getAllLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();

        legalMoves.addAll(this.whiteLegalMoves);
        legalMoves.addAll(this.blackLegalMoves);

        return legalMoves;
    }

    private List<ChessPiece> calculateRemainingPieces(List<Tile> gameBoard, Alliance alliance) {
        List<ChessPiece> remainingPieces = new ArrayList<>();

        for (Tile tile : gameBoard) {
            if (tile.isTileOccupied()) {
                ChessPiece piece = tile.getPiece();
                if (piece.getAlliance() == alliance) {
                    remainingPieces.add(piece);
                }
            }
        }

        return remainingPieces;
    }

    private List<Move> calculateAllLegalMoves(List<ChessPiece> pieces) {
        List<Move> legalMoves = new ArrayList<>();
        for (ChessPiece piece : pieces) {
            legalMoves.addAll(piece.getLegalMoves(this));
        }
        return legalMoves;
    }

    private List<Tile> createGameBoard(GameBuilder builder)  {
        List<Tile> gameBoard = new ArrayList<>();
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLUMNS; j++) {
                gameBoard.add(Tile.createTile(new Coordinate(i, j), builder.boardState.get(i * 8 + j)));
            }
        }
        return gameBoard;
    }

    public Tile getTile(Coordinate coordinate) {
        return gameBoard.get(coordinate.toInteger());
    }

    public static Board createStandardBoard()  {
        GameBuilder builder = new GameBuilder();
        builder.setPiece(new Rook(new Coordinate(7, 0), Alliance.WHITE));
        builder.setPiece(new Knight(new Coordinate(7, 1), Alliance.WHITE));
        builder.setPiece(new Bishop(new Coordinate(7, 2), Alliance.WHITE));
        builder.setPiece(new Queen(new Coordinate(7, 3), Alliance.WHITE));
        builder.setPiece(new King(new Coordinate(7, 4), Alliance.WHITE));
        builder.setPiece(new Bishop(new Coordinate(7, 5), Alliance.WHITE));
        builder.setPiece(new Knight(new Coordinate(7, 6), Alliance.WHITE));
        builder.setPiece(new Rook(new Coordinate(7, 7), Alliance.WHITE));

        builder.setPiece(new
                Pawn(new Coordinate(6, 0), Alliance.WHITE));
        builder.setPiece(new Pawn(new Coordinate(6, 1), Alliance.WHITE));
        builder.setPiece(new Pawn(new Coordinate(6, 2), Alliance.WHITE));
        builder.setPiece(new Pawn(new Coordinate(6, 3), Alliance.WHITE));
        builder.setPiece(new Pawn(new Coordinate(6, 4), Alliance.WHITE));
        builder.setPiece(new Pawn(new Coordinate(6, 5), Alliance.WHITE));
        builder.setPiece(new Pawn(new Coordinate(6, 6), Alliance.WHITE));
        builder.setPiece(new Pawn(new Coordinate(6, 7), Alliance.WHITE));

        builder.setPiece(new Rook(new Coordinate(0, 0), Alliance.BLACK));
        builder.setPiece(new Knight(new Coordinate(0, 1), Alliance.BLACK));
        builder.setPiece(new Bishop(new Coordinate(0, 2), Alliance.BLACK));
        builder.setPiece(new Queen(new Coordinate(0, 3), Alliance.BLACK));
        builder.setPiece(new King(new Coordinate(0, 4), Alliance.BLACK));
        builder.setPiece(new Bishop(new Coordinate(0, 5), Alliance.BLACK));
        builder.setPiece(new Knight(new Coordinate(0, 6), Alliance.BLACK));
        builder.setPiece(new Rook(new Coordinate(0, 7), Alliance.BLACK));

        builder.setPiece(new Pawn(new Coordinate(1, 0), Alliance.BLACK));
        builder.setPiece(new Pawn(new Coordinate(1, 1), Alliance.BLACK));
        builder.setPiece(new Pawn(new Coordinate(1, 2), Alliance.BLACK));
        builder.setPiece(new Pawn(new Coordinate(1, 3), Alliance.BLACK));
        builder.setPiece(new Pawn(new Coordinate(1, 4), Alliance.BLACK));
        builder.setPiece(new Pawn(new Coordinate(1, 5), Alliance.BLACK));
        builder.setPiece(new Pawn(new Coordinate(1, 6), Alliance.BLACK));
        builder.setPiece(new Pawn(new Coordinate(1, 7), Alliance.BLACK));

        return builder.build();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < MAX_TILES; i++) {
            ChessPiece piece = this.gameBoard.get(i).getPiece();
            if (piece == null) {
                builder.append(String.format("%3s", ChessPiece.EMPTY_PIECE_STRING));
            } else {
                builder.append(String.format("%3s", piece.toString()));
            }
            if ((i + 1) % 8 == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}


