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
 */

public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 20;
	GameGrid grid;
	
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
		int numOfSquares = grid.getSize()*grid.getSize();
		
		for (int x = 0; x<grid.getSize(); x++){
			for(int y = 0; y<grid.getSize(); y++){
				g.drawRect(UNIT_SIZE*x, UNIT_SIZE*y, UNIT_SIZE, UNIT_SIZE);
			}
	}
		//for(int i = 0, y = 0; i < numOfSquares; i++, y=y+UNIT_SIZE){
		//	for(int j = 0, x = 0; j < grid.getSize(); j++, x = x+UNIT_SIZE){
		//			g.drawRect(x, y, UNIT_SIZE, UNIT_SIZE);
		//	}
		//}
		
	}
	
}