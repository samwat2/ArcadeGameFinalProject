package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Player { 					//player creation
	
	private String name;
//	private Card[] hand = new Card[7];	//amount set per hand is seven
	private int key;					//secret key
	private Card card;
	private int currentPoints;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	public Player(String name, ArrayList<Card> hand, int key) {
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

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
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
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public int getCurrentPoints() {
		return currentPoints;
	}
	public void setCurrentPoints(ArrayList<Card> hand) {
		//keeps track of the current points.
		for(int i = 0; i < hand.size() - 1; i ++ ) {
			this.currentPoints += hand.get(i).getFaceValue();
		}

	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [name=");
		builder.append(name);
		builder.append(", hand=");
		builder.append(hand.toString());
		builder.append(", key=");
		builder.append(key);
		builder.append("]");
		return builder.toString();
	}
	
	
}
