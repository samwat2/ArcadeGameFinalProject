package model;

import java.util.Arrays;

public class Player {
	private String name;
	private Card[] hand;
	private int key;
	
	public Player(String name, int key) {
		//TODO
	}

	public Player(String name, Card[] hand, int key) {
		super();
		this.name = name;
		this.hand = hand;
		this.key = key;
	}

	public Player(String name2, Card[] hand2) {
		// TODO 
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
