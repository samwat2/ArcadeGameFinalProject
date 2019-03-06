package controller;

import java.util.ArrayList;
import java.util.Random;
import enums.CardColor;
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

//don't touch
public class UNO extends Application {

	public Random rng = new Random();// random for initial
	public static Player player;// current player
	public static Player[] players;// all the players or maybe an arrayList?
	public static Card card;// current card
	public static Deck deck;// all 112 cards.
//	public static Card[] discardPile; // discard pile
	public static ArrayList<Card> discardPile;
	private Button endTurn = new Button("End Turn");
	private Button draw = new Button("Draw");
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
			String url = "file:Sprites/red background.jpg";
			Image backGround = new Image(url);
			BackgroundImage back = new BackgroundImage(backGround, null, null, null,
					new BackgroundSize(800, 800, false, false, true, true));

			Image image = new Image("file:Sprites/" + card.getCardColor() + " " + card.getFaceValue() + ".png", 100, 100,
					false, false);

			background.setBackground(new Background(back));

			Scene scene = new Scene(background, 1000, 800);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Arcade: UNO");
			background.setPadding(new Insets(20, 80, 20, 80));
			draw.setTranslateX(100);
			draw.setTranslateY(150);
			endTurn.setTranslateX(100);
			endTurn.setTranslateY(200);
			background.getChildren().add(draw);
			background.getChildren().add(endTurn);
			

			primaryStage.setResizable(false);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mainMenu() {
		playGame();
		// calls the game
//		System.out.println("Welcome to UNO");

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
			player = new Player(name.getText(), input2);
			players[count] = player;
			count++;
			correct = count == numOfPlayers;
		} while (correct);

	}

	public static void playGame() {
		// new deck
		deck = new Deck();
		// shuffle deck
		deck.shuffleDeck();
//		initial
		discardPile.add(deck.getCards()[0]);
		// checks key for player
		final PasswordField key = new PasswordField();
		key.setText("Please enter your key");
		int input = Integer.parseInt(key.getText());
		if (input == player.getKey()) {
			drawHand(player);
		} else {
//		 throw and error and key again.
			// do-while
		}
		// proceed to game
		play();
		/*
		 * 1) player one enter's pin draws first player must lay a matching color down.
		 * either by number, color, or word. 2) test box for 'UNO'? 3) on to the next
		 * Player
		 */

	}

	public static void checkHand() {
		// TODO

	}

	public static void play() {
		// TODO
		// what options the player had to play
		for (int i = 0; i < 7; i++) {
			// if the card-color has the same color
			if (player.getHand()[i].getCardColor() == currentCard().getCardColor()) {
				// has the option of laying down the card
				discardPile.add(player.getHand()[i]);
				// player must draw new card
				draw();
			}
			// if the face value is the same integer
			if (player.getHand()[i].getFaceValue() == currentCard().getFaceValue()) {
				// has the option of laying down the card
				discardPile.add(player.getHand()[i]);
				// player must draw new card
				draw();// has the option of laying down the card
			}

		}
	}

	public static void draw() {
		//THIS METHOD NEEDS TO BE TESTED
		player.addCard(deck.getCards()[0]);
	}

	public static Card currentCard() {
		//THIS METHOD NEEDS TO BE TESTED
		// gets the current card in play for the discardPile
		card = discardPile.get(discardPile.size() - 1);
		return card;
	}

	public static void declareWinner() {
		/*
		 * objective: the first player to play their hand in each round scores points
		 * per card on their opponents hand.
		 */
	}

	public static void drawHand(Player player) {
		// THROWS ERRORS ON 128
		// every player initially gets a hand of seven cards
		Card[] newHand = new Card[7];
		for (int q = 0; q < 7; q++) {
			newHand[q] = deck.getCards()[q];
		}
		player.setHand(newHand);
	}

}
