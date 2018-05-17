package org.rajnegi.gameoflife;

import java.util.Arrays;
import java.util.List;

/*
 * Life State
 * 0 - Dead
 * 1 - Alive
 */
public class GameOfLife {

	private static int ROWS = 200;
	private static int COLS = 200;
	
	public static void main(String[] args) {
		List<String> liveCoordinates = Arrays.asList("5,5","6,5","7,5","5,6","6,6","7,6");
		
		int[][] currentState = new int[ROWS][COLS];
		
		//Initializing current state
		for(int r=0; r<ROWS; r++) {
			Arrays.fill(currentState[r], 0);//Dead
		}
		
		if(liveCoordinates.isEmpty()) {
			System.exit(1);
		}
		
		//Setting alive coordinates
		for(String alive : liveCoordinates) {
			int x = Integer.parseInt(alive.split(",")[0]);
			int y = Integer.parseInt(alive.split(",")[1]);
			
			if(x>=ROWS || y>=COLS) {
				System.out.println("Invalid Coordinate - "+"("+x+","+y+",");
				System.exit(1);
			}
			
			currentState[x][y] = 1;//Alive
		}
		
		System.out.println("Output of next 100 state");
		int count = 1;
		do {
			
			currentState = nextState(currentState);
			
			System.out.print(count+":");
			for(int i=0; i<ROWS; i++) {
				for(int j=0; j<COLS; j++) {
					if(currentState[i][j] == 1) {
						System.out.print("("+i+","+j+")");
					}
				}
			}
			System.out.println();
			count++;
			
		} while(count <= 100);
		
	}
	
	private static int[][] nextState(int[][] currState){
		
		//Looping through each cell
		for(int r=1; r<ROWS-1; r++) {
			for(int c=1; c<COLS-1; c++) {
				int noOfAlive = 0;
				
				for(int i=-1; i<=1; i++) {
					for(int j=-1; j<=1; j++) {
						noOfAlive += currState[r+i][c+j];
					}
				}
				
				//Removing the current cell
				noOfAlive -= currState[r][c];
				
				//Under population
				if((currState[r][c] == 1) && noOfAlive < 2) {
					currState[r][c] = 0;
				}
				//Over population
				else if((currState[r][c] == 1) && noOfAlive > 3) {
					currState[r][c] = 0;
				}
				//New Cell
				else if((currState[r][c] == 0) && noOfAlive == 3) {
					currState[r][c] = 1;
				}
				
			}
		}
		
		return currState;
	}

}
