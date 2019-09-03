package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CardGameFrame;

public class RemovePlayerListener implements ActionListener
{
	private CardGameFrame cgf;

	public RemovePlayerListener(CardGameFrame cgf)
	{
		this.cgf = cgf;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		cgf.removePlayer(cgf.getSelectedPlayer());
	}
}
