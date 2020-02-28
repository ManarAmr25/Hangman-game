package eg.edu.alexu.csd.datastructure.hangman;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Hangman x = new Hangman();	//create the game object
		x.setMaxWrongGuesses(6);	//set max wrong guesses
		x.selectRandomSecretWord();	//select the random secret word
		Scanner s = new Scanner(System.in);
		String c;
		System.out.print("Guess a letter : ");
		c = s.next();
		String guess = x.guess(c.charAt(0));
		while (x.maxAvailableGuesses != 0) {
			System.out.println(guess);
			System.out.print("Guess a letter : ");
			c = s.next();
			guess = x.guess(c.charAt(0));
		}
		
		
		
	}
		
}
