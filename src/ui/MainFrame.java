package ui;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import data.Pretreatment;

/**
 * 
 * @author stk
 * NBA��ѯƽ̨������
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	private JPanel contentPane;//��panel
	private JPanel pane;//����panel
	private ImageIcon background;//����ͼƬ
	//�˳��������С����ť
	private JPanel top;
	private JButton exit;
	private JButton mini;
	//����
	private JPanel navigation;
	private JButton team;
	private JButton player;
	//�϶�
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	//--------------------------------------------------------
	public MainFrame() {
		//��������С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int frameHeight = screenSize.height * 3 / 4;
		int frameWidth = frameHeight * 9 / 5;
		this.setBounds((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2, frameWidth, frameHeight);
		//����
		background = new ImageIcon("data/pic/TeamFrame_background.jpg");
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
		
		team = new JButton("���");
		team.setSize(new Dimension(80, 30));
		team.setPreferredSize(new Dimension(80, 30));
//		this.setIcon(team, "data/pic/exit1.png", "data/pic/exit2.png");
		player = new JButton("��Ա");
		player.setSize(new Dimension(80, 30));
		player.setPreferredSize(new Dimension(80, 30));
//		this.setIcon(player, "data/pic/exit1.png", "data/pic/exit2.png");
		
		navigation.add(team);
		navigation.add(Box.createVerticalStrut(20));
		navigation.add(player);
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
		pane = new TeamPane();
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
		team.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.remove(pane);
				pane = new TeamPane();
				MainFrame.this.getContentPane().add(pane);
				revalidate();
			}
		});
		player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.remove(pane);
				pane = new PlayerPane();
				MainFrame.this.getContentPane().add(pane);
				revalidate();
			}
		});
		//------------------------------------------------------------------
		this.setTitle("NBA��ѯƽ̨");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        ImageIcon icon1 = new ImageIcon(file1);
		Image temp1 = icon1.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT);
		ImageIcon icon2 = new ImageIcon(file2);
		Image temp2 = icon2.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp1));
		button.setRolloverIcon(new ImageIcon(temp2));
		button.setFocusPainted(false);//��ѡ��Ч��
        button.setOpaque(false);//͸��
		button.setContentAreaFilled(false);//���
		button.setBorderPainted(false);//�ޱ߿�
		button.setMargin(new Insets(0, 0, 0, 0));//�ޱ߾�
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
		Pretreatment.pretreatment();
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//ϵͳ���
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("restriction")
			public void run() {
				MainFrame frame = new MainFrame();
				com.sun.awt.AWTUtilities.setWindowOpacity(frame, 0.9f);//����͸����
				com.sun.awt.AWTUtilities.setWindowShape(frame, new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(), frame.getHeight(), 26.0D, 26.0D));//����Բ��
			}
	    });
	}
}
