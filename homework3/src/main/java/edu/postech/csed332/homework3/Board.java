package edu.postech.csed332.homework3;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * An X-Sudoku board
 */
public class Board {
    //TODO: add private member variables and private methods as needed
    Cell[][] cells = new Cell[9][9];
    Group[] g_rows = new Group[9];
    Group[] g_columns = new Group[9];
    Group[][] g_squares = new Group[3][3];
    Group[] g_diagonals = new Group[2];   // 0: toward right lower, 1: toward left lower


    /**
     * Creates an X-Sudoku board
     *
     * @param game an initial configuration
     */
    Board(@NotNull GameInstance game) {
        //TODO: implement this

        // Initialize cells
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                this.cells[row][col] = new Cell();
            }
        }

        // Initialize rows(Groups)
        for (int idx = 0; idx < 9; idx++) {
            this.g_rows[idx] = new Group();
        }

        // Initialize columns(Groups)
        for (int idx = 0; idx < 9; idx++) {
            this.g_columns[idx] = new Group();
        }
        
        // Initialize squares(Groups)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.g_squares[row][col] = new Group();
            }
        }

        // Initialize diagonals(Groups)
        for (int idx = 0; idx < 2; idx++) {
            this.g_diagonals[idx] = new Group();
        }
        // Register Cells to group(rows)
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                this.g_rows[row].addCell(this.cells[row][col]);
            }
        }

        // Register Cells to group(columns)
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                this.g_columns[col].addCell(this.cells[row][col]);
            }
        }

        // Register cells to group(squares)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        this.g_squares[row][col].addCell(this.cells[row * 3 + r][col * 3 + c]);
                    }
                }
            }
        }

        // Register Cells to group(diagonal)
        for (int idx = 0; idx < 9; idx++) {
            this.g_diagonals[0].addCell(this.cells[idx][idx]);
            this.g_diagonals[1].addCell(this.cells[idx][8 - idx]);
        }

        // Set cell numbers
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Optional<Integer> number = game.getNumbers(row + 1, col + 1);
                if (number.isPresent()) {
                    this.cells[row][col].setNumber(number.get());
                }
            }
        }
    }

    /**
     * Returns a cell in the i-th row of j-th column, where 1 <= i, j <= 9.
     *
     * @param i a row index
     * @param j a column index
     * @return a cell in the i-th row of j-th column
     */
    @NotNull
    Cell getCell(int i, int j) {
        //TODO: implement this
        return this.cells[i - 1][j - 1];
    }
}
