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
	private int inrow = 0;
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
		
	private boolean vertical(int player){
		for(int y = 0; y < grid.length; y++){
			for(int x = 0; x < grid[0].length; x++){
				if(grid[x][y] == player){
					inrow++;
					if(inrow == INROW){
						return(true);
					}
				}else{
					inrow = 0;	
				}
			}		
		}
		return false;
	}
	
	private boolean horisontal(int player){
		for(int x = 0; x < grid.length; x++){
			for(int y = 0; y < grid[0].length; y++){
				if(grid[x][y] == player){
					inrow++;
					if(inrow == INROW){
						return(true);
					}
				}else{
					inrow = 0;	
				}
			}		
		}
		return false;
	}
	
	private boolean diagonal(int player){
		//Diagonal top left to top right (x-value)
		for(int i = 0; i < grid.length; i++){
			for(int x = 0, y = i; x <= i; x++, y--){
				if(grid[x][y] == player){
					inrow++;
					if(inrow == INROW){
						return(true);
					}
				}else{
					inrow = 0;	
				}
			}
		}
		
		inrow = 0;
		
		//Diagonal bottom right to bottom left (x-value)
		for(int i = grid.length-1; i >= 0; i--){
			for(int x = grid.length-1, y = i; x >= i; x--, y++){
				if(grid[x][y] == player){
					inrow++;
					if(inrow == INROW){
						return(true);
					}
				}else{
					inrow = 0;	
				}
			}
		}
		
		//Diagonal top right to top left (x-value)
		for(int i = 0; i < grid.length; i++){
			for(int x = grid.length-1-i, y = 0; y <= i; x++, y++){
				if(grid[x][y] == player){
					inrow++;
					if(inrow == INROW){
						return(true);
					}
				}else{
					inrow = 0;	
				}
			}
		}
		
		//Diagonal bottom left to bottom right (x-value)
		for(int i = 0; i < grid.length-1; i++){
			for(int x = 0, y = grid.length-1-i; x <= i; x++, y++){
				if(grid[x][y] == player){
					inrow++;
					if(inrow == INROW){
						return(true);
					}
				}else{
					inrow = 0;	
				}
			}
		}
		
		return(false);
	}
	/**
	 * Check if a player has 5 in row
	 * 
	 * @param player the player to check for
	 * @return true if player has 5 in row, false otherwise
	 */
	public boolean isWinner(int player){
		if(vertical(player) || horisontal(player) || diagonal(player)){
			return true;
		}
		return false;
	}
	
	
}
