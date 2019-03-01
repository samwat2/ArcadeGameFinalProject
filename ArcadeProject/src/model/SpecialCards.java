package model;

import java.util.Random;

import enums.CardColor;
import enums.CardFace;

public class SpecialCards extends Card{
	private CardFace cardFace;
	private Random rng = new Random();
	
	public SpecialCards(CardColor cardColor, CardFace cardFace) {
		super(cardColor, 0);
		setCardFace(cardFace);
	}
	

	public CardFace getCardFace() {
		return cardFace;
	}

	public void setCardFace(CardFace cardFace) {
		this.cardFace = cardFace;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nCard [cardFace=");
		builder.append(cardFace);
		builder.append(", CardColor: ");
		builder.append(getCardColor());
		builder.append("]");
		return builder.toString();
	}
	
	
}
