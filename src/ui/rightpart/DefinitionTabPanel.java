package ui.rightpart;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JTabbedPane;

import core.Definition;
import core.Word;

public class DefinitionTabPanel extends JTabbedPane{
	private static final long serialVersionUID = 1L;
	
	final static int width = 200;
	final static int height = 200;
	public DefinitionTabPanel() {
		super();
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(width, height));
	}
	
	public void setCurWord(Word newword) {
		removeAll();
		Vector<Definition> definitions = newword.definitions;
		for (int i=0, size=definitions.size(); i<size; i++) {
			this.addTab(definitions.get(i).language,
					new DefinitionTabCard(definitions.get(i)));
		}
	}
   
}
