package ui;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.MouseInputListener;

public class CopyOfFancyWindow extends JDialog{
	private JComponent titlePane, contentPane, bottomPane;
	private JLabel titleLabel, resizeLabel;
	private JButton closeButton;
	private Window w = this;
	private ActionListener closeListener;

	public CopyOfFancyWindow(Frame parent){
		this(parent, "");
	}

	public CopyOfFancyWindow(Frame parent, String title){
		super(parent, title);
		setUndecorated(true);
		setResizable(false);
		setBackground(new Color(0,0,0,0));
		setContentPane(createContentPane());
		initComponents();
		setSize(200, 300);
		setLocationRelativeTo(parent);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTitle(String title){
		titleLabel.setText(title);
	}

	/**
	 * Install window closing listener when close button pressed.
	 * @param listener desired action to take.
	 */
	public void installCloseButtonListener(ActionListener listener){
		if(closeListener != null){
			closeButton.removeActionListener(closeListener);
		}

		closeListener = listener;
		closeButton.addActionListener(closeListener);
	}

	private void initComponents(){
		titleLabel = new JLabel(getTitle());
		titleLabel.setForeground(Color.WHITE);
		closeButton = new JButton();
		/*closeButton.setIcon(new ImageIcon(FancyWindow.class.getResource(
				"close.png")));
		closeButton.setRolloverIcon(new ImageIcon(FancyWindow.class.getResource(
				"close_hover.png")));
		closeButton.setPressedIcon(new ImageIcon(FancyWindow.class.getResource(
				"close_pressed.png")));
		closeButton.setFocusable(false);
		closeButton.setFocusPainted(false);
		closeButton.setBorderPainted(false);
		closeButton.setContentAreaFilled(false);
		*/
		titlePane = createTitlePane();
		resizeLabel = new JLabel();
		//resizeLabel = new JLabel(new ImageIcon(FancyWindow.class.getResource(
			//"resize_corner_dark.png")));
		bottomPane = createBottomPane();

		setLayout(new BorderLayout());
		add(titlePane, BorderLayout.NORTH);
		//add(contentPane, BorderLayout.CENTER);
		add(bottomPane, BorderLayout.SOUTH);

		MouseInputHandler handler = new MouseInputHandler();
		titlePane.addMouseListener(handler);
		titlePane.addMouseMotionListener(handler);
	}

	private JComponent createBottomPane(){
		JComponent result = new JComponent(){};
		result.setLayout(new FlowLayout(FlowLayout.RIGHT));
		result.add(resizeLabel);

		return result;
	}

	private JComponent createTitlePane(){
		JComponent result = new JComponent(){
			protected void paintComponent(Graphics g){
				setOpaque(false);
				Graphics2D g2 = (Graphics2D)g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Composite old = g2.getComposite();
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));

				LinearGradientPaint paint = new LinearGradientPaint(0, 0, 0, getHeight(),
						new float[] {.0f, .499f, .5f, 1.0f},
						new Color[] {new Color(0x858585),
							new Color(0x3c3c3c),
							new Color(0x2c2c2c),
							new Color(0x333334)});
				g2.setPaint(paint);
				Shape shape = new RoundRectangle2D.Float(0,0,getWidth(), getHeight(), 16, 16);
				g2.fill(shape);
				g2.setComposite(old);
				g2.dispose();
			}
		};

		GroupLayout layout = new GroupLayout(result);
		result.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(2)
						.addComponent(closeButton)
						.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(titleLabel)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(2)
				)
		);
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(titleLabel)
								.addComponent(closeButton)
						)
				)
		);

		return result;
	}

	private JComponent createContentPane(){
		return new JComponent(){
			@Override
			protected void paintComponent(Graphics g){
				Graphics2D g2 = (Graphics2D)g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Composite old = g2.getComposite();
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
				g2.setColor(Color.BLACK);
				Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16);
				g2.fill(shape);
				g2.setComposite(old);
				g2.dispose();
			}
		};
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

    public static void main(String[] args){
    	SwingUtilities.invokeLater(new Runnable(){
    		public void run(){
    			new CopyOfFancyWindow(new Frame()).setVisible(true);
    		}
    	});
    }
}