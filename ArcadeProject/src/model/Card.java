package model;

import enums.CardColor;

public class Card {
	private CardColor cardColor;
	private int faceValue;
	protected boolean playable;
	
	public Card(CardColor cardColor, int faceValue, boolean playable) {
		setCardColor(cardColor);
		setFaceValue(faceValue);
	}


	public boolean isPlayable() {
		return playable;
	}


	public void setPlayable(boolean playable) {
		this.playable = playable;
	}


	public CardColor getCardColor() {
		return cardColor;
	}

	public void setCardColor(CardColor cardColor) {
		this.cardColor = cardColor;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nCard [cardColor=");
		builder.append(cardColor);
		builder.append(", faceValue=");
		builder.append(faceValue);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
