package view;

import java.awt.Color;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddDialogListener;
import controller.ExitListener;
import controller.RemovePlayerListener;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class CardMenuBar extends JMenuBar
{
	private JMenuItem remove;

	public CardMenuBar(CardGameFrame cgf)
	{
		setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		setBackground(Color.WHITE);
		JMenu file;
		add(file = new JMenu("Players"));
		JMenuItem add;
		file.add(add = new JMenuItem("Add Player"));
		file.add(remove = new JMenuItem("Remove Player"));
		JMenu help;
		add(help = new JMenu("Help"));
		JMenuItem exit;
		help.add(exit = new JMenuItem("Exit"));
		
		add.addActionListener(new AddDialogListener(cgf));
		remove.addActionListener(new RemovePlayerListener(cgf));
		remove.setEnabled(false);
		exit.addActionListener(new ExitListener());
	}
	//logic for when players can't be removed such as when 
	//there are no players or the game has started
	public void updatePlayers(Collection<Player> players)
	{
		if(players.size() > 0)
			remove.setEnabled(true);
		if(players.size() < 1)
			remove.setEnabled(false);
	}
	public void startGame()
	{
		remove.setEnabled(false);
	}
	public void endGame()
	{
		remove.setEnabled(true);
	}
}
