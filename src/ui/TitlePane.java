package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TitlePane extends JComponent {
	private static final long serialVersionUID = -7845009697666749318L;

	public TitlePane() {
	}

	protected void paintComponent(Graphics g) {
		setOpaque(false);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Composite old = g2.getComposite();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.75f));

		LinearGradientPaint paint = new LinearGradientPaint(0, 0, 0,
				getHeight(), new float[] { .0f, .499f, .5f, 1.0f },
				new Color[] { new Color(0, 255, 0, 0), new Color(255, 0, 0, 0),
						new Color(0, 255, 0, 0), new Color(0, 0, 255, 0), });
		g2.setPaint(paint);
		Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(),
				16, 16);
		g2.fill(shape);
		g2.setComposite(old);
		g2.dispose();
	}

}