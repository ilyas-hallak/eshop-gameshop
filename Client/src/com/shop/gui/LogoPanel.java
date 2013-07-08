package com.shop.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Klasse fuer den Logo-Kopf, bleibt immer an gleicher Stelle mit gleicher Größe 
 *
 */
public class LogoPanel extends JPanel {
    private BufferedImage image;
	public LogoPanel() {
		super();
        try {
			this.image = ImageIO.read(this.getClass().getResource("../../../img/logo.png"));
			JLabel picLabel = new JLabel(new ImageIcon(this.image));
			add(picLabel);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// add(new JLabel("LOGO"));
		setPreferredSize(new Dimension(200, 135));
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(this.image, 0, 0, null);        
    }
}
