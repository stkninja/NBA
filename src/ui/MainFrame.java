package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @date 2015年3月10日
 * @time 下午6:46:00
 * @author stk
 *
 */

/*
 * NBA查询平台主界面
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	//--------------------------------------------------------
	public MainFrame() {
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 3 / 4;
		int frameWidth = frameHeight * 7 / 4;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		
		this.setTitle("NBA查询平台");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//------------------------------------------------------------
	public static void main(String[] args) {
		new MainFrame();
	}
}
