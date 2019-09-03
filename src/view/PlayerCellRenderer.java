package view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerCellRenderer extends JLabel implements ListCellRenderer<Player>
{
	//Class is only used so <Player> works with JComboBox, isnt super important
	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, Player value, int index,
			boolean isSelected, boolean cellHasFocus)
	{
		if (value != null)
		{
			setText(String.format("%s: %s", value.getPlayerId(), value.getPlayerName()));
			setOpaque(true);
			if (isSelected)
			{
				setBackground(list.getSelectionBackground());
			} else
			{
				setBackground(list.getBackground());
			}
		}else {
			setText("No Players");
		}
		return this;
	}

}
