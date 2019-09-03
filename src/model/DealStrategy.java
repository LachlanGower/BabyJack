package model;

import java.util.Deque;

import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public abstract class DealStrategy
{
	Deque<GameEngineCallback> gec;
	GameEngine ge;

	/*
	 * The DealStrategy Abstract Class, isnt an interface because i wanted a constructor
	 * 
	 */
	public DealStrategy(Deque<GameEngineCallback> gec, GameEngine ge)
	{
		this.gec = gec;
		this.ge = ge;
	}
	public abstract void deal(PlayingCard card);

	public abstract void bust(PlayingCard card);

	public abstract void result(int result);
}
