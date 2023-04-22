package entities;

public abstract class Piece {
	private Position position;

	public Piece(Position position) {
		this.position = position;
	}

	public Position getPosition() {return position;}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return false;
	}
	
	public boolean isThereAnyPossibleMove() {
		return true;
	}
	
}
