package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import application.Main;
import enums.CardColor;
import enums.CardFace;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AI;
import model.Card;
import model.Deck;
import model.Player;
import model.SpecialCards;

public class UNO extends Application {

	public Random rng = new Random();// random for initial
	public static Player currentPlayer;// current player

//	public static ArrayList<Player> players = new ArrayList<Player>();
//	public static Player pl;
	public static Player winner;
	public static Card card;// current card
	public static SpecialCards specialCards;
	public static Card currentCard;
	public static Deck deck;// all 112 cards.
	public static ArrayList<Card> discardPile = new ArrayList<Card>();
	private static Main main = new Main();
	private static boolean reverse = false;
	private Button backButton = new Button("Back to the Main Menu");
	private Button draw = new Button("Draw");
	private Button endTurn = new Button("End Turn");
	private static StackPane background = new StackPane();
	private static int turnCount = 0;
	private static HashMap<Integer, Player> players = new HashMap<>();

	@Override
	public void start(Stage primaryStage) {
		try {
			backButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					main.start(primaryStage);

				}

			});
			draw.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					draw();

				}
			});
			endTurn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// This will end turn when player is done with their turn

				}
			});
			
			mainMenu();
			
			String url = "file:Sprites/red background.jpg";
			Image backGround = new Image(url);
			BackgroundImage back = new BackgroundImage(backGround, null, null, null,
					new BackgroundSize(800, 800, false, false, true, true));

			Image image = new Image("file:Sprites/flipped.png", 100, 100, false, false);

			ImageView flipped = new ImageView(image);

			background.setBackground(new Background(back));

			Scene scene = new Scene(background, 1000, 800);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Arcade: UNO");
			background.setPadding(new Insets(20, 80, 20, 80));
			backButton.setTranslateX(-400);
			backButton.setTranslateY(-380);
			draw.setTranslateX(-420);
			draw.setTranslateY(-20);
			endTurn.setTranslateX(100);
			endTurn.setTranslateY(200);
			flipped.setTranslateX(-420);
			flipped.setTranslateY(-100);
			background.getChildren().add(backButton);
			background.getChildren().add(draw);
			background.getChildren().add(endTurn);
			background.getChildren().add(flipped);

			primaryStage.setResizable(false);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mainMenu() {
		do {
		playGame();
		} while (!declareWinner());

	}

	// this method needs to be tested
	public static void createPlayers() {
		Stage primaryStage = new Stage();
		Label label1 = new Label("How Many Players");
		TextField textField = new TextField();
		Button submit = new Button("Submit");

		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				playerInfo(textField);
			}
		});


		HBox secondaryLayout = new HBox();

		secondaryLayout.setSpacing(10);
		secondaryLayout.getChildren().addAll(label1, textField);
		Scene secondScene = new Scene(secondaryLayout, 700, 100);
		Stage newWindow = new Stage();
		newWindow.setTitle("Name");
		newWindow.setScene(secondScene);
		newWindow.initModality(Modality.WINDOW_MODAL);

		newWindow.initOwner(primaryStage);
		newWindow.setX(800);
		newWindow.setY(100);
		newWindow.setResizable(false);

		secondaryLayout.getChildren().add(submit);

		newWindow.setAlwaysOnTop(true);
		newWindow.show();

	}
	
	public static void playerInfo(TextField t) {
		Label alert = new Label("Must be a number");
		try {
			Stage primaryStage = new Stage();
			int count = 0;
			int newField = Integer.parseInt(t.getText());
			for (int i = 0; i < newField; i++) {
				Label name = new Label("Enter your Name");
				TextField text = new TextField();
				Label password = new Label("Enter your key");
				PasswordField key = new PasswordField();	
				Button submit = new Button("Submit");
//	            name.setText("Enter your name.");
//	            key.setText("Your key.");
				
				HBox secondaryLayout = new HBox();

				secondaryLayout.setSpacing(10);
				secondaryLayout.getChildren().addAll(name, text, password, key);
				Scene secondScene = new Scene(secondaryLayout, 700, 100);
				Stage newWindow = new Stage();
				newWindow.setTitle("Name");
				newWindow.setScene(secondScene);
				newWindow.initModality(Modality.WINDOW_MODAL);

				newWindow.initOwner(primaryStage);
				newWindow.setX(800);
				newWindow.setY(100);
				newWindow.setResizable(false);

				secondaryLayout.getChildren().add(submit);
				
	            int input2 = Integer.parseInt(key.getText());
	            currentPlayer = new Player(name.getText(), input2, count);

	            players.put(input2, currentPlayer);
	            count++;
	            
			}
		} catch (NumberFormatException e) {
			alert.setVisible(true);
		}
		background.getChildren().add(alert);
	}

	public static void deckInit() {
		// new deck
		deck = new Deck();
		// shuffle deck
		deck.shuffleDeck();
		card = deck.getCards().get(0);
		discardPile.add(card);

//		Image image1 = new Image("file:Sprites/" + card + ".png", 100, 100, false, false);
//		
//		ImageView start = new ImageView(image1);

//		background.getChildren().add(start);

	}

	public static void testPlayers() {
		int count2 = 0;
		currentPlayer = new Player("Sammy", 2468, count2);
		players.put(2468, currentPlayer);
		count2++;
		currentPlayer = new Player("Howard", 1111, count2);
		players.put(1111, currentPlayer);
		count2++;
//		currentPlayer = new Player("Lowzie", 4444, count);
//		count++;
//		players.put(4444, currentPlayer);
//		currentPlayer = new Player("Izzie", 2222, count);
//		count++;
//		players.put(2222, currentPlayer);

	}

	public static void turn() {
		int turn = (turnCount % players.size());
		for (Player player : players.values()) {
			if (player.getId() == turn) {
				currentPlayer = player;
			}

		}

	}

	public static void playGame() {
		createPlayers();
//		testPlayers();
		deckInit();
		turn();
		playerKeyEntry(currentPlayer);
//		System.out.println(getCurrentCard().toString());
		if (reverse) {
			turnCount--;
		} else {
			turnCount++;
		}
		declareWinner();

	}

	public static void moves() {
		for (int i = 0; i < currentPlayer.getHand().size(); i++) {
			if (currentPlayer.getHand().get(i).isPlayable()) {
				// display cards
				// if selected then make the boolean false;
				// event listener
				boolean played = currentPlayer.getHand().get(i).isPlayable() == false;
				if (played) {
					discardPile.add(currentPlayer.getHand().get(i));
					currentPlayer.removeCard(currentPlayer.getHand().get(i));
					drawHand(currentPlayer);
				}
			}
		}
		// special cards
		if (getCurrentCard().getFaceValue() > 10) {
			if ((getCurrentCard().getClass().equals(SpecialCards.class))) {
				// if reverse
				if (getCurrentCard().getCardFace() == CardFace.REVERSE) {
					reverse = true;
				}
				// if skip
				if (getCurrentCard().getCardFace() == CardFace.SKIP) {
					turnCount++;
				}
				// if draw2
				if (getCurrentCard().getCardFace() == CardFace.DRAW2) {
					for (Player player : players.values()) {
						if (player.getId() != currentPlayer.getId()) {
							for (int i = 0; i < 2; i++) {
								drawHand(player);
							}
						}
					}
				}
				// if wild
				if (getCurrentCard().getCardFace() == CardFace.WILD) {
					//player decides on the new card color that's going to change.
				}
				// if wild4
				if (getCurrentCard().getCardFace() == CardFace.WILDDRAWFOUR) {
					for (Player player : players.values()) {
						if (player.getId() != currentPlayer.getId()) {
							for (int i = 0; i < 4; i++) {
								drawHand(player);
							}
						}
					}
				}
			}

		}

	}

	public static Card getCurrentCard() {// this method needs to be tested.
		int index = discardPile.lastIndexOf(card);// gets the index of the last card added to the ArrayList
//		int index = discardPile.indexOf(0);
		currentCard = discardPile.get(index);// sets the current card from the discard pile.
		return currentCard;
	}

	public static void playerKeyEntry(Player player) {
		// checks key for player
//		int input = 0;
//		do {
//			final PasswordField key = new PasswordField();
//			key.setText("Please enter your key");
//			input = Integer.parseInt(key.getText());
		Stage primaryStage = new Stage();
		Label label1 = new Label("Key:");
		TextField textField = new TextField();
		Button submit = new Button("Submit");
		Label no = new Label("Not the correct key");

		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				String key = String.valueOf(player.getKey());
				String input = textField.getText();
				int input2 = Integer.parseInt(input);
				if (input2 == player.getKey()) {
//					drawHand(player);
					currentPlayer.setHand(deck.retrieveInitialCards());
					legalMoves();
					moves();
					currentPlayer.setHand(deck.retrieveInitialCards());
					legalMoves();
					moves();
//					drawHand(player);
					no.setVisible(false);

				} else {
					no.setVisible(true);
				}
			}
		});

		no.setVisible(true);
		background.getChildren().add(no);

		HBox secondaryLayout = new HBox();

		secondaryLayout.setSpacing(10);
		secondaryLayout.getChildren().addAll(label1, textField);
		Scene secondScene = new Scene(secondaryLayout, 700, 100);
		Stage newWindow = new Stage();
		newWindow.setTitle("Name");
		newWindow.setScene(secondScene);
		newWindow.initModality(Modality.WINDOW_MODAL);

		newWindow.initOwner(primaryStage);
		newWindow.setX(800);
		newWindow.setY(100);
		newWindow.setResizable(false);

		secondaryLayout.getChildren().add(submit);

//		newWindow.setAlwaysOnTop(true);
		newWindow.show();

//		label1.setTranslateX(-170);
//		label1.setTranslateY(320);
//		submit.setTranslateX(300);
//		submit.setTranslateY(350);
//		textField.setTranslateX(300);
//		textField.setTranslateY(320);
//		background.getChildren().addAll(label1, textField, submit);

//			if(input == player.getKey()) {
//				drawHand(player);
//			}
//		}while(input!=player.getKey());
//
//	cardPlay();
//		int input = 0;
//		do {
//			final PasswordField key = new PasswordField();
//			key.setText("Please enter your key");
//			input = Integer.parseInt(key.getText());
//			if (input == player.getKey()) {
//				currentPlayer = player;
//			}
//		} while (input != player.getKey());
	}

	public static void checkHand() {

		for (Player player : players.values()) {
			System.out.println(player.getHand().toString());
		}
//		System.out.println(deck.toString());
//		System.out.println(deck.getCards().size());
	}

	public static void legalMoves() {
		// THIS METHOD NEEDS TO BE TESTED
		currentCard = getCurrentCard();// retrieves the card the player needs to play
//			System.out.println("currentCard: " + currentCard);
//			System.out.println("\n\nOLD:" + Arrays.toString(currentPlayer.getHand())); //shows the card

		for (int i = 0; i < currentPlayer.getHand().size() - 1; i++) {

			CardColor currentCardColor = getCurrentCard().getCardColor();
			CardColor currentPlayerCardColor = currentPlayer.getHand().get(i).getCardColor();
			int currentCardValue = currentPlayer.getHand().get(i).getFaceValue();
			int currentPlayerCardValue = getCurrentCard().getFaceValue();

			// if the card-color has the same color
			// set each boolean for the player to decide which to play.
			if (currentCardColor == currentPlayerCardColor) {
				currentPlayer.getHand().get(i).setPlayable(true); // allows the card to be playable
			}
			// if the face value is the same integer
			if (currentCardValue == currentPlayerCardValue) {
				if (currentCardValue < currentPlayerCardValue) {
					if (currentCardValue <= 19 || currentPlayerCardValue <= 19) {
						currentPlayer.getHand().get(i).setPlayable(true);
					}
				}
			}
			// special cards
			if ((getCurrentCard().getClass().equals(SpecialCards.class))
					&& (currentPlayer.getHand().get(i).getClass().equals(SpecialCards.class))) {
				if ((currentCardColor == currentPlayerCardColor)) {
					currentPlayer.getHand().get(i).setPlayable(true);
				}
				CardFace currentFace = (getCurrentCard().getCardFace());
				CardFace currentPlayerFace = (currentPlayer.getHand().get(i).getCardFace());
				if (0 == currentFace.compareTo(currentPlayerFace)) {
					currentPlayer.getHand().get(i).setPlayable(true);
				}
			}
		}
//			System.out.println("NEW:" + Arrays.toString(currentPlayer.getHand())); //shows the card
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
		Card newCard = null;
		int x = -100;
		int y = -100;
		for (int q = 0; q < 1; q++) {
//			newHand[q] = deck.getCards()[q];
//			newHand[q] = deck.getCards().get(q);
			newCard = deck.getCards().get(q);
			Image image = new Image("file:Sprites/" + deck.getCards().get(q) + ".png", 100, 100, false, false);

			ImageView flipped = new ImageView(image);

			flipped.setTranslateX(x);
			flipped.setTranslateY(y);
			background.getChildren().add(flipped);

			x += 100;
			y += 100;
		}
		player.addCard(newCard);
//		player.setHand(newHand);
	}

	public static void draw() {
		Card newc = getCurrentCard();
		currentPlayer.addCard(newc);
		deck.removeCard();
	}

}
