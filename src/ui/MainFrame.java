package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.hotspot.Hotspot;
import ui.match.MatchPane;
import ui.player.Player;
import ui.player.PlayerPane;
import ui.team.Team;
import ui.team.TeamPane;
import event.DataUpdEventSource;
import event.DataUpdListener;

/**
 * NBA查询平台主界面
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JPanel contentPane;//总panel
	private JPanel pane;//内容panel
	//拖动
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	/**
	 * 
	 */
	public MainFrame() {
		//定义界面大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 7 / 9;
		int frameWidth = frameHeight * 5 / 3;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//背景
		ImageIcon background = new ImageIcon("data/pic/background.png");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		//初始化
		this.initTop();
		this.initNavigation();
		//内容panel
		pane = new Team(this, "East");
		pane.setOpaque(false);
		contentPane.add(pane, BorderLayout.CENTER);
		this.setDragable();
		this.setUndecorated(true);
		this.setVisible(true);
	}
	/**
	 * 初始化退出最小化按钮
	 */
	private void initTop() {
		JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		top.setOpaque(false);
		
		JButton exit = new JButton();
        exit.setSize(new Dimension(25, 25));
        exit.setPreferredSize(new Dimension(25, 25));
		this.setIcon(exit, "data/pic/exit1.png", "data/pic/exit2.png");
		JButton mini = new JButton();
		mini.setSize(new Dimension(25, 25));
		mini.setPreferredSize(new Dimension(25, 25));
		this.setIcon(mini, "data/pic/mini1.png", "data/pic/mini2.png");
		
		top.add(mini);
		top.add(exit);
		contentPane.add(top, BorderLayout.NORTH);
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
	}
	/**
	 * 初始化导航
	 */
	private void initNavigation() {
		JPanel navigation = new JPanel();
		navigation.setOpaque(false);
		navigation.setLayout(new BoxLayout(navigation, BoxLayout.Y_AXIS));
		navigation.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 10));
		//球队
		JButton team = new JButton();
		team.setSize(new Dimension(80, 30));
		team.setPreferredSize(new Dimension(80, 30));
		this.setIcon(team, "data/pic/team1.png", "data/pic/team2.png");
		
		JPopupMenu teamPopup = new JPopupMenu();
		teamPopup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JMenuItem t1 = new JMenuItem("东部联盟");
		t1.setOpaque(true);
		t1.setBackground(Color.BLUE);
		JMenuItem t2 = new JMenuItem("西部联盟");
		t2.setOpaque(true);
		t2.setBackground(Color.BLUE);
		JMenuItem t3 = new JMenuItem("球队数据");
		t3.setOpaque(true);
		t3.setBackground(Color.BLUE);
		
		teamPopup.add(t1);
		teamPopup.add(new JSeparator());
		teamPopup.add(t2);
		teamPopup.add(new JSeparator());
		teamPopup.add(t3);
		
		team.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				teamPopup.show(team, team.getX() + 46, team.getY() - 18);
			}
//			public void mouseExited(MouseEvent e) {
//				Timer timer = new Timer();
//				timer.schedule(new TimerTask() {
//					public void run() {
//						teamPopup.setVisible(false);
//					}
//				}, 1500);
//			}
		});
//		team.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				contentPane.remove(pane);
//				pane = new Team(MainFrame.this, "East");
//				contentPane.add(pane, BorderLayout.CENTER);
//				revalidate();
//			}
//		});
		t1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Team(MainFrame.this, "East");
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		t2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Team(MainFrame.this, "West");
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		t3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new TeamPane(MainFrame.this);
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		//球员
		JButton player = new JButton();
		player.setSize(new Dimension(80, 30));
		player.setPreferredSize(new Dimension(80, 30));
		this.setIcon(player, "data/pic/player1.png", "data/pic/player2.png");
		
		JPopupMenu playerPopup = new JPopupMenu();
		playerPopup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JMenuItem p1 = new JMenuItem("球员信息");
		p1.setOpaque(true);
		p1.setBackground(Color.BLUE);
		JMenuItem p2 = new JMenuItem("球员数据");
		p2.setOpaque(true);
		p2.setBackground(Color.BLUE);
		
		playerPopup.add(p1);
		playerPopup.add(new JSeparator());
		playerPopup.add(p2);
		
		player.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				playerPopup.show(player, player.getX() + 46, player.getY() - 72);
			}
//			public void mouseExited(MouseEvent e) {
//				Timer timer = new Timer();
//				timer.schedule(new TimerTask() {
//					public void run() {
//						playerPopup.setVisible(false);
//					}
//				}, 1500);
//			}
		});
//		player.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				contentPane.remove(pane);
//				pane = new PlayerPane(MainFrame.this);
//				contentPane.add(pane, BorderLayout.CENTER);
//				revalidate();
//			}
//		});
		p1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Player(MainFrame.this);
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		p2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new PlayerPane(MainFrame.this);
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		//比赛
		JButton match = new JButton();
		match.setSize(new Dimension(80, 30));
		match.setPreferredSize(new Dimension(80, 30));
		this.setIcon(match, "data/pic/match1.png", "data/pic/match2.png");
		
		match.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new MatchPane(MainFrame.this);
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		//热点
		JButton hotspot = new JButton();
		hotspot.setSize(new Dimension(80, 30));
		hotspot.setPreferredSize(new Dimension(80, 30));
		this.setIcon(hotspot, "data/pic/hotspot1.png", "data/pic/hotspot2.png");
		
		JPopupMenu hotspotPopup = new JPopupMenu();
		hotspotPopup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JMenuItem h1 = new JMenuItem("当天热点球员");
		h1.setOpaque(true);
		h1.setBackground(Color.BLUE);
		JMenuItem h2 = new JMenuItem("赛季热点球员");
		h2.setOpaque(true);
		h2.setBackground(Color.BLUE);
		JMenuItem h3 = new JMenuItem("赛季热点球队");
		h3.setOpaque(true);
		h3.setBackground(Color.BLUE);
		JMenuItem h4 = new JMenuItem("进步最快球员");
		h4.setOpaque(true);
		h4.setBackground(Color.BLUE);
		
		hotspotPopup.add(h1);
		hotspotPopup.add(new JSeparator());
		hotspotPopup.add(h2);
		hotspotPopup.add(new JSeparator());
		hotspotPopup.add(h3);
		hotspotPopup.add(new JSeparator());
		hotspotPopup.add(h4);
		
		hotspot.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				hotspotPopup.show(hotspot, hotspot.getX() + 46, hotspot.getY() - 180);
			}
//			public void mouseExited(MouseEvent e) {
//				Timer timer = new Timer();
//				timer.schedule(new TimerTask() {
//					public void run() {
//						hotspotPopup.setVisible(false);
//					}
//				}, 1500);
//			}
		});
		h1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Hotspot(MainFrame.this, new String[]{"TodayTopPlayer", "得分", "篮板", "助攻", "盖帽", "抢断"});
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		h2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Hotspot(MainFrame.this, new String[]{"SeasonTopPlayer", "得分", "篮板", "助攻", "盖帽", "抢断", "三分命中率", "投篮命中率", "罚球命中率"});
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		h3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Hotspot(MainFrame.this, new String[]{"SeasonTopTeam", "得分", "篮板", "助攻", "盖帽", "抢断", "三分命中率", "投篮命中率", "罚球命中率"});
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		h4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Hotspot(MainFrame.this, new String[]{"PromotionPlayer", "场均得分", "场均篮板", "场均助攻"});
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		navigation.add(team);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(player);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(match);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(hotspot);
		contentPane.add(navigation, BorderLayout.WEST);
	}
	/**
	 * 设置图标
	 * @param button 图标
	 * @param file1 默认图标路径
	 * @param file2 翻转图标路径
	 */
	private void setIcon(JButton button, String file1, String file2) {  
        Image icon1 = (new ImageIcon(file1)).getImage();
        double scale1 = (double)icon1.getWidth(null) / (double)icon1.getHeight(null);
		Image temp1 = icon1.getScaledInstance((int)(button.getHeight() * scale1), button.getHeight(), Image.SCALE_DEFAULT);
		Image icon2 = (new ImageIcon(file2)).getImage();
		double scale2 = (double)icon2.getWidth(null) / (double)icon2.getHeight(null);
		Image temp2 = icon2.getScaledInstance((int)(button.getHeight() * scale2), button.getHeight(), Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp1));
		button.setRolloverIcon(new ImageIcon(temp2));
		button.setPressedIcon(new ImageIcon(temp2));
		button.setFocusPainted(false);//无选择效果
        button.setOpaque(false);//透明
		button.setContentAreaFilled(false);//填充
		button.setBorderPainted(false);//无边框
		button.setMargin(new Insets(0, 0, 0, 0));//无边距
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//指针变手
	}
	/**
	 * 设置界面可拖动
	 */
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               MainFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               MainFrame.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(MainFrame.this.getLocation().x + e.getX() - tmp.x,
                		   MainFrame.this.getLocation().y + e.getY() - tmp.y);
                   MainFrame.this.setLocation(loc);
               }
            }
        });
	}
	/**
	 * 刷新
	 */
	public void refresh() {
		pane.removeAll();
//		hotspotPane = new HotspotPane(this);
//		playerPane = new PlayerPane(this);
//		teamPane = new TeamPane(this);
//		teamPane = new Team();
//		matchPane = new MatchPane(this);
//		pane.add(hotspotPane, "Hotspot");
//		pane.add(teamPane, "Team");
//		pane.add(playerPane, "Player");
//		pane.add(matchPane, "Match");
		revalidate();
	}
	//--------------------------------------------------------
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		MainFrame mf = new MainFrame();
		mf.setOpacity(0.9f);
		mf.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, mf.getWidth(), mf.getHeight(), 26.0D, 26.0D));
		//启动线程,实时刷新
		DataUpdEventSource dataUpdEventSource = new DataUpdEventSource(mf);
		dataUpdEventSource.addDataUpdListener(new DataUpdListener(){});
	}
}
