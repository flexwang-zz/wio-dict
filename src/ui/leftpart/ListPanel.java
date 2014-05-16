package ui.leftpart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import core.WordBook;


public class ListPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public String lastselect = "";
	public JList<String> wordlist;
	public JScrollPane wordlistScroll;
	public ListPanel(int width, int height, MouseListener mouseListener, KeyListener keyListener) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(0, 0, 0, 0)); 
		setPreferredSize(new Dimension(width, height));
		wordlist = new JList<String>();
		//wordlist.setBackground(Color.GRAY);
		wordlist.addMouseListener(mouseListener);
		wordlist.addKeyListener(keyListener);
		wordlistScroll = new JScrollPane(wordlist);
		//wordlist.setPreferredSize(new Dimension(width, height));
		wordlistScroll.setPreferredSize(new Dimension(width, height));
		wordlistScroll.setVisible(false);
		add(wordlistScroll, BorderLayout.CENTER);
	}
	
	public void setWordBook(WordBook wordbook) {
		wordlistScroll.setVisible(true);
		ArrayList<String> list = wordbook.toList();
		DefaultListModel<String> m = new DefaultListModel<String>();
		for(String s :list) {
			m.addElement(s);
		}
		wordlist.setModel(m);
		validate();
	}

}
