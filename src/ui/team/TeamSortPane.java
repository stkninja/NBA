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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.TeamVO;
import businesslogic.TeamBL;
import businesslogicservice.TeamBLService;

/**
 * 球队排序面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class TeamSortPane extends JFrame implements ActionListener {
	private TeamPane father;
	private TeamBLService teamBL;
	private JPanel contentPane;//总panel
	private ImageIcon background;//背景图片
	private JPanel pane;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JComboBox<String> box1;
	private JComboBox<String> box2;
	private JComboBox<String> box3;
	private JComboBox<String> box4;
	private JButton confirm;
	//拖动
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	//-----------------------------------------------
	public TeamSortPane(TeamPane father) {
		this.father = father;
		teamBL = new TeamBL();
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
		pane.setBorder(BorderFactory.createEmptyBorder(20, 30, 50, 30));
		label1 = new JLabel("排序类型：");
		label1.setFont(new Font("黑体", Font.PLAIN, 14));
		label2 = new JLabel("第一排序依据：");
		label2.setFont(new Font("黑体", Font.PLAIN, 14));
		label3 = new JLabel("第二排序依据：");
		label3.setFont(new Font("黑体", Font.PLAIN, 14));
		label4 = new JLabel("第三排序依据：");
		label4.setFont(new Font("黑体", Font.PLAIN, 14));
		box1 = new JComboBox<String>(new String[]{"升序", "降序"});
		box1.setFont(new Font("楷体", Font.PLAIN, 14));
		box2 = new JComboBox<String>();
		box2.setFont(new Font("楷体", Font.PLAIN, 14));
		box3 = new JComboBox<String>();
		box3.setFont(new Font("楷体", Font.PLAIN, 14));
		box4 = new JComboBox<String>();
		box4.setFont(new Font("楷体", Font.PLAIN, 14));
		confirm = new JButton("确定");
		
		pane.add(label1);
		pane.add(Box.createVerticalStrut(10));
		pane.add(box1);
		pane.add(Box.createVerticalStrut(10));
		pane.add(label2);
		pane.add(Box.createVerticalStrut(10));
		pane.add(box2);
		pane.add(Box.createVerticalStrut(10));
		pane.add(label3);
		pane.add(Box.createVerticalStrut(10));
		pane.add(box3);
		pane.add(Box.createVerticalStrut(10));
		pane.add(label4);
		pane.add(Box.createVerticalStrut(10));
		pane.add(box4);
		pane.add(Box.createVerticalStrut(10));
		pane.add(confirm);
		contentPane.add(pane, BorderLayout.CENTER);
		//监听
		confirm.addActionListener(this);
		//-----------------------------------------------------------------
		this.setAlwaysOnTop(true);
		this.setDragable();
		this.setUndecorated(true);
		this.setVisible(true);
	}
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
	 * 设置位置大小
	 */
	public void setPlace() {
		this.setBounds(father.main.getX(), father.main.getY() + father.main.getHeight() / 2, father.main.getWidth() / 5, father.main.getHeight() / 2);
	}
	/**
	 * 设置界面可拖动
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
