/* 
 * Kayla Washington-Tapia
 * CSCI 212 12W
 * Prof. O. Steinberg
 * 01.10.2022
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class connectGUI extends JFrame{
	//instance variables
	private JFrame frame;
	private Container container = new Container();
	private Color p1 = Color.RED;
	private Color p2 = Color.BLUE;
	private int[][] ConnectBoard;
	private int[] BoardTrack;
	private int players;
	private int move;
	JButton[][] slots = new JButton[6][7];

	//main
	public static void main(String args[]) {
		connectGUI game = new connectGUI();
	}
	
	//GUI constructor
	public connectGUI(){
		//greeting message
		JOptionPane.showMessageDialog(null, "Welcome to Connect Four! This game is meant for two players. Player one start!");
		//variables defined
		ConnectBoard = new int[6][7];
		BoardTrack = new int[7];
		move = 0;
		//frame creation
		frame = new JFrame("Connect Four");
		frame.getContentPane();
		frame.setLayout(new GridLayout(6,7));
		ButtonHandler buttonHandler = new ButtonHandler();
		players = 1;
		//button objects created
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 7; c++) {
				slots[r][c] = new JButton();
				frame.add(slots[r][c]);
				slots[r][c].addActionListener(buttonHandler);
			}
		}
		//frame window size and restrictions
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	//buttonhandler method overriden for game use
	private class ButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource(); 
			for(int i = 0; i < 7 ; i++) {
				for(int j = 0; j < 6; j++) {
					//if button is where the user clicked
					if(source == slots[j][i]) { 
						//if there are more moves or no one has connect four
						if(!boardFull() && !fourcheck()) {
							//lets each player have a turn
							if(players > 2) {
								players = 1;
							}
							//if there is space for the move
							if(moveCheck(i)){
								movePiece(i);
								//color of button depending on player
								if(players == 1) {
									slots[6-BoardTrack[i]][i].setBackground(p1);
								}
								else {
									slots[6-BoardTrack[i]][i].setBackground(p2);
								}
								players++;
							}
						}
						//if someone has connect four
						else if(fourcheck()) { 
							JOptionPane.showMessageDialog(null, "Game over. Player "+ players +" won.");
							frame.setVisible(false);
						}
						//if there are no more moves
						else {
							JOptionPane.showMessageDialog(null, "No more moves left, game over.");
							frame.setVisible(false);
						}
					}
				}
			}
		}
	}

	//algorithm move tracker for connect four
	public void movePiece(int col){
		move++;           //keeps track of how many piece are placed
		BoardTrack[col]++;  //keeps track of how full the board is

		if(players == 1) {
			ConnectBoard[BoardTrack[col]-1][col] = 1;
		}
		else {
			ConnectBoard[BoardTrack[col]-1][col] = 2;
		}
	}

	//checks if the placement is valid
	public boolean moveCheck(int col) {
		if(BoardTrack[col]<6) {
			return true;
		}
		return false;

	}

	//algorithm of checking if there are four
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
