package model;

import java.util.Arrays;

public class Player { 					//player creation
	
	private String name;
	private Card[] hand = new Card[7];	//amount set per hand is seven
	private int key;					//secret key
	private Card card;
	
	public Player(String name, Card[] hand, int key) {
		setName(name);
		setKey(key);
		setHand(hand);
	}
	public Player(String name, int key) {
		setName(name);
		setKey(key);
		getHand();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Card[] getHand() {
		return hand;
	}

	public void setHand(Card[] hand) {
		this.hand = hand;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [name=");
		builder.append(name);
		builder.append(", hand=");
		builder.append(Arrays.toString(hand));
		builder.append(", key=");
		builder.append(key);
		builder.append("]");
		return builder.toString();
	}
	
	
}
