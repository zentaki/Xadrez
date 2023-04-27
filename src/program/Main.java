package program;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.ChessMatch;
import entities.ChessPiece;
import entities.ChessPosition;
import exception.ChessException;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		
		
		ChessMatch chessMatch = new ChessMatch();
		ChessPosition source = null;
		ChessPosition target = null;
		
		while(true) {
			UI.clearScreen();
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			try {
				System.out.print("Source: ");
				source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				
				System.out.println();
				System.out.print("Target: ");
				target = UI.readChessPosition(sc);
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			}
			catch (ChessException e) {
				System.out.println(e.getMessage() + "\n...Press enter to continue...");
				sc.nextLine();
				
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage() + "\n...Press enter to continue...");
				sc.nextLine();
			}
			
		}
		//sc.close();
	}	

}
