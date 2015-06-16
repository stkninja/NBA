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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vo.TeamSeasonVO;
import businesslogic.TeamStatBL;
import businesslogicservice.TeamStatBLService;

@SuppressWarnings("serial")
public class StatPanel extends JPanel {
	private JPanel pane;
	private JPanel pane1;
	private JPanel pane2;
	private JPanel pane3;
	private CardLayout card;
	private TeamStatBLService team;
	private JTable table;
	private JScrollPane sp;
	JComboBox<String> mode;
	JPanel pane12;

	public StatPanel() {
		team = new TeamStatBL();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JPanel top = new JPanel(new BorderLayout());
		top.setOpaque(false);
		JLabel title = new JLabel("勇士自库里加盟后三分球重要性变化分析", JLabel.CENTER);
		title.setFont(new Font("黑体",Font.BOLD,20));
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
		JButton button1 = new JButton("上一步");
		JButton button2 = new JButton("下一步");
		bottom.add(button1);
		bottom.add(button2);
		this.add(bottom, BorderLayout.SOUTH);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.previous(pane);
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.next(pane);
			}
		});
	}

	private void initPane1() {
		pane1 = new JPanel(new BorderLayout());
		pane1.setOpaque(false);
		
		JPanel pane11 =new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane11.setOpaque(false);
		JLabel label = new JLabel("第一步：数据采集");
		label.setFont(new Font("楷体",Font.BOLD,15));
		label.setForeground(Color.lightGray);
		mode = new JComboBox<String>(new String[]{"09-10","10-11","11-12","12-13","13-14","14-15"});
		new XYChart(String.valueOf(mode.getSelectedItem()));
		new PieChart(String.valueOf(mode.getSelectedItem()));
		mode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				pane12.removeAll();
				new XYChart(String.valueOf(mode.getSelectedItem()));
				new PieChart(String.valueOf(mode.getSelectedItem()));
				pane12 = new JPanel(new FlowLayout());
				pane12.setOpaque(false);
				//table
				table = new JTable();
				sp = new JScrollPane();
				sp.setViewportView(table);
				sp.setOpaque(false);
				setData(String.valueOf(mode.getSelectedItem()));
				
				JLabel piclabel1 = new JLabel();
				JLabel piclabel3 = new JLabel();
				try {
					piclabel1.setIcon(new ImageIcon(ImageIO.read(new File("pic1.jpg"))));
					piclabel3.setIcon(new ImageIcon(ImageIO.read(new File("pic2.jpg"))));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				pane12.add(piclabel1);
				pane12.add(table);
				pane12.add(piclabel3);
				
				pane1.add(pane12,BorderLayout.CENTER);
				revalidate();
			}
		});
		pane11.add(label);
		pane11.add(mode);
		
		pane12 = new JPanel(new FlowLayout());
		pane12.setOpaque(false);
		//table
		table = new JTable();
		sp = new JScrollPane();
		sp.setViewportView(table);
		sp.setOpaque(false);
		this.setData(String.valueOf(mode.getSelectedItem()));
		
		JLabel piclabel1 = new JLabel();
		JLabel piclabel3 = new JLabel();
		try {
			piclabel1.setIcon(new ImageIcon(ImageIO.read(new File("pic1.jpg"))));
			piclabel3.setIcon(new ImageIcon(ImageIO.read(new File("pic2.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pane12.add(piclabel1);
		pane12.add(table);
		pane12.add(piclabel3);
		
		pane1.add(pane11,BorderLayout.NORTH);
		pane1.add(pane12,BorderLayout.CENTER);
	}
	
	private void initPane2() {
		pane2 = new JPanel(new BorderLayout());
		pane2.setOpaque(false);
		
		JPanel pane21 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane21.setOpaque(false);
		JLabel subtitle2 = new JLabel("第二步：变化趋势");
		subtitle2.setFont(new Font("楷体",Font.BOLD,15));
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
		JLabel subtitle3 = new JLabel("第三步：深度分析");
		subtitle3.setFont(new Font("楷体",Font.BOLD,15));
		subtitle3.setForeground(Color.MAGENTA);
		pane31.add(subtitle3);
		
		JLabel piclabel7 = new JLabel();
		JLabel piclabel8 = new JLabel();
		try {
			piclabel7.setIcon(new ImageIcon(ImageIO.read(new File("pic7.jpg"))));
			piclabel8.setIcon(new ImageIcon(ImageIO.read(new File("pic8.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JPanel pane32 = new JPanel(new GridLayout(1,4));
		pane32.setOpaque(false);
		
		JTextArea wordlabel3 = new JTextArea("通过一元线性回归，\n"
				+ "算出系数a = -2638.377,b = 1.323,\n"
				+ "决定系数=0.654，Sy = 2.091,\n"
				+ "因此认为回归方程拟合程度很高");
		JTextArea wordlabel4 = new JTextArea("原假设：方差1 >= 方差2\n"
				+ "计算样本方差0.0124，0.01\n"
				+ "计算F检验=1.24\n"
				+ "不拒绝原假设\n"
				+  "因此认为最近3个赛季比前3个赛季在场均三分球命中率方差上更具稳定性");
		wordlabel3.setBorder(new EmptyBorder(0,0,0,0));
		wordlabel4.setBorder(new EmptyBorder(0,0,0,0));
		wordlabel3.setLineWrap(true);
		wordlabel4.setLineWrap(true);
		pane32.add(piclabel7);
		pane32.add(wordlabel3);
		pane32.add(piclabel8);
		pane32.add(wordlabel4);
		
		JPanel pane33 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane33.setOpaque(false);
		JTextArea conclusion = new JTextArea("结论：通过以上分析，勇士队自从库里来了以后，三分球出手更多，所占投篮出手比例更大，\n虽然三分球投篮出手数逐年呈上升趋势，但命中率保持很好的稳定性。三分球成为了勇士的有力武器。");
		conclusion.setFont(new Font("楷体",Font.BOLD,15));
		conclusion.setBorder(new EmptyBorder(0,0,0,0));
		conclusion.setForeground(Color.BLACK);
		conclusion.setLineWrap(true);
		pane33.add(conclusion);
		
		pane3.add(pane31,BorderLayout.NORTH);
		pane3.add(pane32,BorderLayout.CENTER);
		pane3.add(pane33,BorderLayout.SOUTH);
	}
	private void setData(String season) {
		TeamSeasonVO vo1 = team.getAllTeamData(season);
		TeamSeasonVO vo2 = team.getBefore();
		Object[][]  data = new Object[5][3];
		data[0][0] = "场均三分";
		data[0][1] = "09年之前";
		data[0][2] = String.valueOf(mode.getSelectedItem());
		data[1][0] = "三分命中数";
		data[2][0] = "三分出手数";
		data[3][0] = "三分命中率";
		data[4][0] = "投篮出手数";
		data[1][1] = vo2.avgthreepointHit;
		data[2][1] = vo2.avgthreepoint;
		data[3][1] = vo2.avgthreepointrate;
		data[4][1] = vo2.avgshoot;
		data[1][2] = vo1.avgthreepointHit;
		data[2][2] = vo1.avgthreepoint;
		data[3][2] = vo1.avgthreepointrate;
		data[4][2] = vo1.avgshoot;
		this.showTable(data);
				
	}
	
	private void showTable(Object[][] data) {
		sp.remove(table);
		String[] subTitle = {"三分数据", "09年之前", String.valueOf(mode.getSelectedItem())};
		table = new JTable(data, subTitle);
		sp.setViewportView(table);
		revalidate();
	}
}
