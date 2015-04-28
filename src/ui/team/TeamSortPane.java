package ui.team;

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

import vo.TeamVO;
import businesslogic.TeamBL;
import businesslogicservice.TeamBLService;

/**
 * 球队排序面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class TeamSortPane extends JInternalFrame implements ActionListener {
	private TeamPane father;
	private TeamBLService teamBL;
	private JPanel contentPane;//总panel
	private JComboBox<String> box1;
	private JComboBox<String> box2;
	private JComboBox<String> box3;
	//拖动
	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;
	/**
	 * 
	 * @param father 上层TeamPane
	 */
	public TeamSortPane(TeamPane father) {
		this.father = father;
		teamBL = new TeamBL();
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
		JPanel pane = new JPanel(new GridLayout(6, 1, 0, 6));
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		JLabel label1 = new JLabel("排序类型：");
		label1.setFont(new Font("黑体", Font.PLAIN, 14));
		JLabel label2 = new JLabel("第一排序依据：");
		label2.setFont(new Font("黑体", Font.PLAIN, 14));
		JLabel label3 = new JLabel("第二排序依据：");
		label3.setFont(new Font("黑体", Font.PLAIN, 14));
		JLabel label4 = new JLabel("第三排序依据：");
		label4.setFont(new Font("黑体", Font.PLAIN, 14));
		box1 = new JComboBox<String>(new String[]{"降序", "升序"});
		box1.setFont(new Font("楷体", Font.PLAIN, 14));
		box2 = new JComboBox<String>((String[])teamBL.getFilters().toArray(new String[teamBL.getFilters().size()]));
		box2.setFont(new Font("楷体", Font.PLAIN, 14));
		box3 = new JComboBox<String>((String[])teamBL.getFilters().toArray(new String[teamBL.getFilters().size()]));
		box3.setFont(new Font("楷体", Font.PLAIN, 14));
		
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
	 * 监听
	 */
	public void actionPerformed(ActionEvent e) {
		ArrayList<TeamVO> list = this.father.getSearchPane().getList();
		ArrayList<String> filter = new ArrayList<String>();
		filter.add((String)box2.getSelectedItem());
		filter.add((String)box3.getSelectedItem());
		ArrayList<TeamVO> data = teamBL.sortTeam(list, filter, (String)box1.getSelectedItem());
		this.father.getSearchPane().setData(data);
	}
	/**
	 * 初始数据
	 */
	public void initData() {
		ArrayList<TeamVO> list = this.father.getSearchPane().getList();
		ArrayList<String> filter = new ArrayList<String>();
		filter.add((String)box2.getSelectedItem());
		filter.add((String)box3.getSelectedItem());
		ArrayList<TeamVO> data = teamBL.sortTeam(list, filter, (String)box1.getSelectedItem());
		this.father.getSearchPane().setData(data);
	}
	/**
	 * 设置位置大小
	 */
	public void setPlace() {
		this.setBounds(father.dp.getX(), father.dp.getY() + father.dp.getHeight() / 3, father.dp.getWidth() / 6, father.dp.getHeight() / 3);
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
