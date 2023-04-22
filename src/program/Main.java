package program;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("frase");
		
		clearScreen();
		
		int i = sc.nextInt();
		sc.close();
	}
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() { 
	 System.out.print("\033[H\033[2J"); 
	 System.out.flush(); 
	} 
	

}
