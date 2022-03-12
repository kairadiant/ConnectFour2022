/* 
 * Kayla Washington-Tapia
 * CSCI 212 12W
 * Prof. O. Steinberg
 * 01.10.2022
 */

import java.util.Scanner;

public class Connect_Four {
	//all private instance variables are declared
	private int[][] ConnectBoard;
	private int[] BoardTrack;
	private int players;
	private int move;
	private int row;
	private int p;
	
	//constructor for connect four game
	public Connect_Four(int players) {
		ConnectBoard = new int[6][7];
		BoardTrack = new int[7];
		move = 0;
		this.players = players;
		row = 0;
	}
	
	//function to print board
	public void printBoard(){
		int piece;
		//loops that allow the board to be printed from left to right, up to down
		System.out.print('\n');
		
		for(int r = 5; r > -1; r--) {
			for(int c = 0; c < 7; c++) {
				//each piece is represented by piece variable
				piece = ConnectBoard[r][c];
				
				if(piece == 1) {
					System.out.print("O"+'\t');	
				}
				else if(piece == 2) {
					System.out.print("X"+'\t');
				}
				else {
					System.out.print("*"+'\t');
				}
				
			}
			System.out.print("\n");
		}
		for(int i = 0; i < 7; i++) {
			System.out.print((i+1)+" "+'\t');
		}
		System.out.print("\n");
	}
	
	//function to place a piece in a column
	public void movePiece(int player, int col){ 
		                //checks the validity of the move
		move++;           //keeps track of how many piece are placed
		BoardTrack[col]++;  //keeps track of how full the board is
		
		if(player == 1) {
			ConnectBoard[BoardTrack[col]-1][col] = 1;
		}
		else {
			ConnectBoard[BoardTrack[col]-1][col] = 2;
		}
		printBoard();
		
			
	}
	
	//makes sure that the columns aren't full
	public boolean moveCheck(int col) { 
		if(BoardTrack[col]<6) {
			return true;
		}
		return false;
		
	}
	
	//checks if there are any connect fours
	public boolean fourcheck(){
		int p;
		if(move > 3){ //only checks if at least four pieces are placed (min requirement for connect 4)
			
			for(int col = 0; col < 7; col++) {
				for(int row = 0; row < 6; row++) {
					
					p = ConnectBoard[row][col]; //variable holding one place
					
					if(p != 0) { //doesn't check an empty slot for connect four
						if(row < 3) {
							//checks for a vertical connect four
							if((p == ConnectBoard[row+1][col])&&(p == ConnectBoard[row+2][col])&&
									(p == ConnectBoard[row+3][col])) { 
								return true;
							}
							//checks for a upper right diagonal connect four
							if(col < 4) {
								if((p == ConnectBoard[row+1][col+1])&&(p == ConnectBoard[row+2][col+2])&&
										(p == ConnectBoard[row+3][col+3])) { 
									return true;
								}
							}
							//checks for a upper left diagonal connect four
							else if(col > 2) {
								if((p == ConnectBoard[row+1][col-1])&&(p == ConnectBoard[row+2][col-2])&&
										(p == ConnectBoard[row+3][col-3])) { 
									return true;
								}
							}
						}
						else if(row < 6) {
							if(col < 4) {
								//checks for lower right diagonal connect four
								if((p == ConnectBoard[row-1][col+1])&&(p == ConnectBoard[row-2][col+2])&&
										(p == ConnectBoard[row-2][col+2])) {
									return true;
								}
							}
							else if(col > 2) {
								//checks for lower left diagonal connect four
								if((p == ConnectBoard[row-1][col-1])&&(p == ConnectBoard[row-2][col-2])&&
										(p == ConnectBoard[row-2][col-2])) {
									return true;
								}
							}
						}
						if(col < 4) {
							//checks for a horizontal four
							if((p == ConnectBoard[row][col+1])&&(p == ConnectBoard[row][col+2])
									&&(p == ConnectBoard[row][col+3])) { 
								return true;
							}
						}
					}
					
				}
			}
		}//if there are none connect fours, returns false
		return false;
	}
	
	//checks that there are more possible placements and that the board has empty slots
	public boolean boardFull() { 
		int track = 0;
		for(int i:BoardTrack) {
			if(i == 6) {
				track++;
			}
		}
		if(track == 7) {
			return true;
		}
		return false;
	}

}
