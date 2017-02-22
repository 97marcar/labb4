package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GameGrid;

/**
 * A panel providing a graphical view of the game board
 * @author Marcus Carlsson and Henrik Möller
 */

public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 30;
	GameGrid grid;
	
	//Colors to use when painting the grid.
	private final Color MY_COLOR = Color.BLUE;
	private final Color SQUARE_COLOR = Color.BLACK;
	private final Color OTHER_COLOR = Color.RED;
	
	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid){
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Returns a grid position given pixel coordinates
	 * of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y){
		return new int[] {x/UNIT_SIZE, y/UNIT_SIZE};
	}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int x = 0; x<grid.getSize(); x++){
			
			for(int y = 0; y<grid.getSize(); y++){
				
				//If it's empty draw an empty square
				if(grid.getLocation(x, y) == grid.EMPTY){
					g.setColor(SQUARE_COLOR);
					g.drawRect(UNIT_SIZE*x, UNIT_SIZE*y, UNIT_SIZE, UNIT_SIZE);
				
				//if you've made a move here draw a MY_COLOR filled square
				}else if((grid.getLocation(x, y) == grid.ME)){
					g.setColor(MY_COLOR);
					g.fillRect(UNIT_SIZE*x, UNIT_SIZE*y, UNIT_SIZE, UNIT_SIZE);
				}
				//if your opponent made a move here draw a OTHER_COLOR filled square
				else if((grid.getLocation(x, y) == grid.OTHER)){
					g.setColor(OTHER_COLOR);
					g.fillRect(UNIT_SIZE*x, UNIT_SIZE*y, UNIT_SIZE, UNIT_SIZE);
				}
				
			}
		}
	}
	
}
