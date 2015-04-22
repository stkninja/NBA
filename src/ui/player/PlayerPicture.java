package ui.player;

import java.awt.BorderLayout;
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
	JPanel contentPane;
	public PlayerPicture(ImageIcon ii,PlayerFrame dialog){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 2 / 3;
		int frameWidth = frameHeight * 1 / 2;
		this.setBounds(dialog.getX()-frameWidth , dialog.getY(), frameWidth, frameHeight);
		
		//±³¾°Í¼Æ¬
		bg = new ImageIcon("data/pic/playerbg.jpg");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), bg.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		
		i = ii;
		panel = new JPanel(){
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = i.getImage();
				g.drawImage(img, 0, 0, getWidth(), getHeight(), i.getImageObserver());
			}
		};
		panel.setOpaque(false);
		contentPane.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	

}
