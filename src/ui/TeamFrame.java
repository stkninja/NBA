package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.TeamBasicInfoVO;

public class TeamFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571378058437970468L;
	JPanel panel;   //背景panel
	JPanel panel1;  //图片panel
	JPanel panel2;  //信息panel
	JPanel panel3;  //按钮panel
	JButton exit;  //关闭按钮
	JLabel fullName;  //球队
	JLabel abbName;  //球队缩写
	JLabel location;  //所在地
	JLabel competionArea;  //赛区
	JLabel subArea;  //分区
	JLabel homeGround;  //主场
	JLabel setupTime;  //建立时间
	JLabel getfullName;  //球队
	JLabel getabbName;  //球队缩写
	JLabel getlocation;  //所在地
	JLabel getcompetionArea;  //赛区
	JLabel getsubArea;  //分区
	JLabel gethomeGround;  //主场
	JLabel getsetupTime;  //建立时间
	ImageIcon bg;  //背景图
    JLabel lab;  //背景
    ImageIcon logo;  //队标
	
	public TeamFrame (TeamBasicInfoVO vo){
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 2 / 3;
		int frameWidth = frameHeight * 1;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		
		//背景图片
		bg = new ImageIcon("Yellow.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		//肖像照panel
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setOpaque(false);
		logo = new ImageIcon("BOS.png");
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(logo);
		panel1.add(Pic1,BorderLayout.NORTH);
		
		//文字信息panel
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		panel2.setLayout(new GridLayout(7,2,0,20));
		panel2.setOpaque(false);
		fullName = new JLabel("球队名:",JLabel.RIGHT);
		abbName = new JLabel("队名缩写:",JLabel.RIGHT);
		location = new JLabel("所在地:",JLabel.RIGHT);
		competionArea = new JLabel("赛区:",JLabel.RIGHT);
		subArea = new JLabel("分区:",JLabel.RIGHT);
		homeGround = new JLabel("主场馆:",JLabel.RIGHT);
		setupTime = new JLabel("建队时间:",JLabel.RIGHT);
		Font f1 = new Font("宋体",Font.BOLD,16);
		fullName.setFont(f1);
		abbName.setFont(f1);
		location.setFont(f1);
		competionArea.setFont(f1);
		subArea.setFont(f1);
		homeGround.setFont(f1);
		setupTime.setFont(f1);
		Font f2 = new Font("宋体",Font.BOLD,14);
		getfullName = new JLabel(vo.fullName,JLabel.CENTER);
		getabbName = new JLabel(vo.abbName,JLabel.CENTER);
		getlocation = new JLabel(vo.location,JLabel.CENTER);
		getcompetionArea = new JLabel(vo.competionArea,JLabel.CENTER);
		getsubArea = new JLabel(vo.subArea,JLabel.CENTER);
		gethomeGround = new JLabel(vo.homeGround,JLabel.CENTER);
		getsetupTime = new JLabel(vo.setupTime,JLabel.CENTER);
//		getfullName = new JLabel("凯尔特人",JLabel.CENTER);
//		getabbName = new JLabel("BOS",JLabel.CENTER);
//		getlocation = new JLabel("Boston",JLabel.CENTER);
//		getcompetionArea = new JLabel("West",JLabel.CENTER);
//		getsubArea = new JLabel("Atlantic",JLabel.CENTER);
//		gethomeGround = new JLabel("TD Garden",JLabel.CENTER);
//		getsetupTime = new JLabel("1946",JLabel.CENTER);
		getfullName.setFont(f2);
		getabbName.setFont(f2);
		getlocation.setFont(f2);
		getcompetionArea.setFont(f2);
		getsubArea.setFont(f2);
		gethomeGround.setFont(f2);
		getsetupTime.setFont(f2);
		panel2.add(fullName);
		panel2.add(getfullName);
		panel2.add(abbName);
		panel2.add(getabbName);
		panel2.add(location);
		panel2.add(getlocation);
		panel2.add(competionArea);
		panel2.add(getcompetionArea);
		panel2.add(subArea);
		panel2.add(getsubArea);
		panel2.add(homeGround);
		panel2.add(gethomeGround);
		panel2.add(setupTime);
		panel2.add(getsetupTime);
		//---------------------------------------
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel3.setOpaque(false);
		exit = new JButton("X");
		exit.setPreferredSize(new Dimension(30, 30));
		panel3.add(exit);
		exit.addActionListener(new ExitListener());
		//---------------------------------
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panel2,BorderLayout.CENTER);
		panel.add(panel1,BorderLayout.WEST);
		panel.add(panel3,BorderLayout.NORTH);
		this.setContentPane(panel);
		panel.setOpaque(false);
		this.setTitle("凯尔特人");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}
//------------------------------------------------------------
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			 dispose();
		}
	}


}
