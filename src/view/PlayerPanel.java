package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controller.BetPlayerListener;
import controller.PlayerSelectListener;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerPanel extends JToolBar
{

	private JComboBox<Player> pm;
	private HashMap<Player, Integer> isBet = new HashMap<>();
	private JButton placeBet;
	private JTextField setBet;

	public PlayerPanel(CardGameFrame cgf)
	{
		// set Panels defaults
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		add(pm = new JComboBox<Player>(), c);
		pm.addActionListener(new PlayerSelectListener(cgf));
		pm.setRenderer(new PlayerCellRenderer());

		c.gridy = 1;
		c.gridwidth = 1;
		add(setBet = new JTextField("0"), c);
		setBet.setHorizontalAlignment(WIDTH / 2);
		c.gridy = 1;
		c.gridx = 1;
		add(placeBet = new JButton("Place Bet"), c);
		placeBet.addActionListener(new BetPlayerListener(cgf));
		placeBet.setEnabled(false);
		setBet.setEnabled(false);
	}

	public void setSelectedPlayer(Player player)
	{
		if (player != null)
		{
			if (isBet.get(player) == 0)
			{
				setBet.setBorder(BorderFactory.createEmptyBorder());
				placeBet.setEnabled(true);
				setBet.setEnabled(true);
			} else
			{
				setBet.setText(String.valueOf(isBet.get(player)));
				placeBet.setEnabled(false);
				setBet.setEnabled(false);
			}
		} else
		{
			placeBet.setEnabled(false);
			setBet.setEnabled(false);
		}
	}

	public void updatePlayers(Collection<Player> players)
	{
		pm.removeAllItems();
		for (Player player : players)
		{
			isBet.putIfAbsent(player, 0);
			pm.addItem(player);
		}
	}

	public Player getSelectedPlayer()
	{
		return pm.getItemAt(pm.getSelectedIndex());
	}

	public HashMap<Player, Integer> getIsBet()
	{
		return isBet;
	}

	public int getBet()
	{
		return Integer.parseInt(setBet.getText());
	}

	public void setBet(Player player)
	{
		setBet.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
		setBet.setEnabled(false);
		placeBet.setEnabled(false);
		isBet.put(player, getBet());
	}

	public boolean isAllBet()
	{
		boolean allBet = true;
		for (int dealt : isBet.values())
			allBet = (allBet && (dealt > 0));
		return allBet;
	}

	public void endGame()
	{
		setBet.setBorder(BorderFactory.createEmptyBorder());
		setBet.setText("0");
		setBet.setEnabled(true);
		placeBet.setEnabled(true);
		for (Player key : isBet.keySet())
			isBet.put(key, 0);
	}

	public void invalidBet()
	{
		setBet.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		placeBet.setEnabled(true);
		setBet.setEnabled(true);
	}

	public void removePlayer(Player player)
	{
		isBet.remove(player);
	}

}
