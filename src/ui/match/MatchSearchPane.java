package ui.match;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.TeamEnum;
import vo.MatchVO;
import businesslogic.MatchBL;
import businesslogicservice.MatchBLService;

/**
 * �����������
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class MatchSearchPane extends JPanel {
	private MatchPane father;
	private MatchBLService bl;
	private JComboBox<String> comboBox1;
	private JComboBox<String> comboBox2;
	private JComboBox<String> comboBox3;
	private JTextField text;
	private JButton search;
	private JButton reset;
	/**
	 * 
	 * @param father �ϲ�MatchPane
	 */
	public MatchSearchPane(MatchPane father) {
		this.father = father;
		bl = new MatchBL();
		
		this.setLayout(new GridLayout(9, 1, 0, 5));
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 130, 20));
		
		JLabel label1 = new JLabel("������");
		label1.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label2 = new JLabel("���ڣ�");
		label2.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label3 = new JLabel("�����д��");
		label3.setFont(new Font("����", Font.PLAIN, 14));
		comboBox1 = new JComboBox<String>((String[])bl.getAllSeasons().toArray(new String[bl.getAllSeasons().size()]));
		text = new JTextField();
		
		JPanel date = new JPanel(new FlowLayout());
		date.setOpaque(false);
		JLabel label4 = new JLabel("��");
		label4.setFont(new Font("����", Font.PLAIN, 14));
		JLabel label5 = new JLabel("��");
		label5.setFont(new Font("����", Font.PLAIN, 14));
		comboBox2 = new JComboBox<String>(new String[]{"--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		comboBox3 = new JComboBox<String>(new String[]{"--", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
													   "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
													   "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		date.add(comboBox2);
		date.add(label4);
		date.add(comboBox3);
		date.add(label5);
		
		JPanel bottom = new JPanel(new FlowLayout());
		bottom.setOpaque(false);
		search = new JButton("����");
		search.setFont(new Font("����", Font.PLAIN, 14));
		reset = new JButton("����");
		reset.setFont(new Font("����", Font.PLAIN, 14));
		bottom.add(search);
		bottom.add(reset);
		
		this.add(label1);
		this.add(comboBox1);
		this.add(label2);
		this.add(date);
		this.add(label3);
		this.add(text);
		this.add(bottom);
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = (String)comboBox2.getSelectedItem() +"-"+ (String)comboBox3.getSelectedItem();
				if (temp.equals("-----"))
					MatchSearchPane.this.setData(bl.getMatchesAboutTeamSeasonDatePlayer(text.getText(), (String)comboBox1.getSelectedItem(), "All", "All"));
				else
					MatchSearchPane.this.setData(bl.getMatchesAboutTeamSeasonDatePlayer(text.getText(), (String)comboBox1.getSelectedItem(), temp, "All"));
			}
		});
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatchSearchPane.this.setData(bl.getMatches((String)comboBox1.getSelectedItem(), father.type));
			}
		});
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
	 * �����������
	 */
	public void getAll() {
		this.setData(bl.getMatches((String)comboBox1.getSelectedItem(), father.type));
	}
	/**
	 * ˢ��
	 */
	public void refresh() {
		this.setData(bl.getMatches((String)comboBox1.getSelectedItem(), father.type));
	}
	/**
	 * ���ĳ����Աһ����������
	 * @param season ����
	 * @param player ��Ա
	 */
	public void getPlayerMatch(String season, String player) {
		this.setData(bl.getMatchesAboutTeamSeasonDatePlayer("All", season, "All", player));
	}
	/**
	 * ���ĳ�����һ����������
	 * @param season ����
	 * @param team ���
	 */
	public void getTeamMatch(String season, String team) {
		this.setData(bl.getMatchesAboutTeamSeasonDatePlayer(team, season, "All", "All"));
	}
	/**
	 * ��������
	 */
	public void setData(ArrayList<MatchVO> list) {
		Object[][] data = new Object[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i).season;
			data[i][1] = list.get(i).date;
			data[i][2] = TeamEnum.valueToEnum(list.get(i).team1.abbName).name_Ch();
			data[i][3] = Math.round(list.get(i).team1.scores) +":"+ Math.round(list.get(i).team2.scores);
			data[i][4] = TeamEnum.valueToEnum(list.get(i).team2.abbName).name_Ch();
		}
		father.showTable(data);
	}
}
