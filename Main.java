// Umer Kamran
// CSC229
//The purpose of Main.java is to serve as the entry point for the Minesweeper game.
// It initializes a new instance of the Minesweeper game with specified dimensions (10x10 grid)
// and the number of mines (10). It contains the game loop that repeatedly displays the current
// board, simulates a player's move, and checks for win or loss conditions. The game ends when
// the player wins by revealing all non-mine cells or loses by revealing a mine.

//This part of the code..
//Starts the game by creating a Minesweeper object.
//Simulates player moves (currently hardcoded).
//Checks win and loss conditions and end the game accordingly.

public class Main {
    // Main method to start the game
    public static void main(String[] args) {
        // Create a Minesweeper game with specific dimensions and number of mines
        Minesweeper game = new Minesweeper(10, 10, 10);

        // Game loop
        while (!game.getGameOver()) {
            game.displayBoard();

            // Simulates  movefor testing (to be replaced with actual player input)
            // this is setting an example revealing cell at (0, 0)
            game.playerMove(0, 0, "reveal");

            // Check for win or loss conditions using if statment
            if (game.checkWin()) {
                System.out.println("Congratulations! You've won the game.");
                break;
            }
            if (game.checkLoss(0, 0)) {
                System.out.println("Game Over! You hit a mine.");
                game.setGameOver(true);
            }
        }
    }
}
