package ui.stat;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatPanel extends JPanel {
	private JPanel pane;
	private JPanel pane1;
	private JPanel pane2;
	private JPanel pane3;
	private CardLayout card;

	public StatPanel() {
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		JPanel top = new JPanel(new BorderLayout());
		top.setOpaque(false);
		JLabel title = new JLabel("��ʿ�Կ�����˺���������Ҫ�Ա仯����");
		title.setFont(new Font("����",Font.BOLD,20));
		top.add(title);
		this.add(top, BorderLayout.NORTH);
		
		card = new CardLayout();
		pane = new JPanel(card);
		pane.setOpaque(false);
		this.initPane1();
		this.initPane2();
		this.initPane3();
		pane.add(pane1, "1");
		pane.add(pane2, "2");
		pane.add(pane3, "3");
		this.add(pane, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottom.setOpaque(false);
		JButton button1 = new JButton("��һ��");
		JButton button2 = new JButton("��һ��");
		bottom.add(button1);
		bottom.add(button2);
		this.add(bottom, BorderLayout.SOUTH);
	}

	private void initPane1() {
		pane1 = new JPanel(new BorderLayout());
		pane1.setOpaque(false);
		
		JPanel pane11 =new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane11.setOpaque(false);
		JLabel label = new JLabel("��һ�������ݲɼ�");
		label.setFont(new Font("����",Font.BOLD,15));
		label.setForeground(Color.lightGray);
		JComboBox<String> mode = new JComboBox<String>(new String[]{"09-10","10-11","11-12","12-13","13-14","14-15"});
		new XYChart(String.valueOf(mode.getSelectedItem()));
		new PieChart(String.valueOf(mode.getSelectedItem()));
		mode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new XYChart(String.valueOf(mode.getSelectedItem()));
				new PieChart(String.valueOf(mode.getSelectedItem()));
			}
		});
		pane11.add(label);
		pane11.add(mode);
		
		JPanel pane12 = new JPanel();
		pane12.setOpaque(false);
		//table
		JLabel piclabel1 = new JLabel();
		JLabel piclabel3 = new JLabel();
		try {
			piclabel1.setIcon(new ImageIcon(ImageIO.read(new File("pic1.jpg"))));
			piclabel3.setIcon(new ImageIcon(ImageIO.read(new File("pic2.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pane12.add(piclabel1);
		pane12.add(piclabel3);
		
		pane1.add(pane11,BorderLayout.NORTH);
		pane1.add(pane12,BorderLayout.CENTER);
	}
	
	private void initPane2() {
		pane2 = new JPanel(new BorderLayout());
		pane2.setOpaque(false);
		
		JPanel pane21 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane21.setOpaque(false);
		JLabel subtitle2 = new JLabel("�ڶ������仯����");
		subtitle2.setFont(new Font("����",Font.BOLD,15));
		subtitle2.setForeground(Color.GRAY);
		pane21.add(subtitle2);
		
		JPanel pane22 = new JPanel(new GridLayout(1,3));
		pane22.setOpaque(false);
		
		JLabel piclabel4 = new JLabel();
		JLabel piclabel5 = new JLabel();
		JLabel piclabel6 = new JLabel();
		new MixedChart();
		new LineChart();
		try {
			piclabel4.setIcon(new ImageIcon(ImageIO.read(new File("pic4.jpg"))));
			piclabel5.setIcon(new ImageIcon(ImageIO.read(new File("pic5.jpg"))));
			piclabel6.setIcon(new ImageIcon(ImageIO.read(new File("pic6.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pane22.add(piclabel4);
		pane22.add(piclabel5);
		pane22.add(piclabel6);
		
		pane2.add(pane21,BorderLayout.NORTH);
		pane2.add(pane22,BorderLayout.CENTER);
	}
	
	private void initPane3() {
		pane3 = new JPanel(new BorderLayout());
		pane3.setOpaque(false);
		
		JPanel pane31 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane31.setOpaque(false);
		JLabel subtitle3 = new JLabel("����������ȷ���");
		subtitle3.setFont(new Font("����",Font.BOLD,15));
		subtitle3.setForeground(Color.MAGENTA);
		pane31.add(subtitle3);
		
		JLabel piclabel7 = new JLabel();
		JLabel piclabel8 = new JLabel();
		try {
			piclabel7.setIcon(new ImageIcon(ImageIO.read(new File("pic7.jpg"))));
			piclabel8.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/8.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JPanel pane32 = new JPanel(new GridLayout(1,4));
		pane32.setOpaque(false);
		
		JLabel wordlabel3 = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxx"
				+ "hhhhhhhhhhhhhh");
		JLabel wordlabel4 = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxx"
				+ "hhhhhhhhhhhhhh");
		
		pane32.add(piclabel7);
		pane32.add(wordlabel3);
		pane32.add(piclabel8);
		pane32.add(wordlabel4);
		
		JPanel pane33 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel conclusion = new JLabel("���ۣ�����������������������������������������������������������������������������������������");
		conclusion.setFont(new Font("����",Font.BOLD,15));
		conclusion.setForeground(Color.BLACK);
		pane33.add(conclusion);
		
		pane3.add(pane31,BorderLayout.NORTH);
		pane3.add(pane32,BorderLayout.CENTER);
		pane3.add(pane33,BorderLayout.SOUTH);
	}
}
