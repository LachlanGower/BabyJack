package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener
{
	//woulda been a good anon inner class
	@Override
	public void actionPerformed(ActionEvent e){System.exit(0);}
}
