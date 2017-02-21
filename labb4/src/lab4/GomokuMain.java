package lab4;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;
import lab4.gui.GomokuGUI;

public class GomokuMain {

	public static void main(String[] args) {
		GomokuClient client = new GomokuClient(4003);
		GomokuClient client2 = new GomokuClient(4004);
		
		GomokuGameState gameState = new GomokuGameState(client);
		GomokuGameState gameState2 = new GomokuGameState(client2);
		
		GomokuGUI gui = new GomokuGUI(gameState, client);
		GomokuGUI gui2 = new GomokuGUI(gameState2, client2);


	}

}

