package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerPicture extends JFrame{
	ImageIcon i;
	JPanel panel;
	JLabel label;
	public PlayerPicture(ImageIcon ii){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 2 / 3;
		int frameWidth = frameHeight * 1 / 2;
		this.setBounds((screenSize.width - frameHeight * 1) / 2 - frameWidth , (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		
		i = ii;
		panel = new JPanel(){
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = i.getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), i.getImageObserver());
			}
		};
		panel.setOpaque(false);
//		label = new JLabel(i);
//		label.setBounds(0, 0,i.getIconWidth(), i.getIconHeight());
//		this.add(label);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	

}