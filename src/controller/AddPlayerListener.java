package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import view.AddPlayerModal;
import view.CardGameFrame;

public class AddPlayerListener implements ActionListener
{
	CardGameFrame cgf;
	AddPlayerModal apm;
	public AddPlayerListener(CardGameFrame cgf, AddPlayerModal apm)
	{
		this.cgf = cgf;
		this.apm = apm;
	}

	//adds player defined by AddPlayerModal to game engine and updates player lists
	@Override
	public void actionPerformed(ActionEvent e)
	{
		cgf.getGameEngine().addPlayer(new SimplePlayer(apm.getPlayerId(),
				apm.getPlayerName(),apm.getPlayerPoints()));
		
		cgf.updatePlayers(cgf.getGameEngine().getAllPlayers());
	}

}
