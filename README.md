This Minesweeper project is a basic implementation of the classic game where players aim to reveal all non-mine cells on a grid without uncovering any hidden mines. 
The game uses a 2D array to represent the board, with mines randomly placed. Non-mine cells display numbers indicating adjacent mines, and players can either reveal
cells or flag suspected mine locations. The player wins by revealing all safe cells and loses by hitting a mine. The Main.java file serves as the entry point, 
initializing the game and running a loop that processes player actions like revealing cells or placing flags. The game ends when the player wins or loses.

The Minesweeper.java file handles the core game logic. It manages the game board, randomly places mines, calculates numbers for non-mine cells, and processes
player moves. It includes logic for recursive revealing of cells and checks win/loss conditions. Together, both files create a functioning Minesweeper game,
demonstrating key programming concepts like 2D array management, randomization, and recursion.
