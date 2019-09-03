package model;

import java.util.Deque;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class PlayerDealStrategy extends DealStrategy
{
	Player player;

	public PlayerDealStrategy(Deque<GameEngineCallback> gec, GameEngine ge, Player player)
	{
		super(gec, ge);
		this.player = player;
	}

	@Override
	public void deal(PlayingCard card)
	{
		// each for iterates over the callback list to make sure every callback is
		// called. I could refactor this for loop out into a template method
		// but it would probably be more trouble than its worth.
		for (GameEngineCallback callback : gec)
		{
			callback.nextCard(player, card, ge);
		}
	}

	@Override
	public void bust(PlayingCard card)
	{
		for (GameEngineCallback callback : gec)
		{
			callback.bustCard(player, card, ge);
		}
	}

	@Override
	public void result(int result)
	{
		player.setResult(result);
		for (GameEngineCallback callback : gec)
		{
			callback.result(player, player.getResult(), ge);
		}
	}

}
