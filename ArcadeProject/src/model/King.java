package model;

public class King {
	private int row;
	private int column;
	private int[][] direction;
	public King(int row, int column, int[][] direction) {
		setRow(row);
		setColumn(column);
		setDirection(direction);
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int[][] getDirection() {
		return direction;
	}
	public void setDirection(int[][] direction) {
		this.direction = direction;
	}
	
	
}
