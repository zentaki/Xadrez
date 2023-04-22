package entities;

import entities.enums.Color;

public class Pawn extends ChessPiece{

	public Pawn(Position position, Color color, int moveCount) {
		super(position, color, moveCount);
		
	}

	@Override
	public boolean[][] possibleMoves() {
		
		return null;
	}
	
}
