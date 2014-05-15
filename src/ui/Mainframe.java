package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ui.leftpart.ListPanel;
import ui.rightpart.WordPanel;
import core.Word;
import core.WordBook;

public class Mainframe extends JFrame implements ActionListener,
		KeyListener {

	private static final long serialVersionUID = 1L;

	private WordPanel wordPanel;
	private ListPanel listPanel;

	// tool bar
	private JToolBar topToolBar;
	private JButton openWordBook;
	private JTextField wordSearchField;
	private JComboBox<String> wordSearchTips;

	// ActionCommand string
	private final static String OpenWordBook = "openwordbook";
	private final static String SearchWord = "searchword";

	// window size
	private static final int width = 700;
	private static final int height = 500;

	private WordBook wordbook;

	public Mainframe() {
	}

	public void CreateUI() {
		setPreferredSize(new Dimension(width, height));
		setResizable(false);
		setLayout(new BorderLayout());

		// bottom
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(width, 480));
		bottom.setLayout(new BorderLayout());

		wordPanel = new WordPanel(400, 400);
		bottom.add(wordPanel, BorderLayout.EAST);

		listPanel = new ListPanel();
		bottom.add(listPanel, BorderLayout.WEST);

		// top
		topToolBar = new JToolBar();
		topToolBar.setBackground(Color.WHITE);
		topToolBar.setPreferredSize(new Dimension(width, 30));

		openWordBook = new JButton("Open");
		openWordBook.setActionCommand(OpenWordBook);
		openWordBook.addActionListener(this);
		topToolBar.add(openWordBook);

		wordSearchTips = new JComboBox<String>();
		wordSearchTips.setEditable(true);
		wordSearchTips.setSelectedIndex(-1);
		wordSearchField = (JTextField) wordSearchTips.getEditor()
				.getEditorComponent();
		// wordSearchField = new JTextField(10);
		wordSearchField.setActionCommand(SearchWord);
		wordSearchField.addKeyListener(this);
		wordSearchField.addActionListener(this);

		topToolBar.add(wordSearchTips);

		add(topToolBar, BorderLayout.NORTH);
		add(bottom, BorderLayout.SOUTH);

		pack();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals(OpenWordBook)) {
			FileDialog fd = new FileDialog(Mainframe.this, "Choose a file",
					FileDialog.LOAD);
			fd.setFile("*.xml");
			fd.setVisible(true);
			String filename = fd.getDirectory() + fd.getFile();
			if (filename != null) {
				wordbook = new WordBook(filename);
			}
		} else if (e.getActionCommand().equals(SearchWord)) {
			String keyword = wordSearchField.getText().trim();
			wordPanel.setCurWord(wordbook.search(keyword));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				String keyword = wordSearchField.getText().trim();
				ArrayList<String> tips = wordbook.findTips(keyword, 10);
				DefaultComboBoxModel<String> m = new DefaultComboBoxModel<String>();

				for (String s : tips) {
					m.addElement(s);
				}
				String str[] = tips.toArray(new String[tips.size()]);
				Mainframe.this.wordSearchTips.setModel(m);
				wordSearchTips.setSelectedIndex(-1);
				((JTextField) wordSearchTips.getEditor().getEditorComponent())
						.setText(keyword);
				wordSearchTips.showPopup();
				System.out.println("tips");
				for (int i = 0, size = str.length; i < size; i++) {
					System.out.println(tips.get(i));
				}
			}
		});
	}
}
