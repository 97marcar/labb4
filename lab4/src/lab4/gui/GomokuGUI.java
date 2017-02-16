package lab4.gui;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import lab4.MouseListener;
import lab4.client.GomokuClient;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;

/*
 * The GUI class
 */

public class GomokuGUI implements Observer{

	private GomokuClient client;
	private GomokuGameState gamestate;
	private JFrame frame;
	private	JPanel content;
	private GamePanel gameGridPanel;
	private JLabel messageLabel;
	private JButton connectButton;
	private JButton newGameButton;
	private JButton disconnectButton;
	
	/**
	 * The constructor
	 * 
	 * @param g   The game state that the GUI will visualize
	 * @param c   The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c){
		this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);
		
		frame = new JFrame("Gomoku");
		content = new JPanel();
		gameGridPanel = new GamePanel(g.getGameGrid());
		messageLabel = new JLabel("123");
		connectButton = new JButton("Connect");
		newGameButton = new JButton("New Game");
		disconnectButton = new JButton("Disconnect");
		
		content.add(gameGridPanel);
		content.add(messageLabel);
		content.add(connectButton);
		content.add(newGameButton);
		content.add(disconnectButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(content);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public void update(Observable arg0, Object arg1) {
		
		// Update the buttons if the connection status has changed
		if(arg0 == client){
			if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			}else{
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}
		
		// Update the status text if the gamestate has changed
		if(arg0 == gamestate){
			messageLabel.setText(gamestate.getMessageString());
		}
		
	}


	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}