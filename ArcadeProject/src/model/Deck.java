package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import enums.CardColor;
import enums.CardFace;

public class Deck {
	private final int amountOfCards = 108;// set of amount of cards the deck needs
	private Card[] cards = new Card[amountOfCards];// array for the normal card
	private Card card;
	private SpecialCards specialCards;// for the special cards

	public Deck() {
		// first for-loop for the whole deck
		for (int i = 0; i < cards.length; i++) {
			// second for-loop for red standard cards
			for (int red = 0; red < 19; red++) {
				card = new Card(CardColor.RED, (red % 10));
				cards[red] = card;
			}
			// third for-loop for blue standard cards
			for (int blue = 0; blue < 19; blue++) {
				card = new Card(CardColor.BLUE, (blue % 10));
				cards[19 + blue] = card;
			}
			// fourth for-loop for green standard cards
			for (int green = 0; green < 19; green++) {
				card = new Card(CardColor.GREEN, (green % 10));
				cards[38 + green] = card;
			}
			// fifth for-loop for yellow standard cards
			for (int yellow = 0; yellow < 19; yellow++) {
				card = new Card(CardColor.YELLOW, (yellow % 10));
				cards[57 + yellow] = card;
			}
			// sixth for-loop for skip special cards
			for (int skip = 0; skip < 8; skip++) {
				int newNumber = skip + 76;// number of places in array

				if (skip == 0 || skip < 2) {// only creating two of each
					specialCards = new SpecialCards(CardColor.RED, CardFace.SKIP);
					cards[newNumber] = specialCards;
				} else if (skip == 2 || skip < 4) {
					specialCards = new SpecialCards(CardColor.BLUE, CardFace.SKIP);
					cards[newNumber] = specialCards;
				} else if (skip == 4 || skip < 6) {
					specialCards = new SpecialCards(CardColor.GREEN, CardFace.SKIP);
					cards[newNumber] = specialCards;
				} else if (skip == 6 || skip <= 8) {
					specialCards = new SpecialCards(CardColor.YELLOW, CardFace.SKIP);
					cards[newNumber] = specialCards;
				}
			}
			// seventh for-loop for reverse special cards
			for (int reverse = 0; reverse < 8; reverse++) {
				int newNum = reverse + 84;// previous index

				if (reverse == 0 || reverse < 2) {// only creating two of each
					specialCards = new SpecialCards(CardColor.RED, CardFace.REVERSE);
					cards[newNum] = specialCards;
				} else if (reverse == 2 || reverse < 4) {
					specialCards = new SpecialCards(CardColor.BLUE, CardFace.REVERSE);
					cards[newNum] = specialCards;
				} else if (reverse == 4 || reverse < 6) {
					specialCards = new SpecialCards(CardColor.GREEN, CardFace.REVERSE);
					cards[newNum] = specialCards;
				} else if (reverse == 6 || reverse <= 8) {
					specialCards = new SpecialCards(CardColor.YELLOW, CardFace.REVERSE);
					cards[newNum] = specialCards;
				}
			}
			// eighth for-loop for draw special cards
			for (int draw = 0; draw < 8; draw++) {
				int newnum = draw + 92;// previous index

				if (draw == 0 || draw < 2) {// only creating two of each
					specialCards = new SpecialCards(CardColor.RED, CardFace.DRAW2);
					cards[newnum] = specialCards;
				} else if (draw == 2 || draw < 4) {
					specialCards = new SpecialCards(CardColor.BLUE, CardFace.DRAW2);
					cards[newnum] = specialCards;
				} else if (draw == 4 || draw < 6) {
					specialCards = new SpecialCards(CardColor.GREEN, CardFace.DRAW2);
					cards[newnum] = specialCards;
				} else if (draw == 6 || draw <= 8) {
					specialCards = new SpecialCards(CardColor.YELLOW, CardFace.DRAW2);
					cards[newnum] = specialCards;
				}
			}
			// final for-loop for wild, wild-draw-four, and blank
			for (int special = 0; special < 12; special++) {
				int newnums = special + 100;// previous index
				if (special == 0 || special < 4) {// only creating four of each
					specialCards = new SpecialCards(CardColor.BLANK, CardFace.WILDDRAWFOUR);
					cards[newnums] = specialCards;
				} else if (special == 4 || special < 8) {
					specialCards = new SpecialCards(CardColor.BLANK, CardFace.WILD);
					cards[newnums] = specialCards;
				} else if (special == 8 || special < 12) {
					specialCards = new SpecialCards(CardColor.BLANK, CardFace.BLANK);
					cards[newnums] = specialCards;
				}

			}
		}

		setCards(cards);// SETS DECK
	}

	public Deck(Card[] cards) {
		setCards(cards);
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		int red = 0, blue = 0, green = 0, yellow = 0;
		// check to see if the array is at length of 112---> containing the required
		if (cards.length > amountOfCards) {
			System.out.println("you're cards aren't up to par");
		}
		// check to see blue card stack
		for (int i = 0; i < cards.length; i++) {
			if (cards[i].getCardColor().equals(CardColor.RED)) {
				red++;
//				System.out.println("red count: " + red);
			}
			if (cards[i].getCardColor().equals(CardColor.BLUE)) {
				blue++;
//				System.out.println("blue count: " + blue);
			}
			if (cards[i].getCardColor().equals(CardColor.GREEN)) {
				green++;
//				System.out.println("green count: " + green);
			}
			if (cards[i].getCardColor().equals(CardColor.YELLOW)) {
				yellow++;
//				System.out.println("yellow count: " + yellow);
			}

		}
		// check to see red card stack
		if (!(red == 25)) {
//			System.out.println("lacking red yo!");

		}
		// check to see green card stack
		if (!(green == 25)) {
//			System.out.println("lacking blue yo!");

		}
		// check to see yellow card stack
		if (!(yellow == 25)) {
//			System.out.println("lacking yellow yo!");

		}
		// check to see blue card stack
		if (!(blue == 25)) {
//			System.out.println("lacking blue yo!");

		}
		this.cards = cards;
	}

	public int getAmountOfCards() {
		return amountOfCards;
	}

	public void shuffleDeck() {
		List<Card> cardList = new ArrayList<Card>();// puts in arrayList
		Collections.addAll(cardList, cards);// adds everything from our card array to the card ArrayList
		Collections.shuffle(cardList);// SHUFFLE!!!
		cards = cardList.toArray(new Card[cardList.size()]);// Returns the shuffled array to cards
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Deck [cards=");
		builder.append(Arrays.toString(cards));
		builder.append("]");
		return builder.toString();
	}

}
