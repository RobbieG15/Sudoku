package Sudoku;

import java.util.ArrayList;

/**
 * Class that will correctly solve a valid Sudoku puzzle.
 *
 * @author Robert Greenslade
 * @version 1.00 (12/13/2023)
 */
public class Solver {

    // Instance Variables

    /**
     * The data structure to hold the board. Needs to be a 9x9 board.
     */
    int[][] board;

    // Constructors

    /**
     * Constructor for the Sudoku board solver class.
     *
     * @requires the {@code board} needs to be 9x9 full of digits [0-9]
     * @ensures the {@code this.board} will be a hard copy of {@code board}
     *
     * @param board
     *            9x9 board of digits between [0-9]
     */
    public Solver(int[][] board) {
        this.board = board;
    }

    // Instance methods

    /**
     * Method to exchange {@code this.board} with {@code newBoard}.
     *
     * @requires the {@code newBoard} needs to be 9x9 full of digits [0-9]
     * @ensures the {@code this.board} will be a hard copy of {@code newBoard}
     *
     * @param newBoard
     *            9x9 board of digits between [0-9]
     */
    public void exchangeBoard(int[][] newBoard) {
        this.board = newBoard;
    }

    /**
     * Function that checks that {@code number} is a valid number to put at
     * {@code this.board}[{@code row}][{@code column}].
     *
     * @param row
     *            the 9x9 board of digits [0-9]
     * @param column
     *            the column that the {@code number} would be checked at
     * @param number
     *            the number to check for safeness
     * @return boolean false if it is unsafe to add, true otherwise
     */
    public boolean safeSpotCheck(int row, int column, int number) {
        return this.safeCheck(this.board, row, column, number);
    }

    /**
     * Recursive algorithm for solving sudoku that implements a backtracking
     * algorithm as well to improve runtime.
     *
     * @return boolean value with true meaning the sudoku was solved validly,
     *         false otherwise
     */
    public boolean solve() {
        ArrayList<Integer> rowCol = this.firstOpenSpace(this.board);

        if (rowCol.size() == 0) {
            return true;
        }

        for (int i = 1; i < 10; i++) {
            if (this.safeSpotCheck(rowCol.get(0), rowCol.get(1), i)) {
                this.board[rowCol.get(0)][rowCol.get(1)] = i;

                if (this.solve()) {
                    return true;
                }
            }
            this.board[rowCol.get(0)][rowCol.get(1)] = 0;
        }
        return false;
    }

    /**
     * A helper method to check if the entire {@code this.board} is valid at any
     * type of stage.
     *
     * @return boolean value of true if the {@code this.board} is in a valid
     *         orientation, false otherwise
     */
    public boolean isValid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board[i][j] != 0) {
                    if (!this.safeSpotCheck(i, j, this.board[i][j])) {
                        System.out.println("Row: " + i + "\nCol: " + j);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Method to print the entire {@code this.board} in whatever state it
     * currently is in.
     */
    public void print() {
        for (int i = 0; i < 9; i++) {
            System.out.print("[ ");
            for (int j = 0; j < 9; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println("]");
        }
        System.out.println("Is Valid? " + this.isValid() + "\n");
    }

    // Static Methods
    /**
     * Function that checks that {@code number} is a valid number to put at
     * {@code board}[{@code row}][{@code column}].
     *
     * @param board
     *            the 9x9 board of digits [0-9]
     * @param row
     *            the row that the {@code number} would be checked at
     * @param column
     *            the column that the {@code number} would be checked at
     * @param number
     *            the number to check for safeness
     * @return boolean false if it is unsafe to add, true otherwise
     */
    private boolean safeCheck(int[][] board, int row, int column, int number) {
        // Check same row / column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == number && i != column) {
                return false;
            }
            if (board[i][column] == number && i != row) {
                return false;
            }
        }

        // Check Sub-Matrix (If any is true, whole function would be true after
        // getting passed above checks)
        if (row < 3) {
            if (column < 3) {
                return this.safeSubMatrixTest(board, 0, 3, 0, 3, number, row,
                        column);
            } else if (column < 6) {
                return this.safeSubMatrixTest(board, 0, 3, 3, 6, number, row,
                        column);
            } else {
                return this.safeSubMatrixTest(board, 0, 3, 6, 9, number, row,
                        column);
            }
        } else if (row < 6) {
            if (column < 3) {
                return this.safeSubMatrixTest(board, 3, 6, 0, 3, number, row,
                        column);
            } else if (column < 6) {
                return this.safeSubMatrixTest(board, 3, 6, 3, 6, number, row,
                        column);
            } else {
                return this.safeSubMatrixTest(board, 3, 6, 6, 9, number, row,
                        column);
            }
        } else {
            if (column < 3) {
                return this.safeSubMatrixTest(board, 6, 9, 0, 3, number, row,
                        column);
            } else if (column < 6) {
                return this.safeSubMatrixTest(board, 6, 9, 3, 6, number, row,
                        column);
            } else {
                return this.safeSubMatrixTest(board, 6, 9, 6, 9, number, row,
                        column);
            }
        }

    }

    /**
     * Function that looks to see if the {@code number} is in the subMatrix of
     * {@code board} using the ranges of {@code rowLow} to {@code rowHigh} and
     * {@code colLow} to {@code colHigh}.
     *
     * @param board
     *            the 9x9 board of digits [0-9]
     * @param rowLow
     *            the lower range of the rows
     * @param rowHigh
     *            the higher range of the rows
     * @param colLow
     *            the lower range of the columns
     * @param colHigh
     *            the higher range of the columns
     * @param number
     *            the number to check within the submatrix of {@code board}
     * @param row
     *            the row of the actual spot to check
     * @param column
     *            the column of the actual spot to check
     * @return boolean value false if sub matrix contains {@code number} and
     *         true otherwise
     */
    private boolean safeSubMatrixTest(int[][] board, int rowLow, int rowHigh,
            int colLow, int colHigh, int number, int row, int column) {
        for (int i = rowLow; i < rowHigh; i++) {
            for (int j = colLow; j < colHigh; j++) {
                if (board[i][j] == number && (i != row || j != column)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Static method to find first available square in {@code board}. Available
     * square is denoted with a 0.
     *
     * @param board
     *            the 9x9 board of digits [0-9]
     * @return ArrayList<Integer> with the first entry being the row and the
     *         second entry being the column. The {@code board}[row][column] is
     *         the first available space. If the returned {@code board} has size
     *         0, then there is no open space.
     */
    private ArrayList<Integer> firstOpenSpace(int[][] board) {
        ArrayList<Integer> rowCol = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    rowCol.add(i);
                    rowCol.add(j);
                }
            }
        }
        return rowCol;
    }

    public static void main(String[] args) {
        int[][] board = new int[][] { { 0, 0, 0, 2, 6, 0, 7, 0, 1 },
                { 6, 8, 0, 0, 7, 0, 0, 9, 0 }, { 1, 9, 0, 0, 0, 4, 5, 0, 0 },
                { 8, 2, 0, 1, 0, 0, 0, 4, 0 }, { 0, 0, 4, 6, 0, 2, 9, 0, 0 },
                { 0, 5, 0, 0, 0, 3, 0, 2, 8 }, { 0, 0, 9, 3, 0, 0, 0, 7, 4 },
                { 0, 4, 0, 0, 5, 0, 0, 3, 6 }, { 7, 0, 3, 0, 1, 8, 0, 0, 0 } };
        Solver solver = new Solver(board);
        solver.print();
        solver.solve();
        solver.print();
    }
}
