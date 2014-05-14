package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;










import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ui.leftpart.ListPanel;
import ui.rightpart.WordPanel;
import core.Word;

public class Mainframe extends JFrame implements ActionListener,MouseMotionListener,MouseListener, ItemListener{

	private static final long serialVersionUID = 1L;
	
	private WordPanel wordPanel;
	private ListPanel listPanel;
	
	//window size
	private static final int width = 700;
	private static final int height = 500;
	
	public Mainframe() {
	}
	
	public void CreateUI() {
		setPreferredSize(new Dimension(width, height));
		setResizable(false);
		setLayout(new BorderLayout());
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		
		wordPanel = new WordPanel();
		bottom.add(wordPanel, BorderLayout.EAST);
		
		listPanel = new ListPanel();
		bottom.add(listPanel, BorderLayout.WEST);
		
		add(bottom, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		wordPanel.setCurWord(new Word());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
