package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import enums.CardColor;
import enums.CardFace;

public class Deck {
	
	private final int amountOfCards = 108;// set of amount of cards the deck needs
	private ArrayList<Card> cards = new ArrayList<Card>(amountOfCards);
	private Card card;
	private SpecialCards specialCards;// for the special cards

	public Deck() {
		// first for-loop for the whole deck
		for (int i = 0; i < 1; i++) {
			// second for-loop for red standard cards
			for (int red = 0; red < 19; red++) {
				card = new Card(CardColor.RED, (red % 10), false);
				cards.add(card);
			}
			// third for-loop for blue standard cards
			for (int blue = 0; blue < 19; blue++) {
				card = new Card(CardColor.BLUE, (blue % 10), false);
				cards.add(card);
			}
			// fourth for-loop for green standard cards
			for (int green = 0; green < 19; green++) {
				card = new Card(CardColor.GREEN, (green % 10), false);
				cards.add(card);
			}
			// fifth for-loop for yellow standard cards
			for (int yellow = 0; yellow < 19; yellow++) {
				card = new Card(CardColor.YELLOW, (yellow % 10), false);
				cards.add(card);
			}
			// sixth for-loop for skip special cards
			for (int skip = 0; skip < 8; skip++) {
				int newNumber = skip + 76;// number of places in array

				if (skip == 0 || skip < 2) {// only creating two of each
					specialCards = new SpecialCards(CardColor.RED, CardFace.SKIP, false);
					cards.add(specialCards);
				} else if (skip == 2 || skip < 4) {
					specialCards = new SpecialCards(CardColor.BLUE, CardFace.SKIP, false);
					cards.add(specialCards);
				} else if (skip == 4 || skip < 6) {
					specialCards = new SpecialCards(CardColor.GREEN, CardFace.SKIP, false);
					cards.add(specialCards);
				} else if (skip == 6 || skip <= 8) {
					specialCards = new SpecialCards(CardColor.YELLOW, CardFace.SKIP, false);
					cards.add(specialCards);
				}
			}
			// seventh for-loop for reverse special cards
			for (int reverse = 0; reverse < 8; reverse++) {
				int newNum = reverse + 84;// previous index
				if (reverse == 0 || reverse < 2) {// only creating two of each
					specialCards = new SpecialCards(CardColor.RED, CardFace.REVERSE, false);
					cards.add(specialCards);
				} else if (reverse == 2 || reverse < 4) {
					specialCards = new SpecialCards(CardColor.BLUE, CardFace.REVERSE, false);
					cards.add(specialCards);
				} else if (reverse == 4 || reverse < 6) {
					specialCards = new SpecialCards(CardColor.GREEN, CardFace.REVERSE, false);
					cards.add(specialCards);
				} else if (reverse == 6 || reverse <= 8) {
					specialCards = new SpecialCards(CardColor.YELLOW, CardFace.REVERSE, false);
					cards.add(specialCards);
				}
			}
			// eighth for-loop for draw special cards
			for (int draw = 0; draw < 8; draw++) {
				int newnum = draw + 92;// previous index
				if (draw == 0 || draw < 2) {// only creating two of each
					specialCards = new SpecialCards(CardColor.RED, CardFace.DRAW2, false);
					cards.add(specialCards);
				} else if (draw == 2 || draw < 4) {
					specialCards = new SpecialCards(CardColor.BLUE, CardFace.DRAW2, false);
					cards.add(specialCards);
				} else if (draw == 4 || draw < 6) {
					specialCards = new SpecialCards(CardColor.GREEN, CardFace.DRAW2, false);
					cards.add(specialCards);
				} else if (draw == 6 || draw <= 8) {
					specialCards = new SpecialCards(CardColor.YELLOW, CardFace.DRAW2, false);
					cards.add(specialCards);
				}
			}
			// final for-loop for wild, wild-draw-four, and blank
			for (int special = 0; special < 12; special++) {
				int newnums = special + 100;// previous index
				if (special == 0 || special < 4) {// only creating four of each
					specialCards = new SpecialCards(CardColor.BLANK, CardFace.WILDDRAWFOUR, true);
					cards.add(specialCards);
				} else if (special == 4 || special < 8) {
					specialCards = new SpecialCards(CardColor.BLANK, CardFace.WILD, true);
					cards.add(specialCards);
				} 

			}
		}

		setCards(cards);// SETS DECK
	}

	public Deck(ArrayList<Card> cards) {
		setCards(cards);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards2) {
		// check to see if the array is at length of 112---> containing the required
//		if (cards2.size() > amountOfCards) {
//			System.out.println("you're cards aren't up to par");
//		}
		this.cards = cards2;
	}

	public int getAmountOfCards() {
		return amountOfCards;
	}

	public void shuffleDeck() {
		Collections.shuffle(cards);// SHUFFLE!!!
	}
	
	public ArrayList<Card> retrieveInitialCards() {
		ArrayList<Card> hand = new ArrayList<Card>();
		for(int i = 0; i < 7; i++ ) {
			hand.add(cards.get(i));
			cards.remove(i);
		}
		setCards(cards);
		return hand;
	}
	
	public void removeCard() {
		int index = cards.size() - 1;
		cards.remove(index);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Deck [cards=");
		builder.append(Arrays.toString(cards.toArray()));
		builder.append("]");
		return builder.toString();
	}

}
