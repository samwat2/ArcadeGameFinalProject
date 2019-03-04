package controller;

import java.awt.TextField;
import java.util.Arrays;
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
	public static Card card;//current card
	public static Deck deck;//all 112 cards.
	public static Card[] discardPile; //discard pile
	
	public static void mainMenu() {
		playGame();
		//every player draws first
		//first draw is initial
			//if it is wild re draw
			//if it is four re draw
	}
	
	//this method needs to be tested
	public static void createPlayers() {
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
		discardPile = new Card[112];
		//new deck
		deck = new Deck();
		//shuffle deck
		deck.shuffleDeck();
//		initial
	    discardPile[0] = deck.getCards()[0];
	    System.out.println(currentCard());
		//checks key for player
//		final PasswordField key = new PasswordField();
//		key.setText("Please enter your key");
//		int input = Integer.parseInt(key.getText());
//		if(input == player.getKey()) {
			//draw
			draw(player);
//		}else {
			//throw and error and key again.
//		}
		/*
		 * 1) player one enter's pin
		 * 		draws first
		 * 			player must lay a matching color down.
		 * 				either by number, color, or word.
		 * 2) test box for 'UNO'?
		 * 3) on to the next Player
		 */
		
	}
	
	public static void play() {
		//what options the player had to play
//		String[] prompt = {""};
	}
	
	public static void checkHand() {
		for(int i = 0 ; i < 7; i ++) {
//			if(player.getHand()[i].getCardColor() == dicardPile) {}
			if(player.getHand()[i].getFaceValue() == 0) {}
			
		}
	}
	
	public static Card currentCard() {
		//gets the current card in play for the discardPile
		for(int i = 0; i < discardPile.length; i ++) {
			card = discardPile[i];
		}
		
		return card;
	}

	
	public static void declareWinner() {
		/*
		 * objective: the first player to play their hand in each
		 * round scores points per card on their opponents hand.
		 */
	}
	
	public static void draw(Player player) {
		//every player initially gets a hand of seven cards
		Card[] newHand = new Card[7];
		for(int q = 0; q < 7; q ++) {
			newHand[q] = deck.getCards()[q];
		}
		player.setHand(newHand);
	}
}

