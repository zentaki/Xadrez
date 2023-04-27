package program;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		
		List<ChessPiece> captured = new ArrayList<>();
		
		while(!chessMatch.isCheckMate()) {
			UI.clearScreen();
			UI.printMatch(chessMatch, captured);
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
				if(capturedPiece != null)captured.add(capturedPiece);
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
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
		sc.close();
	}	

}
