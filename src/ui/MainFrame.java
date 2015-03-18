package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.rwArrangedFiles.WritePOs;

/**
 * 
 * @date 2015年3月18日
 * @author stk
 *
 */

/*
 * NBA查询平台主界面
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	private JPanel contentPane;//总panel
	private JPanel pane;//内容panel
	private ImageIcon background;//背景图片
	//退出、最大最小化按钮
	private JPanel top;
	private JButton exit;
	private JButton max;
	private JButton mini;
	//导航
	private JPanel navigation;
	private JButton team;
	private JButton player;
	//--------------------------------------------------------
	public MainFrame() {
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 3 / 4;
		int frameWidth = frameHeight * 9 / 5;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//背景
		background = new ImageIcon("data/pic/TeamFrame_background.jpg");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = background.getImage();  
                g.drawImage(img, 0, 0, background.getIconWidth(), background.getIconHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		//导航
		navigation = new JPanel();
		navigation.setOpaque(false);
		navigation.setLayout(new BoxLayout(navigation, BoxLayout.Y_AXIS));
		navigation.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 10));
		team = new JButton("球队");
		player = new JButton("球员");
		navigation.add(team);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(player);
		contentPane.add(navigation, BorderLayout.WEST);
		//按钮
		top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		top.setOpaque(false);
		exit = new JButton("X");
		exit.setPreferredSize(new Dimension(25, 25));
//		this.setIcon("data/pic/TeamFrame_icon.jpg", exit);
		max = new JButton("[]");
		max.setPreferredSize(new Dimension(25, 25));
		mini = new JButton("-");
		mini.setPreferredSize(new Dimension(25, 25));
		top.add(mini);
		top.add(max);
		top.add(exit);
		contentPane.add(top, BorderLayout.NORTH);
		//内容panel
		pane = new TeamPane();
		contentPane.add(pane, BorderLayout.CENTER);
		//监听
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		max.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MainFrame.this.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
					MainFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					MainFrame.this.setExtendedState(JFrame.NORMAL);
				}
			}
		});
		mini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		team.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.remove(pane);
				pane = new TeamPane();
				MainFrame.this.getContentPane().add(pane);
				revalidate();
			}
		});
		player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.remove(pane);
				pane = new PlayerPane();
				MainFrame.this.getContentPane().add(pane);
				revalidate();
			}
		});
		//------------------------------------------------------------------
		this.setTitle("NBA查询平台");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}
//	public void paint(Graphics g) {
//		super.paint(g);
//        RoundRectangle2D rect = new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),70,70);
//        g.setClip(rect);
//        if(background!=null)
//            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
//        revalidate();
//	}
	
//	public void setIcon(String file, JButton iconButton) {  
//        ImageIcon icon = new ImageIcon(file);  
//        Image temp = icon.getImage().getScaledInstance(iconButton.getWidth(),  
//                iconButton.getHeight(), icon.getImage().SCALE_DEFAULT);  
//        icon = new ImageIcon(temp);  
//        iconButton.setIcon(icon);  
//  }
	//------------------------------------------------------------
	public static void main(String[] args) {
		WritePOs.writePOs();
		new MainFrame();
	}
}
