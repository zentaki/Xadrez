package entities;

import java.util.ArrayList;
import java.util.List;

import entities.enums.Color;
import exception.ChessException;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch(){
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initalSetup();
	}
	
	
	public int getTurn() {return turn;}
	public Color getCurrentPlayer() {return currentPlayer;}
	public boolean isCheck() {return check;}
	public boolean isCheckMate() {return checkMate;}
	public ChessPiece getEnPassantVulnerable() {return enPassantVulnerable;}
	public ChessPiece getPromoted() {return promoted;}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0; i < board.getRows(); i++) {
			for(int j=0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece)board.piece(i,j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece p = board.piece(source);
		p.setMoved(true);
		nextTurn();
		return (ChessPiece)makeMove(source,target);
		
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position source) {
		ChessPosition p = ChessPosition.fromPosition(source);
		if(!board.thereIsAPiece(source))throw new ChessException("Nao há peça na posiçao " + p.toString());
		if(currentPlayer != ((ChessPiece)board.piece(source)).getColor())throw new ChessException("Essa peça nao é sua");
		if(!board.piece(source).isThereAnyPossibleMove())throw new ChessException("Nao há movimentos possiveis");
	}
	
	public void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target))throw new ChessException("Nao pode mover para o destino");
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
	}
	
	public ChessPiece replacePromotedPiece(String type) {
		return null;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void initalSetup() {
		
		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		
		
		placeNewPiece('d', 4, new King(board, Color.WHITE));	
		placeNewPiece('d', 3, new Rook(board, Color.WHITE));
		placeNewPiece('d', 5, new Rook(board, Color.WHITE));
		placeNewPiece('e', 4, new Rook(board, Color.WHITE));
		placeNewPiece('c', 4, new Rook(board, Color.WHITE));
		
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
