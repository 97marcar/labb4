package lab4;

import lab4.client.*;
import lab4.data.*;
import lab4.gui.*;


public class GomokuMain {
	
	public static void main(String[] args){
		GomokuClient client = new GomokuClient(4000);
		GomokuClient client2 = new GomokuClient(3000);
		GomokuGameState gameState = new GomokuGameState(client);
		GomokuGUI gui = new GomokuGUI(gameState, client);
	}
	
}
