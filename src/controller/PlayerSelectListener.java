package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CardGameFrame;

public class PlayerSelectListener implements ActionListener
{
	private CardGameFrame cgf;

	public PlayerSelectListener(CardGameFrame cgf)
	{
		this.cgf = cgf;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (cgf.getGameEngine().getAllPlayers().size() > 0)
		{
			cgf.setSelectedPlayer(cgf.getPlayerPanel().getSelectedPlayer());
		}

	}
}
