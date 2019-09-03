package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AssociativeTextField extends JPanel
{
	private JTextField jtf;
	//a quick class just because i used it alot, can return textfield data as an int
	public AssociativeTextField(String descriptor, String placeholder)
	{
		setLayout(new GridLayout(1,2));
		add(new JLabel(descriptor));
		add(jtf = new JTextField(placeholder));
	}

	public String getText()
	{
		return jtf.getText();
	}

	public int getInt()
	{
		return Integer.parseInt(jtf.getText());
	}

}
