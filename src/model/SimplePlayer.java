package model;

import model.interfaces.Player;

public class SimplePlayer implements Player 
{
	private String playerId;
	private String playerName;
	private int points;
	private int bet = 0;
	private int result;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
	}

	@Override
	public String getPlayerName() 
	{
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}
	
	@Override
	public int getPoints() 
	{
		return points;
	}

	@Override
	public void setPoints(int points) 
	{
		this.points = points;
	}

	@Override
	public String getPlayerId() 
	{
		return playerId;
	}

	//implemented hashCode because players are held in HashMaps
	@Override
	public int hashCode()
	{
		//improved for GUI, now can have any string be an id no Exceptions needed
		int hashCode = 0;
		for(int i = 0; i < playerId.length();i++)
		{
			hashCode += ((int) playerId.charAt(i)) * Math.pow(100, (double) i);
		}
		return hashCode;
	}

	//this is only implemented because hashCode is
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof SimplePlayer)
		{
			return playerId.equals(((SimplePlayer) obj).getPlayerId());
		}
		return false;
	}

	@Override
	public boolean placeBet(int bet) 
	{
		if(bet >= 0 && bet <= points) 
		{
			this.bet = bet;
			return true;
		}
		return false;
	}

	@Override
	public int getBet() 
	{
		return bet;
	}

	@Override
	public void resetBet() 
	{
		bet = 0;
	}

	@Override
	public int getResult() 
	{
		return result;
	}

	@Override
	public void setResult(int result) 
	{
		this.result = result;
	}
	
	@Override
	public String toString()
	{
		return "Player: id=" + getPlayerId() + ", name=" + getPlayerName() + ", points=" + getPoints();
	}
}
