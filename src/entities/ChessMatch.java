package entities;

import entities.enums.Color;
import exception.BoardException;
import exception.ChessException;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	
	public ChessMatch() throws BoardException, ChessException {
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
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) throws BoardException {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source,target);
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) throws BoardException {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position source) throws BoardException {
		ChessPosition p = ChessPosition.fromPosition(source);
		if(!board.thereIsAPiece(source))throw new ChessException("Nao há peça na posiçao " + p.toString());
	}
	
	public ChessPiece replacePromotedPiece(String type) {
		return null;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) throws BoardException, ChessException {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initalSetup() throws BoardException, ChessException {
		
		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		
		
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));
		placeNewPiece('e', 1, new Queen(board, Color.WHITE));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		
		for(int i=0; i < board.getColumns(); i++) {
			placeNewPiece((char)('a'+ i), 7, new Pawn(board, Color.BLACK));
			placeNewPiece((char)('a' + i), 2, new Pawn(board, Color.WHITE));
		}
		
	}
	
}
