package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AddPlayerListener;

@SuppressWarnings("serial")
public class AddPlayerModal extends JDialog
{
	private AssociativeTextField playerId;
	private AssociativeTextField playerName;
	private AssociativeTextField playerPoints;
	public AddPlayerModal(CardGameFrame cgf)
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setSize(300, 300);
		frame.add(panel);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		panel.add(new JLabel("Please add a Player"));
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(playerId = new AssociativeTextField("Player Id: ","1"), c);
		c.gridy = 2;
		panel.add(playerName = new AssociativeTextField("Player Name: ","The Shark"), c);
		c.gridy = 3;
		panel.add(playerPoints = new AssociativeTextField("Player Points: ","1000"),c);
		c.gridy = 4;
		JButton addPlayer;
		panel.add(addPlayer = new JButton("Add Player"),c);
		addPlayer.addActionListener(new AddPlayerListener(cgf, this));
		
		frame.setVisible(true);
	}
	//simple getters used by addPlayerListener for the Associative Text Fields
	public String getPlayerId(){return playerId.getText();}
	public String getPlayerName(){return playerName.getText();}
	public int getPlayerPoints(){return playerPoints.getInt();}
}
