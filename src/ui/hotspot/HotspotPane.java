package ui.hotspot;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * @author stk
 * 热点界面
 *
 */
@SuppressWarnings("serial")
public class HotspotPane extends JScrollPane {
	private JPanel pane;
	private ExpandablePane ep1;
	private ExpandablePane ep2;
	private ExpandablePane ep3;
	private ExpandablePane ep4;
	//-----------------------------------------------------
	public HotspotPane() {
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
        
		ep1 = new ExpandablePane("data/pic/2.png", "data/pic/1.png", new String[]{"当天热点球员", "得分", "篮板", "助攻", "盖帽", "抢断"});
		ep2 = new ExpandablePane("data/pic/2.png", "data/pic/1.png", new String[]{"赛季热点球员", "得分", "篮板", "助攻", "盖帽", "抢断", "三分命中率", "投篮命中率", "罚球命中率"});
		ep3 = new ExpandablePane("data/pic/2.png", "data/pic/1.png", new String[]{"赛季热点球队", "得分", "篮板", "助攻", "盖帽", "抢断", "三分命中率", "投篮命中率", "罚球命中率"});
		ep4 = new ExpandablePane("data/pic/2.png", "data/pic/1.png", new String[]{"进步最快球员", "场均得分", "场均篮板", "场均助攻"});
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