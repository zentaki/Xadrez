package entities;

public abstract class Piece {
	protected Position position;
	private Board board;
	private boolean moved;

	public Piece(Board board) {
		this.board = board;
		moved = false;
	}

	public boolean getMoved() {return moved;}
	protected Board getBoard() {return board;}
	public void setMoved(boolean moved) {this.moved = moved;}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for(int i=0; i < mat.length; i++) {
			for(int j=0; j < mat.length; j++) {
				if(mat[i][j])return true;
			}
		}
		return false;
	}
	
}
