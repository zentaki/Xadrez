package entities;

public class Board {
	private int rows;
	private int columns;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}

	public int getRows() {return rows;}
	public int getColumns() {return columns;}
	
	public Piece piece(int row, int columns) {
		return null;
	}
	public Piece piece(Position position) {
		return null;
	}
	
	public void placePiece(Piece piece, Position position) {}
	
	public Piece removePiece(Position position) {
		return null;
	}
	
	public boolean positionExists(Position position) {
		return false;
	}
	
	public boolean thereIsAPiece(Position position) {
		return false;
	}
	
	
}
