package ui.team;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.MainFrame;
import ui.TeamEnum;

/**
 * �����Ϣ���
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class Team extends JPanel {
	public MainFrame main;
	private String type;
	private TeamInfo[] teamInfo;
	/**
	 * 
	 * @param main �����
	 * @param type	���ͣ�����������
	 */
	public Team(MainFrame main, String type) {
		this.main = main;
		this.type = type;
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(0, 50, 15, 70));
		this.initTop();
		this.initCategory();
		this.initContent();
	}
	/**
	 * ��ʼ���������
	 */
	private void initTop() {
		ImageIcon background;
		if (type.equals("East"))
			background = new ImageIcon("data/pic/east.png");
		else
			background = new ImageIcon("data/pic/west.png");
		JPanel top = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image bg = background.getImage();
				double scale = (double)bg.getWidth(null) / (double)bg.getHeight(null);
				g.drawImage(bg, 0, 0, getWidth(), (int)(getWidth() / scale), background.getImageObserver());
			}
		};
		top.setOpaque(false);
		this.add(top);
	}
	/**
	 * ��ʼ���������
	 */
	private void initCategory() {
		JPanel category = new JPanel(new GridLayout(1, 3));
		category.setOpaque(false);
		JLabel label1;
		JLabel label2;
		JLabel label3;
		if (type.equals("East")) {
			label1 = new JLabel("���Ϸ���", JLabel.CENTER);
			label2 = new JLabel("�������", JLabel.CENTER);
			label3 = new JLabel("���������", JLabel.CENTER);
		} else {
			label1 = new JLabel("̫ƽ�����", JLabel.CENTER);
			label2 = new JLabel("��������", JLabel.CENTER);
			label3 = new JLabel("���Ϸ���", JLabel.CENTER);
		}
		label1.setFont(new Font("����", Font.PLAIN, 14));
		label2.setFont(new Font("����", Font.PLAIN, 14));
		label3.setFont(new Font("����", Font.PLAIN, 14));
		category.add(label1);
		category.add(label2);
		category.add(label3);
		this.add(category);
	}
	/**
	 * ��ʼ���������
	 */
	private void initContent() {
		JPanel pane = new JPanel(new GridLayout(5, 3, 30, 5));
		pane.setOpaque(false);
		teamInfo = new TeamInfo[15];
		String[] region1;
		String[] region2;
		String[] region3;
		if (type.equals("East")) {
			region1 = TeamEnum.getSoutheast();
			region2 = TeamEnum.getCentral();
			region3 = TeamEnum.getAtlantic();
		} else {
			region1 = TeamEnum.getPacific();
			region2 = TeamEnum.getNorthwest();
			region3 = TeamEnum.getSouthwest();
		}
		Boolean judge = true;
		for (int i = 0, j = 0; i < 15; i = i + 3, j++) {
			teamInfo[i] = new TeamInfo(main, region1[j]);
			teamInfo[i + 1] = new TeamInfo(main, region2[j]);
			teamInfo[i + 2] = new TeamInfo(main, region3[j]);
			if (judge) {
				teamInfo[i].setBackground(new Color(206,231,255));
				teamInfo[i + 1].setBackground(new Color(206,231,255));
				teamInfo[i + 2].setBackground(new Color(206,231,255));
				judge = false;
			} else {
				teamInfo[i].setBackground(Color.LIGHT_GRAY);
				teamInfo[i + 1].setBackground(Color.LIGHT_GRAY);
				teamInfo[i + 2].setBackground(Color.LIGHT_GRAY);
				judge = true;
			}
		}
		for (int i = 0; i < 15; i++)
			pane.add(teamInfo[i]);
		this.add(pane);
	}
}
