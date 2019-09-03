package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import factory.PlayingCardFactory;
import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class CardLabel extends JLabel
{
	//could be done in CardPanel 
	//class that basically just styles the label
	public CardLabel()
	{
		super("No Cards In Play");
		setPreferredSize(new Dimension(130, 200));
		setHorizontalAlignment(WIDTH/2);
	}
	public void nextCard(PlayingCard card)
	{
		setText("");
		setIcon(PlayingCardFactory.getPlayingCard(card));
		setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
	}
	public void bustCard(PlayingCard card)
	{
		if(card != null) {
			setText("");
			setIcon(PlayingCardFactory.getPlayingCard(card));
			setBorder(BorderFactory.createLineBorder(Color.RED, 5));
		}else{
			setText("No Cards in Play");
			setIcon(null);
			setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		}
	}
}
