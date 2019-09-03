package view;

import java.awt.Dimension;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class SummaryPanel extends JList<String>
{
	private DefaultListModel<String> model;

	public SummaryPanel(CardGameFrame cgf, DefaultListModel<String> model)
	{
		super(model);//HAHAHAHAHA
		this.model = model;
		setPreferredSize(new Dimension(200, 400));
	}

	//sets the individual summary on the left for each player
	//logic to figure out if they won or lost
	//resetBet is the bane of my existence
	//updates on houseResult-updates on add or remove
	public void updateSummary(Collection<Player> allPlayers)
	{
		model.removeAllElements();
		for (Player player : allPlayers)
			model.addElement(String.format("%s-%s: %s",player.getPlayerId(),
					player.getPlayerName(), player.getPoints()));
	}
}
