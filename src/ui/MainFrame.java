package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import data.predo.PreRead;
import event.DataUpdEventSource;
import event.DataUpdListener;

/**
 * NBA��ѯƽ̨������
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JPanel contentPane;//��panel
	private JPanel pane;//����panel
	//�϶�
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	/**
	 * 
	 */
	public MainFrame() {
		//��������С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 8 / 9;
		int frameWidth = frameHeight * 5 / 3;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//����
		ImageIcon background = new ImageIcon("data/pic/background.png");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		//��ʼ��
		this.initTitle();
		//����panel
		pane = new JPanel();
		pane.setOpaque(false);
		contentPane.add(pane, BorderLayout.CENTER);
		this.setDragable();
		this.setUndecorated(true);
		this.setVisible(true);
	}
	/**
	 * ��ʼ������
	 */
	private void initTitle() {
		ImageIcon background = new ImageIcon("data/pic/logo.png");
		Image temp = background.getImage();
		double scale = (double)temp.getWidth(null) / (double)temp.getHeight(null);
		JPanel title = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(temp, 0, 0, getWidth(), (int)(getWidth() / scale), background.getImageObserver());
			}
		};
		title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));
		//��ʼ���˳���С����ť
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
		title.add(top);
		
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
		
		title.add(Box.createVerticalStrut(40));
		//�˵�
		this.initMenu(title);
		contentPane.add(title, BorderLayout.NORTH);
	}
	/**
	 * ��ʼ���˵�
	 * @param father ������
	 */
	private void initMenu(JPanel father) {
		JPanel menu = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		menu.setOpaque(false);
		menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		JButton button1 = new JButton("���");
		JButton button2 = new JButton("��Ա");
		JButton button3 = new JButton("����");
		JButton button4 = new JButton("�ȵ�");
		JButton button5 = new JButton("ͳ��");
		this.setMenu(button1);
		this.setMenu(button2);
		this.setMenu(button3);
		this.setMenu(button4);
		this.setMenu(button5);
		
		menu.add(button1);
		menu.add(button2);
		menu.add(button3);
		menu.add(button4);
		menu.add(button5);
		father.add(menu);
		
		JPopupMenu popMenuA = new JPopupMenu();
		JMenuItem menuItemA1 = new JMenuItem("��������");
		JMenuItem menuItemA2 = new JMenuItem("��������");
		this.setMenuItem(menuItemA1);
		this.setMenuItem(menuItemA2);
		popMenuA.add(menuItemA1);
		popMenuA.add(new JSeparator());
		popMenuA.add(menuItemA2);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popMenuA.show(button1, 0, button1.getHeight());
			}
		});
		menuItemA1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Team(MainFrame.this, "East");
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		menuItemA2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Team(MainFrame.this, "West");
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Player(MainFrame.this);
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new MatchPane(MainFrame.this);
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		JPopupMenu popMenuB = new JPopupMenu();
		JMenuItem menuItemB1 = new JMenuItem("�����ȵ���Ա");
		JMenuItem menuItemB2 = new JMenuItem("�����ȵ���Ա");
		JMenuItem menuItemB3 = new JMenuItem("�����ȵ����");
		JMenuItem menuItemB4 = new JMenuItem("���������Ա");
		this.setMenuItem(menuItemB1);
		this.setMenuItem(menuItemB2);
		this.setMenuItem(menuItemB3);
		this.setMenuItem(menuItemB4);
		popMenuB.add(menuItemB1);
		popMenuB.add(new JSeparator());
		popMenuB.add(menuItemB2);
		popMenuB.add(new JSeparator());
		popMenuB.add(menuItemB3);
		popMenuB.add(new JSeparator());
		popMenuB.add(menuItemB4);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popMenuB.show(button4, 0, button4.getHeight());
			}
		});
		menuItemB1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Hotspot(MainFrame.this, new String[]{"TodayTopPlayer", "�÷�", "����", "����", "��ñ", "����"});
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		menuItemB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Hotspot(MainFrame.this, new String[]{"SeasonTopPlayer", "�÷�", "����", "����", "��ñ", "����", "����������", "Ͷ��������", "����������"});
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		menuItemB3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Hotspot(MainFrame.this, new String[]{"SeasonTopTeam", "�÷�", "����", "����", "��ñ", "����", "����������", "Ͷ��������", "����������"});
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		menuItemB4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new Hotspot(MainFrame.this, new String[]{"PromotionPlayer", "�����÷�", "��������", "��������"});
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		

		JPopupMenu popMenuC = new JPopupMenu();
		JMenuItem menuItemC1 = new JMenuItem("�������");
		JMenuItem menuItemC2 = new JMenuItem("��Ա����");
		this.setMenuItem(menuItemC1);
		this.setMenuItem(menuItemC2);
		popMenuC.add(menuItemC1);
		popMenuC.add(new JSeparator());
		popMenuC.add(menuItemC2);
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popMenuC.show(button5, 0, button5.getHeight());
			}
		});
		menuItemC1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new TeamPane(MainFrame.this);
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
		menuItemC2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(pane);
				pane = new PlayerPane(MainFrame.this);
				contentPane.add(pane, BorderLayout.CENTER);
				revalidate();
			}
		});
	}
	/**
	 * ���ò˵�����ʽ
	 * @param menu �˵���
	 */
	private void setMenu(JButton menu) {
		menu.setFont(new Font("����", Font.BOLD, 16));
		menu.setForeground(Color.WHITE);
		menu.setContentAreaFilled(false);//���
		menu.setFocusPainted(false);//��ѡ��Ч��
		menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	/**
	 * ���ò˵�������ʽ
	 * @param menuItem �˵�����
	 */
	private void setMenuItem(JMenuItem menuItem) {
		menuItem.setFont(new Font("����", Font.PLAIN, 17));
		menuItem.setOpaque(true);
		menuItem.setBackground(new Color(206,231,255));
		menuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	/**
	 * ����ͼ��
	 * @param button ͼ��
	 * @param file1 Ĭ��ͼ��·��
	 * @param file2 ��תͼ��·��
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
		button.setFocusPainted(false);//��ѡ��Ч��
        button.setOpaque(false);//͸��
		button.setContentAreaFilled(false);//���
		button.setBorderPainted(false);//�ޱ߿�
		button.setMargin(new Insets(0, 0, 0, 0));//�ޱ߾�
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//ָ�����
	}
	/**
	 * ���ý�����϶�
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
	 * ��ʾĳ����Ա�ı���
	 * @param season ����
	 * @param name ��Ա����
	 */
	public void getPlayerMatch(String season, String name) {
		contentPane.remove(pane);
		pane = new MatchPane(MainFrame.this);
		((MatchPane) pane).getSearchPane().getPlayerMatch(season, name);
		contentPane.add(pane, BorderLayout.CENTER);
		revalidate();
	}
	/**
	 * ��ʾĳ����ӵı���
	 * @param season ����
	 * @param name �������
	 */
	public void getTeamMatch(String season, String name) {
		contentPane.remove(pane);
		pane = new MatchPane(MainFrame.this);
		((MatchPane) pane).getSearchPane().getTeamMatch(season, name);
		contentPane.add(pane, BorderLayout.CENTER);
		revalidate();
	}
	/**
	 * ��ʾĳ����ӵ���Ա
	 * @param season ����
	 * @param name �������
	 */
	public void getPlayers(String season, String name) {
		contentPane.remove(pane);
		pane = new PlayerPane(MainFrame.this);
		((PlayerPane) pane).getSearchPane().getPlayers(season, name);
		contentPane.add(pane, BorderLayout.CENTER);
		revalidate();
	}
	/**
	 * ˢ��
	 */
	public void refresh() {
		if (pane instanceof TeamPane)
			((TeamPane) pane).refresh();
		else if (pane instanceof PlayerPane)
			((PlayerPane) pane).refresh();
		else if (pane instanceof MatchPane)
			((MatchPane) pane).refresh();
		else if (pane instanceof Hotspot)
			((Hotspot) pane).refresh();
		else
			System.out.println("panel error");
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
		new PreRead();
		MainFrame mf = new MainFrame();
		mf.setOpacity(0.9f);
		mf.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, mf.getWidth(), mf.getHeight(), 26.0D, 26.0D));
		//�����߳�,ʵʱˢ��
		DataUpdEventSource dataUpdEventSource = new DataUpdEventSource(mf);
		dataUpdEventSource.addDataUpdListener(new DataUpdListener(){});
	}
}
