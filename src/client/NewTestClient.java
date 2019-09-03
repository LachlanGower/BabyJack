package client;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.CardGameFrame;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;

public class NewTestClient
{

	public static void main(String[] args)
	{
		final GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				CardGameFrame window = new CardGameFrame("Baby Jack", gameEngine);
				gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(window));
			}
		});
		
	}

}
