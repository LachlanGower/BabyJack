package model;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine
{
	private Map<String, Player> players = new HashMap<>();
	private Deque<GameEngineCallback> gec = new LinkedList<GameEngineCallback>();
	private LinkedList<PlayingCard> deck = new LinkedList<PlayingCard>();

	public GameEngineImpl()
	{
		// i was constantly remaking the deck when using getShuffledDeck
		// using a variable for an unshuffled deck fixes that
		for (PlayingCard.Suit suit : PlayingCard.Suit.values())
		{
			for (PlayingCard.Value value : PlayingCard.Value.values())
			{
				deck.add(new PlayingCardImpl(suit, value));
			}
		}
		// it makes sense to shuffle the deck before the game starts
		getShuffledDeck();
	}

	private void dealImpl(DealStrategy ds, int delay)
	{
		// Deal Implementation method, This is a Template method which implements a
		// Strategy Pattern (switching an Interface for an Abstract Class however)
		// If dealPlayer is called then the methods in DealPlayerStrategy are called
		// vice versa for dealHouse
		PlayingCard card = deck.pollFirst();
		deck.addLast(card);
		int result = 0;
		while (result + card.getScore() <= GameEngine.BUST_LEVEL)
		{
			result += card.getScore();
			ds.deal(card);
			try
			{
				Thread.sleep(delay);
			} catch (InterruptedException e)
			{
				System.out.println("Exception-- Thread Interrupted");
			}
			card = deck.pollFirst();
			deck.addLast(card);
		}
		ds.bust(card);
		ds.result(result);
	}

	// these two methods call the template class and create their respective
	// strategy classes
	public void dealPlayer(Player player, int delay)
	{
		dealImpl(new PlayerDealStrategy(gec, GameEngineImpl.this, player), delay);
	}

	public void dealHouse(int delay)
	{
		dealImpl(new HouseDealStrategy(gec, GameEngineImpl.this), delay);
		getShuffledDeck();
	}

	// as players is a map, adding a playing with the same id will overwrite the old
	// one
	public void addPlayer(Player player)
	{
		players.put(player.getPlayerId(), player);
		System.out.println(player.toString());
	}

	public Player getPlayer(String id)
	{
		return players.get(id);
	}

	public boolean removePlayer(Player player)
	{
		return players.remove(player.getPlayerId(), player);
	}

	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		gec.add(gameEngineCallback);
	}

	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		return gec.remove(gameEngineCallback);
	}

	public Collection<Player> getAllPlayers()
	{
		return Collections.unmodifiableCollection(players.values());
	}

	public boolean placeBet(Player player, int bet)
	{
		return player.placeBet(bet);
	}

	public Deque<PlayingCard> getShuffledDeck()
	{
		Collections.shuffle(deck);
		return deck;
	}

}
