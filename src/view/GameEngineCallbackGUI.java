package view;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private CardGameFrame cgf;
	public GameEngineCallbackGUI(CardGameFrame window)
	{
		this.cgf = window;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				//deals card, updates status and disables buttons (like remove player)
				cgf.getPlayerCardPanel().nextCard(player, card);
				cgf.getStatusBar().update(String.format("%s dealt %s of %s",
						player.getPlayerName(), card.getValue(), card.getSuit()));
				cgf.getCardMenuBar().startGame();
			}
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				cgf.getPlayerCardPanel().bustCard(player, card);
				cgf.getStatusBar().update(String.format("%s busted on %s of %s",
						player.getPlayerName(), card.getValue(), card.getSuit()));
			}
		});

	}

	@Override
	public void result(Player player, int result, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				cgf.getStatusBar().update(String.format("%s finished with a score of %d",
						player.getPlayerName(), result));
			}
		});
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				cgf.getHouseCardPanel().nextCard(card);
				cgf.getStatusBar().update(String.format("House dealt %s of %s",
						card.getValue(), card.getSuit()));
			}
		});
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				cgf.getHouseCardPanel().bustCard(card);
				cgf.getStatusBar().update(String.format("House Busted on %s of %s",
						card.getValue(), card.getSuit()));
			}
		});
	}

	@Override
	public void houseResult(int result, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				cgf.endGame(result);
			}
		});
	}
}
