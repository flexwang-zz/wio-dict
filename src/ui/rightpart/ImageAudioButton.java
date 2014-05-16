package ui.rightpart;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;

public class ImageAudioButton extends JLabel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private Image normalicon;
	private Image pressedicon;
	private Image disabledicon;
	private Image curIcon;
	private int width, height;
	private boolean valid = false;
	private AudioClip clip = null;

	public ImageAudioButton(int width, int height, 
			String normal, String pressed, String disabled) {
		addMouseListener(this);
		
		this.width = width;
		this.height = height;
		
		this.setPreferredSize(new Dimension(width, height));
		try {
			normalicon = ImageIO.read(new File(normal));
			pressedicon = ImageIO.read(new File(pressed));
			disabledicon = ImageIO.read(new File(disabled));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		curIcon = disabledicon;
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		if (curIcon != null) {
			g2d.drawImage(curIcon, 0, 0, width, height, 0, 0, curIcon.getWidth(null), curIcon.getHeight(null), null);
		}
		g2d.dispose();
	}
	
	public void setAudio(String filepath) {
		if (filepath == null || filepath.isEmpty()) {
			clip = null;
			curIcon = disabledicon;
			valid = false;
			validate();
			return;
		}
		try {
			System.out.println(filepath);
			//clip = AudioSystem.getClip();
			//AudioInputStream inputStream = AudioSystem
				//	.getAudioInputStream(new File(filepath).getAbsoluteFile());
			//clip.open(inputStream);
			clip = Applet.newAudioClip(
					new URL("file://"+filepath));
			curIcon = normalicon;
			valid = true;
		} catch (IOException e) {
			clip = null;
			curIcon = disabledicon;
			valid = false;
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (valid) {
			clip.play();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (valid) {
			System.out.println("entered");
			setCursor(new Cursor(Cursor.HAND_CURSOR));
			curIcon = pressedicon;
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (valid) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			curIcon = normalicon;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
