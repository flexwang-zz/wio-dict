package ui.rightpart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;


import core.Word;

public class WordPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private Word curWord = null;
	
	private JTextArea wordArea;
	private JTextArea phoneticArea;
	private DefinitionTabPanel definitionArea;
	private ImagePanel imageArea;
	private ImageAudioButton pronounceButton;
	
	private final static Color bgcolor= new Color(242, 242, 242);
	public WordPanel(int width, int height) {
		
		setBackground(bgcolor);
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
        wordArea.setBackground(bgcolor);
        
        phoneticArea = new JTextArea();
        phoneticArea.setFont(new Font("Serif", Font.BOLD, 16));
        phoneticArea.setLineWrap(true);
        phoneticArea.setWrapStyleWord(true);
        phoneticArea.setEditable(false);
        phoneticArea.setBackground(bgcolor);
        
        definitionArea = new DefinitionTabPanel();
        
        imageArea = new ImagePanel(100, 100);
        pronounceButton = new ImageAudioButton(48, 48, 
        		"./res/images/dict_pronounce-_icon_normal.png", 
        		"./res/images/dict_pronounce-_icon_pressed.png", 
        		"./res/images/dict_pronounce-_icon_disable.png");
        
        wordArea.setVisible(false);
        phoneticArea.setVisible(false);
        definitionArea.setVisible(false);
        imageArea.setVisible(false);
        pronounceButton.setVisible(false);
        
        add(imageArea);
        add(definitionArea);
        add(wordArea);
        add(phoneticArea);
        add(pronounceButton);
       
        wordArea.setBounds(20, 40, 500, 60);
        phoneticArea.setBounds(20, 100, 300, 20); 
        pronounceButton.setBounds(300, 100, 48, 48);
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
        pronounceButton.setVisible(true);
        
        definitionArea.setCurWord(curWord);
        imageArea.setImage(curWord.image_url);
        pronounceButton.setAudio(curWord.sound_url);
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
