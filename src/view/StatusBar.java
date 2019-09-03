package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatusBar extends JPanel
{
	private JLabel status;
	public StatusBar()
	{
		setPreferredSize(new Dimension(800, 50));
		setBorder(BorderFactory.createMatteBorder(2,0,0,0,Color.BLACK));
		setBackground(Color.WHITE);
		add(status = new JLabel("Welcome to Baby Jack"));
	}
	public void update(String status)
	{
		this.status.setText(status);
	}
}
