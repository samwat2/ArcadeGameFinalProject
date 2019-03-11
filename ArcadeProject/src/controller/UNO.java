package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Card;
import model.Deck;
import model.Player;
import model.SpecialCards;

public class UNO extends Application {

	public Random rng = new Random();// random for initial
	public static Player currentPlayer;// current player
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Player winner;
	public static Card card;// current card
	public static SpecialCards specialCards;
	public static Card currentCard;
	public static Deck deck;// all 112 cards.
	public static ArrayList<Card> discardPile = new ArrayList<Card>();
	public Button endTurn = new Button();
	private static boolean quit = false;
	private ButtonBase draw = new Button();
	private StackPane background = new StackPane();

	@Override
	public void start(Stage primaryStage) {
		try {
			draw.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// This will have the player to draw a card form the draw pile, can only do once
					// per turn

				}
			});
			endTurn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// This will end turn when player is done with their turn

				}
			});
			deck = new Deck();
			String url = "file:Sprites/red background.jpg";
			Image backGround = new Image(url);
			// ImageView view= new ImageView(url);
			BackgroundImage back = new BackgroundImage(backGround, null, null, null,
					new BackgroundSize(800, 800, false, false, true, true));

			background.setBackground(new Background(back));

			Scene scene = new Scene(background, 1050, 800);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Arcade: UNO");
			background.setPadding(new Insets(20, 80, 20, 80));

			primaryStage.setResizable(false);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mainMenu() {
		do {
			playGame();
		} while (quit);

	}

	// this method needs to be tested
	public static void createPlayers() {
		int input2, count = 0;
		boolean correct = false;
		// text-field for amount of players
		final TextField amountOfPlayers = new TextField();
		// text-field text
		amountOfPlayers.setText("How many players will be joining?");
		// gathered input
		String input = amountOfPlayers.getText();
		// parsed input
		int numOfPlayers = Integer.parseInt(input);
		do {
			// player's info per player
			final TextField name = new TextField();
			name.setText("Enter your name.");
			PasswordField key = new PasswordField();
			key.setText("Your key.");
			input2 = Integer.parseInt(key.getText());
			currentPlayer = new Player(name.getText(), input2);
			currentPlayer.setHand(deck.retrieveInitialCards());
			players.add(currentPlayer);
			count++;
			correct = count == numOfPlayers;
		} while (correct);

	}

	public static void deckInit() {
		// new deck
		deck = new Deck();
		// shuffle deck
//		System.out.println(deck.toString());
//		System.out.println(deck.getCards().size());
		//initial
//		card = deck.getCards().get(0);
//		int index = deck.getCards().(106);
//		System.out.println(index);
		card = deck.getCards().get(92);
		deck.shuffleDeck();
		discardPile.add(card);
	}

	public static void playGame() {
		boolean declaredWinner = false;
		int count = 0;
//		createPlayers();
//		 tested out players
		deckInit();
		currentPlayer = new Player("Sammy", 2468);
		currentPlayer.setHand(deck.retrieveInitialCards());
		players.add(currentPlayer);
		currentPlayer = null;
		currentPlayer = new Player("Howard", 1111);
		currentPlayer.setHand(deck.retrieveInitialCards());
		players.add(currentPlayer);
		currentPlayer = null;
		currentPlayer = new Player("Lowzie", 4444);
		currentPlayer.setHand(deck.retrieveInitialCards());
		players.add(currentPlayer);
		currentPlayer = null;
		currentPlayer = new Player("Izzie", 2222);
		currentPlayer.setHand(deck.retrieveInitialCards());
		players.add(currentPlayer);
		do {
			currentPlayer = null;
			int turn = (count % players.size());
			currentPlayer = players.get(turn);
			System.out.println(currentPlayer.getName());
//			playerKeyEntry(currentPlayer);
			legalMoves();
//			checkHand();
			System.out.println(getCurrentCard().toString());
//			 System.out.println(player.getName() + ", " + "hand " + Arrays.toString(player.getHand()));
			count++;
//			declaredWinner = declareWinner();
		} while (count != players.size());
//		checkHand();

	}

	public static Card getCurrentCard() {//this method needs to be tested.
		int index = discardPile.lastIndexOf(card);// gets the index of the last card added to the ArrayList
		currentCard = discardPile.get(index);// sets the current card from the discard pile.
		return currentCard;
	}

	public static void playerKeyEntry(Player player) {
		// checks key for player
		int input = 0;
		do {
			final PasswordField key = new PasswordField();
			key.setText("Please enter your key");
			input = Integer.parseInt(key.getText());
			if (input == player.getKey()) {
				currentPlayer = player;
			}
		} while (input != player.getKey());
		legalMoves(); //makes cards that are legal to play play-able
	}

	public static void checkHand() {
		for(Player player: players) {
			System.out.println(player.getName());
			System.out.println(Arrays.toString(player.getHand()));
		}
//		System.out.println(deck.toString());
		System.out.println(deck.getCards().size());
	}

	public static void legalMoves() {
		// THIS METHOD NEEDS TO BE TESTED
		currentCard = getCurrentCard();//retrieves the card the player needs to play
		System.out.println("currentCard: " + currentCard);
//		Arrays.toString(player.getHand());
		System.out.println("\n\nOLD:" + Arrays.toString(currentPlayer.getHand())); //shows the card

		for (int i = 0; i < currentPlayer.getHand().length-1; i++) {
			// if the card-color has the same color
			if (currentPlayer.getHand()[i].getCardColor() == getCurrentCard().getCardColor()) {
				// set each boolean for the player to decide which to play.
				currentPlayer.getHand()[i].setPlayable(true); //allows the card to be playable
			}
			// if the face value is the same integer
			if (currentPlayer.getHand()[i].getFaceValue() == getCurrentCard().getFaceValue()) {
				// set each boolean for the player to decide which to play.
				currentPlayer.getHand()[i].setPlayable(true);
			}
			// special cards
			if((getCurrentCard().getClass().equals(SpecialCards.class)) && (currentPlayer.getHand()[i].getClass().equals(SpecialCards.class))) {
//				System.out.println("nees some testing");
				//special cards can't tell the difference between them.
				if (getCurrentCard().getCardColor() == (currentPlayer.getHand()[i].getCardColor())) { 
					// set each boolean for the player to decide which to play.
					currentPlayer.getHand()[i].setPlayable(true);
				}
				if((getCurrentCard().getCardFace() == currentPlayer.getHand()[i].getCardFace())) {
					currentPlayer.getHand()[i].setPlayable(true);
				}
			}  
		}
		System.out.println("NEW:" + Arrays.toString(currentPlayer.getHand())); //shows the card
	}

	public static void draw() {
		//CHECK LOGIC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// this method needs to be tested
		// check to see if they have the option to draw
//		for (int i = 0; i < currentPlayer.getHand().length; i++) {
//			if (currentPlayer.getHand()[i] == null) {
//				currentPlayer.addCard(deck.getCards());
//			}
//		}
	}


	public static boolean declareWinner() {
		/*
		 * objective: the first player to play their hand in each round scores points
		 * per card on their opponents hand.
		 */
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getCurrentPoints() == 0) {
				winner = players.get(i);
				return true;
			}
		}
		return false;
	}

}
