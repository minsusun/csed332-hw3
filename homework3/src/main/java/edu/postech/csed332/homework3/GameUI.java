package edu.postech.csed332.homework3;

import javax.swing.*;
import java.awt.*;

public class GameUI {
    private static final int unitSize = 10;
    private static final int width = 45;
    private static final int height = 45;

    final JFrame top;

    public GameUI(Board board) {
        top = new JFrame("X-Sudoku");
        top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        top.setLayout(new GridLayout(3, 3));

        var dim = new Dimension(unitSize * width, unitSize * height);
        top.setMinimumSize(dim);
        top.setPreferredSize(dim);

        createCellUI(board);

        top.pack();
        top.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var board = new Board(GameInstanceFactory.createGameInstance());
            new GameUI(board);
        });
    }

    private void createCellUI(Board board) {
        JTextField[][] cells = new JTextField[9][9];
        JPanel[][] squares = new JPanel[3][3];

        //TODO: implement this. Create cells and squares, to add them to top, and to
        // define layouts for them.

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new CellUI(board.getCell(row + 1, col + 1), row == col || row + col == 8);
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                squares[row][col] = new JPanel();
                squares[row][col].setLayout(new GridLayout(3, 3));
                for (int cell_row = 0; cell_row < 3; cell_row++) {
                    for (int cell_col = 0; cell_col < 3; cell_col++) {
                        squares[row][col].add(cells[row * 3 + cell_row][col * 3 + cell_col]);
                    }
                }
                top.add(squares[row][col]);
            }
        }

    }

}
