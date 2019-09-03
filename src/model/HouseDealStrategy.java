package model;

import java.util.Deque;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class HouseDealStrategy extends DealStrategy
{
	public HouseDealStrategy(Deque<GameEngineCallback> gec, GameEngine ge)
	{
		super(gec, ge);
	}

	@Override
	public void deal(PlayingCard card)
	{
		for(GameEngineCallback callback : gec)
		{
			callback.nextHouseCard(card, ge);
		}
	}

	@Override
	public void bust(PlayingCard card)
	{
		for(GameEngineCallback callback : gec)
		{
			callback.houseBustCard(card, ge);
		}
	}

	@Override
	public void result(int result)
	{
		// this loop iterates over the players and uses their score in relation to the
		// house score to decide what happens to their points. 
		for (Player player : ge.getAllPlayers())
		{
			// less points : lose bet 
			if (player.getResult() < result)
			{
				player.setPoints(player.getPoints() - player.getBet());
			}
			// tie points : no change
			if (player.getResult() == result)
			{
				player.setPoints(player.getPoints());
			}
			// more points : add bet
			if (player.getResult() > result)
			{
				player.setPoints(player.getPoints() + player.getBet());
			}
			player.resetBet();
		}
		ge.getShuffledDeck();
		for(GameEngineCallback callback : gec)
		{
			callback.houseResult(result, ge);
		}
	}

}
