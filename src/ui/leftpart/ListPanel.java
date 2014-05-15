package ui.leftpart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import core.WordBook;


public class ListPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public JList<String> wordlist;
	public ListPanel(int width, int height, MouseListener mouseListener, KeyListener keyListener) {

		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(width, height));
		wordlist = new JList<String>();
		wordlist.setBackground(Color.GRAY);
		wordlist.addMouseListener(mouseListener);
		wordlist.addKeyListener(keyListener);
		JScrollPane listScrollPane = new JScrollPane(wordlist);
		listScrollPane.setPreferredSize(new Dimension(width, height));
		add(listScrollPane);
	}
	
	public void setWordBook(WordBook wordbook) {
		ArrayList<String> list = wordbook.toList();
		DefaultListModel<String> m = new DefaultListModel<String>();
		for(String s :list) {
			m.addElement(s);
		}
		wordlist.setModel(m);
	}

}
