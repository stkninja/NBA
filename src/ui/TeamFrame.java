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
	JPanel panel;   //����panel
	JPanel panel1;  //ͼƬpanel
	JPanel panel2;  //��Ϣpanel
	JPanel panel3;  //��ťpanel
	JButton exit;  //�رհ�ť
	JLabel fullName;  //���
	JLabel abbName;  //�����д
	JLabel location;  //���ڵ�
	JLabel competionArea;  //����
	JLabel subArea;  //����
	JLabel homeGround;  //����
	JLabel setupTime;  //����ʱ��
	JLabel getfullName;  //���
	JLabel getabbName;  //�����д
	JLabel getlocation;  //���ڵ�
	JLabel getcompetionArea;  //����
	JLabel getsubArea;  //����
	JLabel gethomeGround;  //����
	JLabel getsetupTime;  //����ʱ��
	ImageIcon bg;  //����ͼ
    JLabel lab;  //����
    ImageIcon logo;  //�ӱ�
	
	public TeamFrame (TeamBasicInfoVO vo){
		//��������С
		Toolkit kit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 2 / 3;
		int frameWidth = frameHeight * 1;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		
		//����ͼƬ
		bg = new ImageIcon("Yellow.jpg");
		lab = new JLabel(bg);
		lab.setBounds(0, 0,bg.getIconWidth(), bg.getIconHeight());
		this.getLayeredPane().add(lab, new Integer(Integer.MIN_VALUE));
		
		//Ф����panel
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setOpaque(false);
		logo = new ImageIcon("BOS.png");
		JLabel Pic1 = new JLabel();
		Pic1.setIcon(logo);
		panel1.add(Pic1,BorderLayout.NORTH);
		
		//������Ϣpanel
		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		panel2.setLayout(new GridLayout(7,2,0,20));
		panel2.setOpaque(false);
		fullName = new JLabel("�����:",JLabel.RIGHT);
		abbName = new JLabel("������д:",JLabel.RIGHT);
		location = new JLabel("���ڵ�:",JLabel.RIGHT);
		competionArea = new JLabel("����:",JLabel.RIGHT);
		subArea = new JLabel("����:",JLabel.RIGHT);
		homeGround = new JLabel("������:",JLabel.RIGHT);
		setupTime = new JLabel("����ʱ��:",JLabel.RIGHT);
		Font f1 = new Font("����",Font.BOLD,16);
		fullName.setFont(f1);
		abbName.setFont(f1);
		location.setFont(f1);
		competionArea.setFont(f1);
		subArea.setFont(f1);
		homeGround.setFont(f1);
		setupTime.setFont(f1);
		Font f2 = new Font("����",Font.BOLD,14);
		getfullName = new JLabel(vo.fullName,JLabel.CENTER);
		getabbName = new JLabel(vo.abbName,JLabel.CENTER);
		getlocation = new JLabel(vo.location,JLabel.CENTER);
		getcompetionArea = new JLabel(vo.competionArea,JLabel.CENTER);
		getsubArea = new JLabel(vo.subArea,JLabel.CENTER);
		gethomeGround = new JLabel(vo.homeGround,JLabel.CENTER);
		getsetupTime = new JLabel(vo.setupTime,JLabel.CENTER);
//		getfullName = new JLabel("��������",JLabel.CENTER);
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
		this.setTitle("��������");
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
