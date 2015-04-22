package ui.hotspot;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.MainFrame;

/**
 * 热点界面
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class HotspotPane extends JScrollPane {
	public MainFrame main;
	private JPanel pane;
	private ExpandablePane ep1;
	private ExpandablePane ep2;
	private ExpandablePane ep3;
	private ExpandablePane ep4;
	//-----------------------------------------------------
	public HotspotPane(MainFrame main) {
		this.main = main;
		this.setBorder(null);
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
		//界面布局
		pane = new JPanel();
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 0, 1, 0);
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        
		ep1 = new ExpandablePane(main, "data/pic/TodayTopPlayer1.png", "data/pic/TodayTopPlayer2.png", new String[]{"当天热点球员", "得分", "篮板", "助攻", "盖帽", "抢断"});
		ep2 = new ExpandablePane(main, "data/pic/SeasonTopPlayer1.png", "data/pic/SeasonTopPlayer2.png", new String[]{"赛季热点球员", "得分", "篮板", "助攻", "盖帽", "抢断", "三分命中率", "投篮命中率", "罚球命中率"});
		ep3 = new ExpandablePane(main, "data/pic/SeasonTopTeam1.png", "data/pic/SeasonTopTeam2.png", new String[]{"赛季热点球队", "得分", "篮板", "助攻", "盖帽", "抢断", "三分命中率", "投篮命中率", "罚球命中率"});
		ep4 = new ExpandablePane(main, "data/pic/PromotionPlayer1.png", "data/pic/PromotionPlayer2.png", new String[]{"进步最快球员", "场均得分", "场均篮板", "场均助攻"});
		pane.add(ep1, gbc);
		pane.add(ep2, gbc);
		pane.add(ep3, gbc);
		pane.add(ep4, gbc);
		
		JPanel temp = new JPanel(new BorderLayout());
		temp.setOpaque(false);
		temp.add(pane, BorderLayout.NORTH);
		this.getViewport().add(temp);
	}
}