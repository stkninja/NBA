package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.PlayerBasicInfoVO;

public class PlayerFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;   //����panel
	JPanel panel1;  //ͼƬpanel
	JPanel panel2;  //��Ϣpanel
	JPanel panel3;  //��ťpanel
	JButton exit;  //�رհ�ť
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
    ImageIcon bg;  //����ͼ
    JLabel lab;  //����
    Image portrait;
    ImageIcon portraiticon;
	Image action;
	ImageIcon actionicon;
	
    
	public PlayerFrame (PlayerBasicInfoVO vo) throws IOException{
		
		//��������С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 2 / 3;
		int frameWidth = frameHeight * 8 / 9;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//����ͼƬ
		bg = new ImageIcon("Yellow.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		//Ф����panel-----------------------------------------------------------
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setOpaque(false);
		portrait=ImageIO.read(vo.portrait);
		portraiticon=new ImageIcon(portrait);
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(portraiticon);
		panel1.add(Pic1,BorderLayout.NORTH);
		action=ImageIO.read(vo.action);
		actionicon = new ImageIcon(action);
		actionicon.setImage(actionicon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
		JLabel Pic2 = new JLabel();
		
		Pic2.setIcon(actionicon);
		panel1.add(Pic2,BorderLayout.CENTER);
		
		//������Ϣpanel----------------------------------------
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		panel2.setLayout(new GridLayout(9,2,0,20));
		panel2.setOpaque(false);
		Font f1 = new Font("����",Font.BOLD,14);
		name = new JLabel("����:", JLabel.RIGHT);
		age = new JLabel("����:", JLabel.RIGHT);
		position = new JLabel("λ��:", JLabel.RIGHT);
		height = new JLabel("���:", JLabel.RIGHT);
		weight = new JLabel("����:", JLabel.RIGHT);
		number = new JLabel("����:", JLabel.RIGHT);
		exp = new JLabel("����:", JLabel.RIGHT);
		school = new JLabel("��ҵѧУ:", JLabel.RIGHT);
		team = new JLabel("Ч�����:", JLabel.RIGHT);
		name.setFont(f1);
		age.setFont(f1);
		position.setFont(f1);
		height.setFont(f1);
		weight.setFont(f1);
		number.setFont(f1);
		exp.setFont(f1);
		school.setFont(f1);
		team.setFont(f1);
		Font f2 = new Font("����",Font.BOLD,12);

		getName = new JLabel(vo.name, JLabel.CENTER);
		getAge = new JLabel(vo.age, JLabel.CENTER);
		getPosition = new JLabel(vo.position, JLabel.CENTER);
		getHeight = new JLabel(vo.height, JLabel.CENTER);
		getWeight = new JLabel(vo.weight, JLabel.CENTER);
		getNumber = new JLabel(vo.number, JLabel.CENTER);
		getExp = new JLabel(vo.exp, JLabel.CENTER);
		getSchool = new JLabel(vo.school, JLabel.CENTER);
		getTeam = new JLabel(vo.school, JLabel.CENTER);
		getName.setFont(f2);
		getAge.setFont(f2);
		getPosition.setFont(f2);
		getHeight.setFont(f2);
		getWeight.setFont(f2);
		getNumber.setFont(f2);
		getExp.setFont(f2);
		getSchool.setFont(f2);
		getTeam.setFont(f2);
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
		//----------------------------------------
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel3.setOpaque(false);
		exit = new JButton("X");
		exit.setPreferredSize(new Dimension(30, 30));
		panel3.add(exit);
		exit.addActionListener(new ExitListener());
		//-----------------------------------------
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panel1,BorderLayout.WEST);
		panel.add(panel2,BorderLayout.CENTER);
		panel.add(panel3,BorderLayout.NORTH);
		
//		action = new ImageIcon("Brandon Jennings(1).png");
//		action.setImage(action.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
//		JLabel Pic2 = new JLabel();
//		Pic2.setIcon(action);
//		panel1.add(Pic2,BorderLayout.CENTER);
		this.setContentPane(panel);
		panel.setOpaque(false);
		this.setTitle(vo.name);
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

