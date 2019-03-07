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
//	public static Player[] players;// all the players or maybe an arrayList?
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Player winner;
	public static Card card;// current card
	public static Card currentCard;
	public static Deck deck;// all 112 cards.
//	public static Card[] discardPile; // discard pile
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
//			players[count] = player;
			players.add(currentPlayer);
			count++;
			correct = count == numOfPlayers;
		} while (correct);

	}

	public static void deckInit() {
		// new deck
		deck = new Deck();
		// shuffle deck
		deck.shuffleDeck();
//		initial
		card = deck.getCards()[1];
		discardPile.add(card);
	}

	public static void playGame() {
		boolean declaredWinner = false;
		int count = 0;
//		createPlayers();
//		 tested out players
		currentPlayer = new Player("Sammy", 2468);
		players.add(currentPlayer);
		currentPlayer = new Player("Howard", 1111);
		players.add(currentPlayer);
		currentPlayer = new Player("Lowzie", 4444);
		players.add(currentPlayer);
		currentPlayer = new Player("Izzie", 2222);
		players.add(currentPlayer);
		deckInit();
		do {
			currentPlayer = null;
			int turn = (count % players.size());
			currentPlayer = players.get(turn);
			System.out.println(currentPlayer.getName());
			drawHand(currentPlayer);
//			playerKeyEntry(player);
			cardPlay();
//			 System.out.println(player.getName() + ", " + "hand " + Arrays.toString(player.getHand()));
			count++;
//			declaredWinner = declareWinner();
		} while (!declaredWinner);

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
				drawHand(player);
			}
		} while (input != player.getKey());
		cardPlay();
	}

	public static void checkHand() {
		// TODO!
		// cards in hand
		currentPlayer.getHand();
		// points
		currentPlayer.getCurrentPoints();
		// display hand only via gui
	}

	public static void cardPlay() {
		// THIS METHOD NEEDS TO BE TESTED
		currentCard = getCurrentCard();//retrieves the card the player needs to play
		System.out.println("currentCard: " + currentCard);
//		Arrays.toString(player.getHand());
		System.out.println("\n\n" + Arrays.toString(currentPlayer.getHand())); //shows the card
	
		// player must first select the card
		
		// then we must check to see if it is a legal move
		
		// if the card isn't play-able then it won't be selectable
		
		// what options the player had to play
		for (int i = 0; i < currentPlayer.getHand().length; i++) {
			// if the card-color has the same color
			if (currentPlayer.getHand()[i].getCardColor() == getCurrentCard().getCardColor()) {
				currentPlayer.getHand()[i].setPlayable(true); //allows the card to be playable
				// first must display all options.
				// maybe edit card class for isPlayable boolean and this method can check and
				// set each boolean for the player to decide which to play.
				// edit player class to have the ability to have many cards.
				// if players have no cards to match
				// re-draw once
				// has the option of laying down the card
				discardPile.add(currentPlayer.getHand()[i]);
				// player must draw new card
				draw();
			}
			// if the face value is the same integer
			if (currentPlayer.getHand()[i].getFaceValue() == getCurrentCard().getFaceValue()) {
				currentPlayer.getHand()[i].setPlayable(true);
				// has the option of laying down the card
				discardPile.add(currentPlayer.getHand()[i]);
				// player must draw new card
				draw();// has the option of laying down the card
			}
			// special cards
			if(getCurrentCard().getClass().getName() == "SpecialCards") {
				System.out.println("nees some testing");
				if ((currentPlayer.getHand()[i]).getCardFace() == getCurrentCard().getCardFace()) { 
					currentPlayer.getHand()[i].setPlayable(true);/*
				 * problem with calling the special cards with custom methods through the card
				 * class.
				 */
					// has the option of laying down the card
//					discardPile.add(player.getHand()[i]);
					// player must draw new card
//					draw();// has the option of laying down the card
				}
			}
		}
		System.out.println(Arrays.toString(currentPlayer.getHand())); //shows the card
	}

	public static void draw() {
		// this method needs to be tested
		// check to see if they have the option to draw
		for (int i = 0; i < currentPlayer.getHand().length; i++) {
			if (currentPlayer.getHand()[i] == null) {
				currentPlayer.addCard(deck.getCards()[0]);
			}
		}
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

	public static void drawHand(Player player) {
		// every player initially gets a hand of seven cards
		Card[] newHand = new Card[7];
		for (int q = 0; q < 7; q++) {
			newHand[q] = deck.getCards()[q];
		}
		player.setHand(newHand);
	}

}
