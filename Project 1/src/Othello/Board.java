package Othello;

public class Board {
	int length = 8; //Sets the default length of the board
	int width = 8; //Sets the default width og the board
	
	/**Contains all the positions for the board*/
	int[][] boardArray = new int[length][width];
	
	String divider = "-----------------------------------"; //Used for making the board
	/** Variables correspond to positions for the direction[] below */
	int upperLeft = 0, upper = 1, upperRight = 2, left = 3, right = 4, lowerLeft = 5,
			lower = 6, lowerRight = 7;
	boolean direction[] = { false, false, false, false, false, false, false, false };

	/**
	 * Constructor: initializes the board with these default parameters
	 */
	public Board() {
		//Sets all the elements in boardArray to zero
		for (int i = 0; i < length; i++) {
			for (int x = 0; x < width; x++) {
				boardArray[i][x] = 0;
			}
		}
		//Sets the starting positions for the pieces
		boardArray[3][3] = -1;
		boardArray[4][4] = -1;
		boardArray[3][4] = 1;
		boardArray[4][3] = 1;

	}
	
	//Creates a formated printout for the board in ASCII art style
	public void printBoard() {
		System.out.print(" ");
		for (int x = 0; x < width; x++) {
			System.out.printf(" |%2s", x + 1);
		}
		System.out.print(" |\n");
		for (int i = 0; i < length; i++) {
			System.out.println(divider);
			System.out.print(i + 1 + " "); //Prints the row number for each row
			//Prints each row on the the board
			for (int x = 0; x < width; x++) {
				if (boardArray[i][x] == -1) { //Displays the proper piece at a position
					System.out.printf("|%2s ", "W");
				} else if (boardArray[i][x] == 1) {
					System.out.printf("|%2s ", "B");
				} else {
					System.out.printf("|%2s ", "");
				}
			}
			System.out.println("|");
		}
		System.out.println(divider); //Prints the very last horizontal line for the board

	}

/**
 * This method checks whether a given position is a valid move for the player.
 * If the inputed position is valid, then all the directions for the valid move
 * are stored in the array direction[].
 * 
 * 
 * @param row: the row value for the coordinates to be checked.
 * @param column: the column valid for the coordinates to be checked.
 * @param player: the current player (either -1 for white or 1 for black)
 * @return true if the inputed position is a valid move.  Else false.
 **/
	public boolean checkMoves(int row, int column, int player) {
		int x, y;// x corresponds to row, y to column
		if (((row < 0) || (row > 7)) || ((column < 0) || (column > 7))) {
			return false;
		} else if (boardArray[row][column] != 0) {
			return false;
		} else {
			for (x = 0; x < 8; x++) {
				direction[x] = false;
			}
			//Checks whether the upper left position relative to the inputed coords is valid
			if (row > 0 && column > 0 && boardArray[row - 1][column - 1] == -player) {
				for (x = row - 1, y = column - 1; x > 0 && y > 0 && boardArray[x][y] == -player; x--, y--) {
					//If there is a diagonal piece of turn player's color, then that position is valid
					if (boardArray[x - 1][y - 1] == player) {
						direction[upperLeft] = true; //sets that direction to valid
					}
				}
			}
			//Checks whether the upper position relative to the inputed coords is valid
			if (row > 0 && boardArray[row - 1][column] == -player) {
				for (x = row - 1; x > 0 && boardArray[x][column] == -player; x--) {
					//If there is piece of turn player's color vertically above, then that position is set to valid
					if (boardArray[x - 1][column] == player) {
						direction[upper] = true;
					}
				}
			}
			//Checks whether the upper right position relative to the inputed coords is valid
			if (row > 0 && column < 7 && boardArray[row - 1][column + 1] == -player) {
				for (x = row - 1, y = column + 1; x > 0 && y < 7 && boardArray[x][y] == -player; x--, y++) {
					if (boardArray[x - 1][y + 1] == player) {
						direction[upperRight] = true;
					}
				}
			}
			//Checks whether the left position relative to the inputed coords is valid
			if (column > 0 && boardArray[row][column - 1] == -player) {
				for (y = column - 1; y > 0 && boardArray[row][y] == -player; y--) {
					if (boardArray[row][y - 1] == player) {
						direction[left] = true;
					}
				}
			}
			//Checks whether the  right position relative to the inputed coords is valid
			if (column < 7 && boardArray[row][column + 1] == -player) {
				for (y = column + 1; y < 7 && boardArray[row][y] == -player; y++) {
					if (boardArray[row][y + 1] == player) {
						direction[right] = true;
					}
				}
			}
			//Checks whether the lower left position relative to the inputed coords is valid
			if (row < 7 && column > 0 && boardArray[row + 1][column - 1] == -player) {
				for (x = row + 1, y = column - 1; x < 7 && y > 0 && boardArray[x][y] == -player; x++, y--) {
					if (boardArray[x + 1][y - 1] == player) {
						direction[lowerLeft] = true;
					}
				}
			}
			//Checks whether the lower middle position relative to the inputed coords is valid
			if (row < 7 && boardArray[row + 1][column] == -player) {
				for (x = row + 1; x < 7 && boardArray[x][column] == -player; x++) {
					if (boardArray[x + 1][column] == player) {
						direction[lower] = true;
					}
				}
			}
			//Checks whether the lower right position relative to the inputed coords is valid
			if (row < 7 && column < 7 && boardArray[row + 1][column + 1] == -player) {
				for (x = row + 1, y = column + 1; x < 7 && y < 7 && boardArray[x][y] == -player; x++, y++) {
					if (boardArray[x + 1][y + 1] == player) {
						direction[lowerRight] = true;
					}
				}
			}
			//if any of the directions returns true, then the method returns true
			for (int i = 0; i < 8; i++) {
				if (direction[i] == true) {
					return true;
				}
			}
			return false; //if none of the directions has valid moves, than the method returns false
		}
	}
/**
 * Checks whether the player has any valid moves to make.
 * @param player: Used to get the current turn player
 * @return a boolean 
 * @see #checkMoves(x, y, player)
 */
	public boolean checkForMoves(int player) {
		boolean validPosition;
		int x, y;
		for (x = 0; x < length; x++) {
			for (y = 0; y < width; y++) {
				if (boardArray[x][y] == 0) {
					validPosition = checkMoves(x, y, player);
					if (validPosition == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Counts the number of pieces on the board for a given player and returns their score.
	 * 
	 * @param player: Used to get the player being considered.
	 * @return: Returns an integer value for their score;
	 */
	public int countPieces(int player) {
		int score = 0; //contains the score of the player
		//Count the number of positions of type player
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < width; y++) {
				if (boardArray[x][y] == player) {
					score++;
				}
			}
		}
		return score;
	}


	/**
	 * Takes the position of the user's input and flips the pieces in all valid directions as
	 * tested by the checkMoves method.
	 * 
	 * @param row: Horizontal inputed value
	 * @param column: Vertical inputed value
	 * @param player: Current player
	 * @see #checkMoves(row, column, player): Sets the values of the direction array. Must be ran first.
	 */
	public void updateBoard(int row, int column, int player) {
		int x, y; //Used to test a given position
		boardArray[row][column] = player;
		if (direction[upperLeft] == true) {
			for (x = row - 1, y = column - 1; boardArray[x][y] == -player; x--, y--) {
				boardArray[x][y] = player;
			}
		}
		if (direction[upper] == true) {
			for (x = row - 1; boardArray[x][column] == -player; x--) {
				boardArray[x][column] = player;
			}
		}
		if (direction[upperRight] == true) {
			for (x = row - 1, y = column + 1; boardArray[x][y] == -player; x--, y++) {
				boardArray[x][y] = player;
			}
		}
		if (direction[left] == true) {
			for (y = column - 1; boardArray[row][y] == -player; y--) {
				boardArray[row][y] = player;
			}
		}
		if (direction[right] == true) {
			for (y = column + 1; boardArray[row][y] == -player; y++) {
				boardArray[row][y] = player;
			}
		}
		if (direction[lowerLeft] == true) {
			for (x = row + 1, y = column - 1; boardArray[x][y] == -player; x++, y--) {
				boardArray[x][y] = player;
			}
		}
		if (direction[lower] == true) {
			for (x = row + 1; boardArray[x][column] == -player; x++) {
				boardArray[x][column] = player;
			}
		}
		if (direction[lowerRight] == true) {
			for (x = row + 1, y = column + 1; boardArray[x][y] == -player; x++, y++) {
				boardArray[x][y] = player;
			}
		}

	}
	
}
