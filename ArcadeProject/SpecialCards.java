package model;

import java.util.Random;

import enums.CardColor;
import enums.CardFace;

public class SpecialCards extends Card{
	private CardFace cardFace;
	private Random rng = new Random();
	
	public SpecialCards() {
		//TODO
	}

	public SpecialCards(CardColor cardColor, int faceValue, CardFace cardFace) {
		super(cardColor, faceValue);
		this.cardFace = cardFace;
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
		builder.append("SpecialCards [cardFace=");
		builder.append(cardFace);
		builder.append(", getCardColor()=");
		builder.append(getCardColor());
		builder.append(", getFaceValue()=");
		builder.append(getFaceValue());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}
	
	
}
