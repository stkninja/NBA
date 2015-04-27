package ui.match;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vo.MatchVO;
import businesslogic.MatchBL;
import businesslogicservice.MatchBLService;

/**
 * 比赛搜索面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class MatchSearchPane extends JInternalFrame {
	private MatchPane father;
	private MatchBLService bl;
	private JPanel contentPane;//总panel
	private JComboBox<String> comboBox1;
	private JComboBox<String> comboBox2;
	private JComboBox<String> comboBox3;
	private JTextField text1;
	private JTextField text2;
	private JButton search;
	private JButton reset;
	//拖动
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	/**
	 * 
	 * @param father 上层MatchPane
	 */
	public MatchSearchPane(MatchPane father) {
		this.father = father;
		bl = new MatchBL();
		this.setPlace();
		//背景
		ImageIcon background = new ImageIcon("data/pic/PanelBG.png");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		
		this.init();
		this.setDragable();
		this.setBorder(BorderFactory.createEmptyBorder());
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setVisible(true);
	}
	/**
	 * 初始化
	 */
	private void init() {
		JPanel pane = new JPanel(new GridLayout(9, 1, 0, 2));
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
		JLabel label1 = new JLabel("赛季：");
		label1.setFont(new Font("黑体", Font.PLAIN, 14));
		JLabel label2 = new JLabel("日期：");
		label2.setFont(new Font("黑体", Font.PLAIN, 14));
		JLabel label3 = new JLabel("球员：");
		label3.setFont(new Font("黑体", Font.PLAIN, 14));
		JLabel label4 = new JLabel("球队缩写：");
		label4.setFont(new Font("黑体", Font.PLAIN, 14));
		comboBox1 = new JComboBox<String>((String[])bl.getAllSeasons().toArray(new String[bl.getAllSeasons().size()]));
		text1 = new JTextField();
		text2 = new JTextField();
		
		JPanel date = new JPanel(new FlowLayout());
		date.setOpaque(false);
		JLabel label5 = new JLabel("月");
		label5.setFont(new Font("黑体", Font.PLAIN, 14));
		JLabel label6 = new JLabel("日");
		label6.setFont(new Font("黑体", Font.PLAIN, 14));
		comboBox2 = new JComboBox<String>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		comboBox3 = new JComboBox<String>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
													   "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
													   "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		date.add(comboBox2);
		date.add(label5);
		date.add(comboBox3);
		date.add(label6);
		date.setPreferredSize(new Dimension(100, 25));
		
		JPanel bottom = new JPanel(new FlowLayout());
		bottom.setOpaque(false);
		search = new JButton("搜索");
		search.setFont(new Font("楷体", Font.PLAIN, 14));
		reset = new JButton("重置");
		reset.setFont(new Font("楷体", Font.PLAIN, 14));
		bottom.add(search);
		bottom.add(reset);
		bottom.setPreferredSize(new Dimension(100, 30));
		
		pane.add(label1);
		pane.add(comboBox1);
		pane.add(label2);
		pane.add(date);
		pane.add(label3);
		pane.add(text1);
		pane.add(label4);
		pane.add(text2);
		pane.add(bottom);
		contentPane.add(pane, BorderLayout.NORTH);
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatchSearchPane.this.setData(bl.getMatchesAboutTeamSeasonDatePlayer(text2.getText(), (String)comboBox1.getSelectedItem(), (String)comboBox2.getSelectedItem() +"-"+ (String)comboBox3.getSelectedItem(), text1.getText()));
			}
		});
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatchSearchPane.this.setData(bl.getMatches((String)comboBox1.getSelectedItem()));
			}
		});
	}
	/**
	 * 设置位置大小
	 */
	public void setPlace() {
		this.setBounds(father.dp.getX(), father.dp.getY(), father.dp.getWidth() / 5, father.dp.getHeight() * 4 / 7);
	}
	/**
	 * 获得所有数据
	 */
	public void getAll() {
		this.setData(bl.getMatches((String)comboBox1.getSelectedItem()));
	}
	/**
	 * 获得某个球员一个赛季比赛
	 * @param season 赛季
	 * @param player 球员
	 */
	public void getPlayerMatch(String season, String player) {
		this.setData(bl.getMatchesAboutTeamSeasonDatePlayer("All", season, "All", player));
	}
	/**
	 * 获得某个球队一个赛季比赛
	 * @param season 赛季
	 * @param team 球队
	 */
	public void getTeamMatch(String season, String team) {
		this.setData(bl.getMatchesAboutTeamSeasonDatePlayer(team, season, "All", "All"));
	}
	/**
	 * 设置数据
	 */
	public void setData(ArrayList<MatchVO> list) {
		Object[][] data = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).season;
			data[i][1] = list.get(i).date;
			data[i][2] = list.get(i).team1.abbName;
			data[i][3] = Math.round(list.get(i).team1.scores) +":"+ Math.round(list.get(i).team2.scores);
			data[i][4] = list.get(i).team2.abbName;
		}
		father.showTable(data);
	}
	/**
	 * 设置界面可拖动
	 */
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               MatchSearchPane.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               MatchSearchPane.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(MatchSearchPane.this.getLocation().x + e.getX() - tmp.x,
                		   MatchSearchPane.this.getLocation().y + e.getY() - tmp.y);
                   MatchSearchPane.this.setLocation(loc);
               }
            }
        });
	}
}
