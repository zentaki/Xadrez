package entities;

import entities.enums.Color;

public abstract class ChessPiece extends Piece{
	private Color color;
	private int moveCount;
	
	public ChessPiece(Position position, Color color, int moveCount) {
		super(position);
		this.color = color;
		this.moveCount = moveCount;
	}

	public Color getColor() {return color;}
	public int getMoveCount() {return moveCount;}
	
	public ChessPosition getChessPosition() {
		return null;
	}
	public boolean isThereOpponentPiece(Position position) {
		return false;
	}
	public void increaseMoveCount() {}
	public void decreaseMoveCount() {}
	
	
}
