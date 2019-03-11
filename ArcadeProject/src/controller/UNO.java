package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import application.Main;
import enums.CardColor;
import enums.CardFace;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

	public static Player winner;
	public static Card card;// current card
	public static SpecialCards specialCards;
	public static Card currentCard;
	public static Deck deck;// all 112 cards.
	public static ArrayList<Card> discardPile = new ArrayList<Card>();
	private static Main main = new Main();
	private static boolean quit = false;
	private Button backButton = new Button("Back to the Main Menu");
	private Button draw = new Button("Draw");
	private Button endTurn = new Button("End Turn");
	private static StackPane background = new StackPane();
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
//		do {
		playGame();
//		} while (quit);

	}

	// this method needs to be tested
	public static void createPlayers() {
		int input2, count = 0, numOfPlayers = 0;
		boolean correct = false;
		// text-field for amount of players
		final TextField amountOfPlayers = new TextField();
		// text-field text
		amountOfPlayers.setText("How many players will be joining?");
		String input;
		do {
			// gathered input
			input = amountOfPlayers.getText();
			// parsed input
			numOfPlayers = Integer.parseInt(input);
		}while(numOfPlayers > 0 || numOfPlayers < 4);
		//AI
		if(numOfPlayers == 1) {
			AI player2 = new AI("player2", deck.retrieveInitialCards(), 000);
			players.put(000, player2);
		}
//		do {
		for (int i = 0; i < numOfPlayers; i++) {
			// player's info per player
			final TextField name = new TextField();
			name.setText("Enter your name.");
			PasswordField key = new PasswordField();
			key.setText("Your key.");
			input2 = Integer.parseInt(key.getText());
			currentPlayer = new Player(name.getText(), input2);

			players.put(input2, currentPlayer);
		}
			currentPlayer.setHand(deck.retrieveInitialCards());

//		} while (correct);

	}

	public static void deckInit() {
		// new deck
		deck = new Deck();
		// shuffle deck
		deck.shuffleDeck();
		card = deck.getCards().get(0);
		discardPile.add(card);
	}
	
	public static void testPlayers() {
		currentPlayer = new Player("Sammy", 2468);
		players.put(2468, currentPlayer);
		currentPlayer = new Player("Howard", 1111);
		players.put(1111, currentPlayer);
		currentPlayer = new Player("Lowzie", 4444);
		players.put(4444, currentPlayer);
		currentPlayer = new Player("Izzie", 2222);
		players.put(2222, currentPlayer);
		
	}

	public static void playGame() {
		boolean declaredWinner = false;
		int count = 0;
//		createPlayers();
//		 tested out players
		testPlayers();
		deckInit();
//		do {
//		currentPlayer = null;
		int turn = (count % players.size());
		currentPlayer = players.get(turn);
//		System.out.println(currentPlayer.getName());
//		drawHand(currentPlayer);
//		playerKeyEntry(currentPlayer);
//		cardPlay();
		currentPlayer.setHand(deck.retrieveInitialCards());
		legalMoves();
		moves();
		System.out.println(getCurrentCard().toString());
		count++;
//			declaredWinner = declareWinner();


	}
	
	public static void moves() {
		//if the card is playable then this will happen
			//all are selectable
		for(Card playable : currentPlayer.getHand()) {
			if(playable.isPlayable()){
				System.out.println(playable);
			}
		}
			//once played remove the card from players deck
				//if special card then 
					//skip count ++
					//reverse count--
					//wild card
						//all players draw
					//wild4
						//all players draw four cards
					//draw2
						//next player has to draw two cards
		//some players may not have the option to play so they must draw or skip their turn
	}
	
	public static void addingNormal() {
		if(currentCard.getFaceValue() != 0) {
			
		}
	}
	
	public static void skipCard(Card card) {
		
	}
	
	public static void reverseCard(Card card) {
		
	}
	
	public static void wildCard(Card card) {
		
	}
	
	public static void wild4Card(Card card) {
		
	}
	
	public static void draw2Card(Card card) {
		
	}

	public static Card getCurrentCard() {// this method needs to be tested.
		int index = discardPile.lastIndexOf(card);// gets the index of the last card added to the ArrayList
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

		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String key = String.valueOf(player.getKey());
				if (textField.getText() == key) {
					drawHand(player);
				} else {
					Label no = new Label("Not the correct key");
					no.setVisible(true);
					background.getChildren().add(no);
				}
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
//		for(Player player: players) {
//			System.out.println(player.getName());
//		for (Player player : players.values()) {
//			System.out.println(Arrays.toString(player.getHand()));
//		}
//		System.out.println(deck.toString());
		System.out.println(deck.getCards().size());
	}

		public static void legalMoves() {
			// THIS METHOD NEEDS TO BE TESTED
			currentCard = getCurrentCard();//retrieves the card the player needs to play
//			System.out.println("currentCard: " + currentCard);
//			System.out.println("\n\nOLD:" + Arrays.toString(currentPlayer.getHand())); //shows the card

			for (int i = 0; i < currentPlayer.getHand().size()-1; i++) {
				
				CardColor currentCardColor = getCurrentCard().getCardColor();
				CardColor currentPlayerCardColor = currentPlayer.getHand().get(i).getCardColor();
				
				int currentCardValue = currentPlayer.getHand().get(i).getFaceValue();
				int currentPlayerCardValue = getCurrentCard().getFaceValue();
				
				// if the card-color has the same color
				// set each boolean for the player to decide which to play.
				if (currentCardColor  == currentPlayerCardColor ) {
					currentPlayer.getHand().get(i).setPlayable(true); //allows the card to be playable
				}
				// if the face value is the same integer
				if (currentCardValue == currentPlayerCardValue) {
					//if the value of the player's card is bigger then the last
					if(currentCardValue < currentPlayerCardValue) {
						//if it maybe a special card with high value
						if(currentCardValue <= 19 || currentPlayerCardValue <= 19) {
							currentPlayer.getHand().get(i).setPlayable(true);
						}	
					}
				}
				// special cards
				if((getCurrentCard().getClass().equals(SpecialCards.class)) && (currentPlayer.getHand().get(i).getClass().equals(SpecialCards.class))) {
					if ((currentCardColor == currentPlayerCardColor)) { 
						currentPlayer.getHand().get(i).setPlayable(true);
					}
					CardFace currentFace = (getCurrentCard().getCardFace());
					CardFace currentPlayerFace = (currentPlayer.getHand().get(i).getCardFace());
					if(0 == currentFace.compareTo(currentPlayerFace)) {
						currentPlayer.getHand().get(i).setPlayable(true);
					}
				}  
			}
//			System.out.println("NEW:" + Arrays.toString(currentPlayer.getHand())); //shows the card
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

	public static void drawHand(Player player) {
		// every player initially gets a hand of seven cards
		Card[] newHand = new Card[7];
		
		int x = -100;
		int y = -100;
		for (int q = 0; q < 7; q++) {
//			newHand[q] = deck.getCards()[q];
			newHand[q] = deck.getCards().get(q);
//			Image image = new Image("file:Sprites/" + deck.getCards()[q] +".png", 100, 100, false, false);
//
//			ImageView flipped = new ImageView(image);
//			
//			flipped.setTranslateX(x);
//			flipped.setTranslateY(y);
//			background.getChildren().add(flipped);
			
			x += 100;
			y += 100;
		}
//		player.setHand(newHand);
	}

}
