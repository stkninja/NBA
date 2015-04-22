package ui.team;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.TeamVO;
import businesslogic.TeamBL;
import businesslogicservice.TeamBLService;

/**
 * ����������
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class TeamSortPane extends JInternalFrame implements ActionListener {
	private TeamPane father;
	private TeamBLService teamBL;
	private JPanel contentPane;//��panel
	private ImageIcon background;//����ͼƬ
	private JPanel pane;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JComboBox<String> box1;
	private JComboBox<String> box2;
	private JComboBox<String> box3;
	private JComboBox<String> box4;
	//�϶�
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	//-----------------------------------------------
	public TeamSortPane(TeamPane father) {
		this.father = father;
		teamBL = new TeamBL();
		this.setPlace();
		//����
		background = new ImageIcon("data/pic/PanelBG.jpg");
		contentPane = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
			}
		};
		this.setContentPane(contentPane);
		//��������
		pane = new JPanel();
		pane.setOpaque(false);
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		label1 = new JLabel("�������ͣ�");
		label1.setFont(new Font("����", Font.PLAIN, 14));
		label2 = new JLabel("��һ�������ݣ�");
		label2.setFont(new Font("����", Font.PLAIN, 14));
		label3 = new JLabel("�ڶ��������ݣ�");
		label3.setFont(new Font("����", Font.PLAIN, 14));
		label4 = new JLabel("�����������ݣ�");
		label4.setFont(new Font("����", Font.PLAIN, 14));
		box1 = new JComboBox<String>(new String[]{"����", "����"});
		box1.setFont(new Font("����", Font.PLAIN, 14));
		box2 = new JComboBox<String>((String[])teamBL.getFilters().toArray(new String[teamBL.getFilters().size()]));
		box2.setFont(new Font("����", Font.PLAIN, 14));
		box3 = new JComboBox<String>((String[])teamBL.getFilters().toArray(new String[teamBL.getFilters().size()]));
		box3.setFont(new Font("����", Font.PLAIN, 14));
		box4 = new JComboBox<String>((String[])teamBL.getFilters().toArray(new String[teamBL.getFilters().size()]));
		box4.setFont(new Font("����", Font.PLAIN, 14));
		
		pane.add(label1);
		pane.add(Box.createVerticalStrut(5));
		pane.add(box1);
		pane.add(Box.createVerticalStrut(5));
		pane.add(label2);
		pane.add(Box.createVerticalStrut(5));
		pane.add(box2);
		pane.add(Box.createVerticalStrut(5));
		pane.add(label3);
		pane.add(Box.createVerticalStrut(5));
		pane.add(box3);
		pane.add(Box.createVerticalStrut(5));
		pane.add(label4);
		pane.add(Box.createVerticalStrut(5));
		pane.add(box4);
		contentPane.add(pane, BorderLayout.CENTER);
		//����
		box1.addActionListener(this);
		box2.addActionListener(this);
		box3.addActionListener(this);
		box4.addActionListener(this);
		//-----------------------------------------------------------------
		this.setDragable();
		this.setBorder(BorderFactory.createEmptyBorder());
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		this.setVisible(true);
	}
	/**
	 * ����
	 */
	public void actionPerformed(ActionEvent e) {
		ArrayList<TeamVO> list = this.father.getSearchPane().getList();
		ArrayList<String> filter = new ArrayList<String>();
		filter.add((String)box2.getSelectedItem());
		filter.add((String)box3.getSelectedItem());
		filter.add((String)box4.getSelectedItem());
		ArrayList<TeamVO> data = teamBL.sortTeam(list, filter, (String)box1.getSelectedItem());
		this.father.getSearchPane().setData(data);
	}
	/**
	 * ����λ�ô�С
	 */
	public void setPlace() {
		this.setBounds(father.getX(), father.getY() + father.getHeight() / 2, father.getWidth() / 5, father.getHeight() / 2);
	}
	/**
	 * ���ý�����϶�
	 */
	private void setDragable() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
               isDragged = false;
               TeamSortPane.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
               tmp = new Point(e.getX(), e.getY());
               isDragged = true;
               TeamSortPane.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
               if(isDragged) {
                   loc = new Point(TeamSortPane.this.getLocation().x + e.getX() - tmp.x,
                		   TeamSortPane.this.getLocation().y + e.getY() - tmp.y);
                   TeamSortPane.this.setLocation(loc);
               }
            }
        });
	}
}
