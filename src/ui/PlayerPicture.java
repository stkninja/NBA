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
	ImageIcon bg;
	JLabel lab;
	public PlayerPicture(ImageIcon ii,PlayerFrame dialog){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 2 / 3;
		int frameWidth = frameHeight * 1 / 2;
		this.setBounds(dialog.getX()-frameWidth , dialog.getY(), frameWidth, frameHeight);
		
		//±³¾°Í¼Æ¬
		bg = new ImageIcon("data/pic/Yellow.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		i = ii;
		panel = new JPanel(){
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = i.getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), i.getImageObserver());
			}
		};
		panel.setOpaque(false);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	

}
