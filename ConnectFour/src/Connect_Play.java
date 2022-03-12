/* 
 * Kayla Washington-Tapia
 * CSCI 212 12W
 * Prof. O. Steinberg
 * 01.10.2022
 */
import java.util.*;

public class Connect_Play{
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); //game, scanner tool, and other variables are instantiated
		
		int temp, p;
		
		System.out.print("Welcome to Connect Four! Please enter the amount of players playing (2 max): ");
		p = scanner.nextInt();
		Connect_Four game = new Connect_Four(p);
		//game.printBoard(); //initial board is printed
		
		while(!game.boardFull() && !game.fourcheck()) { 
			//while loop is created representing a run of both players having a turn
			for(int i = 1; i <= p; i++) {
				//for loop allows each player to have a move per turn
				System.out.print("Player " + i +", please choose a column to place your piece: ");
				temp = scanner.nextInt()-1;
				
				//checks the validity of the piece move 
				while(!game.moveCheck(temp)) {
					System.out.print("Sorry that column is full please choose a different column: ");
					temp = scanner.nextInt();
				}
				game.movePiece(i,temp);
				
				//after each player moves a piece, board is checked for any connect fours
				if(game.fourcheck()) {
					System.out.print("Player " + i +", Congrats! You won!");
					break; //inner for loop is broken from if a player gets connect four
				}
				System.out.print('\n');
			}
			//printed text for full board
			if(game.boardFull()) {
				System.out.println("Sorry there a no more moves possible.");
			}
		}

	}

}
