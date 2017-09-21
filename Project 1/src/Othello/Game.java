/**
 * @author: Jacob Regan
 * 
 */
package Othello;
import java.util.Scanner;

public class Game {
	//default scores on the board
	private int whiteScore = 2;
	private int blackScore = 2;

	//Prints the winner of the game
	public void printWinner() {
		if (whiteScore > blackScore) {
			System.out.print("WHITE HAS WON!");
		} else if (whiteScore < blackScore) {
			System.out.print("Black HAS WON!");
		} else {
			System.out.print("IT'S A DRAW!");
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		Board board = new Board();
		Scanner scan = new Scanner(System.in);
		int turnNumber = 1; //counts the number of turns.
		int player;
		int input = 0;
		String turnplayer;
		boolean endGameCondition = false;
		boolean endSession = false;
		while (endSession == false) { //Ends the session if player chooses to not start new game.
			while (endGameCondition == false) {//Ends game if condition is met
				boolean endTurnCondition = false;
				boolean validInput = false;
				//sets out of range row and column coordinates
				int row = -1, column = -1;

				if (turnNumber % 2 == 0) { //Determines whose turn it is.
					player = -1;
					turnplayer = "White";
				} else {
					player = 1;
					turnplayer = "Black";
				}
				board.printBoard(); //Prints the board
				System.out.println(turnplayer + "'s turn.");
				boolean movePossible = board.checkForMoves(player);
				if (movePossible == true) {
					while (validInput == false) {
						try {
							System.out.println("Press 1 to start turn, 2 to skip turn, or 3 to quit.");
							input = scan.nextInt();
							if (input == 1 || input == 2 || input == 3) {
								validInput = true;
							} else {
								System.out.println("Invalid entry. Try again.");
							}
						} catch (java.util.InputMismatchException e) {
							System.out.println("Invalid entry.  Try again.");
						}
					}
					//Checks whether the player has chosen to skip their turn, quit, or play.
					if (input == 2) {
						endTurnCondition = true;
						System.out.println(turnplayer + " has skipped their turn.");
						turnNumber++;
					}
					if (input == 3) {
						endGameCondition = true;
						System.out.println(turnplayer + " has opted to quit.");

					}
					if (input == 1) {
						while (endTurnCondition == false) { //Continues the turn until a valid position has been inputed

							try {
								System.out.println("Enter the row number:");
								row = scan.nextInt() - 1;
								System.out.println("Enter the column number:");
								column = scan.nextInt() - 1;
							} catch (java.util.InputMismatchException e) {
							}

							boolean validMove = board.checkMoves(row, column, player);
							//if input is a valid move, ends the turn
							if (validMove == true) {
								board.updateBoard(row, column, player);
								turnNumber++;
								endTurnCondition = true;
							} else {
								System.out.println("Invalid move. Try again.");
							}
						}
					}
				} else {
					System.out.println("No moves available!");
					turnNumber++;
					if (board.checkForMoves(-player) == false) {//If no moves are available, ends the game.
						endGameCondition = true;
					}
				}
			}
			//Declares the outcome of the game
			game.blackScore = board.countPieces(1);
			game.whiteScore = board.countPieces(-1);
			System.out.println("\nGAME OVER!");
			System.out.println("Black Points: " + game.blackScore);
			System.out.println("White Points: " + game.whiteScore);
			game.printWinner();
			//Allows player to play again if they wish.
			System.out.println("Would you like to play again?" + "Press 1 for Yes and anything else for No.");
			input = 0;
			try {
				input = scan.nextInt();
				if (input != 1) {
					endSession = true;
				}
			} catch (java.util.InputMismatchException e) {
				endSession = true;
			}
		}
		scan.close();
	}
}
