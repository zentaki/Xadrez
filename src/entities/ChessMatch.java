package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		//Piece p = board.piece(source);
		Piece capturedPiece = makeMove(source,target);
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Voce esta em check");
		}
		
		check = testCheck(opponent(currentPlayer)) ? true : false;
		
		if(testCheckMate(opponent(currentPlayer)))checkMate = true;
		else nextTurn();
		
		
		return (ChessPiece)capturedPiece;
		
	}
	
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
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
		currentPlayer = opponent(currentPlayer);
	}
	
	private Color opponent(Color color) {
		return color == Color.WHITE ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			if(p instanceof King)return (ChessPiece)p;
		}
		throw new IllegalStateException("There is no " + color + " King on the board");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		
		for(Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()])return true;
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if(!testCheck(color))return false;
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for(int i=0; i < board.getRows(); i++) {
				for(int j=0; j < board.getColumns(); j++) {
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i,j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck)return false;
					}
				}
			}
		}
		return true;
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
