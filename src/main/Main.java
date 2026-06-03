package main;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static final String INTRO_MESSAGE = """
			Welcome to the Number Guessing Game!
			I'm thinking of a number between 1 and 100.
			You have 5 chances to guess the correct number.
			""";
	
	public static final int MIN = 1, MAX = 100;
	
	public static void printIntroMessage() {
		System.out.println(INTRO_MESSAGE);
	}
	
	public static int getDifficultyLevel(Scanner scanner) {
		System.out.println("""
			Please select the difficulty level:
			1. Easy (10 chances)
			2. Medium (5 chances)
			3. Hard (3 chances)
			""");
		
		System.out.print("Enter your choice: ");
		int chosenDifficulty = scanner.nextInt();
		scanner.nextLine();
		
		while (chosenDifficulty < 1 || chosenDifficulty > 3) {
			System.out.println("\nInvalid choice!");
			System.out.println("""
					\nPlease select the difficulty level:
					1. Easy (10 chances)
					2. Medium (5 chances)
					3. Hard (3 chances)
					""");
			
			System.out.print("Enter your choice: ");
			chosenDifficulty = scanner.nextInt();
			scanner.nextLine();
		}
		
		String difficultyStr = difficultyToText(chosenDifficulty);
		System.out.println("\nGreat! You have selected the " + difficultyStr + " difficulty level.");
		
		return chosenDifficulty;
	}
	
	public static String difficultyToText(int difficultyLevel) {
		return switch (difficultyLevel) {
		case 1 -> "Easy";
		case 2 -> "Medium";
		case 3 -> "Hard";
		default -> "Unknown";
		};
	}
	
	public static int difficultyToChances(int difficultyLevel) {
		return switch (difficultyLevel) {
		case 1 -> 10;
		case 2 -> 5;
		case 3 -> 3;
		default -> -1;
		};
	}
	
	public static int getRandomNumber(Random random, int min, int max) {
		return random.nextInt((max - min) + 1) + min;
	}
	
	public static int getUserGuess(Scanner scanner) {
		System.out.print("\nEnter your guess: ");
		int guessNumber = scanner.nextInt();
		scanner.nextLine();
		
		while (guessNumber < MIN || guessNumber > MAX) {
			System.out.println("\nInvalid guess number! Please choose a number between 1 - 100.");
			System.out.print("\nEnter your guess: ");
			guessNumber = scanner.nextInt();
			scanner.nextLine();
		}
		
		return guessNumber;
	}
	
	public static void main(String[] args) {
		printIntroMessage();
		
		Scanner scanner = new Scanner(System.in);
		
		int difficultyLevel = getDifficultyLevel(scanner);
		int chances = difficultyToChances(difficultyLevel);
		int attempts = 0;
		
		System.out.println("Let's start the game!");
		
		Random random = new Random();
		int randomNumber = getRandomNumber(random, MIN, MAX);
		boolean won = false;
		
		while (chances > 0) {
			int userGuess = getUserGuess(scanner);
			attempts++;
			chances--;
			
			if (userGuess == randomNumber) {
				won = true;
				break;
			}
			
			if (userGuess < randomNumber) {
				System.out.println("Incorrect! The number is greater than " + userGuess + ".");
			} else if (userGuess > randomNumber) {
				System.out.println("Incorrect! The number is less than " + userGuess + ".");
			}
			
			System.out.println("\nChances remaining: " + chances);
		}
				
		scanner.close();
		
		if (won) {
			System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");			
		} else {
			System.out.println("Game over! You've run out of chances (" + chances + ") and the number was " + randomNumber);
		}
	}
}
