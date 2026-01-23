package ticTacToe;
import java.util.Scanner;

//==== TicTacToe class (not public) ====
class TicTacToe {
 private char[][] board;
 private int rowNumber;
 private int columnNumber;
 private char markSelected;
 private int turnCount;
 private Scanner input;  // single Scanner for the whole game
 // Constructor
 public TicTacToe() {
     board = new char[3][3];
     turnCount = 0;
     input = new Scanner(System.in); // create scanner once
     // Initialize the board with spaces
     for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
             board[i][j] = ' ';
         }
     }
 }
 // Display welcome message
 public void displayWelcomeMessage() {
     System.out.println("Welcome to Tic Tac Toe");
     System.out.println();
 }
// Display the board
 public void displayGrid() {
     System.out.println("+---+---+---+");
     for (int i = 0; i < 3; i++) {
         System.out.print("|");
         for (int j = 0; j < 3; j++) {
             System.out.print(" " + board[i][j] + " |");
         }
         System.out.println();
         System.out.println("+---+---+---+");
     }
     System.out.println();
 }
 // Start the game
 public void startGame() {
     boolean keepPlaying = true;
     while (keepPlaying) {
         keepPlaying = takeTurn();
     }
     input.close(); // close scanner after game ends
 }
 // Handle one turn
 public boolean takeTurn() {
     markSelected = (turnCount % 2 == 0) ? 'X' : 'O';
     System.out.println(markSelected + "'s turn");
     while (true) {
         System.out.print("Pick a row (1, 2, 3): ");
         rowNumber = input.nextInt() - 1;
         System.out.print("Pick a column (1, 2, 3): ");
         columnNumber = input.nextInt() - 1;
         if (rowNumber < 0 || rowNumber > 2 || columnNumber < 0 || columnNumber > 2) {
             System.out.println("Invalid position. Try again.");
         } else if (board[rowNumber][columnNumber] != ' ') {
             System.out.println("That spot is already taken. Try again.");
         } else {
             break;
         }
     }
     board[rowNumber][columnNumber] = markSelected;
     turnCount++;
     System.out.println();
     displayGrid();
     if (checkForWinner()) {
         System.out.println(markSelected + " wins!");
         return false;
     }
     if (turnCount == 9) {
         System.out.println("It's a tie!");
         return false;
     }
     return true;
 }
 // Check for a winner
 public boolean checkForWinner() {
     // Check rows
     for (int i = 0; i < 3; i++) {
         if (board[i][0] != ' ' &&
             board[i][0] == board[i][1] &&
             board[i][1] == board[i][2]) {
             return true;
         }
     }
     // Check columns
     for (int j = 0; j < 3; j++) {
         if (board[0][j] != ' ' &&
             board[0][j] == board[1][j] &&
             board[1][j] == board[2][j]) {
             return true;
         }
     }
     // Check diagonals
     if (board[0][0] != ' ' &&
         board[0][0] == board[1][1] &&
         board[1][1] == board[2][2]) {
         return true;
     }
     if (board[0][2] != ' ' &&
         board[0][2] == board[1][1] &&
         board[1][1] == board[2][0]) {
         return true;
     }
     return false;
 }
}
