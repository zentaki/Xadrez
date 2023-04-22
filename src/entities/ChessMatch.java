package entities;

import entities.enums.Color;

public class ChessMatch {
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	
	public ChessMatch(int turn, Color currentPlayer, boolean check, boolean checkMate, ChessPiece enPassantVulnerable,
			ChessPiece promoted) {
		this.turn = turn;
		this.currentPlayer = currentPlayer;
		this.check = check;
		this.checkMate = checkMate;
		this.enPassantVulnerable = enPassantVulnerable;
		this.promoted = promoted;
	}
	
	public int getTurn() {return turn;}
	public Color getCurrentPlayer() {return currentPlayer;}
	public boolean isCheck() {return check;}
	public boolean isCheckMate() {return checkMate;}
	public ChessPiece getEnPassantVulnerable() {return enPassantVulnerable;}
	public ChessPiece getPromoted() {return promoted;}

	public ChessPiece[][] getPiece(){
		return null;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		return null;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		return null;
	}
	
	public ChessPiece replacePromotedPiece(String type) {
		return null;
	}
	
}
