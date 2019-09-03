package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.Player;
import view.CardGameFrame;

public class BetPlayerListener implements ActionListener
{
	private CardGameFrame cgf;

	public BetPlayerListener(CardGameFrame cgf)
	{
		this.cgf = cgf;
	}

	//gets bet from the textField and sets it
	//bet must be greater than 0 and player must have enough points
	//player is removed on a bet of 0
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int bet = cgf.getPlayerPanel().getBet();
		Player player = cgf.getSelectedPlayer();
		if(player.getPoints() == 0) {
			cgf.removePlayer(player);
		}
		else if (bet > 0 && player.getPoints() >= bet)
		{
			player.placeBet(bet);
			cgf.getPlayerPanel().setBet(player);
			new Thread()
			{
				@Override
				public void run()
				{
					cgf.getGameEngine().dealPlayer(player, 1000);
					if (cgf.getPlayerPanel().isAllBet())
						cgf.getGameEngine().dealHouse(1000);
				}
			}.start();
		}else {
			cgf.getPlayerPanel().invalidBet();
		}
	}

}
