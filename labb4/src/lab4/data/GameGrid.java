package lab4.data;

import java.util.Observable;

/**
 * Represents the 2-d game grid
 */

public class GameGrid extends Observable{
	int[][] grid;
	public final int EMPTY = 0;
	public final int ME = 1;
	public final int OTHER = 2;
	public final int INROW = 5;
	
	/**
	 * Constructor
	 * 
	 * @param size The width/height of the game grid
	 */
	public GameGrid(int size){
		grid = new int[size][size];
	} 
	/**
	 * Reads a location of the grid
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return the value of the specified location
	 */
	public int getLocation(int x, int y){
		return(grid[x][y]);
	}
	
	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize(){
		return(grid.length);
	}
	
	/**
	 * Enters a move in the game grid
	 * 
	 * @param x the x position
	 * @param y the y position
	 * @param player
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean move(int x, int y, int player){
		if(grid[x][y] == EMPTY){
			grid[x][y] = player;
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
	
	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid(){
		for(int i = 0; i < grid.length; i++){
			for(int n = 0; n < grid[i].length; n++){
				grid[i][n] = EMPTY;
			}
		}
	}
		
	
	/**
	 * Check if a player has 5 in row
	 * 
	 * @param player the player to check for
	 * @return true if player has 5 in row, false otherwise
	 */
	public boolean isWinner(int player){
		/*int inrow = 0;
		for(int i = 0; i < grid.length; i++){
			for(int n = 0; n < grid[i].length; n++){
				if(grid[i][n] == player){
					inrow++;
				}else{
					inrow = 0;
				}
			}
		}
		
		if(inrow == INROW){
			return true;
		}
		return false;*/
		if(grid[0][0] == player){
			return true;
		}
		return false;
	}
	
	
}
