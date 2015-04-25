package ui.player;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.PlayerVO;
import businesslogic.PlayerBL;
import businesslogicservice.PlayerBLService;

/**
 * ��Ա�������
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class PlayerSortPane extends JInternalFrame implements ActionListener {
	private PlayerPane father;
	private PlayerBLService playerBL;
	private JPanel contentPane;//��panel
	private JComboBox<String> box1;
	private JComboBox<String> box2;
	private JComboBox<String> box3;
	//�϶�
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	/**
	 * 
	 * @param father �ϲ�PlayerPane
	 */
	public PlayerSortPane(PlayerPane father) {
		this.father = father;
		playerBL = new PlayerBL();
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
		JPanel pane = new JPanel(new GridLayout(6, 1, 0, 6));
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
		JLabel label1 = new JLabel("�������ͣ�");
		label1.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label2 = new JLabel("��һ�������ݣ�");
		label2.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label3 = new JLabel("�ڶ��������ݣ�");
		label3.setFont(new Font("����", Font.PLAIN, 14));
		box1 = new JComboBox<String>(new String[]{"����", "����"});
		box1.setFont(new Font("����", Font.PLAIN, 14));
		box2 = new JComboBox<String>((String[])playerBL.getFilters().toArray(new String[playerBL.getFilters().size()]));
		box2.setFont(new Font("����", Font.PLAIN, 14));
		box3 = new JComboBox<String>((String[])playerBL.getFilters().toArray(new String[playerBL.getFilters().size()]));
		box3.setFont(new Font("����", Font.PLAIN, 14));
		
		pane.add(label1);
		pane.add(box1);
		pane.add(label2);
		pane.add(box2);
		pane.add(label3);
		pane.add(box3);
		contentPane.add(pane, BorderLayout.CENTER);
		
		box1.addActionListener(this);
		box2.addActionListener(this);
		box3.addActionListener(this);
	}
	/**
	 * ����
	 */
	public void actionPerformed(ActionEvent e) {
		ArrayList<PlayerVO> list = this.father.getSearchPane().getList();
		ArrayList<String> filter = new ArrayList<String>();
		filter.add((String)box2.getSelectedItem());
		filter.add((String)box3.getSelectedItem());
		ArrayList<PlayerVO> data = playerBL.sortPlayer(list, filter, (String)box1.getSelectedItem());
		this.father.getSearchPane().setData(data);
	}
	/**
	 * ����λ�ô�С
	 */
	public void setPlace() {
		this.setBounds(father.dp.getX(), father.dp.getY() + father.dp.getHeight() * 3 / 5, father.dp.getWidth() / 5, father.dp.getHeight() * 2 / 5);
	}
	/**
	 * ���ý�����϶�
	 */
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               PlayerSortPane.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               PlayerSortPane.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(PlayerSortPane.this.getLocation().x + e.getX() - tmp.x,
                		   PlayerSortPane.this.getLocation().y + e.getY() - tmp.y);
                   PlayerSortPane.this.setLocation(loc);
               }
            }
        });
	}
}
