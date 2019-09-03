package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard
{
	private PlayingCard.Suit suit;
	private PlayingCard.Value value;

	public PlayingCardImpl(Suit suit, Value value)
	{
		this.suit = suit;
		this.value = value;
	}

	public Suit getSuit()
	{
		return suit;
	}

	public Value getValue()
	{
		return value;
	}

	public int getScore()
	{
		// returns the value of the card. if the card is 10/jack... return 10 other wise
		// return value
		return (value.ordinal() > 9) ? 10 : value.ordinal() + 1;
	}

	public int hashCode()
	{
		// returns a unique (to PlayingCard) value based on the suit and value
		// This mean i can compare cards using only the hashCode
		return (suit.ordinal() * 100) + value.ordinal();
	}

	// basically if its not a Playing card it can't be equal anyway
	public boolean equals(Object obj)
	{
		return (obj instanceof PlayingCard && this.equals((PlayingCard) obj));
	}

	public boolean equals(PlayingCard card)
	{
		return card.getSuit().equals(getSuit()) && card.getValue().equals(getValue());
	}

	public String toString()
	{
		return "Suit: " + getSuit() + ", Value: " + getValue() + ", Score: " + getScore();
	}

}
