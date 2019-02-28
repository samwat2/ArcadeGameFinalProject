package model;

import java.util.ArrayList;

public class NormPiece extends Pieces {

	public NormPiece(String color, String type, int x, int y) {
		super(color, type, x, y);
		// TODO Auto-generated constructor stub
	}
	@Override 
	public void setX(int x) {
		this.x = x;
	}
	//
	@Override
	public void setY(int y) {
		this.y = y;
	}



	@Override
	public
	ArrayList<Move> getMoves() {
		ArrayList<Move> moves= new ArrayList<Move>();
		moves.add(new Move(getX()+2, getY()+2));
		moves.add(new Move(getX()+2, getY()-2));
	
		return moves;
	}

}
