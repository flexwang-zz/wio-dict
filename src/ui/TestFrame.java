	package ui;
	
	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
	

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

	import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
	
	public class TestFrame extends JFrame implements DocumentListener, KeyListener {
	
		private static final long serialVersionUID = 1L;
	
		// tool bar
		private JToolBar topToolBar;
		private JTextField wordSearchField;
		private JComboBox<String> wordSearchTips;
	
		// window size
		private static final int width = 700;
		private static final int height = 500;
	
		public TestFrame() {
		}
	
		public static void main(String[] argv) {
			new TestFrame().CreateUI();
		}
	
		public void CreateUI() {
			setPreferredSize(new Dimension(width, height));
			setResizable(false);
			setLayout(new BorderLayout());
	
			// bottom
			JPanel bottom = new JPanel();
			bottom.setPreferredSize(new Dimension(width, 480));
			bottom.setLayout(new BorderLayout());
	
			// top
			topToolBar = new JToolBar();
			topToolBar.setBackground(Color.WHITE);
			topToolBar.setPreferredSize(new Dimension(width, 30));
	
			wordSearchTips = new JComboBox<String>();
			wordSearchTips.setEditable(true);
			wordSearchTips.setSelectedIndex(-1);
			wordSearchField = (JTextField) wordSearchTips.getEditor()
					.getEditorComponent();
			wordSearchField.getDocument().addDocumentListener(this);
			wordSearchField.addKeyListener(this);
			topToolBar.add(wordSearchTips);
	
			add(topToolBar, BorderLayout.NORTH);
			add(bottom, BorderLayout.SOUTH);
	
			pack();
			setVisible(true);
	
		}
	
		@Override
		public void changedUpdate(DocumentEvent e) {
	
		}
		
		private String last = "";
		@Override
		public void insertUpdate(DocumentEvent e) {

			
		}
	
		@Override
		public void removeUpdate(DocumentEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			SwingUtilities.invokeLater(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String keyword = wordSearchField.getText().trim();
					if (last.equals(keyword)) {
						//return;
					}
					
					last = keyword;
					DefaultComboBoxModel<String> m = new DefaultComboBoxModel<String>();
					;
					for (int i = 0; i < 10; i++) {
						m.addElement(i + "");
					}
					wordSearchTips.setModel(m);
					wordSearchTips.setSelectedIndex(-1);
					((JTextField) wordSearchTips.getEditor().getEditorComponent())
							.setText(keyword);
					wordSearchTips.showPopup();
				}});
		}
	}
