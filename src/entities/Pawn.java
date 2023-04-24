package entities;

import entities.enums.Color;

public class Pawn extends ChessPiece{

	public Pawn(Board board, Color color, int moveCount) {
		super(board, color, moveCount);
	}

	@Override
	public boolean[][] possibleMoves() {
		
		return null;
	}
	
}
