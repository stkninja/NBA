package ui.player;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
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
public class PlayerSortPane extends JPanel implements ActionListener {
	private PlayerPane father;
	private PlayerBLService playerBL;
	private JComboBox<String> box1;
	private JComboBox<String> box2;
	private JComboBox<String> box3;
	/**
	 * 
	 * @param father �ϲ�PlayerPane
	 */
	public PlayerSortPane(PlayerPane father) {
		this.father = father;
		playerBL = new PlayerBL();
		
		this.setLayout(new GridLayout(6, 1, 0, 10));
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 250, 20));
		
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
		
		this.add(label1);
		this.add(box1);
		this.add(label2);
		this.add(box2);
		this.add(label3);
		this.add(box3);
		
		box1.addActionListener(this);
		box2.addActionListener(this);
		box3.addActionListener(this);
	}
	/**
	 * ����
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon background = new ImageIcon("data/pic/PanelBG.png");
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
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
	 * ��ʼ������
	 */
	public void initData() {
		ArrayList<PlayerVO> list = this.father.getSearchPane().getList();
		ArrayList<String> filter = new ArrayList<String>();
		filter.add((String)box2.getSelectedItem());
		filter.add((String)box3.getSelectedItem());
		ArrayList<PlayerVO> data = playerBL.sortPlayer(list, filter, (String)box1.getSelectedItem());
		this.father.getSearchPane().setData(data);
	}
}
