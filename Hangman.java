package eg.edu.alexu.csd.datastructure.hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hangman implements IHangman{
	
	public int maxAvailableGuesses;	
	private String[] dictionary = new String[15];
	private String secretWord;
	private String wordInProgress;
	
	private String setWordInProgress(int length) {
		String w = "";
		for(int i = 0; i < length ; i++) {
			w = w +"-";
		}
		return w ;
	}
	
	boolean testBuggyWord(String s) {
		if(s != null) {
			return s.matches("[^A-Za-z]");
		}
		return false;
	}
	
	private void readFile(String[] words) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("file.txt"));
			String line = br.readLine();
			int i = 0;
			while(line != null && i < words.length) {		
				if(line.length()!=0) {
					words[i] = line;
					i++;
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setDictionary(String[] words) {
		readFile(words);
		for(int i = 0 ; i < words.length ; i++) {
			this.dictionary[i] = words[i]; 
		}
	}

	@Override
	public String selectRandomSecretWord() {
		
		setDictionary(this.dictionary);
		Random r = new Random();
		int randomIndex = r.nextInt(15);
		if(this.dictionary[randomIndex] == null)
		{
			return null;
		}
		this.secretWord = this.dictionary[randomIndex];
		this.wordInProgress =  setWordInProgress(this.secretWord.length());
		return this.secretWord;
		
	}

	@Override
	public String guess(Character c) throws Exception {
		if(this.maxAvailableGuesses != 0 && c != null && this.wordInProgress.indexOf('-') != -1 && !testBuggyWord(this.secretWord)) {
			if(this.secretWord.indexOf(Character.toLowerCase(c)) != -1 || this.secretWord.indexOf(Character.toUpperCase(c)) != -1) {
				
				for(int i = 0 ; i < this.secretWord.length() ; i++) {
					if(this.secretWord.charAt(i) == Character.toLowerCase(c) || this.secretWord.charAt(i) == Character.toUpperCase(c)) {
						this.wordInProgress = this.wordInProgress.substring(0,i) + c + this.wordInProgress.substring(i+1);
					}
				}
				
				if(this.wordInProgress.indexOf('-') == -1) {
					System.out.println(this.secretWord);
					System.out.println("You win!");
					this.maxAvailableGuesses = 0;
				}
				
				return this.wordInProgress;
			}else {
				System.out.println("Wrong answer!");
				this.maxAvailableGuesses--;
				if(this.maxAvailableGuesses == 0) {
					System.out.println("Out of guesses!");
					throw new Exception("Out of guesses!");
				}
				
			}
		} 
		return null;
	}

	@Override
	public void setMaxWrongGuesses(Integer max) {
		if(max == null) {
			this.maxAvailableGuesses = 1 ;
		}else {
			this.maxAvailableGuesses = max ;
		}
		
	}

}
