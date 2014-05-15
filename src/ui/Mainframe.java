package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import ui.leftpart.ListPanel;
import ui.rightpart.WordPanel;
import core.WordBook;

public class Mainframe extends JFrame implements ActionListener, KeyListener,
		MouseListener {

	private static final long serialVersionUID = 1L;

	private WordPanel wordPanel;
	private ListPanel listPanel;

	// tool bar
	private JToolBar topToolBar;
	private JButton openWordBook;
	private JTextField wordSearchField;
	private JComboBox<String> wordSearchTips;
	private Boolean shouldHide;

	// ActionCommand string
	private final static String OpenWordBook = "openwordbook";
	private final static String SearchWord = "searchword";

	// window size
	private static final int width = 700;
	private static final int height = 500;
	private static final int wordpanelwidth = 500;
	private static final int toolbarheight = 30;

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

		wordPanel = new WordPanel(wordpanelwidth, height - toolbarheight);
		bottom.add(wordPanel, BorderLayout.EAST);

		listPanel = new ListPanel(width - wordpanelwidth, height
				- toolbarheight, this, this);
		bottom.add(listPanel, BorderLayout.WEST);

		// top
		topToolBar = new JToolBar();
		topToolBar.setBackground(Color.WHITE);
		topToolBar.setPreferredSize(new Dimension(width, toolbarheight));

		openWordBook = new JButton("Open");
		openWordBook.setActionCommand(OpenWordBook);
		openWordBook.addActionListener(this);
		topToolBar.add(openWordBook);

		wordSearchTips = new JComboBox<String>();
		wordSearchTips.setBackground(Color.WHITE);
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
		if (e.getActionCommand().equals(OpenWordBook)) {
			FileDialog fd = new FileDialog(Mainframe.this, "Choose a file",
					FileDialog.LOAD);
			fd.setFile("*.xml");
			fd.setVisible(true);
			String filename = fd.getDirectory() + fd.getFile();
			if (filename != null) {
				wordbook = new WordBook(filename);
				listPanel.setWordBook(wordbook);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getComponent() == wordSearchField) {
			shouldHide = false;
			switch (e.getKeyCode()) {
			case KeyEvent.VK_TAB:
			case KeyEvent.VK_RIGHT:
				if (this.wordSearchTips.getModel().getSize() > 0) {
					this.wordSearchField.setText(wordSearchTips.getModel()
							.getElementAt(0));
				}
				break;
			case KeyEvent.VK_ENTER:
				String keyword = wordSearchField.getText().trim();
				wordPanel.setCurWord(wordbook.search(keyword));
				shouldHide = true;
				break;
			case KeyEvent.VK_ESCAPE:
				shouldHide = true;
				break;
			default:
				break;
			}
		} else if(e.getComponent() == listPanel.wordlist) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String keyword = listPanel.wordlist.getSelectedValue();
				wordPanel.setCurWord(wordbook.search(keyword));
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getComponent() == wordSearchField) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					String keyword = wordSearchField.getText().trim();

					if (keyword.isEmpty()) {
						wordSearchTips.hidePopup();
					} else {
						ArrayList<String> tips = wordbook.findTips(keyword, 10);
						DefaultComboBoxModel<String> m = new DefaultComboBoxModel<String>();
						for (String s : tips) {
							m.addElement(s);
						}

						Mainframe.this.wordSearchTips.setModel(m);
						wordSearchTips.setSelectedIndex(-1);
						((JTextField) wordSearchTips.getEditor()
								.getEditorComponent()).setText(keyword);

						if (!shouldHide) {
							wordSearchTips.showPopup();
						}
					}
				}
			});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
