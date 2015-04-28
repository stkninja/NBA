package ui.player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.PositionEnum;
import ui.RegionEnum;
import ui.TeamEnum;
import vo.PlayerVO;
import businesslogic.MatchBL;
import businesslogic.PlayerBL;
import businesslogicservice.MatchBLService;
import businesslogicservice.PlayerBLService;

/**
 * ��Ա�������
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class PlayerSearchPane extends JInternalFrame implements ActionListener {
	private PlayerPane father;
	private PlayerBLService playerBL;
	private MatchBLService matchBL;
	private ArrayList<PlayerVO> list;
	private JPanel contentPane;//��panel
	private JComboBox<String> mode;
	private JComboBox<String> season;
	private JComboBox<String> region;
	private JComboBox<String> team;
	private JComboBox<String> position;
	private JTextField text;
	//�϶�
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	/**
	 * 
	 * @param father �ϲ�PlayerPane
	 */
	public PlayerSearchPane(PlayerPane father) {
		this.father = father;
		playerBL = new PlayerBL();
		matchBL = new MatchBL();
		this.setPlace();
		//����
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
	 * ��ʼ��
	 */
	private void init() {
		JPanel pane = new JPanel(new GridLayout(12, 1, 0, 1));
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
		
		JLabel label1 = new JLabel("�������ͣ�");
		label1.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label2 = new JLabel("������");
		label2.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label3 = new JLabel("��ӣ�");
		label3.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label4 = new JLabel("λ�ã�");
		label4.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label5 = new JLabel("������");
		label5.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label6 = new JLabel("��Ա���ƣ�");
		label6.setFont(new Font("����", Font.PLAIN, 14));
		mode = new JComboBox<String>(new String[]{"����", "����"});
		mode.setFont(new Font("����", Font.PLAIN, 14));
		String[] strList = TeamEnum.getRegion();
		for (int i = 0; i < strList.length; i++) {
			strList[i] = RegionEnum.valueToEnum(strList[i]).name_Ch();
		}
		region = new JComboBox<String>(strList);
		team = new JComboBox<String>();
		team.addItem("�������");
		team.setEnabled(false);
		String[] positionList = new String[10];
		int j = 0;
		for (PositionEnum i : PositionEnum.values()) {
			positionList[j] = i.name_Ch();
			j++;
		}
		position = new JComboBox<String>(positionList);
		season = new JComboBox<String>((String[])matchBL.getAllSeasons().toArray(new String[matchBL.getAllSeasons().size()]));
		text = new JTextField("���س���ȷ��");
		text.setForeground(Color.GRAY);
		
		pane.add(label1);
		pane.add(mode);
		pane.add(label2);
		pane.add(region);
		pane.add(label3);
		pane.add(team);
		pane.add(label4);
		pane.add(position);
		pane.add(label5);
		pane.add(season);
		pane.add(label6);
		pane.add(text);
		contentPane.add(pane, BorderLayout.CENTER);
		
		mode.addActionListener(this);
		region.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (region.getSelectedItem().equals("�������")) {
					team.removeAllItems();
					team.addItem("�������");
					team.setEnabled(false);
				} else {
					team.removeAllItems();
					String[] list = TeamEnum.getTeam(RegionEnum.valueToEnum((String)region.getSelectedItem()).name_En());
					for (int i = 0; i < list.length; i++) {
						list[i] = TeamEnum.valueToEnum(list[i]).name_Ch();
					}
					team.addItem("�������");
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
					PlayerSearchPane.this.setData(playerBL.getPlayers((String)season.getSelectedItem(), RegionEnum.valueToEnum((String)region.getSelectedItem()).name_En(), PositionEnum.valueToEnum((String)position.getSelectedItem()).abbr(), TeamEnum.valueToEnum((String)team.getSelectedItem()).abbr()));
			}
		});
		position.addActionListener(this);
		season.addActionListener(this);
		text.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				text.setText("���س���ȷ��");
				text.setForeground(Color.GRAY);
			}
			public void focusGained(FocusEvent e) {
				text.setText("");
			}
		});
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					ArrayList<PlayerVO> vo = playerBL.getPlayersInfo(list, (String)season.getSelectedItem(), text.getText());
					if (vo != null) {
						PlayerSearchPane.this.setData(vo);
					} else {
						JOptionPane.showMessageDialog(null, "���޴��ˣ�", "��ʾ", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	/**
	 * ����λ�ô�С
	 */
	public void setPlace() {
		this.setBounds(father.dp.getX(), father.dp.getY(), father.dp.getWidth() / 5, father.dp.getHeight() * 3 / 5);
	}
	/**
	 * ����
	 */
	public void actionPerformed(ActionEvent e) {
		this.setData(playerBL.getPlayers((String)season.getSelectedItem(), RegionEnum.valueToEnum((String)region.getSelectedItem()).name_En(), PositionEnum.valueToEnum((String)position.getSelectedItem()).abbr(), TeamEnum.valueToEnum((String)team.getSelectedItem()).abbr()));
	}
	/**
	 * �����������
	 */
	public void getAll() {
		this.setData(playerBL.getSeasonPlayers((String)season.getSelectedItem()));
	}
	/**
	 * ˢ��
	 */
	public void refresh() {
		this.setData(playerBL.getPlayers((String)season.getSelectedItem(), RegionEnum.valueToEnum((String)region.getSelectedItem()).name_En(), PositionEnum.valueToEnum((String)position.getSelectedItem()).abbr(), TeamEnum.valueToEnum((String)team.getSelectedItem()).abbr()));
	}
	/**
	 * ���ĳ����ӵ���Ա
	 * @param season ����
	 * @param name �����
	 */
	public void getPlayers(String season, String name) {
		this.setData(playerBL.getPlayers(season, "All", "All", name));
	}
	/**
	 * ���ñ������
	 * @param list ��ԱVO�б�
	 */
	public void setData(ArrayList<PlayerVO> list) {
		this.list = list;
		Object[][] data = new Object[list.size()][41];
		if (mode.getSelectedItem() == "����") {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = list.get(i).name;
				data[i][2] = TeamEnum.valueToEnum(list.get(i).team).name_Ch();
				data[i][3] = PositionEnum.valueToEnum(list.get(i).position).name_Ch();
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
				data[i][2] = TeamEnum.valueToEnum(list.get(i).team).name_Ch();
				data[i][3] = PositionEnum.valueToEnum(list.get(i).position).name_Ch();
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
	 * �����Ա����
	 * @return ��Ա����
	 */
	public ArrayList<PlayerVO> getList() {
		return list;
	}
	/**
	 * ���ý�����϶�
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
