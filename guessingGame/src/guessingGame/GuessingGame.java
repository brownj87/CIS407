//Jordan Brown, CIS407, Guessing Game Lab
package guessingGame;
import java.util.Random;
import java.util.Scanner;
// Step 1: Game class
class Game {
    // Data
    private int number;       // Random number to guess
    private int guessNumber;  // User's guess
    private int counter;      // Number of guesses
    // Methods
    public void generateNumberToBeGuessed() {
        Random rand = new Random();
        number = rand.nextInt(100) + 1; // Random number between 1-100
        counter = 0; // Reset counter for a new game
    }
    public void makeGuess(int guessNumber) {
        this.guessNumber = guessNumber;
        counter++;
    }
    public boolean isCorrectGuess() {
        return guessNumber == number;
    }
    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Guess the Number Game");
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println("");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("Try to guess it.");
        System.out.println("");
    }
    public void displayPleaseGuessMessage() {
        System.out.print("Enter a number between 1 and 100: ");
    }
    public void displayCorrectGuessMessage() {
        System.out.println("You got it in " + counter + " " + (counter == 1? "try" : "tries") + ".");
        if (counter <= 3) {
            System.out.println("Great work! You are a mathematical wizard.");
        } else if (counter <= 7) {
            System.out.println("Not too bad! You've got some potential.");
        } else {
            System.out.println("What took you so long?");
        }
    }
    public void displayGuessAgainMessage() {
        int difference = guessNumber - number;
        if (difference > 10) {
            System.out.println("Way too high! Guess again.");
        } else if (difference > 0) {
            System.out.println("Too high! Guess again.");
        } else if (difference < -10) {
            System.out.println("Way too low! Guess again.");
        } else {
            System.out.println("Too low! Guess again.");
        }
    }
}
// Step 2: Test class
public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        game.displayWelcomeMessage();
        String playAgain = "y";
        while (playAgain.equalsIgnoreCase("y")) {
            game.generateNumberToBeGuessed();
            game.displayPleaseGuessMessage();
            boolean correct = false;
            while (!correct) {
                int userGuess = scanner.nextInt();
                game.makeGuess(userGuess);
                if (game.isCorrectGuess()) {
                    game.displayCorrectGuessMessage();
                    correct = true;
                } else {
                    game.displayGuessAgainMessage();
                    System.out.println("");
                    game.displayPleaseGuessMessage();
                }
            }
            System.out.println("");
            System.out.print("Try again? (y/n): ");
            playAgain = scanner.next();
        }
        System.out.println("Bye - Come back soon!");
        scanner.close();
    }
}