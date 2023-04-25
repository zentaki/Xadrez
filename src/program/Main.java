package program;

import java.util.Scanner;

import entities.ChessMatch;
import entities.ChessPiece;
import entities.ChessPosition;
import exception.BoardException;
import exception.ChessException;

public class Main {

	public static void main(String[] args) throws BoardException, ChessException {
		Scanner sc = new Scanner(System.in);		
		
		
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			clearScreen();
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc);
			
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}
		//sc.close();
	}
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() { 
		System.out.print("\033[H\033[2J"); 
	 	System.out.flush(); 
	} 
	

}
