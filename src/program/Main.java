package program;

import java.util.Scanner;

import entities.ChessMatch;
import exception.BoardException;
import exception.ChessException;

public class Main {

	public static void main(String[] args) throws BoardException, ChessException {
		Scanner sc = new Scanner(System.in);		
		clearScreen();
		
		ChessMatch chessMatch = new ChessMatch();
		UI.printBoard(chessMatch.getPieces());
	
		sc.close();
	}
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() { 
		System.out.print("\033[H\033[2J"); 
	 	System.out.flush(); 
	} 
	

}
