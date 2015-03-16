package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import vo.PlayerVO;

public class PlayerFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;   //背景paenl
	JPanel panel1;  //图片panel
	JPanel panel2;  //信息panel
	JPanel panel3;  //按钮panel
	JButton exit;  //关闭按钮
	JLabel name;  
	JLabel getName;
	JLabel age;
	JLabel getAge;
	JLabel position;
	JLabel getPosition;
	JLabel height;
	JLabel getHeight;
	JLabel weight;
	JLabel getWeight;
    JLabel number;
    JLabel getNumber;
    JLabel exp;
    JLabel getExp;
    JLabel school;
    JLabel getSchool;
    JLabel team;
    JLabel getTeam;
    ImageIcon bg;  //背景图
    JLabel lab;  //背景
    ImageIcon portrait;
	ImageIcon action;
	
    
	public PlayerFrame (PlayerVO vo){
		
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 2 / 3;
		int frameWidth = frameHeight * 6 / 5;
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
		portrait = new ImageIcon("Brandon Jennings.png");
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(portrait);
		panel1.add(Pic1,BorderLayout.NORTH);
		
		//文字信息panel
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		panel2.setLayout(new GridLayout(9,2,0,20));
		panel2.setOpaque(false);
		name = new JLabel("姓名:", JLabel.RIGHT);
		age = new JLabel("年龄:", JLabel.RIGHT);
		position = new JLabel("位置:", JLabel.RIGHT);
		height = new JLabel("身高:", JLabel.RIGHT);
		weight = new JLabel("体重:", JLabel.RIGHT);
		number = new JLabel("号码:", JLabel.RIGHT);
		exp = new JLabel("经验:", JLabel.RIGHT);
		school = new JLabel("毕业学校:", JLabel.RIGHT);
		team = new JLabel("效力球队:", JLabel.RIGHT);
		getName = new JLabel("库里", JLabel.CENTER);
		getAge = new JLabel("27", JLabel.CENTER);
		getPosition = new JLabel("控卫", JLabel.CENTER);
		getHeight = new JLabel("1.92", JLabel.CENTER);
		getWeight = new JLabel("90kg", JLabel.CENTER);
		getNumber = new JLabel("6", JLabel.CENTER);
		getExp = new JLabel("5", JLabel.CENTER);
		getSchool = new JLabel("杜克大学", JLabel.CENTER);
		getTeam = new JLabel("金州勇士", JLabel.CENTER);
		panel2.add(name);
		panel2.add(getName);
		panel2.add(team);
		panel2.add(getTeam);
		panel2.add(number);
		panel2.add(getNumber);
		panel2.add(position);
		panel2.add(getPosition);
		panel2.add(height);
		panel2.add(getHeight);
		panel2.add(weight);
		panel2.add(getWeight);
		panel2.add(age);
		panel2.add(getAge);
		panel2.add(school);
		panel2.add(getSchool);
		
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panel2,BorderLayout.CENTER);
		panel.add(panel1,BorderLayout.WEST);
		action = new ImageIcon("Brandon Jennings(1).png");
		action.setImage(action.getImage().getScaledInstance(200,500,Image.SCALE_DEFAULT));
		JLabel Pic2 = new JLabel();
		Pic2.setIcon(action);
		panel.add(Pic2,BorderLayout.EAST);
		this.setContentPane(panel);
		panel.setOpaque(false);
		this.setTitle(vo.name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
//------------------------------------------------------------
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		PlayerVO vo = new PlayerVO();
		new PlayerFrame(vo);
	}

}
