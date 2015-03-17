package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.rwArrangedFiles.WritePOs;

/**
 * 
 * @date 2015年3月11日
 * @author stk
 *
 */

/*
 * NBA查询平台主界面
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	private JPanel navigation;
	private JPanel pane;
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
		//--------------------------------------------------------------------
		navigation = new JPanel();
		navigation.setLayout(new BoxLayout(navigation, BoxLayout.Y_AXIS));
		navigation.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 10));
		team = new JButton("球队");
		player = new JButton("球员");
		navigation.add(team);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(player);
		
		this.getContentPane().add(navigation, BorderLayout.WEST);
		//----------------------------------------------------------------
		pane = new TeamPane();
		this.getContentPane().add(pane, BorderLayout.CENTER);
		//监听
		team.addActionListener(new TeamListener());
		player.addActionListener(new PlayerListener());
		//------------------------------------------------------------------
		this.setTitle("NBA查询平台");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//--------------------------------------------------------------
	private class TeamListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MainFrame.this.remove(pane);
			pane = new TeamPane();
			MainFrame.this.getContentPane().add(pane);
			revalidate();
		}
	}
	
	private class PlayerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MainFrame.this.remove(pane);
			pane = new PlayerPane();
			MainFrame.this.getContentPane().add(pane);
			revalidate();
		}
	}
	//------------------------------------------------------------
	public static void main(String[] args) {
		WritePOs.writePOs();
		new MainFrame();
	}
}
