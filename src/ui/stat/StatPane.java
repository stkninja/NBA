package ui.stat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import vo.TeamSeasonVO;
import businesslogic.TeamStatBL;
import businesslogicservice.TeamStatBLService;

@SuppressWarnings("serial")
public class StatPane extends JPanel{
	
	JLabel title;
	JLabel subtitle1;
	JLabel subtitle2;
	JLabel subtitle3;
	JLabel conclusion;
	
	JLabel piclabel1;
	JLabel piclabel2;
	JLabel piclabel3;
	JLabel piclabel4;
	JLabel piclabel5;
	JLabel piclabel6;
	JLabel piclabel7;
	JLabel piclabel8;
	JLabel wordlabel1;
	JLabel wordlabel2;
	JLabel wordlabel3;
	JLabel wordlabel4;
	
	JTable table;
	JScrollPane sp;
	
	JComboBox<String> mode;
	
	
	JPanel panel;
	JPanel panelA; //数据采集
	JPanel subpanelA1;
	JPanel subpanelA2;
	JPanel panelB; //变化规律
	JPanel subpanelB1;
	JPanel subpanelB2;
	JPanel panelC; //深度分析
	JPanel subpanelC1;
	JPanel subpanelC2;
	JPanel panelD; //结论
	
	private TeamStatBLService team;
	
	public StatPane(){
		team = new TeamStatBL();
		Font f = new Font("楷体",Font.BOLD,15);
		//标题panel
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setOpaque(false);
		title = new JLabel("勇士自库里加盟后三分球重要性变化分析");
		title.setFont(new Font("黑体",Font.BOLD,20));
		panel.add(title);
		
		//数据采集
		panelA = new JPanel();
		panelA.setLayout(new BorderLayout());
		panelA.setOpaque(false);
		
		subpanelA1 = new JPanel();
		subpanelA1.setLayout(new FlowLayout(FlowLayout.LEFT));
		subpanelA1.setOpaque(false);
		subtitle1 = new JLabel("第一步：数据采集      ");
		subtitle1.setFont(f);
		subtitle1.setForeground(Color.lightGray);
		subpanelA1.add(subtitle1);
		mode = new JComboBox<String>(new String[]{"09-10","10-11","11-12","12-13","13-14","14-15"});
//		new XYChart(String.valueOf(mode.getSelectedItem()));
		mode.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						new XYChart(String.valueOf(mode.getSelectedItem()));
					}
				});
		subpanelA1.add(mode);
		subpanelA2 = new JPanel();
		subpanelA2.setLayout(new GridLayout(1,3));
		piclabel1 = new JLabel();
		piclabel3 = new JLabel();
		
		table = new JTable();
		sp = new JScrollPane();
		sp.setViewportView(table);
		sp.setOpaque(false);
		
		try {
			piclabel1.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/8.jpg"))));
			piclabel3.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/8.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		subpanelA2.add(piclabel1);
		subpanelA2.add(piclabel3);
		
		panelA.add(subpanelA1,BorderLayout.NORTH);
		panelA.add(subpanelA2,BorderLayout.CENTER);
		
		//变化趋势
		panelB = new JPanel();
		panelB.setLayout(new BorderLayout());
		panelB.setOpaque(false);
		
		subpanelB1 = new JPanel();
		subpanelB1.setLayout(new FlowLayout(FlowLayout.LEFT));
		subpanelB1.setOpaque(false);
		subtitle2 = new JLabel("第二步：变化趋势");
		subtitle2.setFont(f);
		subtitle2.setForeground(Color.GRAY);
		subpanelB1.add(subtitle2);
		
		subpanelB2 = new JPanel();
		subpanelB2.setLayout(new GridLayout(1,3));
		subpanelB2.setOpaque(false);
		
		piclabel4 = new JLabel();
		piclabel5 = new JLabel();
		piclabel6 = new JLabel();
		try {
			piclabel4.setIcon(new ImageIcon(ImageIO.read(new File("pic4.jpg"))));
			piclabel5.setIcon(new ImageIcon(ImageIO.read(new File("pic5.jpg"))));
			piclabel6.setIcon(new ImageIcon(ImageIO.read(new File("pic6.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		subpanelB2.add(piclabel4);
		subpanelB2.add(piclabel5);
		subpanelB2.add(piclabel6);
		
		panelB.add(subpanelB1,BorderLayout.NORTH);
		panelB.add(subpanelB2,BorderLayout.CENTER);
		
		//深度分析
		panelC = new JPanel();
		panelC.setLayout(new BorderLayout());
		panelC.setOpaque(false);
		
		subpanelC1 = new JPanel();
		subpanelC1.setLayout(new FlowLayout(FlowLayout.LEFT));
		subpanelC1.setOpaque(false);
		subtitle3 = new JLabel("第三步：深度分析");
		subtitle3.setFont(f);
		subtitle3.setForeground(Color.MAGENTA);
		subpanelC1.add(subtitle3);
		
		piclabel7 = new JLabel();
		piclabel8 = new JLabel();
		try {
			piclabel7.setIcon(new ImageIcon(ImageIO.read(new File("pic7.jpg"))));
			piclabel8.setIcon(new ImageIcon(ImageIO.read(new File("data/pic/8.jpg"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		subpanelC2 = new JPanel();
		subpanelC2.setLayout(new GridLayout(1,4));
		subpanelC2.setOpaque(false);
		
		wordlabel3 = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxx"
				+ "hhhhhhhhhhhhhh");
		wordlabel4 = new JLabel("xxxxxxxxxxxxxxxxxxxxxxxx"
				+ "hhhhhhhhhhhhhh");
		
		subpanelC2.add(piclabel7);
		subpanelC2.add(wordlabel3);
		subpanelC2.add(piclabel8);
		subpanelC2.add(wordlabel4);
		
		panelC.add(subpanelC1,BorderLayout.NORTH);
		panelC.add(subpanelC2,BorderLayout.CENTER);
		
		panelD = new JPanel();
		panelD.setLayout(new FlowLayout(FlowLayout.LEFT));
		conclusion = new JLabel("结论：巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉");
		conclusion.setFont(f);
		conclusion.setForeground(Color.BLACK);
		panelD.add(conclusion);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(panel);
		this.add(panelA);
		this.add(panelB);
		this.add(panelC);
		this.add(panelD);
		
	}
	
	private void setData(String season) {
		ArrayList<TeamSeasonVO> list = new ArrayList<TeamSeasonVO>();
		Object[][] data = new Object[2][2];
	}
	
	private void showTable(Object[][] data) {
		sp.remove(table);
		String[] subTitle = {"三分数据", "09年", String.valueOf(data[0][0])};
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 8 / 9;
		int frameWidth = frameHeight * 5 / 3;
		frame.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		frame.setLayout(new BorderLayout());
		frame.add(new StatPane());
		frame.setVisible(true);
	}

}
