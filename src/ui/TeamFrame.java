package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
	
	public TeamFrame (){
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
		logo = new ImageIcon("Brandon Jennings.png");
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
		getfullName = new JLabel("��������",JLabel.CENTER);
		getabbName = new JLabel("BOS",JLabel.CENTER);
		getlocation = new JLabel("Boston",JLabel.CENTER);
		getcompetionArea = new JLabel("West",JLabel.CENTER);
		getsubArea = new JLabel("Atlantic",JLabel.CENTER);
		gethomeGround = new JLabel("TD Garden",JLabel.CENTER);
		getsetupTime = new JLabel("1946",JLabel.CENTER);
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
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(panel2,BorderLayout.CENTER);
		panel.add(panel1,BorderLayout.WEST);
		this.setContentPane(panel);
		panel.setOpaque(false);
		this.setTitle("��������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
//------------------------------------------------------------
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new TeamFrame();
	}

}
