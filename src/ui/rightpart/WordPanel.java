package ui.rightpart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import core.Word;

public class WordPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;
	
	private Word curWord = null;
	
	private JTextArea wordArea;
	private JTextArea phoneticArea;
	private DefinitionTabPanel definitionArea;
	private ImagePanel imageArea;
	
	public WordPanel(int width, int height) {
		this.width = width;
		this.height = height;
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		createUI();
		
	}
	
	private void createUI() {
		UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(1,0,0,0));
		UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);
		wordArea = new JTextArea();
        wordArea.setFont(new Font("Serif", Font.BOLD, 40));
        wordArea.setLineWrap(false);
        wordArea.setWrapStyleWord(true);
        wordArea.setEditable(false);
        
        phoneticArea = new JTextArea();
        phoneticArea.setFont(new Font("Serif", Font.BOLD, 16));
        phoneticArea.setLineWrap(true);
        phoneticArea.setWrapStyleWord(true);
        phoneticArea.setEditable(false);
        
        definitionArea = new DefinitionTabPanel();
        
        imageArea = new ImagePanel(100, 100);
        
        wordArea.setVisible(false);
        phoneticArea.setVisible(false);
        definitionArea.setVisible(false);
        imageArea.setVisible(false);
        
        add(imageArea);
        add(definitionArea);
        add(wordArea);
        add(phoneticArea);
        
       
        wordArea.setBounds(20, 40, 500, 60);
        phoneticArea.setBounds(20, 100, 500, 20);  
        definitionArea.setBounds(20, 140, 400, 300);
        imageArea.setBounds(200, 40, 100, 100);
        
	}
	
	public void setCurWord(Word newword) {
		if (newword == null) {
			return;
		}
		
		curWord = newword;
        
        wordArea.setVisible(true);
        phoneticArea.setVisible(true);
        definitionArea.setVisible(true);
        imageArea.setVisible(true);
        
        definitionArea.setCurWord(curWord);
        imageArea.setImage(curWord.image_url);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		if (curWord != null && !curWord.IsEmptyorNull()) {
			wordArea.setText(curWord.word);
			phoneticArea.setText(curWord.phonetic);
		}
	}
	
}
