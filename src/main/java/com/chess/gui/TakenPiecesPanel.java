package com.chess.gui;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import com.chess.board.moves.Move;
import com.chess.pieces.ChessPiece;
import com.chess.pieces.properties.Alliance;

public class TakenPiecesPanel extends JPanel {

    protected JPanel northPanel;
    protected JPanel southPanel;

    protected static final Color PANEL_COLOR = Color.decode("#FDF5E6");
    protected static final Dimension TAKEN_PIECES_DIMENSION = new Dimension(40, 80);
    protected static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);

    private final String iconsPath = "images/";

    public TakenPiecesPanel() {
        super(new BorderLayout());
        setBackground(PANEL_COLOR);
        setBorder(PANEL_BORDER);

        this.northPanel = new JPanel(new GridLayout(8, 2));
        this.northPanel.setBackground(PANEL_COLOR);
        this.southPanel = new JPanel(new GridLayout(8, 2));
        this.southPanel.setBackground(PANEL_COLOR);

        this.add(this.northPanel, BorderLayout.NORTH);
        this.add(this.southPanel, BorderLayout.SOUTH);

        setPreferredSize(TAKEN_PIECES_DIMENSION);
    }

    public void redo(final MoveLogger moveLog) {
        southPanel.removeAll();
        northPanel.removeAll();

        List<ChessPiece> whiteTakenPieces = new ArrayList<>();
        List<ChessPiece> blackTakenPieces = new ArrayList<>();

        for(final Move move : moveLog.getMoves()) {
            if(move.isAttackMove()) {
                final ChessPiece takenPiece = move.getAttackedChessPiece();
                if(takenPiece.getAlliance() == Alliance.WHITE) {
                    whiteTakenPieces.add(takenPiece);
                } else {
                    blackTakenPieces.add(takenPiece);
                }
            }
        }

        Collections.sort(whiteTakenPieces, new Comparator<ChessPiece>() {
            @Override
            public int compare(final ChessPiece p1, final ChessPiece p2) {
                return Integer.compare(p1.getPieceValue(), p2.getPieceValue());
            }
        });

        Collections.sort(blackTakenPieces, new Comparator<ChessPiece>() {
            @Override
            public int compare(final ChessPiece p1, final ChessPiece p2) {
                return Integer.compare(p1.getPieceValue(), p2.getPieceValue());
            }
        });

        for (final ChessPiece takenPiece : whiteTakenPieces) {
            String fullPath = iconsPath +
                    takenPiece.getAlliance().toString() +
                    takenPiece.toString().toUpperCase() + ".gif";
            try {
                final BufferedImage image = ImageIO.read(new File(getClass().getClassLoader().getResource(fullPath).getFile()));
                final ImageIcon ic = new ImageIcon(image);
                final JLabel imageLabel = new JLabel(new ImageIcon(ic.getImage().getScaledInstance(
                        ic.getIconWidth() - 15, ic.getIconWidth() - 15, Image.SCALE_SMOOTH)));
                this.southPanel.add(imageLabel);
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }

        for (final ChessPiece takenPiece : blackTakenPieces) {
            String fullPath = iconsPath +
                    takenPiece.getAlliance().toString() +
                    takenPiece.toString().toUpperCase() + ".gif";
            try {
                final BufferedImage image = ImageIO.read(new File(getClass().getClassLoader().getResource(fullPath).getFile()));
                final ImageIcon ic = new ImageIcon(image);
                final JLabel imageLabel = new JLabel(new ImageIcon(ic.getImage().getScaledInstance(
                        ic.getIconWidth() - 15, ic.getIconWidth() - 15, Image.SCALE_SMOOTH)));
                this.northPanel.add(imageLabel);

            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        validate();
    }
}
