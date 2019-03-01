package controller;

import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import model.Player;

public class UNO extends Application {

	public Random rng = new Random();// random for initial
	public Player player;// current player
	public Player[] players;// all the players
	public Card card;// current card
	public Deck deck;// all 112 cards.

	@Override
	public void start(Stage primaryStage) throws Exception {
		

	}

	public static void mainMenu() {
		// calls the game
	}

	public static void createPlayers() {
		// creates players and stores in the player array
	}

	public static void playGame() {
		// game
		// additional methods required to fulfill game requirements
	}

	public static void turn() {
		// to be used in the mainMenu.
	}

	public static void declareWinner() {
		// determines which player has no cards and has the highest point value
		// other hands.
	}

}
