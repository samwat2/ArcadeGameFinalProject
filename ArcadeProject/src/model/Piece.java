package model;

public abstract class Piece {
	String color;
	String type;
	
	public Piece(String color, String type) {
		setColor(color);
		setType(type);
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
