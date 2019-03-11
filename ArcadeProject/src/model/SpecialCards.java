package model;

import enums.CardColor;
import enums.CardFace;

public class SpecialCards extends Card{
	private CardFace cardFace;
	
	public SpecialCards(CardColor cardColor, CardFace cardFace, boolean playable) {
		super(cardColor, determineFaceValue(cardFace), playable);
		setCardFace(cardFace);
	}
	
	public static int determineFaceValue(CardFace cardFace) {
		int faceValue = 0;
		if(cardFace == CardFace.WILDDRAWFOUR || cardFace == CardFace.WILD) {
			faceValue = 50;
		}else if(cardFace == CardFace.SKIP || cardFace == CardFace.REVERSE || cardFace == CardFace.DRAW2) {
			faceValue = 20;
		}
		return faceValue;
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
		builder.append(", playable:");
		builder.append(playable);
		builder.append("]");
		return builder.toString();
	}
	
	
}
