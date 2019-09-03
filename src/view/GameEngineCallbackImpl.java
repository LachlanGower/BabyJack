package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java
 * logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public GameEngineCallbackImpl()
	{
		logger.setLevel(Level.FINE);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine)
	{
		String dealString = String.format("Card Dealt to %s .. %s", player.getPlayerName(), card.toString());
		logger.log(Level.FINE, dealString);
	}

	@Override
	public void result(Player player, int result, GameEngine engine)
	{
		String resultString = String.format("%s, final result="+result, player.getPlayerName());
		logger.log(Level.INFO, resultString);
	}

	public void bustCard(Player player, PlayingCard card, GameEngine engine)
	{
		String dealString = String.format("Card Dealt to %s .. %s ... YOU BUSTED!",
				player.getPlayerName(), card.toString());
		logger.log(Level.FINE, dealString);
	}

	public void nextHouseCard(PlayingCard card, GameEngine engine)
	{
		String dealString = String.format("Card Dealt to House .. %s", card.toString());
		logger.log(Level.FINE, dealString);
	}

	public void houseBustCard(PlayingCard card, GameEngine engine)
	{
		String dealString = String.format("Card Dealt to House .. %s ... HOUSE BUSTED!", card.toString());
		logger.log(Level.FINE, dealString);
	}

	public void houseResult(int result, GameEngine engine)
	{
		String resultString = String.format("House, final result=%d", result);
		logger.log(Level.INFO, resultString);
		
		resultString = String.format("Final Player Results\n");
		for(Player player: engine.getAllPlayers())
		{
			resultString += String.format("Player: id=%s, name=%s, points=%s\n", 
											player.getPlayerId(),
											player.getPlayerName(),
											player.getPoints());
		}
		logger.log(Level.INFO, resultString);
	}

}
