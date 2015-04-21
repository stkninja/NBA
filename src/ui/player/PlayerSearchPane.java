package ui.player;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ui.Region;
import vo.PlayerBasicInfoVO;
import vo.PlayerVO;
import businesslogic.MatchBL;
import businesslogic.PlayerBL;
import businesslogicservice.MatchBLService;
import businesslogicservice.PlayerBLService;

/**
 * 球员搜索面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class PlayerSearchPane extends JInternalFrame implements ActionListener {
	private PlayerPane father;
	private PlayerBLService playerBL;
	private MatchBLService matchBL;
	private ArrayList<PlayerVO> list;
	private JPanel contentPane;//总panel
	private ImageIcon background;//背景图片
	private JPanel pane;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JComboBox<String> mode;
	private JComboBox<String> season;
	private JComboBox<String> region;
	private JComboBox<String> team;
	private JComboBox<String> position;
	private JTextField text;
	//拖动
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	//----------------------------------------------------
	public PlayerSearchPane(PlayerPane father) {
		this.father = father;
		playerBL = new PlayerBL();
		matchBL = new MatchBL();
		this.setPlace();
		//背景
		background = new ImageIcon("data/pic/background.jpg");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		//搜索界面
		pane = new JPanel();
		pane.setOpaque(false);
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBorder(BorderFactory.createEmptyBorder(6, 30, 6, 30));
		
		label1 = new JLabel("数据类型：");
		label1.setFont(new Font("黑体", Font.PLAIN, 14));
		mode = new JComboBox<String>(new String[]{"总数", "场均"});
		mode.setFont(new Font("楷体", Font.PLAIN, 14));
		label2 = new JLabel("地区：");
		label2.setFont(new Font("黑体", Font.PLAIN, 14));
		region = new JComboBox<String>(Region.getRegion());
		label3 = new JLabel("球队：");
		label3.setFont(new Font("黑体", Font.PLAIN, 14));
		team = new JComboBox<String>();
		team.addItem("All");
		team.setEnabled(false);
		label4 = new JLabel("位置：");
		label4.setFont(new Font("黑体", Font.PLAIN, 14));
		String[] positionList = {"All", "G", "F", "C"};
		position = new JComboBox<String>(positionList);
		label5 = new JLabel("赛季：");
		label5.setFont(new Font("黑体", Font.PLAIN, 14));
		season = new JComboBox<String>((String[])matchBL.getAllSeasons().toArray(new String[matchBL.getAllSeasons().size()]));
		label6 = new JLabel("球员名称：");
		label6.setFont(new Font("黑体", Font.PLAIN, 14));
		text = new JTextField();
		
		pane.add(label1);
		pane.add(Box.createVerticalStrut(3));
		pane.add(mode);
		pane.add(Box.createVerticalStrut(3));
		pane.add(label2);
		pane.add(Box.createVerticalStrut(3));
		pane.add(region);
		pane.add(Box.createVerticalStrut(3));
		pane.add(label3);
		pane.add(Box.createVerticalStrut(3));
		pane.add(team);
		pane.add(Box.createVerticalStrut(3));
		pane.add(label4);
		pane.add(Box.createVerticalStrut(3));
		pane.add(position);
		pane.add(Box.createVerticalStrut(3));
		pane.add(label5);
		pane.add(Box.createVerticalStrut(3));
		pane.add(season);
		pane.add(Box.createVerticalStrut(3));
		pane.add(label6);
		pane.add(Box.createVerticalStrut(3));
		pane.add(text);
		contentPane.add(pane, BorderLayout.CENTER);
		//监听
		mode.addActionListener(this);
		region.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (region.getSelectedItem().equals("All")) {
					team.removeAllItems();
					team.addItem("All");
					team.setEnabled(false);
				} else {
					team.removeAllItems();
					String[] list = Region.valueOf((String)region.getSelectedItem()).getTeam();
					team.addItem("All");
					for (int i = 0; i < list.length; i++) {
						team.addItem(list[i]);
					}
					team.setEnabled(true);
				}
			}
		});
		team.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					PlayerSearchPane.this.setData(playerBL.getPlayers((String)season.getSelectedItem(), (String)region.getSelectedItem(), (String)position.getSelectedItem(), (String)team.getSelectedItem()));
			}
		});
		position.addActionListener(this);
		season.addActionListener(this);
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
//					PlayerBasicInfoVO vo = playerBL.getPlayersInfo(text.getText());
//					if (vo != null) {
//								try {
//									JFrame.setDefaultLookAndFeelDecorated(true);
//									PlayerFrame frame = new PlayerFrame(vo);
//									com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.9f);//设置透明度
//									com.sun.awt.AWTUtilities.setWindowShape(frame, new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));//设置圆角
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
//					} else {
//						JOptionPane.showMessageDialog(null, "查无此人！", "提示", JOptionPane.ERROR_MESSAGE);
//					}
				}
			}
		});
		//-------------------------------------------------
		this.setDragable();
		this.setBorder(BorderFactory.createEmptyBorder());
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setVisible(true);
	}
	/**
	 * 设置位置大小
	 */
	public void setPlace() {
		this.setBounds(father.getX(), father.getY(), father.getWidth() / 5, father.getHeight() / 2);
	}
	/**
	 * 监听
	 */
	public void actionPerformed(ActionEvent e) {
		this.setData(playerBL.getPlayers((String)season.getSelectedItem(), (String)region.getSelectedItem(), (String)position.getSelectedItem(), (String)team.getSelectedItem()));
	}
	/**
	 * 获得所有数据
	 */
	public void getAll() {
		this.setData(playerBL.getSeasonPlayers((String)season.getSelectedItem()));
	}
	/**
	 * 设置表格数据
	 * @param list 队员VO列表
	 */
	public void setData(ArrayList<PlayerVO> list) {
		this.list = list;
		Object[][] data = new Object[list.size()][41];
		if (mode.getSelectedItem() == "总数") {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).name;
				data[i][2] = list.get(i).team;
				data[i][3] = list.get(i).position;
				data[i][4] = list.get(i).gameplay;
				data[i][5] = list.get(i).gamestart;
				data[i][6] = list.get(i).allminute;
				
				data[i][7] = list.get(i).allshootmade;
				data[i][8] = list.get(i).allshoot;
				data[i][9] = list.get(i).allfieldgoalpercent;
				data[i][10] = list.get(i).allrealshootpercent;
				
				data[i][11] = list.get(i).allthreepointmade;
				data[i][12] = list.get(i).allthreepoint;
				data[i][13] = list.get(i).allthreepointpercent;
				
				data[i][14] = list.get(i).allfreethrowmade;
				data[i][15] = list.get(i).allfreethrow;
				data[i][16] = list.get(i).allfreethrowpercent;
				
				data[i][17] = list.get(i).alloffensiverebound;
				data[i][18] = list.get(i).alldefensiverebound;
				data[i][19] = list.get(i).allrebound;
				
				data[i][20] = list.get(i).alloffense;
				data[i][21] = list.get(i).alldefence;
				data[i][22] = list.get(i).allassist;
				data[i][23] = list.get(i).allsteal;
				data[i][24] = list.get(i).allblock;
				data[i][25] = list.get(i).allerror;
				data[i][26] = list.get(i).allfoul;
				data[i][27] = list.get(i).allpoint;
				data[i][28] = list.get(i).doubledouble;
				data[i][29] = list.get(i).allpointReboundAssist;
				 
				data[i][30] = list.get(i).allshootefficiency;
				data[i][31] = list.get(i).alloffensivereboundrate;
				data[i][32] = list.get(i).alldefensivereboundrate;
				data[i][33] = list.get(i).allreboundrate;
				data[i][34] = list.get(i).allstealrate;
				data[i][35] = list.get(i).allassistrate;
				data[i][36] = list.get(i).allblockrate;
				data[i][37] = list.get(i).allerrorrate;
				data[i][38] = list.get(i).allusage;
				data[i][39] = list.get(i).allgmsc;;
				data[i][40] = list.get(i).allefficiency;
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).name;
				data[i][2] = list.get(i).team;
				data[i][3] = list.get(i).position;
				data[i][4] = list.get(i).gameplay;
				data[i][5] = list.get(i).gamestart;
				data[i][6] = list.get(i).minute;
				
				data[i][7] = list.get(i).shootmade;
				data[i][8] = list.get(i).shoot;
				data[i][9] = list.get(i).fieldgoalpercent;
				data[i][10] = list.get(i).realshootpercent;
				
				data[i][11] = list.get(i).threepointmade;
				data[i][12] = list.get(i).threepoint;
				data[i][13] = list.get(i).threepointpercent;
				
				data[i][14] = list.get(i).freethrowmade;
				data[i][15] = list.get(i).freethrow;
				data[i][16] = list.get(i).freethrowpercent;
				
				data[i][17] = list.get(i).offensiverebound;
				data[i][18] = list.get(i).defensiverebound;
				data[i][19] = list.get(i).rebound;
				
				data[i][20] = list.get(i).offense;
				data[i][21] = list.get(i).defence;
				data[i][22] = list.get(i).assist;
				data[i][23] = list.get(i).steal;
				data[i][24] = list.get(i).block;
				data[i][25] = list.get(i).error;
				data[i][26] = list.get(i).foul;
				data[i][27] = list.get(i).point;
				data[i][28] = list.get(i).doubledouble;
				data[i][29] = list.get(i).pointReboundAssist;
				 
				data[i][30] = list.get(i).shootefficiency;
				data[i][31] = list.get(i).offensivereboundrate;
				data[i][32] = list.get(i).defensivereboundrate;
				data[i][33] = list.get(i).reboundrate;
				data[i][34] = list.get(i).stealrate;
				data[i][35] = list.get(i).assistrate;
				data[i][36] = list.get(i).blockrate;
				data[i][37] = list.get(i).errorrate;
				data[i][38] = list.get(i).usage;
				data[i][39] = list.get(i).gmsc;;
				data[i][40] = list.get(i).efficiency;
			}
		}
		father.showTable(data);
	}
	/**
	 * 获得球员数据
	 * @return 球员数据
	 */
	public ArrayList<PlayerVO> getList() {
		return list;
	}
	/**
	 * 设置界面可拖动
	 */
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               PlayerSearchPane.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               PlayerSearchPane.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(PlayerSearchPane.this.getLocation().x + e.getX() - tmp.x,
                		   PlayerSearchPane.this.getLocation().y + e.getY() - tmp.y);
                   PlayerSearchPane.this.setLocation(loc);
               }
            }
        });
	}
}
