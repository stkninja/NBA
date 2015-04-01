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
 * ÈÈµã½çÃæ
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
		//½çÃæ²¼¾Ö
		pane = new JPanel();
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 0, 1, 0);
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        
		ep1 = new ExpandablePane("data/pic/2.png", "data/pic/1.png", new String[]{"µÃ·Ö", "Àº°å", "Öú¹¥", "¸ÇÃ±", "ÇÀ¶Ï"});
		ep2 = new ExpandablePane("data/pic/2.png", "data/pic/1.png", new String[]{"µÃ·Ö", "Àº°å", "Öú¹¥", "¸ÇÃ±", "ÇÀ¶Ï"});
		ep3 = new ExpandablePane("data/pic/2.png", "data/pic/1.png", new String[]{"µÃ·Ö", "Àº°å", "Öú¹¥", "¸ÇÃ±", "ÇÀ¶Ï"});
		ep4 = new ExpandablePane("data/pic/2.png", "data/pic/1.png", new String[]{"µÃ·Ö", "Àº°å", "Öú¹¥", "¸ÇÃ±", "ÇÀ¶Ï"});
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