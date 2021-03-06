package model;

import java.util.ArrayList;

public class NormPiece extends Pieces {
	private boolean hasMoved;
	private boolean validMove;
	private boolean king = false;
	private boolean attackLeft = false;
	private boolean attackRight = false;

	public boolean getAttackRight() {
		return attackRight;
	}

	public void setAttackRight(boolean attack) {
		this.attackRight = attack;
	}
	public boolean getAttackLeft() {
		return attackLeft;
	}

	public void setAttackLeft(boolean attack) {
		this.attackLeft = attack;
	}

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
	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (getColor() == "Black Piece") {
			moves.add(new Move(getX() - 1, getY() + 1));
			moves.add(new Move(getX() + 1, getY() + 1));
//			moves.add(new Move(getX() - 2, getY() + 2));
//			moves.add(new Move(getX() + 2, getY() + 2));
			if (getColor() == "Black Piece" && getY() == 7 || getKing()) {
				setKing(true);
//				moves.add(new Move(getX() - 1, getY() + 1));
//				moves.add(new Move(getX() + 1, getY() + 1));
				moves.add(new Move(getX() - 1, getY() - 1));
				moves.add(new Move(getX() + 1, getY() - 1));
			}
			if(getColor() == "Black Piece" && attackRight == true) {
				moves.add(new Move(getX() + 2, getY() + 2));
			}
			if(getColor() == "Black Piece" && attackLeft == true) {
				moves.add(new Move(getX() - 2, getY() + 2));
			}
			
		}
		if (getColor() == "Red Piece") {
//			moves.add(new Move(getX() - 2, getY() - 2));
//			moves.add(new Move(getX() + 2, getY() - 2));
			moves.add(new Move(getX() - 1, getY() - 1));
			moves.add(new Move(getX() + 1, getY() - 1));
			if (getColor() == "Red Piece" && getY() == 0 || getKing()) {
				setKing(true);
				moves.add(new Move(getX() - 1, getY() + 1));
				moves.add(new Move(getX() + 1, getY() + 1));
//				moves.add(new Move(getX() - 1, getY() - 1));
//				moves.add(new Move(getX() + 1, getY() - 1));

			}
			if(getColor() == "Red Piece" && attackRight == true) {
				moves.add(new Move(getX() + 2, getY() - 2));
			}
			if(getColor() == "Red Piece" && attackLeft == true) {
				moves.add(new Move(getX() - 2, getY() - 2));
			}
			
		}

		return moves;
	}

}
