package controller;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import model.Player;
//don't touch
public class UNO extends Application{
	
	public Random rng = new Random();//random for initial
	public Player player;//current player
	public Player[] players;//all the players
	public Card card;//current card
	public Deck deck;//all 112 cards.
	public Button endTurn = new Button();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
	}
	
	
	public static void mainMenu() {
		//calls the game
		System.out.println("Welcome to UNO");
		
	}
	
	public static void createPlayers() {
		//creates players and stores in the player array
		String prompt = "What is your name?", prompt2 = "Please enter a secret key";
		
	}
	
	public static void playGame() {
		//game
			//additional methods required to fulfill game requirements
	}
	
	public static void turn() {
		//to be used in the mainMenu.
	}
	
	public static void declareWinner() {
		/*
		 * objective: the first player to play their hand in each
		 * round scores points per card on their opponents hand.
		 */
	}

}

