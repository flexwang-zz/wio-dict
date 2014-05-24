package ui.rightpart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import core.Definition;

public class DefinitionTabCard extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea definitionArea;
	private JPanel exampleArea;
	private final static Color bgcolor= new Color(242, 242, 242);
	
	public DefinitionTabCard(Definition definition) {
		super();
		
		setBackground(bgcolor);
		setBorder(BorderFactory.createEmptyBorder());
		initDefinitionArea(definition);
		initExampleArea(definition);
		
		this.setPreferredSize(new Dimension(400, 300));
		setLayout(null);
		
		definitionArea.setBackground(bgcolor);
		exampleArea.setBackground(bgcolor);
		
		add(definitionArea);
		add(exampleArea);
		
		definitionArea.setBounds(0, 0, 400, 100);
		exampleArea.setBounds(0, 120, 400, 100);
	}
	
	private void initDefinitionArea(Definition definition) {
			definitionArea = new JTextArea();
			definitionArea.setFont(new Font("Serif", Font.PLAIN, 12));
			definitionArea.setLineWrap(false);
			definitionArea.setWrapStyleWord(true);
			definitionArea.setEditable(false);
			definitionArea.setText(definition.content);
	}
	
	private void initExampleArea(Definition definition) {		
		exampleArea = new JPanel();
		int nexample = definition.examples.size();
		exampleArea.setLayout(new GridLayout(nexample, 1));
		exampleArea.setBackground(bgcolor);
		for (int i=0; i<nexample; i++) {
			JTextArea exaText = new JTextArea();
			exaText.setFont(new Font("Serif", Font.PLAIN, 12));
			exaText.setBackground(bgcolor);
			exaText.setLineWrap(false);
			exaText.setWrapStyleWord(true);
			exaText.setEditable(false);
			exaText.setText(definition.examples.get(i).sentence
					+ "\n"
					+ definition.examples.get(i).meaning);
		
			exampleArea.add(exaText);
		}			
	}
}
