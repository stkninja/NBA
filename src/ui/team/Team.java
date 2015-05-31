package ui.team;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.MainFrame;
import ui.TeamEnum;

/**
 * 球队信息面板
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
	 * @param main 主框架
	 * @param type	类型：东部或西部
	 */
	public Team(MainFrame main, String type) {
		this.main = main;
		this.type = type;
		this.setOpaque(false);
		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(BorderFactory.createEmptyBorder(10, 100, 20, 100));
		this.initCategory();
		this.initContent();
	}
	/**
	 * 初始化分类面板
	 */
	private void initCategory() {
		JPanel category = new JPanel(new GridLayout(1, 3));
		category.setOpaque(false);
		JLabel label1;
		JLabel label2;
		JLabel label3;
		if (type.equals("East")) {
			label1 = new JLabel("东南分区", JLabel.CENTER);
			label2 = new JLabel("中央分区", JLabel.CENTER);
			label3 = new JLabel("大西洋分区", JLabel.CENTER);
		} else {
			label1 = new JLabel("太平洋分区", JLabel.CENTER);
			label2 = new JLabel("西北分区", JLabel.CENTER);
			label3 = new JLabel("西南分区", JLabel.CENTER);
		}
		label1.setFont(new Font("黑体", Font.PLAIN, 14));
		label2.setFont(new Font("黑体", Font.PLAIN, 14));
		label3.setFont(new Font("黑体", Font.PLAIN, 14));
		category.add(label1);
		category.add(label2);
		category.add(label3);
		this.add(category, BorderLayout.NORTH);
	}
	/**
	 * 初始化内容面板
	 */
	private void initContent() {
		JPanel pane = new JPanel(new GridLayout(5, 3, 40, 10));
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
		this.add(pane, BorderLayout.CENTER);
	}
}
