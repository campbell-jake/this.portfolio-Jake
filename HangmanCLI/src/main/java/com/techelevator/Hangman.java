package com.techelevator;

import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		
		Scanner theKeyboard = new Scanner(System.in);
		String secretWord = "";
		
		System.out.println("Please provide the word you would like your opponent to guess: ");
		while(!theKeyboard.hasNext("[A-Za-z]+")) {
			
			System.out.println("Please utilize only alphabetical characters in choosing your word. Try again: ");
			theKeyboard.nextLine();
		}
		
		secretWord = theKeyboard.nextLine();
		System.out.println("How many rounds of guessing would you like to allow?");
		
		String numberOfGuesses = null;
		int numberOfRounds = 0;
		while(numberOfGuesses == null) {
			try {
				numberOfGuesses = theKeyboard.nextLine();
				numberOfRounds = Integer.valueOf(numberOfGuesses);
			}
			catch(NumberFormatException exceptionObject) {
				System.out.println("Rather than: " +  '"' + numberOfGuesses + '"' + ", please provide a numerical input as digits.");
				numberOfGuesses = null;
			}
		}

		StringBuilder guessInProgress = new StringBuilder(secretWord.length()); 
		for(int i = 1; i <= secretWord.length(); i++) {
			guessInProgress.append("*");
		}
		System.out.println("Secret word: " + guessInProgress.toString() + "\n");

		boolean userWon = false;
		while(numberOfRounds > 0 && userWon == false) {
		
			String userGuess = null;
			while(userGuess == null) {
				System.out.println("Please provide a guess, limited to a single character");
				userGuess = theKeyboard.nextLine();
				if(userGuess.length() > 1 || userGuess.isEmpty()) {
					userGuess = null;	
				}
			}
		
			if(secretWord.toLowerCase().contains(userGuess.toLowerCase())) {
				int lastIndex = 0;
				while (true) {
					lastIndex = secretWord.indexOf(userGuess, lastIndex);
					if (lastIndex > -1) {
						guessInProgress.setCharAt(lastIndex, userGuess.charAt(0));
						lastIndex++;
						continue;
					}
					else {
						break;
					}
				}
			}
			else {
				numberOfRounds--;
			}
			
			System.out.println(guessInProgress.toString());
			
			if (guessInProgress.toString().equals(secretWord)) {
				
				System.out.print("You won! \n");
				userWon = true;
			}
		
		if(!guessInProgress.toString().equals(secretWord)) {
			System.out.println("Number of attempts remaining: " + numberOfRounds);
		}
		
		if(numberOfRounds == 0) {
			System.out.println("The correct word was: " + secretWord);
		}
		
		}
		
		theKeyboard.close();
			
//		System.out.println("O");
//		
//		System.out.println("O");
//		System.out.println("|");
//		
//		System.out.println("O");
//		System.out.println("|");
//		System.out.println("|");
//		
//		System.out.println("  O");
//		System.out.println(" \\|");
//		System.out.println("  |");
//		
//		System.out.println("  O");
//		System.out.println(" \\|/");
//		System.out.println("  |");
//		
//		System.out.println("  O");
//		System.out.println(" \\|/");
//		System.out.println("  |");
//		System.out.println(" / ");
//		
//		System.out.println("  O");
//		System.out.println(" \\|/");
//		System.out.println("  |");
//		System.out.println(" / \\");	
	}

}
