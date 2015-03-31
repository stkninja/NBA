package ui.hotspot;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * 可折叠面板
 * @author stk
 *
 */
@SuppressWarnings("serial")
public class ExpandablePane extends JPanel{
	private HeaderPane header;//标题面板
	private ContentPane content;//内容面板
	//-----------------------------------------------
	/**
	 * 
	 * @param path1 背景路径1
	 * @param path2 背景路径2
	 * @param condition 筛选条件
	 */
	public ExpandablePane(String path1, String path2, String[] condition) {
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		header = new HeaderPane(path1, path2);
		content = new ContentPane(condition);
		content.setVisible(false);
		this.add(header, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		header.addMouseListener(new Action());
	}
	/**
	 * 监听鼠标点击标题界面
	 * @author stk
	 *
	 */
	private class Action extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			HeaderPane hp = (HeaderPane)e.getSource();
			if (content.isShowing()) {
				content.setVisible(false);
				hp.setIsShow(false);
			} else {
				content.setVisible(true);
				hp.setIsShow(true);
			}
			hp.getParent().validate();
			hp.getParent().repaint();
		}
	}
}
