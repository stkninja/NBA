package ui.team;

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

import ui.RegionEnum;
import ui.TeamEnum;
import vo.TeamVO;
import businesslogic.MatchBL;
import businesslogic.TeamBL;
import businesslogicservice.MatchBLService;
import businesslogicservice.TeamBLService;

/**
 * 球队搜索面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class TeamSearchPane extends JPanel implements ActionListener {
	private TeamPane father;
	private TeamBLService teamBL;
	private MatchBLService matchBL;
	private ArrayList<TeamVO> list;
	private JComboBox<String> mode;
	private JComboBox<String> region;
	private JComboBox<String> season;
	/**
	 * 
	 * @param father 上层TeamPane
	 */
	public TeamSearchPane(TeamPane father) {
		this.father = father;
		teamBL = new TeamBL();
		matchBL = new MatchBL();
		
		this.setLayout(new GridLayout(6, 1, 0, 10));
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 250, 20));
		
		JLabel label1 = new JLabel("数据类型：");
		label1.setFont(new Font("黑体", Font.PLAIN, 14));
		JLabel label2 = new JLabel("地区：");
		label2.setFont(new Font("黑体", Font.PLAIN, 14));
		JLabel label3 = new JLabel("赛季：");
		label3.setFont(new Font("黑体", Font.PLAIN, 14));
		mode = new JComboBox<String>(new String[]{"总数", "场均"});
		mode.setFont(new Font("楷体", Font.PLAIN, 14));
		String[] strList = TeamEnum.getRegion();
		for (int i = 0; i < strList.length; i++) {
			strList[i] = RegionEnum.valueToEnum(strList[i]).name_Ch();
		}
		region = new JComboBox<String>(strList);
		season = new JComboBox<String>((String[])matchBL.getAllSeasons().toArray(new String[matchBL.getAllSeasons().size()]));
		
		this.add(label1);
		this.add(mode);
		this.add(label2);
		this.add(region);
		this.add(label3);
		this.add(season);
		
		mode.addActionListener(this);
		season.addActionListener(this);
		region.addActionListener(this);
	}
	/**
	 * 背景
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon background = new ImageIcon("data/pic/PanelBG.png");
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), background.getImageObserver());
	}
	/**
	 * 监听
	 */
	public void actionPerformed(ActionEvent e) {
		this.refresh();
	}
	/**
	 * 刷新
	 */
	public void refresh() {
		this.setData(teamBL.getTeams((String)season.getSelectedItem(), RegionEnum.valueToEnum((String)region.getSelectedItem()).name_En()));
	}
	/**
	 * 设置表格数据
	 */
	public void setData(ArrayList<TeamVO> list) {
		this.list = list;
		Object[][] data = new Object[list.size()][31];
		if (mode.getSelectedItem() == "总数") {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = TeamEnum.valueToEnum(list.get(i).abbName).name_Ch();
				data[i][2] = list.get(i).abbName;
				data[i][3] = (int)list.get(i).winsNum;
				data[i][4] = (int)list.get(i).gamesNum;
				data[i][5] = list.get(i).winsRate;
				
				data[i][6] = (int)list.get(i).allshootingHit;
				data[i][7] = (int)list.get(i).allshooting;
				data[i][8] = list.get(i).allshootingHitRate;
				
				data[i][9] = (int)list.get(i).allthreePointHits;
				data[i][10] = (int)list.get(i).allthreePoint;
				data[i][11] = list.get(i).allthreePointHitRate;
				
				data[i][12] = (int)list.get(i).allfreeThrowHit;
				data[i][13] = (int)list.get(i).allfreeThrow;
				data[i][14] = list.get(i).allfreeThrowHitRate;
				
				data[i][15] = (int)list.get(i).alloffensiveRebounds;
				data[i][16] = (int)list.get(i).alldefensiveRebounds;
				data[i][17] = (int)list.get(i).allrebounds;
				
				data[i][18] = (int)list.get(i).allassists;
				data[i][19] = (int)list.get(i).allsteal;
				data[i][20] = (int)list.get(i).allcaps;
				data[i][21] = (int)list.get(i).allturnovers;
				data[i][22] = (int)list.get(i).allfouls;
				data[i][23] = (int)list.get(i).allscores;
				data[i][24] = list.get(i).allattackRound;
				
				data[i][25] = list.get(i).allattackEfficiency;
				data[i][26] = list.get(i).alldefenceEfficiency;
				data[i][27] = list.get(i).alloffensivereboundsEfficiency;
				data[i][28] = list.get(i).alldefensivereboundsEfficiency;
				data[i][29] = list.get(i).allstealEfficiency;
				data[i][30] = list.get(i).allassistEfficiency;
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				data[i][0] = i + 1;
				data[i][1] = TeamEnum.valueToEnum(list.get(i).abbName).name_Ch();
				data[i][2] = list.get(i).abbName;
				data[i][3] = (int)list.get(i).winsNum;
				data[i][4] = (int)list.get(i).gamesNum;
				data[i][5] = list.get(i).winsRate;
				
				data[i][6] = list.get(i).shootingHit;
				data[i][7] = list.get(i).shooting;
				data[i][8] = list.get(i).shootingHitRate;
				
				data[i][9] = list.get(i).threePointHits;
				data[i][10] = list.get(i).threePoint;
				data[i][11] = list.get(i).threePointHitRate;
				
				data[i][12] = list.get(i).freeThrowHit;
				data[i][13] = list.get(i).freeThrow;
				data[i][14] = list.get(i).threePointHitRate;
				
				data[i][15] = list.get(i).offensiveRebounds;
				data[i][16] = list.get(i).defensiveRebounds;
				data[i][17] = list.get(i).rebounds;
				
				data[i][18] = list.get(i).assists;
				data[i][19] = list.get(i).steal;
				data[i][20] = list.get(i).caps;
				data[i][21] = list.get(i).turnovers;
				data[i][22] = list.get(i).fouls;
				data[i][23] = list.get(i).scores;
				data[i][24] = list.get(i).attackRound;
				
				data[i][25] = list.get(i).attackEfficiency;
				data[i][26] = list.get(i).defenceEfficiency;
				data[i][27] = list.get(i).offensivereboundsEfficiency;
				data[i][28] = list.get(i).defensivereboundsEfficiency;
				data[i][29] = list.get(i).stealEfficiency;
				data[i][30] = list.get(i).assistEfficiency;
			}
			
		}
		father.showTable(data);
	}
	/**
	 * 获得球队数据
	 * @return 球队数据
	 */
	public ArrayList<TeamVO> getList() {
		return list;
	}
}
