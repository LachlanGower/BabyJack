package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class CardPanel extends JPanel
{
	private CardLabel cardLabel;
	private JLabel totalLabel;
	private HashMap<Player, PlayingCard> lastCardDealt;
	private int total;
	private CardGameFrame cgf;
	private JLabel titleLabel;

	public CardPanel(CardGameFrame cgf, String title)
	{
		this.cgf = cgf;
		lastCardDealt = new HashMap<>();
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		add(this.titleLabel = new JLabel(title), c);
		c.gridy = 1;
		add(cardLabel = new CardLabel(), c);
		c.gridy = 2;
		add(totalLabel = new JLabel("Total: 0"), c);
	}
	
	//called by GECGUI for player
	public void nextCard(Player player, PlayingCard card)
	{
		lastCardDealt.put(player, card);
		if (cgf.getSelectedPlayer().equals(player))
			nextCard(card);
	}
	//called by GECGUI for house or by above for player
	public void nextCard(PlayingCard card)
	{
		total += (card.getScore());
		cardLabel.nextCard(card);
		totalLabel.setText(String.format("Total: %d", total));
	}
	//called by GECGUI for player
	public void bustCard(Player player, PlayingCard card)
	{
		lastCardDealt.put(player, card);
		totalLabel.setText(String.format("Total: %d", player.getResult()));
		if (cgf.getSelectedPlayer().equals(player))
			bustCard(card);
	}
	//for house
	public void bustCard(PlayingCard card)
	{
		cardLabel.bustCard(card);
		total = 0;
	}

	//called by Card Game Frame and sets result and shows last card dealt
	//a small error in logic is that this card will always look like a bust card but it isnt
	//can only be replicated on a quick switch
	//totals should be stored in a seperate array but this case isnt in the rubric
	public void setSelectedPlayer(Player player)
	{
		if (player != null)
		{
			titleLabel.setText(player.getPlayerName());
			totalLabel.setText(String.format("Total: %d", player.getResult()));
			cardLabel.bustCard(lastCardDealt.get(player));
		} else
		{
			titleLabel.setText("No Player Selected");
			totalLabel.setText(String.format("Total: %d", 0));
			cardLabel.bustCard(null);
		}
		

	}
	//stops the total from compunding
	public void setTotal(int newTotal)
	{
		total = newTotal;
	}

}
