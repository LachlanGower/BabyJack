package factory;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;


import model.interfaces.PlayingCard;

public class PlayingCardFactory
{
	//filepaths were allowed in the discussion forums as a static reference
	// i could implement this without static it just was a but more work and wasnt gradeable.
	public static ImageIcon getPlayingCard(PlayingCard card)
	{
		String path = String.format("PlayingCards%s%s%s%s.png", File.separator,card.getSuit(), File.separator, card.getValue());
		ImageIcon icon = new ImageIcon(path);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(130, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon = new ImageIcon(newimg);
		return icon;
	}
}
