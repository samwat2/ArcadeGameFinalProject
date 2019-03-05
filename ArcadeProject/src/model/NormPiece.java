package model;

import java.util.ArrayList;

public class NormPiece extends Pieces {
	private boolean hasMoved;
	private boolean validMove;
	private boolean king= false;

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
public boolean isHasMoved() {
		
		return hasMoved;
	}
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public boolean getKing() {
        return king;
    }
    public void setKing(boolean state) {
        king = state;
    }

	@Override
	public
	ArrayList<Move> getMoves() {
		ArrayList<Move> moves= new ArrayList<Move>();
		if(getColor() == "Black Piece" || king == true) {
		moves.add(new Move(getX()+1, getY()+1));
		moves.add(new Move(getX()+1, getY()-1));
		}
		if(getColor() == "Red Piece" || king == true) {
		moves.add(new Move(getX()-1, getY()+1));
		moves.add(new Move(getX()-1, getY()-1));
		}
	
		return moves;
	}

}
