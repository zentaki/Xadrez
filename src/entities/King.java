package entities;

import entities.enums.Color;

public class King extends ChessPiece{
	
	private ChessMatch chessMatch;

	public King(Board board, Color color,ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean testRoque(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p instanceof Rook && p.getColor() == this.getColor() && p.getMoveCount() == 0;
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || isThereOpponentPiece(p.position);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//acima
		p.setValues(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p))mat[p.getRow()][p.getColumn()] = true;		
		//abaixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p))mat[p.getRow()][p.getColumn()] = true;		
		//direita
		p.setValues(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p))mat[p.getRow()][p.getColumn()] = true;		
		//esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p))mat[p.getRow()][p.getColumn()] = true;
		
		//diagona cima direita
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p))mat[p.getRow()][p.getColumn()] = true;
		//diagona cima esquerda
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p))mat[p.getRow()][p.getColumn()] = true;
		//diagonal baixo direita
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p))mat[p.getRow()][p.getColumn()] = true;
		//diagonal baixo esquerda
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if(getBoard().positionExists(p) && canMove(p))mat[p.getRow()][p.getColumn()] = true;
		
		//jogada roque
		if(getMoveCount() == 0 && !chessMatch.isCheck()) {
			//roque pequeno
			p.setValues(position.getRow(), position.getColumn() + 3);
			if(testRoque(p)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if( getBoard().piece(p1) == null && getBoard().piece(p2) == null)
					mat[position.getRow()][position.getColumn() + 2] = true;
			}
			p.setValues(position.getRow(), position.getColumn() - 4);
			if(testRoque(p)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if( getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null)
					mat[position.getRow()][position.getColumn() - 2] = true;
			}
		}
		
		return mat;
		
	}
	
}
