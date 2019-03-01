package controller;

import java.awt.TextField;
import java.util.Random;

import javafx.scene.control.PasswordField;
import model.Card;
import model.Deck;
import model.Player;
import sun.security.tools.KeyStoreUtil;
//don't touch
public class UNO {
	
	public Random rng = new Random();//random for initial
	public static Player player;//current player
	public static Player[] players;//all the players or maybe an arrayList?
	public Card card;//current card
	public Deck deck;//all 112 cards.
	public Card[] dicardPile; //dicard pile
	
	public static void mainMenu() {
		//calls the game
	}
	
	public static void createPlayers() {
		//SAMMY
		//creates players and stores in the player array
		//LEAST AMOUNT OF PLAYERS: 2
		//MOST AMOUNT OF PLAYERS: 4
		int input2, count = 0;
		String prompt1 = "What is your name?", prompt2 = "Please enter a secret key", prompt = "How many players will be playing?";
		boolean correct = false;
		final TextField amountOfPlayers = new TextField();
		amountOfPlayers.setText("How many players will be joining?");
		String input = amountOfPlayers.getText();
		int numOfPlayers = Integer.parseInt(input);
		do {
			final TextField name = new TextField();
			name.setText("Enter your name.");
			PasswordField key = new PasswordField();
			key.setText("Your key.");
			input2 = Integer.parseInt(key.getText());
			player = new Player(name.getText(), input2);
			players[count] = player;
			count++;
			correct = count == numOfPlayers;
		}while(correct);
		
	}
	
	public static void playGame() {
		//SAMMY
		/*
		 * 1) player one enter's pin
		 * 		draws first
		 * 			player must lay a matching color down.
		 * 				either by number, color, or word.
		 * 2) test box for 'UNO'?
		 */
		
	}
	
	public static void turn() {
		//SAMMY
		//to be used in the mainMenu.
	}
	
	public static void declareWinner() {
		/*
		 * objective: the first player to play their hand in each
		 * round scores points per card on their opponents hand.
		 */
	}
}

