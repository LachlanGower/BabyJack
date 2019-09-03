package view;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class CardGameFrame extends JFrame
{
	private GameEngine ge;
	private PlayerPanel ptb;
	private CardPanel playerCardPanel;
	private CardPanel houseCardPanel;
	private CardMenuBar cmb;
	private StatusBar sb;
	private Player selectedPlayer;
	private SummaryPanel summary;

	public CardGameFrame(String title, GameEngine ge)
	{
		super(title);
		this.ge = ge;

		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		setJMenuBar(cmb = new CardMenuBar(this));

		JPanel cardCenter = new JPanel();
		add(cardCenter, BorderLayout.CENTER);
		JPanel playerPanel = new JPanel();
		JPanel house = new JPanel();

		cardCenter.add(playerPanel);
		cardCenter.add(house);

		playerPanel.add(playerCardPanel = new CardPanel(this, "No Players"));

		house.add(houseCardPanel = new CardPanel(this, "House"));

		add(sb = new StatusBar(), BorderLayout.SOUTH);

		JPanel east = new JPanel();
		add(east, BorderLayout.EAST);
		east.setLayout(new BorderLayout());
		east.add(summary = new SummaryPanel(this, new DefaultListModel<String>()), BorderLayout.CENTER);
		east.add(ptb = new PlayerPanel(this), BorderLayout.NORTH);
		setVisible(true);

	}
	
	public Player getSelectedPlayer()
	{
		return selectedPlayer;
	}

	//sets selected player variable as well as switching sets up switching card panels
	// and bet consistency
	public void setSelectedPlayer(Player player)
	{
		selectedPlayer = player;
		getPlayerCardPanel().setSelectedPlayer(player);
		getPlayerPanel().setSelectedPlayer(player);
	}

	//called by add or remove player Listeners, takes a collection
	// like ge.getAllPlayers so lists are consistent and ids that overlap are deleted
	public void updatePlayers(Collection<Player> allPlayers)
	{
		getCardMenuBar().updatePlayers(allPlayers);
		getPlayerPanel().updatePlayers(allPlayers);
		getSummaryPanel().updateSummary(allPlayers);
	}
	
	//called by House Result: updates points in summary, reenables disbaled buttons and updates status
	public void endGame(int result)
	{
		getSummaryPanel().updateSummary(getGameEngine().getAllPlayers());
		getPlayerPanel().endGame();
		getStatusBar().update(String.format("House finished with a score of %d", result));
		getCardMenuBar().endGame();
		getPlayerCardPanel().setTotal(0);
		getHouseCardPanel().setTotal(0);
	}
	
	//Getters so as long as an object has a reference to CardGameFrame 
	//it will have references to its components 
	public GameEngine getGameEngine(){return ge;}

	public CardMenuBar getCardMenuBar(){return cmb;}

	public PlayerPanel getPlayerPanel(){return ptb;}

	public CardPanel getPlayerCardPanel(){return playerCardPanel;}

	public CardPanel getHouseCardPanel(){return houseCardPanel;}

	public StatusBar getStatusBar(){return sb;}

	public SummaryPanel getSummaryPanel(){return summary;}

	public void removePlayer(Player player)
	{
		getGameEngine().removePlayer(player);
		getPlayerPanel().updatePlayers(getGameEngine().getAllPlayers());
		getPlayerPanel().removePlayer(player);
		setSelectedPlayer(getPlayerPanel().getSelectedPlayer());
		getCardMenuBar().updatePlayers(getGameEngine().getAllPlayers());
	}
}
