package entities;

public class ChessPosition {
	private char column;
	private int row;
	public ChessPosition(char column, int row) {
		this.column = column;
		this.row = row;
	}
	public char getColumn() {return column;}
	public int getRow() {return row;}
	
	public Position toPosition() {
		return null;
	}
	public ChessPosition fromPosition(Position position) {
		return null;
	}
	
}
