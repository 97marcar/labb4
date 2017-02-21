package lab4.gui;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import lab4.client.GomokuClient;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;

/*
 * The GUI class
 */

public class GomokuGUI implements Observer{

	private GomokuClient client;
	private GomokuGameState gamestate;
	private GamePanel gameGridPanel;
	private JLabel messageLabel;
	private JFrame frame;
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
		frame = new JFrame("Gomoku");
		this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);

		gameGridPanel = new GamePanel(gamestate.getGameGrid());
		messageLabel = new JLabel("Welcome to Gomoku");
		connectButton = new JButton("Connect");
		newGameButton = new JButton("New Game");
		disconnectButton = new JButton("Disconnect");
		
		JPanel buttonPanel = new JPanel();
		JPanel messagePanel = new JPanel();
		
		frame.setLayout(new GridLayout(3,1));
		gameGridPanel.setLayout(new BoxLayout(gameGridPanel, BoxLayout.Y_AXIS));
		frame.add(gameGridPanel);
		frame.add(buttonPanel);
		frame.add(messagePanel);
		
		messagePanel.add(messageLabel);
		buttonPanel.add(connectButton);
		buttonPanel.add(newGameButton);
		buttonPanel.add(disconnectButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
}
