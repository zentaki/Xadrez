package entities;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {return rows;}
	public int getColumns() {return columns;}
	
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
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
