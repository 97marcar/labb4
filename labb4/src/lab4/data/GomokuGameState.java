
package lab4.data;

import java.util.Observable;
import java.util.Observer;
import lab4.client.GomokuClient;

/**
 * Represents the state of a game
 *	@author Marcus Carlsson and Henrik M�ller
 */

public class GomokuGameState extends Observable implements Observer{

   // Game variables
	private final int DEFAULT_SIZE = 15;
	private GameGrid gameGrid;
	
    //Possible game states
	private final int NOT_STARTED = 0;
	private final int MY_TURN = 1;
	private final int OTHER_TURN = 2;
	private final int FINISHED = 3; 
	private int currentState=0;
	private GomokuClient client;
	
	private String message;
	
	/**
	 * The constructor
	 * 
	 * @param gc The client used to communicate with the other player
	 */
	public GomokuGameState(GomokuClient gc){
		client = gc;
		client.addObserver(this);
		gc.setGameState(this);
		currentState = NOT_STARTED;
		gameGrid = new GameGrid(DEFAULT_SIZE);
	}
	

	/**
	 * Returns the message string
	 * 
	 * @return the message string
	 */
	public String getMessageString(){
		return message;
	}
	
	/**
	 * Returns the game grid
	 * 
	 * @return the game grid
	 */
	public GameGrid getGameGrid(){
		return gameGrid;
	}

	/**
	 * This player makes a move at a specified location
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void move(int x, int y){
		if (currentState==NOT_STARTED){
			message="Game not started";
			notifyAndChange();
		}
		else if(currentState==FINISHED){
			message="Game is already finished";
			notifyAndChange();
		}
		else if(currentState==OTHER_TURN){
			message="It's the other players turn";
			notifyAndChange();
		}
		
		else if (gameGrid.grid[x][y]!=0){
			message="Square is NOT empty, choose another square";
			notifyAndChange();
		}
		else if  (currentState==MY_TURN){
			gameGrid.move(x, y,gameGrid.ME);
			client.sendMoveMessage(x, y);
			message="Other players turn";
			currentState=OTHER_TURN;
			
			if(gameGrid.isWinner(gameGrid.ME) == true){
				currentState=FINISHED;
				message="You have won";
				notifyAndChange();
			}else{
				notifyAndChange();
			}
		}
		else{
			message="nothing happend";
		}
	}
	
	
	/**
	 * Starts a new game with the current client
	 */
	public void newGame(){
		gameGrid.clearGrid();
		currentState=OTHER_TURN;
		message="A new game is started";
		client.sendNewGameMessage();
		notifyAndChange();
	}
	
	/**
	 * Other player has requested a new game, so the 
	 * game state is changed accordingly
	 */
	public void receivedNewGame(){
		gameGrid.clearGrid();
		currentState=MY_TURN;
		message="The other player has started a game";
		notifyAndChange();
	}
	
	/**
	 * The connection to the other player is lost, 
	 * so the game is interrupted
	 */
	public void otherGuyLeft(){
		gameGrid.clearGrid();
		currentState=NOT_STARTED;
		message="The other player disconnected from the game";
		notifyAndChange();
	}
	
	/**
	 * The player disconnects from the client
	 */
	public void disconnect(){
		gameGrid.clearGrid();
		currentState=NOT_STARTED;
		message="You have disconnected from the game";
		client.disconnect();
		notifyAndChange();
	}
	
	/**
	 * The player receives a move from the other player
	 * 
	 * @param x The x coordinate of the move
	 * @param y The y coordinate of the move
	 */
	public void receivedMove(int x, int y){
		gameGrid.move(x, y,gameGrid.OTHER);
		if(gameGrid.isWinner(gameGrid.OTHER) == true){
			currentState=FINISHED;
			message="The other player has won the game";
		}else{
			currentState=MY_TURN;
			message="It's your turn";	
		}
		notifyAndChange();
	}
	
	
	/**
	 * Update
	 */
	public void update(Observable o, Object arg) {
		switch(client.getConnectionStatus()){
		case GomokuClient.CLIENT:
			message = "Game started, it is your turn!";
			currentState = MY_TURN;
			break;
		case GomokuClient.SERVER:
			message = "Game started, waiting for other player...";
			currentState = OTHER_TURN;
			break;
		}
		notifyAndChange();
	}
	
	//Make sure notifiers know a change happened
	private void notifyAndChange(){
		setChanged();
		notifyObservers();
	}
	
}
