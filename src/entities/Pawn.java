package entities;

import entities.enums.Color;

public class Pawn extends ChessPiece{
	

	public Pawn(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || isThereOpponentPiece(p.position);
	}
	

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		
		if(this.getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
			if(getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(position.getRow() - 2, position.getColumn());
				if(getBoard().positionExists(p) && canMove(p) && !this.getMoved())mat[p.getRow()][p.getColumn()] = true;
			}
			//diagona cima direita
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p))mat[p.getRow()][p.getColumn()] = true;
			//diagona cima esquerda
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p))mat[p.getRow()][p.getColumn()] = true;
		}
		
		if(this.getColor() == Color.BLACK) {
			p.setValues(position.getRow() + 1, position.getColumn());
			if(getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;
				p.setValues(position.getRow() + 2, position.getColumn());
				if(getBoard().positionExists(p) && canMove(p) && !this.getMoved())mat[p.getRow()][p.getColumn()] = true;
			}
			//diagona cima direita
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p))mat[p.getRow()][p.getColumn()] = true;
			//diagona cima esquerda
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p))mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		return mat;
	}
	
	
	
}
