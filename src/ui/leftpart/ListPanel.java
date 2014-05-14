package ui.leftpart;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class ListPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final int width = 350;
	private static final int height = 480;
	public ListPanel() {
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(width, height));
	}

}
