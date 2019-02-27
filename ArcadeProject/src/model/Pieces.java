package model;

import java.util.ArrayList;

public abstract class Pieces {
	
	protected String color;
	protected String type;
	protected boolean isAlive;
	protected int xTo;
	protected int yTo;
	protected int x;
	protected int y;
	protected boolean collide;
	
	public boolean isCollide() {
		return collide;
	}

	public void setCollide(boolean colide) {
		this.collide = colide;
	}


	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getxTo() {
		return xTo;
	}

	public void setxTo(int xTo) {
		this.xTo = xTo;
	}

	public int getyTo() {
		return yTo;
	}

	public void setyTo(int yTo) {
		this.yTo = yTo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	void move(int xTo, int yTo) {
		setX(xTo);
		setY(yTo);
	}
	
	abstract ArrayList<Move> getMoves();
	
	
	public Pieces (String color, String type) {
		setColor(color);
		setType(type);
	}
	
	public Pieces (String color, String type, int x, int y) {
		setColor(color);
		setType(type);
		setY(y);
		setX(x);
	}	
}
