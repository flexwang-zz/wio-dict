package ui.rightpart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image = null;
	private int width, height;

	public ImagePanel(int width, int height) {
		setBackground(Color.WHITE);
		this.width = width;
		this.height = height;
	}

	public void setImage(String imageURL) {
		try {
			image = ImageIO.read(new File(imageURL));
		} catch (Exception ex) {
			image = null;
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, width, height, null);
		}
	}
}
