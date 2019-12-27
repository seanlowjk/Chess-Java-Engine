package com.chess.gui;

import static javax.swing.SwingUtilities.isLeftMouseButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.chess.board.moves.Move;
import com.chess.board.moves.MoveFactory;
import com.chess.board.moves.MoveStatus;
import com.chess.board.moves.MoveTransition;
import com.chess.board.tiles.Tile;
import com.chess.board.Board;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.properties.Coordinate;

public class Table {
    protected JFrame frame;
    protected BoardPanel boardPanel;
    protected Board board;

    protected MoveLogger moveLogger;

    protected GameHistoryPanel gameHistoryPanel;
    protected TakenPiecesPanel takenPiecesPanel;

    protected Tile sourceTile;
    protected Tile destinationTile;
    protected ChessPiece movedChessPiece;

    protected BoardDirection boardDirection;

    private static final Dimension BOARD_DIMENSION = new Dimension(400, 350);
    private static Dimension OUTER_DIMENSION = new Dimension(600, 600);
    private final static Dimension TILE_DIMENSION = new Dimension(10, 10);

    public Table() {
        this.frame = new JFrame("Chess");
        this.frame.setLayout(new BorderLayout());

        JMenuBar menuBar = createMenuBar();

        this.moveLogger = new MoveLogger();
        this.gameHistoryPanel = new GameHistoryPanel();
        this.takenPiecesPanel = new TakenPiecesPanel();

        this.board = Board.createStandardBoard();
        this.boardPanel = new BoardPanel();

        this.boardDirection = new NormalBoardDirection();

        this.frame.add(this.takenPiecesPanel, BorderLayout.WEST);
        this.frame.add(this.boardPanel, BorderLayout.CENTER);
        this.frame.add(this.gameHistoryPanel, BorderLayout.EAST);
        this.frame.setJMenuBar(menuBar);
        this.frame.setSize(OUTER_DIMENSION);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createOpetionsMenu());
        menuBar.add(createPreferencesMenu());
        return menuBar;
    }

    private JMenu createOpetionsMenu() {
        JMenu fileMenu = new JMenu("Options");

        JMenuItem exit = new JMenuItem("Exit");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(exit);

        return fileMenu;
    }

    private JMenu createPreferencesMenu() {
        JMenu preferencesMenu = new JMenu("Preferences");
        JMenuItem flipBoard = new JMenuItem("Flip Board");

        flipBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.drawBoard(board);
                boardDirection = boardDirection.makeOpposite();
                boardPanel.drawBoard(board);
            }
        });

        preferencesMenu.add(flipBoard);
        return preferencesMenu;
    }

    protected class BoardPanel extends JPanel {

        private List<TilePanel> tiles;

        public BoardPanel() {
            super(new GridLayout(8, 8));

            this.tiles = new ArrayList<>();

            for (int i = 0; i < Board.MAX_TILES; i++) {
                TilePanel tilePanel = new TilePanel(this, i);
                this.tiles.add(tilePanel);
                this.add(tilePanel);
            }

            setPreferredSize(BOARD_DIMENSION);
            validate();
        }

        public void drawBoard(Board board) {
            removeAll();

            for (TilePanel tilePanel : boardDirection.traverse(tiles)) {
                tilePanel.drawTile(board);
                add(tilePanel);
            }

            validate();
            repaint();
        }
    }

    protected class TilePanel extends JPanel {

        private final Color lightTileColor = Color.decode("#FFFACD");
        private final Color darkTileColor = Color.decode("#593E1A");
        private final String iconsPath = "images/";
        private int id;
        private Coordinate coordinate;

        public final String greenDotIconPath = iconsPath + "green_dot.png";

        public TilePanel(BoardPanel boardPanel, int id) {
            super (new GridBagLayout());

            this.id = id;
            this.setPreferredSize(TILE_DIMENSION);

            this.coordinate = new Coordinate(getRow(), getColumn());

            assignTileColor();
            assignIcon(board);

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (isLeftMouseButton(e)) {
                        if (sourceTile == null) {
                            sourceTile = board.getTile(coordinate);
                            movedChessPiece = sourceTile.getPiece();

                            if (movedChessPiece == null) {
                                sourceTile = null;
                            }
                        } else {
                            destinationTile = board.getTile(coordinate);
                            Move move = MoveFactory.createMove(board,
                                    sourceTile.getCoordinate(),
                                    destinationTile.getCoordinate());

                            MoveTransition moveTransition = board.getCurrentPlayer().makeMove(move);
                            if (moveTransition.getMoveStatus() == MoveStatus.DONE) {
                                board = moveTransition.getBoard();
                                moveLogger.addMove(move);
                            }

                            sourceTile = null;
                            destinationTile = null;
                            movedChessPiece = null;
                        }

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                gameHistoryPanel.redo(board, moveLogger);
                                takenPiecesPanel.redo(moveLogger);
                                boardPanel.drawBoard(board);
                            }
                        });
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            validate();
        }

        private void assignTileColor() {
            int row = getRow();
            int column = getColumn();

            if (row % 2 != 0) {
                setBackground(column % 2 != 0 ? lightTileColor : darkTileColor);
            } else {
                setBackground(column % 2 == 0 ? lightTileColor : darkTileColor);
            }
        }

        private int getRow() {
            return id / 8;
        }

        private int getColumn() {
            return id % 8;
        }

        private void assignIcon(Board board) {
            this.removeAll();

            if (board.getTile(coordinate).isTileOccupied()) {
                String fullPath = iconsPath +
                        board.getTile(coordinate).getPiece().getAlliance().toString() +
                        board.getTile(coordinate).getPiece().toString().toUpperCase() + ".gif";
                try {
                    BufferedImage image = ImageIO.read(new File(getClass().getClassLoader().getResource(fullPath).getFile()));
                    this.add(new JLabel(new ImageIcon(image)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void drawTile(Board board) {
            assignTileColor();
            assignIcon(board);
            highlightLegalMoves(board);
            validate();
            repaint();
        }

        public void highlightLegalMoves(Board board) {
            if (sourceTile != null) {
                for (Move move : getPieceLegalMoves(board)) {
                    if (move.getDestination().toInteger() == this.id) {
                        try {
                            add(new JLabel(new ImageIcon(ImageIO.read(new File(getClass().getClassLoader().getResource(greenDotIconPath).getFile())))));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        protected List<Move> getPieceLegalMoves(Board board) {
            if (movedChessPiece != null && movedChessPiece.getAlliance() == board.getCurrentPlayer().getAlliance()) {
                return movedChessPiece.getLegalMoves(board);
            }

            return new ArrayList<>();
        }
    }

}