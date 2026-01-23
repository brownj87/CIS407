package ticTacToe;
public class TicTacToeApp {
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.displayWelcomeMessage();
		game.displayGrid();
		game.startGame();
	}
}
	