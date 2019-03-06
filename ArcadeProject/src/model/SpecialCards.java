package model;

import java.util.Random;

import enums.CardColor;
import enums.CardFace;

public class SpecialCards extends Card{
	private CardFace cardFace;
	private Random rng = new Random();
	
	public SpecialCards(CardColor cardColor, CardFace cardFace) {
		//edit the zero because if it's special there is still a face value
		super(cardColor, determineFaceValue(cardFace), true);
		setCardFace(cardFace);
	}
	
	public static int determineFaceValue(CardFace cardFace) {
		int faceValue = 0;
		if(cardFace == cardFace.WILDDRAWFOUR || cardFace == cardFace.WILD) {
			faceValue = 50;
		}else if(cardFace == cardFace.SKIP || cardFace == cardFace.REVERSE || cardFace == cardFace.DRAW2) {
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
		builder.append("]");
		return builder.toString();
	}
	
	
}
