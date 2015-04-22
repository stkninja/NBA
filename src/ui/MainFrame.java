package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.hotspot.HotspotPane;
import ui.match.MatchPane;
import ui.player.PlayerPane;
import ui.team.TeamPane;
import data.Pretreatment;
import event.DataUpdEvent;
import event.DataUpdEventSource;
import event.DataUpdListener;

/**
 * NBA��ѯƽ̨������
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public JPanel contentPane;//��panel
	public JPanel pane;//����panel
	public CardLayout cardLayout;//��Ƭʽ����
	private ImageIcon background;//����ͼƬ
	public HotspotPane hotspotPane;
	public PlayerPane playerPane;
	public TeamPane teamPane;
	public MatchPane matchPane;
	//�˳�����С����ť
	private JPanel top;
	private JButton exit;
	private JButton mini;
	//����
	private JPanel navigation;
	private JButton hotspot;
	private JButton team;
	private JButton player;
	private JButton match;
	//�϶�
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	//--------------------------------------------------------
	public MainFrame() {
		hotspotPane = new HotspotPane(this);
		playerPane = new PlayerPane(this);
		teamPane = new TeamPane(this);
		matchPane = new MatchPane(this);
		//��������С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 3 / 4;
		int frameWidth = frameHeight * 16 / 9;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//����
		background = new ImageIcon("data/pic/background.png");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		//����
		navigation = new JPanel();
		navigation.setOpaque(false);
		navigation.setLayout(new BoxLayout(navigation, BoxLayout.Y_AXIS));
		navigation.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 10));
		
		hotspot = new JButton();
		hotspot.setSize(new Dimension(80, 30));
		hotspot.setPreferredSize(new Dimension(80, 30));
		this.setIcon(hotspot, "data/pic/hotspot1.png", "data/pic/hotspot2.png");
		team = new JButton();
		team.setSize(new Dimension(80, 30));
		team.setPreferredSize(new Dimension(80, 30));
		this.setIcon(team, "data/pic/team1.png", "data/pic/team2.png");
		player = new JButton();
		player.setSize(new Dimension(80, 30));
		player.setPreferredSize(new Dimension(80, 30));
		this.setIcon(player, "data/pic/player1.png", "data/pic/player2.png");
		match = new JButton();
		match.setSize(new Dimension(80, 30));
		match.setPreferredSize(new Dimension(80, 30));
		this.setIcon(match, "data/pic/match1.png", "data/pic/match2.png");
		
		navigation.add(hotspot);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(team);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(player);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(match);
		contentPane.add(navigation, BorderLayout.WEST);
		//��ť
		top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		top.setOpaque(false);
		
		exit = new JButton();
        exit.setSize(new Dimension(25, 25));
        exit.setPreferredSize(new Dimension(25, 25));
		this.setIcon(exit, "data/pic/exit1.png", "data/pic/exit2.png");
		mini = new JButton();
		mini.setSize(new Dimension(25, 25));
		mini.setPreferredSize(new Dimension(25, 25));
		this.setIcon(mini, "data/pic/mini1.png", "data/pic/mini2.png");
		
		top.add(mini);
		top.add(exit);
		contentPane.add(top, BorderLayout.NORTH);
		//����panel
		pane = new JPanel();
		cardLayout = new CardLayout();
		pane.setLayout(cardLayout);
		pane.setOpaque(false);
		
		pane.add(hotspotPane, "Hotspot");
		pane.add(teamPane, "Team");
		pane.add(playerPane, "Player");
		pane.add(matchPane, "Match");
		contentPane.add(pane, BorderLayout.CENTER);
		//����
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
		hotspot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pane, "Hotspot");
			}
		});
		team.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pane, "Team");
			}
		});
		player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pane, "Player");
			}
		});
		match.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pane, "Match");
			}
		});
		//------------------------------------------------------------------
		this.setDragable();
		this.setUndecorated(true);
		this.setVisible(true);
	}
	/**
	 * ����ͼ��
	 * @param button ͼ��
	 * @param file1 Ĭ��ͼ��·��
	 * @param file2 ��תͼ��·��
	 */
	public void setIcon(JButton button, String file1, String file2) {  
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
	//------------------------------------------------------------
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		Pretreatment.pretreatment();//Ԥ����
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//ϵͳ���
		MainFrame frame = new MainFrame();
		frame.setOpacity(0.9f);
		frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
		//�����߳�
		DataUpdEventSource dataUpdEventSource = new DataUpdEventSource();
		dataUpdEventSource.addDataUpdListener(new DataUpdListener(){
			public void dataUpdated(DataUpdEvent e){
				ProgressBar pb = new ProgressBar();
				//�������ݿ�����
				Pretreatment.redoMBasic();
				//ˢ��
				pb.dispose();
				JOptionPane.showMessageDialog(null, "�������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				MainFrame frame = new MainFrame();
				frame.setOpacity(0.9f);
				frame.setShape(new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));
			} 
		});
	}
}
