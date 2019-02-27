package model;

import java.util.Arrays;
import java.util.Random;

public class Deck {
	private Card[] cards;
	private Random rng = new Random();
	private SpecialCards[] specialCards;
	private final int amountOfCards = 112;
	
	public Deck() {
		
	}

	public Deck(Card[] cards, SpecialCards[] specialCards) {
		super();
		this.cards = cards;
		this.specialCards = specialCards;
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	public SpecialCards[] getSpecialCards() {
		return specialCards;
	}

	public void setSpecialCards(SpecialCards[] specialCards) {
		this.specialCards = specialCards;
	}

	public int getAmountOfCards() {
		return amountOfCards;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Deck [cards=");
		builder.append(Arrays.toString(cards));
		builder.append(", rng=");
		builder.append(rng);
		builder.append(", specialCards=");
		builder.append(Arrays.toString(specialCards));
		builder.append(", amountOfCards=");
		builder.append(amountOfCards);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
