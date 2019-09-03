package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AddPlayerModal;
import view.CardGameFrame;

public class AddDialogListener implements ActionListener
{
	CardGameFrame cgf;
	public AddDialogListener(CardGameFrame cgf)
	{
		this.cgf = cgf;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		new AddPlayerModal(cgf);
	}

}
