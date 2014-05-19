package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Window;
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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import ui.leftpart.ListPanel;
import ui.rightpart.WordPanel;
import core.WordBook;

public class CopyOfMainframe extends JFrame implements ActionListener, KeyListener,
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

	private Window w = this;
	private WordBook wordbook = null;

	private JComponent titlePane;
	
	public CopyOfMainframe() {
		super();
		setUndecorated(true);
		setResizable(false);
		setPreferredSize(new Dimension(width, height));
		setResizable(false);
		setLocationRelativeTo(new Frame());
	}

	public void CreateUI() {
		setLayout(null);
		// bottom
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(width, height - toolbarheight));
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

		topToolBar.setBounds(0, 0, width, toolbarheight);
		bottom.setBounds(0, toolbarheight, width, height - toolbarheight);
		//add(topToolBar);
		TitlePane tilepane = new TitlePane();
		MouseInputHandler handler = new MouseInputHandler();
		
		System.out.println(handler==null);
		//titlePane.addMouseListener(handler);
		//titlePane.addMouseMotionListener(handler);
		tilepane.setBounds(0, 0, width, toolbarheight);
		add(tilepane);
		add(bottom);
		pack();
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(OpenWordBook)) {
			FileDialog fd = new FileDialog(CopyOfMainframe.this, "Choose a file",
					FileDialog.LOAD);
			fd.setFile("*.xml");
			fd.setVisible(true);
			if (fd.getFile() != null && fd.getDirectory() != null) {
				String filename = fd.getDirectory() + fd.getFile();
				System.setProperty("user.dir", fd.getDirectory());
				System.out.println(filename);
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
				listPanel.wordlist.setSelectedValue(keyword, true);
				break;
			case KeyEvent.VK_ESCAPE:
				shouldHide = true;
				break;
			default:
				break;
			}
		} else if (e.getComponent() == listPanel.wordlist) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (wordbook != null) {
					String keyword = listPanel.wordlist.getSelectedValue();
					listPanel.lastselect = keyword;
					wordPanel.setCurWord(wordbook.search(keyword));
				}
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
					if (wordbook == null) {
						return;
					}
					String keyword = wordSearchField.getText().trim();

					if (keyword.isEmpty()) {
						wordSearchTips.hidePopup();
					} else {
						ArrayList<String> tips = wordbook.findTips(keyword, 10);
						DefaultComboBoxModel<String> m = new DefaultComboBoxModel<String>();
						for (String s : tips) {
							m.addElement(s);
						}

						CopyOfMainframe.this.wordSearchTips.setModel(m);
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
		if (e.getComponent() == listPanel.wordlist) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (wordbook != null) {
					String keyword = listPanel.wordlist.getSelectedValue();
					if (listPanel.lastselect.equals(keyword)) {
						wordPanel.setCurWord(wordbook.search(keyword));
					}
					listPanel.lastselect = keyword;
				}
			}
		}
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
	
	public static void main(String[] argv) {
		new CopyOfMainframe().CreateUI();
	}
	
	/**
	 * Class handling mouse input to enable titlePane become drag-able and window become move-able.
	 */
	private class MouseInputHandler implements MouseInputListener {
        private boolean isMovingWindow;
        private int dragOffsetX;
        private int dragOffsetY;
        private static final int BORDER_DRAG_THICKNESS = 5;

        public void mousePressed(MouseEvent ev) {
            Point dragWindowOffset = ev.getPoint();
            if (w != null) {
                w.toFront();
            }
            Point convertedDragWindowOffset = SwingUtilities.convertPoint(
                           w, dragWindowOffset, titlePane);

            Frame f = null;
            Dialog d = null;

            if (w instanceof Frame) {
                f = (Frame)w;
            } else if (w instanceof Dialog) {
                d = (Dialog)w;
            }

            int frameState = (f != null) ? f.getExtendedState() : 0;

            if (titlePane.contains(convertedDragWindowOffset)) {
                if ((f != null && ((frameState & Frame.MAXIMIZED_BOTH) == 0)
                        || (d != null))
                        && dragWindowOffset.y >= BORDER_DRAG_THICKNESS
                        && dragWindowOffset.x >= BORDER_DRAG_THICKNESS
                        && dragWindowOffset.x < w.getWidth()
                            - BORDER_DRAG_THICKNESS) {
                    isMovingWindow = true;
                    dragOffsetX = dragWindowOffset.x;
                    dragOffsetY = dragWindowOffset.y;
                }
            }
            else if (f != null && f.isResizable()
                    && ((frameState & Frame.MAXIMIZED_BOTH) == 0)
                    || (d != null && d.isResizable())) {
                dragOffsetX = dragWindowOffset.x;
                dragOffsetY = dragWindowOffset.y;
            }
        }

        public void mouseReleased(MouseEvent ev) {
            isMovingWindow = false;
        }

        public void mouseDragged(MouseEvent ev) {
            if (isMovingWindow) {
                Point windowPt = MouseInfo.getPointerInfo().getLocation();
                windowPt.x = windowPt.x - dragOffsetX;
                windowPt.y = windowPt.y - dragOffsetY;
                w.setLocation(windowPt);
            }
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
	}
}

