package entities;

import entities.enums.Color;
import exception.BoardException;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	
	public ChessMatch() throws BoardException {
		board = new Board(8,8);
		initalSetup();
	}
	
	
	public int getTurn() {return turn;}
	public Color getCurrentPlayer() {return currentPlayer;}
	public boolean isCheck() {return check;}
	public boolean isCheckMate() {return checkMate;}
	public ChessPiece getEnPassantVulnerable() {return enPassantVulnerable;}
	public ChessPiece getPromoted() {return promoted;}

	public ChessPiece[][] getPieces() throws BoardException{
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0; i < board.getRows(); i++) {
			for(int j=0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece)board.piece(i,j);
			}
		}
		return mat;
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
	
	private void initalSetup() throws BoardException {
		
		board.placePiece(new Rook(board, Color.BLACK), new Position(0,0));
		board.placePiece(new Knight(board, Color.BLACK), new Position(0,1));
		board.placePiece(new Bishop(board, Color.BLACK), new Position(0,2));
		board.placePiece(new Queen(board, Color.BLACK), new Position(0,3));
		board.placePiece(new King(board, Color.BLACK), new Position(0,4));
		board.placePiece(new Bishop(board, Color.BLACK), new Position(0,5));
		board.placePiece(new Knight(board, Color.BLACK), new Position(0,6));
		board.placePiece(new Rook(board, Color.BLACK), new Position(0,7));
		
		
		board.placePiece(new Rook(board, Color.WHITE), new Position(7,0));
		board.placePiece(new Knight(board, Color.WHITE), new Position(7,1));
		board.placePiece(new Bishop(board, Color.WHITE), new Position(7,2));
		board.placePiece(new King(board, Color.WHITE), new Position(7,3));
		board.placePiece(new Queen(board, Color.WHITE), new Position(7,4));
		board.placePiece(new Bishop(board, Color.WHITE), new Position(7,5));
		board.placePiece(new Knight(board, Color.WHITE), new Position(7,6));
		board.placePiece(new Rook(board, Color.WHITE), new Position(7,7));
		
		for(int i=0; i < board.getColumns(); i++) {
			board.placePiece(new Pawn(board, Color.BLACK), new Position(1,i));
			board.placePiece(new Pawn(board, Color.WHITE), new Position(6,i));
		}
		
	}
	
}
