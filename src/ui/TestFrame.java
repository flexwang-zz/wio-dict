	package ui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
	
	
	public class TestFrame extends JFrame{
	
		private static final long serialVersionUID = 1L;
		private Window w = this;
		private JComponent titlePane;
	
		public TestFrame() {
			setUndecorated(true);
			setResizable(false);
			setPreferredSize(new Dimension(500, 500));
			setResizable(false);
		}
	
		public static void main(String[] argv) {
			new TestFrame().CreateUI();
		}
	
		public void CreateUI() {
			titlePane = new TitlePane();
			MouseInputListener m = new MouseInputHandler();
			titlePane.addMouseMotionListener(m);

			add(titlePane);
			pack();
			setVisible(true);
	
		}
	

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
