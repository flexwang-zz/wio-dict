package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.MouseInputListener;

public class TitlePane extends JComponent {
	private static final long serialVersionUID = -7845009697666749318L;
	private Window w;
	public TitlePane(Window window) {
		w = window;
		
		MouseInputHandler handler = new MouseInputHandler();
		
		System.out.println(handler==null);
		addMouseListener(handler);
		addMouseMotionListener(handler);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		setOpaque(false);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Composite old = g2.getComposite();
		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				//0.75f));
		LinearGradientPaint paint = new LinearGradientPaint(0, 0, 0,
				getHeight(), new float[] { .0f, 1.0f },
				new Color[] { new Color(21, 160, 245, 255), new Color(21, 160, 245, 255) });
		g2.setPaint(paint);
		Shape shape = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
		g2.fill(shape);
		g2.setComposite(old);
		Shape rect = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
		g2.clip(rect);
		g2.dispose();
		System.out.println("Draw");
		
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
                           w, dragWindowOffset, TitlePane.this);

            Frame f = null;
            Dialog d = null;

            if (w instanceof JFrame) {
                f = (JFrame)w;
            } else if (w instanceof Dialog) {
                d = (Dialog)w;
            }

            int frameState = (f != null) ? f.getExtendedState() : 0;

            if (TitlePane.this.contains(convertedDragWindowOffset)) {
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