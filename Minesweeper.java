//The purpose of Minesweeper.java is to implement the core functionality of the Minesweeper game.
// It manages the game board, randomly places mines, calculates the numbers for non-mine cells
// (indicating adjacent mines), and handles player actions such as revealing or flagging cells.
// It also contains the logic for checking whether the player has won or lost the game.

//This part of the code:
//Initialize the game board and place mines randomly.
//Calculates the number of adjacent mines for each non-mine cell.
//Allow splayers to reveal cells or flag/unflag potential mine locations.
//Checks win and loss conditions based on the player's actions.
//Handle recursive revealing of cells if the revealed cell has no adjacent mines.


import java.util.Random;

public class Minesweeper {

    // Data members
    private char[][] board; // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over

    // Constructor to initialize the board with the specified dimensions and number of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.gameOver = false;

        initializeBoard();
        placeMines();
        calculateNumbers();
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public void setGameOver(boolean status) {
        this.gameOver = status;
    }

    // Method to initialize the game board with empty values
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '-'; // Initially hidden cells
            }
        }
    }

    // Method to randomly place mines on the board
    private void placeMines() {
        Random rand = new Random();
        int placedMines = 0;
        while (placedMines < numMines) {
            int row = rand.nextInt(rows);
            int col = rand.nextInt(cols);

            if (!mines[row][col]) {
                mines[row][col] = true;
                placedMines++;
            }
        }
    }

    // Method to calculate nummbers on the board for non-mine cells
    private void calculateNumbers() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!mines[row][col]) {
                    board[row][col] = (char) ('0' + countAdjacentMines(row, col));
                }
            }
        }
    }

    // Helper method to count the number of adjacent mines to a given cell
    private int countAdjacentMines(int row, int col) {
        int mineCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && mines[newRow][newCol]) {
                    mineCount++;
                }
            }
        }
        return mineCount;
    }

    // Method to display the current state of the board
    public void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (revealed[i][j]) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    // Method to handle a player's move (reveal a cell or place a flag)
    public void playerMove(int row, int col, String action) {
        if (action.equals("reveal")) {
            revealCell(row, col);
        } else if (action.equals("flag")) {
            flagCell(row, col);
        } else if (action.equals("unflag")) {
            unflagCell(row, col);
        }
    }

    // Method to check if the player has won the game
    public boolean checkWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!mines[i][j] && !revealed[i][j]) {
                    return false; // Non-mine cell is still hidden
                }
            }
        }
        return true; // All non-mine cells revealed
    }

    // Method to check if the player has lost the game
    public boolean checkLoss(int row, int col) {
        return mines[row][col]; // True if the revealed cell contains a mine
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    private void revealCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || revealed[row][col]) {
            return; // Out of bounds or already revealed
        }
        revealed[row][col] = true;

        if (board[row][col] == '0') {
            // Recursively reveal adjacent cells if no adjacent mines
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    revealCell(row + i, col + j);
                }
            }
        }
    }

    // Method to flag a cell as containing a mine
    private void flagCell(int row, int col) {
        if (!revealed[row][col]) {
            board[row][col] = 'F'; // Mark as flagged
        }
    }

    // Method to unflag a cell
    private void unflagCell(int row, int col) {
        if (board[row][col] == 'F') {
            board[row][col] = '-'; // Unflag and revert to hidden
        }
    }
}
