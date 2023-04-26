package entities;

import exception.BoardException;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1)throw new BoardException("Error: Os valores devem ser inteiros maiores que 0");
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {return rows;}
	public int getColumns() {return columns;}
	
	public Piece piece(int row, int column) {
		if(!positionExists(row,column))throw new BoardException("Posiçao fora do tabuleiro");
		return pieces[row][column];
	}
	public Piece piece(Position position) {
		if(!positionExists(position))throw new BoardException("Posiçao fora do tabuleiro");
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if(thereIsAPiece(position))throw new BoardException("Posiçao " + position + "ocupada");
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;		
	}
	
	public Piece removePiece(Position position) {
		if(!positionExists(position))throw new BoardException("Fora do tabuleiro");
		if(piece(position) == null) return null;
		Piece p = piece(position);
		p.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return p;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(),position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position)  {
		if(!positionExists(position))throw new BoardException("Posiçao fora do tabuleiro");
		return piece(position) != null;
	}
	
	
}
