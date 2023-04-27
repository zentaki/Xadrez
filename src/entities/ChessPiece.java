package entities;

import entities.enums.Color;

public abstract class ChessPiece extends Piece{
	private Color color;
	private int moveCount;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
		moveCount = 0;
	}

	public Color getColor() {return color;}
	public int getMoveCount() {return moveCount;}
	
	public ChessPosition getChessPosition() {return null;}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
	public void increaseMoveCount() {moveCount++;}
	public void decreaseMoveCount() {moveCount--;}
	
	
}
