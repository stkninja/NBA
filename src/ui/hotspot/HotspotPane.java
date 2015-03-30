package ui.hotspot;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * @author stk
 * ÈÈµã½çÃæ
 *
 */
@SuppressWarnings("serial")
public class HotspotPane extends JPanel{
	private JScrollPane sp;
	private JPanel pane;
	private HeaderPane header1;
	private HeaderPane header2;
	private HeaderPane header3;
	private HeaderPane header4;
	private ContentPane content1;
	private ContentPane content2;
	private ContentPane content3;
	private ContentPane content4;
	//-----------------------------------------------------
	public HotspotPane() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		pane = new JPanel();
		pane.setOpaque(false);
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 30));
		//½çÃæ²¼¾Ö
		header1 = new HeaderPane("data/pic/exit1.png");
		header2 = new HeaderPane("data/pic/mini1.png");
		header3 = new HeaderPane("data/pic/exit1.png");
		header4 = new HeaderPane("data/pic/mini1.png");
		content1 = new ContentPane(new String[]{"µÃ·Ö", "Àº°å", "Öú¹¥", "¸ÇÃ±", "ÇÀ¶Ï"});
		content2 = new ContentPane(new String[]{"µÃ·Ö", "Àº°å", "Öú¹¥", "¸ÇÃ±", "ÇÀ¶Ï"});
		content3 = new ContentPane(new String[]{"µÃ·Ö", "Àº°å", "Öú¹¥", "¸ÇÃ±", "ÇÀ¶Ï"});
		content4 = new ContentPane(new String[]{"µÃ·Ö", "Àº°å", "Öú¹¥", "¸ÇÃ±", "ÇÀ¶Ï"});
		
		pane.add(header1);
		pane.add(content1);
		pane.add(header2);
		pane.add(content2);
		pane.add(header3);
		pane.add(content3);
		pane.add(header4);
		pane.add(content4);
		
		sp = new JScrollPane(pane);
		sp.setBorder(null);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		this.add(sp, BorderLayout.CENTER);
	}
	
}